import java.io.IOException;
import java.util.TreeMap;

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

public class MRTop10Ships {

    public static enum Counters { // compteurs 
        LINES_CNT, // nombre de lignes lues
        VALID_CNT, // nombre de lignes contenant des données correctes
        INVALID_CNT, // nombre de lignes contenant des données incorrectes
        INVALID_MMSI, // nombre de lignes contenant des MMSIimport java.io.IOException;
import java.util.TreeMap;

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

public class MRTop10Ships {

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
    
    public static final String default_input= "s3://ubs-datasets/ais/2021/20211229.csv.bz2";
    public static final String default_output= "output-mr-ais-topships";
    static Text output_key;

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(key,values.iterator().next());
    }

    public static void main(String[] args)
      throws IOException, InterruptedException, ClassNotFoundException {

    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "MRTop10Ships");
    job.setJarByClass(MRTop10Ships.class);
    FileInputFormat.setInputPaths(job, new Path((args.length>0)? args[0] : default_input));
    job.setMapperClass(MRTop10ShipsMapper.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setReducerClass(MRTop10ShipsReducer.class);
    job.setCombinerClass(MRTop10Ships.class);
    if(args.length==1) {
      FileSystem hdfs = FileSystem.get(conf);
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
  static public class MRTop10ShipsMapper extends Mapper<LongWritable, Text, Text, Text> {

    public TreeMap<key, value> treeMap;

    @Override
    protected void setup(Context context) {
        output_value = new Text();
        treeMap = new TreeMap<key,value>(10);
    }

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
            context.getCounter(Counters.VALID_CNT).increment(1);
            output_key.set(mmsi+epoch); // groupe par MMSI et TIME identiques
            treeMap.put(output_key, value);
            if(treeMap.size() == 10)
                treeMap.remove(treeMap.firstEntry());
            context.write(output_key, value);
        }
    }

    static public class MRTop10ShipsReducer extends Reducer<Text, Text, NullWritable, Text> {
        @Override
        protected void setup(Context context) { // pour éviter la création d'objets dans chaque appel à reduce()
            output_value = new Text();
        }
        
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            output_value.set(values.iterator().next());
            context.write(NullWritable.get(),output_value);
            context.getCounter(Counters.FINAL_CNT).increment(1);
        }
    }
} incorrects
        NULL_LAT_CNT, // nombre de lignes sans donnée LATITUDE 
        NULL_LON_CNT, // nombre de lignes sans donnée LONGITUDE 
        NULL_TIME_CNT, // nombre de lignes sans donnée TIME 
        NULL_MMSI_CNT, // nombre de lignes sans donnée LATITUDE 
        INVALID_LAT_CNT, // nombre de lignes avec une donnée LATITUDE incorrecte
        INVALID_LON_CNT, // nombre de lignes avec une donnée LONGITUDE incorrecte
        INVALID_TIME_CNT, // nombre de lignes avec une donnée TIME incorrecte
        FINAL_CNT, // nombre de lignes conservées
    };

    public static final String default_input= "s3://ubs-datasets/ais/2021/20211229.csv.bz2";
    public static final String default_output= "output-mr-ais-topships";
    static Text output_key;

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        context.write(key,values.iterator().next());
    }

    public static void main(String[] args)
      throws IOException, InterruptedException, ClassNotFoundException {

    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "MRTop10Ships");
    job.setJarByClass(MRTop10Ships.class);
    FileInputFormat.setInputPaths(job, new Path((args.length>0)? args[0] : default_input));
    job.setMapperClass(MRTop10ShipsMapper.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(Text.class);
    job.setReducerClass(MRTop10ShipsReducer.class);
    job.setCombinerClass(MRTop10Ships.class);
    if(args.length==1) {
      FileSystem hdfs = FileSystem.get(conf);
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
  static public class MRTop10ShipsMapper extends Mapper<LongWritable, Text, Text, Text> {

    public TreeMap<key, value> treeMap;

    @Override
    protected void setup(Context context) {
        output_value = new Text();
        treeMap = new TreeMap<key,value>(10);
    }

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
            context.getCounter(Counters.VALID_CNT).increment(1);
            output_key.set(mmsi+epoch); // groupe par MMSI et TIME identiques
            treeMap.put(output_key, value);
            if(treeMap.size() == 10)
                treeMap.remove(treeMap.firstEntry());
            context.write(output_key, value);
        }
    }

    static public class MRTop10ShipsReducer extends Reducer<Text, Text, NullWritable, Text> {
        @Override
        protected void setup(Context context) { // pour éviter la création d'objets dans chaque appel à reduce()
            output_value = new Text();
        }
        
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            output_value.set(values.iterator().next());
            context.write(NullWritable.get(),output_value);
            context.getCounter(Counters.FINAL_CNT).increment(1);
        }
    }
}