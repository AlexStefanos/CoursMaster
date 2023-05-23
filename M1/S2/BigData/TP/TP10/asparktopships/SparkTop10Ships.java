//result are in the file result/top100.txt in the archive (due to the length of the ouput)

package asparktopships;

import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.io.IOException;
import java.io.Serializable;

import scala.Tuple2;

import org.apache.spark.*;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.api.java.*;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.FloatWritable;

import aisubs.AISFields;
import aisubs.AISFormat;
import aisubs.TupleWritable;

public class SparkTop10Ships {

  public static final String DEFAULT_INPUT_PATH = "s3a://ubs-datasets/ais/clean/2021/part-r-00000";
  static final String DEFAULT_OUTPUT_FOLDER = "output-spark-ais-top10";

  public static void main(String[] args) throws IOException{

    String inputPath = (args.length > 0)? args[0] : DEFAULT_INPUT_PATH;
    String outputFolder = (args.length > 1) ? args[1] : DEFAULT_OUTPUT_FOLDER;

    JavaSparkContext sc = new JavaSparkContext(new SparkConf().setMaster("yarn").setAppName("SparkWorldCountAWS"));
    
    JavaRDD<String> lines = sc.textFile(inputPath); //read files

    JavaRDD<TupleWritable> messages = lines.map(line -> AISFormat.getFieldTuple(line));

    messages.persist(StorageLevel.MEMORY_ONLY());

    JavaPairRDD<String,Float> speeds = messages.mapToPair(tw -> new Tuple2<String,Float>(
      tw.get(AISFields.MMSI.ordinal()),
      Float.valueOf(tw.get(AISFields.SOG.ordinal()))
    ));

    JavaPairRDD<String,Float> reducedSpeeds = speeds.reduceByKey((s1,s2) -> (s1 > s2 ? s1 : s2));

    JavaPairRDD<String,Float> top10 = sc.parallelizePairs(reducedSpeeds.top(10, new SpeedComparator()));

    Configuration hadoopConf = new Configuration();
    FileSystem hdfs = FileSystem.get(hadoopConf);

    Path outputFolderPath = new Path(outputFolder);

    hdfs.delete(outputFolderPath,true);

    JavaPairRDD<Text, FloatWritable> writables = top10.mapToPair(t -> new Tuple2<>(
      new Text(t._1()),
      new FloatWritable(t._2())
    ));

    writables.saveAsTextFile(outputFolder);

    sc.close();
    sc.stop();

  }

  public static class SpeedComparator implements Comparator<Tuple2<String,Float>>, Serializable {

    @Override
    public int compare(Tuple2<String,Float> t1, Tuple2<String,Float> t2) {
      return Float.compare(t1._2(),t2._2());
    }

  }
}
