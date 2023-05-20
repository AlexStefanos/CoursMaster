package sparktop;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;
import javaslang.Tuple2;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.spark.storage.StorageLevel;
import org.apache.hadoop.io.IntWritable;

public class SparkTop100 {
    private static final Pattern SPACE = Pattern.compile("\\P{L}+");

    public static void main(String[] args) throws Exception {
        SparkConf sparkConf = new SparkConf().setMaster("yarn").setAppName("SparkTop100");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile("s3a://ubs-datasets/gutenberg/*.txt");
        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator()).map(s -> s.toLowerCase().trim());

        words.persist(StorageLevel.MEMORY_ONLY());
        long words_cnt = words.count();
        JavaRDD<String> accepted = words.filter(s -> s.matches("\\w+"));

        accepted.persist(StorageLevel.MEMORY_ONLY());
        List<String> stop_words_list = sc.textFile("s3a://ubs-datasets/gutenberg/*.txt").collect();
        HashSet<String> stop_words = new HashSet<>(stop_words_list);
        Broadcast<HashSet<String>> stop_words_broadcast = sc.broadcast(stop_words);

        LongAccumulator accepted_cnt = sc.sc().longAccumulator();
        LongAccumulator rejected_cnt = sc.sc().longAccumulator();
        JavaRDD<String> accepted = words.filter(s -> {
            if(stop_words_broadcast.value().contains(s)) {
                rejected_cnt.add(1);
                return(false);
            } else {
                accepted_cnt.add(1);
                return(true);
            }
        });
        JavaPairRDD<String, Integer> ones = accepted.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> (i1 + i2));

        counts.persist(StorageLevel.MEMORY_ONLY());
        long distinct_cnt = counts.count();
        System.out.println("Number of distinct words = " + distinct_cnt);
        Configuration hadoopConf = new Configuration();
        FileSystem hdfs = FileSystem.get(hadoopConf);
        Path output_path = new Path("output-spark-top");

        hdfs.delete(output_path, true);
        JavaPairRDD<Text, IntWritable> writables = counts.mapToPair(t -> new Tuple2<Text, IntWritable>(new Text(t._1()), new IntWritable(t._2())));
        writables.saveAsTextFile("output-spark-top");
        sc.close();
        sc.stop();
    }
}