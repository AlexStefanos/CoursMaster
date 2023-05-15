package aisubs;
/**
 * Filtrage des données AIS brutes : suppression des doublons et éliminations des données invalides
 * @author raimbaul
 */


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class MRCleaning {
  
  //a executer par : yarn jar mr-aisubs.jar aisubs.Cleaning [dataset-path [output-path]]
  // résultats (sans paramètres)
  //  LINES_CNT:2261297
  //  VALID_CNT:2261297
  //  FINAL_CNT:2259376
  //  INVALID_CNT:0
  //  NULL_LAT_CNT:0
  //  NULL_LON_CNT:0
  //  NULL_TIME_CNT:0
  //  NULL_MMSI_CNT:0
  //  INVALID_LAT_CNT:0
  //  INVALID_LON_CNT:0
  //  INVALID_TIME_CNT:0
  // résultats sur s3://ubs-m1info/ais/2021/
  //  LINES_CNT:3951246150
  //  VALID_CNT:3951242703
  //  FINAL_CNT:3939231650
  //  INVALID_CNT:3447
  //  NULL_LAT_CNT:63
  //  NULL_LON_CNT:31
  //  NULL_TIME_CNT:0
  //  NULL_MMSI_CNT:0
  //  INVALID_LAT_CNT:2988
  //  INVALID_LON_CNT:235
  //  INVALID_TIME_CNT:130
  
  static final String default_input= "s3://ubs-datasets/ais/2021/20211229.csv.bz2";
  static final String default_output= "output-mr-ais-cleaning"; // folder in HDFS (not permanent !!)
  //static final AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();

  public static enum Counters { // compteurs 
    LINES_CNT, // nombre de lignes lues
    VALID_CNT, // nombre de lignes contenant des données correctes
    INVALID_CNT, // nombre de lignes contenant des données incorrectes
    INVALID_MMSI, // nombre de lignes contenant des MMSI incorrects
    NULL_LAT_CNT, // nombre de lignes sans donnée LATITUDE 
    NULL_LON_CNT, // nombre de lignes sans donnée LONGITUDE 
    NULL_TIME_CNT, // nombre de lignes sans donnée TIME 
    NULL_MMSI_CNT, // nombre de lignes sans donnée LATITUDE 
    INVALID_LAT_CNT, // nombre de lignes avec une donnée LATITUDE incorrecte
    INVALID_LON_CNT, // nombre de lignes avec une donnée LONGITUDE incorrecte
    INVALID_TIME_CNT, // nombre de lignes avec une donnée TIME incorrecte
    FINAL_CNT, // nombre de lignes conservées
  };
  
  /**
   * 
   */
  static public class CleaningMapper extends Mapper<LongWritable, Text, Text, Text> {
    
    Text output_key; 
    
    @Override
    protected void setup(Context context) { // pour éviter la création d'objets dans chaque appel à map()
      output_key= new Text();
     }
    
    /**INF2245_AISUBS/mr-aisubs.jar
     * @see org.apache.hadoop.mapreduce.Mapper#map(java.lang.Object, java.lang.Object, org.apache.hadoop.mapreduce.Mapper.Context)
     */
    @Override
    protected void map(LongWritable key, Text value, Context context)
        throws IOException, InterruptedException {
      
      
      context.getCounter(Counters.LINES_CNT).increment(1);
      TupleWritable fields= AISFormat.getFieldTuple(value.toString());
      String mmsi= fields.get(AISFields.MMSI.ordinal());
      if (mmsi==null) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.NULL_MMSI_CNT).increment(1);
        return;
      }
      char mmsi_0= mmsi.charAt(0);
      if ((mmsi.length()!=9) || ! ((mmsi_0 >= '0') && (mmsi_0 <='9'))) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.INVALID_MMSI).increment(1);
        return;
      }
      String time= fields.get(AISFields.TIME.ordinal());
      if (time==null) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.NULL_TIME_CNT).increment(1);
        return;
      }
      long epoch= AISFormat.isoTimeToEpoch(time); // time au format Epoch
      if (epoch==-1) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.INVALID_TIME_CNT).increment(1);
        return;
      }
      epoch= AISFormat.trunkToMinute(epoch);  // sous-echantillonage à la minute près
      String lat= fields.get(AISFields.LATITUDE.ordinal());
      if (lat==null) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.NULL_LAT_CNT).increment(1);
        return;
      }
      try {
        float lat_value= Float.valueOf(lat);
        if ((lat_value < -90) || (lat_value > 90)) {
          context.getCounter(Counters.INVALID_CNT).increment(1);
          context.getCounter(Counters.INVALID_LAT_CNT).increment(1);
          return;
        }
      } catch (NumberFormatException e) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.INVALID_LAT_CNT).increment(1);
        return;
      }
      String lon= fields.get(AISFields.LONGITUDE.ordinal());
      if (lon==null) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.NULL_LON_CNT).increment(1);
        return;
      }
      try {
       float lon_value= Float.valueOf(lon);
        if ((lon_value < -180) || (lon_value > 180)) {
          context.getCounter(Counters.INVALID_CNT).increment(1);
          context.getCounter(Counters.INVALID_LON_CNT).increment(1);
          return;
        }
      } catch (NumberFormatException e) {
        context.getCounter(Counters.INVALID_CNT).increment(1);
        context.getCounter(Counters.INVALID_LON_CNT).increment(1);
        return;
      }
      // fields.set(AISFields.TIME.ordinal(), String.valueOf(epoch)); // on conserve la donnée de TIME initiale
      context.getCounter(Counters.VALID_CNT).increment(1);
      output_key.set(mmsi+epoch); // groupe par MMSI et TIME identiques
      context.write(output_key, value);
    } 
  }

  /**
   * 
   */
  static public class CleaningReducer extends Reducer<Text, Text, NullWritable, Text> {
    
    Text output_value; 
    
    @Override
    protected void setup(Context context) { // pour éviter la création d'objets dans chaque appel à reduce()
      output_value= new Text();
     }
 
    /**
     * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      
      output_value.set(values.iterator().next());
      context.write(NullWritable.get(),output_value);
      context.getCounter(Counters.FINAL_CNT).increment(1);
    }
  }
  
  /**
   * 
   */
  static public class CleaningCombiner extends Reducer<Text, Text, Text, Text> {

    /**
     * @see org.apache.hadoop.mapreduce.Reducer#reduce(java.lang.Object, java.lang.Iterable, org.apache.hadoop.mapreduce.Reducer.Context)
     */
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      
      context.write(key,values.iterator().next());
    }
  }
 
  public static void main(String[] args) 
      throws IOException, InterruptedException, ClassNotFoundException {

    Configuration conf = new Configuration();  
    Job job= Job.getInstance(conf,"AISUBS-Cleaning");
    job.setJarByClass(MRCleaning.class);
    FileInputFormat.setInputPaths(job, new Path((args.length>0)? args[0] : default_input));
    job.setMapperClass(MRCleaning.CleaningMapper.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setReducerClass(MRCleaning.CleaningReducer.class);
    job.setCombinerClass(MRCleaning.CleaningCombiner.class);
    if (args.length==1) {
      FileSystem hdfs= FileSystem.get(conf); 
      hdfs.delete(new Path(default_output), true);
    }
    FileOutputFormat.setOutputPath(job, new Path((args.length>1)? args[1] : default_output));
    job.setOutputFormatClass(TextOutputFormat.class); 
    job.setOutputKeyClass(NullWritable.class);
    job.setOutputValueClass(Text.class); 
    job.waitForCompletion(true);
    // affichage des compteurs
    for(Counters counter:Counters.values()) {
      Counter job_counter= job.getCounters().findCounter(counter);
      System.out.println(counter+":"+job_counter.getValue());
    }
  }

}
