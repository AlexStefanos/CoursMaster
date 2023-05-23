package sparktopships;

import java.io.IOException;
import java.io.Serializable;
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
    public static void main(String[] args) throws Exception {

        SparkConf sparkConf = new SparkConf().setMaster("yarn").setAppName("SparkTop10Ships");
        JavaSparkContext sc = new JavaSparkContext(sparkConf);
        JavaRDD<String> lines = sc.textFile("s3a://ubs-datasets/ais/clean/2021/part-r-00000");
        JavaRDD<TupleWritable> ships = lines.map(s -> AISFormat.getFieldTuple(s));

        ships.persist(StorageLevel.MEMORY_ONLY());
        long ships_cnt = ships.count();
        JavaPairRDD<String,Float> boatSpeeds = ships.mapToPair(s -> new Tuple2<String, Float>(
            s.get(AISFields.MMSI.ordinal()),
            Float.valueOf(s.get(AISFields.SOG.ordinal()))
        ));

        JavaPairRDD<String, Float> reduce = boatSpeeds.reduceByKey((s1, s2) -> (s1 > s2 ? s1 : s2));
        JavaPairRDD<String, Float> top = sc.parallelizePairs(reduce.top(10, new BoatSpeedComparator()));

        Configuration hadoopConf = new Configuration();
        FileSystem hdfs = FileSystem.get(hadoopConf);
        Path output_path = new Path("output-spark-top-ships");

        hdfs.delete(output_path, true);
        JavaPairRDD<Text, FloatWritable> writables = top.mapToPair(t -> new Tuple2<>(new Text(t._1()), new FloatWritable(t._2())));
        writables.saveAsTextFile("output-spark-top-ships");
        sc.close();
        sc.stop();
    }

    public static class BoatSpeedComparator implements Comparator<Tuple2<String, Float>>, Serializable {
        @Override
        public int compare(Tuple2<String, Float> boatSpeed1, Tuple2<String, Float> boatSpeed2) {
            return(Float.compare(boatSpeed1._2(), boatSpeed2._2()));
        }
    }
}

