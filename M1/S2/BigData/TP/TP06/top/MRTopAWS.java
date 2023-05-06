package top;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class MRTopAWS {
  
  static final String default_input = "output-mr-wc";
  static final String default_output = "output-mr-top";

  public static enum Counters {
    TOP_CNT
  }
  
  public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    String inputFolder = (args.length > 0) ? args[0] : default_input;
    String outputFolder = (args.length > 1) ? args[1] : default_output;
    Configuration conf = new Configuration();
    Job job = Job.getInstance(conf, "MRTopAWS");
    job.setJarByClass(MRTopAWS.class);
    FileInputFormat.setInputPaths(job, inputFolder);
    job.setInputFormatClass(KeyValueTextInputFormat.class);
    job.setMapperClass(MRTopAWSMapper.class);
    job.setMapOutputKeyClass(LongWritable.class);
    job.setMapOutputValueClass(Text.class);
    job.setSortComparatorClass(LongWritable.DecreasingComparator.class);
    job.setReducerClass(MRTopAWSReducer.class);
    job.setNumReduceTasks(1);
    FileOutputFormat.setOutputPath(job, new Path(outputFolder));
    FileSystem hdfs= FileSystem.get(conf); 
    hdfs.delete(new Path(default_output), true);
    job.waitForCompletion(true);
    System.out.println(Counters.TOP_CNT+": "+job.getCounters().findCounter(Counters.TOP_CNT).getValue());
    System.out.println("Top100: ");
    FSDataInputStream inputStream = hdfs.open(new Path(outputFolder+"/part-r-00000"));
    IOUtils.copyBytes(inputStream, System.out, 4096, false);
    inputStream.close();
  }

  public static class MRTopAWSMapper extends Mapper<Text, Text, LongWritable, Text> {
    @Override
    protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
      context.write(new LongWritable(Long.valueOf(value.toString())),key);
    }
  }

  public static class MRTopAWSReducer extends Reducer<LongWritable, Text, Text, LongWritable>{

    private static int count;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
      MRTopAWSReducer.count = 0;
    }

    @Override
    protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
      for(Text value : values){
        if(MRTopAWSReducer.count < 100){
          context.write(value, key);
          MRTopAWSReducer.count += 1;
          context.getCounter(Counters.TOP_CNT).increment(1);
        }
      }
    }
  }
}
