package sparktopships;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import scala.Tuple2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.storage.StorageLevel;
import org.apache.hadoop.io.FloatWritable;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.util.LongAccumulator;

import aisubs.AISFields;
import aisubs.AISFormat;
import aisubs.TupleWritable;

public class SparkTop10Ships {
    private static final Pattern SPACE = Pattern.compile("\\P{L}+");
    public static final String DEFAULT_INPUT_PATH = "s3a://ubs-datasets/ais/clean/2021/part-r-00000";
    static final String DEFAULT_OUTPUT_FOLDER = "output-spark-ais-top10";

    public static void main(String[] args) throws Exception {
        String inputPath = (args.length > 0)? args[0] : DEFAULT_INPUT_PATH;
        String outputFolder = (args.length > 1) ? args[1] : DEFAULT_OUTPUT_FOLDER;

        SparkConf sparkConf = new JavaSpark().setMaster("yarn").setAppName("SparkTop10Ships");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile();
        JavaRDD<TupleWritable> ships = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator()).map(s -> s.trim());

        ships.persist(StorageLevel.MEMORY_ONLY());
        long ships_cnt = ships.count();

        List<String> stop_words_list = sc.textFile("s3://ubs-datasets/ais/clean/2021/*.txt").collect();
        HashSet<String> stop_words = new HashSet<>(stop_words_list);
        Broadcast<HashSet<String>> stop_words_broadcast = sc.broadcast(stop_words);

        LongAccumulator accepted_cnt = sc.sc().longAccumulator();
        LongAccumulator rejected_cnt = sc.sc().longAccumulator();
        JavaRDD<String> accepted = ships.filter(s -> {
            if(stop_words_broadcast.value().contains(s)) {
                rejected_cnt.add(1);
                return(false);
            } else {
                accepted_cnt.add(1);
                return(true);
            }
        });
        JavaPairRDD<String, Integer> ones = accepted.mapToPair(s -> new Tuple2<>(s, 1)).filter("speed");
        ones.limit(10).show();
        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> (i1 + i2));

        counts.persist(StorageLevel.MEMORY_ONLY());
        long distinct_cnt = counts.count();
        System.out.println("Number of distinct ships = " + distinct_cnt);
        Configuration hadoopConf = new Configuration();
        FileSystem hdfs = FileSystem.get(hadoopConf);
        Path output_path = new Path("output-spark-top-ships");

        hdfs.delete(output_path, true);
        JavaPairRDD<Text, IntWritable> writables = counts.mapToPair(t -> new Tuple2<Text, IntWritable>(new Text(t._1()), new IntWritable(t._2())));
        writables.saveAsTextFile("output-spark-top-ships");
        sc.close();
        sc.stop();
    }
}

