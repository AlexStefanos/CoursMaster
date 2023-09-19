// s3a://ubs-datasets/gutenberg/
package sparkwc;

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

public class SparkWordCountAWS {
    private static final Pattern SPACE = Pattern.compile("\\P{L}+");

    public static void main(String[] args) throws Exception {
        SparkConf sparkConf = new SparkConf().setMaster("yarn").setAppName("SparkWordCountAWS");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile("s3a://ubs-datasets/gutenberg/*.txt");
        JavaRDD<String> words = lines.flatMap(s -> Arrays.asList(SPACE.split(s)).iterator()).map(s -> s.toLowerCase().trim());

        words.persist(StorageLevel.MEMORY_ONLY());
        long words_cnt = words.count();
        JavaRDD<String> accepted = words.filter(s -> s.matches("\\w+"));

        accepted.persist(StorageLevel.MEMORY_ONLY());
        long accepted_cnt = accepted.count();
        long refused_cnt = (words_cnt - accepted_cnt);

        System.out.println("# of accepted words = " + accepted_cnt);
        System.out.println("# of rejected words = " + refused_cnt);
        JavaPairRDD<String, Integer> ones = accepted.mapToPair(s -> new Tuple2<>(s, 1));
        JavaPairRDD<String, Integer> counts = ones.reduceByKey((i1, i2) -> (i1 + i2));
        
        counts.persist(StorageLevel.MEMORY_ONLY());
        long distinct_cnt = counts.count();
        System.out.println("Number of distinct words = " + distinct_cnt);
        Configuration hadoopConf = new Configuration();
        FileSystem hdfs = FileSystem.get(hadoopConf);
        Path output_path = new Path("output-spark-wc");

        hdfs.delete(output_path, true);
        JavaPairRDD<Text, IntWritable> writables = counts.mapToPair(t -> new Tuple2<Text, IntWritable>(new Text(t._1()), new IntWritable(t._2())));
        writables.saveAsTextFile("output-spark-wc");
        sc.close();
        sc.stop();
    }
}