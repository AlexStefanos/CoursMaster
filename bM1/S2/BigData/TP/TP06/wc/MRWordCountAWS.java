package wc;
/**
 * Exemple de programme MapReduce à lancer sur AWS
 * Compte le nombre d'occurrences des mots des texte du dataset Gutenberg (par défaut) en excluant ceux d'une stoplist
 * @author raimbaul
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.regex.Pattern;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3URI;

public class MRWordCountAWS {
  
  //a executer par : yarn jar aws-wc.jar wc.MRWordCountAWS [dataset-path [stop-word-list-path]]
  // résultats (sans paramètres)
  // ACCEPTED_CNT:49021
  // REJECTED_CNT:59572
  // DISTINCT_CNT:6446
  // résultats (sur la totalité de Gutenberg: s3://ubs-m1info/gutenberg/ )
  // Number of accepted words =  91 625 828
  // Number of rejected words = 130 634 311
  // Number of distinct words =     399 487  
  // temps : 19 min avec 14 noeuds (2 cores)

  static final String default_input= "s3://ubs-datasets/gutenberg/Zane_Grey___Wildfire.txt";
  //static final String default_input= "gutenberg/Zane_Grey___Wildfire.txt";
  static final String default_stoplist= "s3://ubs-datasets/stop-words/stop-words-english4.txt";
  //static final String default_stoplist= "stop-words/stop-words-english4.txt" ;
  //static final String output_bucket= "ubs-m1info-output";
  static final String output_folder= "output-mr-wc";
  //static final AmazonS3 s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();

  public static enum Counters {
    ACCEPTED_CNT, // nombre de mots acceptés
    REJECTED_CNT, // nombre de mots filtrés par la stoplist
    DISTINCT_CNT, // nombre de mots uniques
  };

  public static void main(String[] args) 
      throws IOException, InterruptedException, ClassNotFoundException {

    System.out.println("- starting");
    Configuration conf = new Configuration();  
    Job job= Job.getInstance(conf,"MRWordCountAWS");
    job.setJarByClass(MRWordCountAWS.class);
    System.out.println("- set input paths");
    FileInputFormat.setInputPaths(job, new Path((args.length==0)? default_input : args[0]));
    job.setMapperClass(WordCountMapper.class);
    job.setInputFormatClass(TextInputFormat.class);
    job.setMapOutputKeyClass(Text.class);
    job.setMapOutputValueClass(LongWritable.class);
    job.setReducerClass(WordCountReducer.class);
    job.setCombinerClass(WordCountCombiner.class);
    System.out.println("- set stopwords URI");
    AmazonS3URI stopwords_uri= (args.length==2) ? new AmazonS3URI(args[1]) : new AmazonS3URI(default_stoplist);
    //URI stopwords_path= new URI(default_stoplist);
    //job.addCacheFile(stopwords_path);    // les stop-words sont distribués à tous les mapper
    job.addCacheFile(stopwords_uri.getURI());    // les stop-words sont distribués à tous les mapper
//    ObjectListing objects = s3client.listObjects(output_bucket, output_folder);
//    for (S3ObjectSummary objectSummary : objects.getObjectSummaries()) { // suppression du contenu de output
//      s3client.deleteObject(output_bucket, objectSummary.getKey());
//    }           
//    FileOutputFormat.setOutputPath(job, new Path("s3://"+output_bucket+"/"+output_folder));
    // on supprime le répertoire de sortie
    FileSystem hdfs= FileSystem.get(conf); 
    hdfs.delete(new Path(output_folder), true);
    System.out.println("- set output folder");
    FileOutputFormat.setOutputPath(job, new Path(output_folder));
    job.setOutputFormatClass(TextOutputFormat.class); // attention les couples écrits sont de type (Text,Text)
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(LongWritable.class); // le recordWriter de TextOutputFormat convertit en Text
    job.waitForCompletion(true);
    // affichage des compteurs
    for(Counters counter:Counters.values()) {
      Counter job_counter= job.getCounters().findCounter(counter);
      System.out.println(counter+":"+job_counter.getValue());
    }
  }
  
  public static class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> { 
    
    //private static final Log LOG = LogFactory.getLog(WordCountMapper2.class);

    private static final Pattern SPACE = Pattern.compile("\\P{L}+"); // tout sauf au moins une lettre
    
    private static final LongWritable ONE= new LongWritable(1); // évite de recréer un objet
    
    private static HashSet<String> stop_words= new HashSet<String>();
    
    Text output_key; // évite de créer un nouvel objet à chaque appel de map()

    /**
     * Lecture de la liste des mots dont on ne tient pas compte 
     * @param stopwords_file fichier (ouvert en lecture)
     * @throws IOException
     */
    private void readStopWords(BufferedReader stopwords_reader) throws IOException {
      String word= stopwords_reader.readLine();
      while(word != null){ // jusqu'a la fin de stop words
        stop_words.add(word.trim());
        word= stopwords_reader.readLine();
      }
      stopwords_reader.close();
    }

    @Override
    public void setup(Context context) throws IOException {
      
      URI[] dataFile = context.getCacheFiles();
      BufferedReader cacheReader = new BufferedReader(new FileReader(new File(dataFile[0].getPath()).getName()));
      readStopWords(cacheReader);
      output_key= new Text();
    }
    
    @Override
    public void map(LongWritable key, Text value, Context context) 
          throws IOException, InterruptedException { 
         
      long accepted_cnt= 0;
      long rejected_cnt= 0;
      String[] words= SPACE.split(value.toString());
      for(String word:words) {
        String w = word.toLowerCase().trim();
        if (! stop_words.contains(w)){
          output_key.set(w);
          context.write(output_key, ONE);
          accepted_cnt += 1;
        } else{
          rejected_cnt += 1;
        }
      }
      context.getCounter(Counters.ACCEPTED_CNT).increment(accepted_cnt);
      context.getCounter(Counters.REJECTED_CNT).increment(rejected_cnt);
    }

    @Override
    public void cleanup(Context context) {
    }
    
  }
  public static class WordCountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    @Override
    public void setup(Context context) throws IOException {
    }
 
   public void reduce(Text key, Iterable<LongWritable> values, Context context) 
      throws IOException, InterruptedException{ 
      
      int sum = 0;
      for (LongWritable value : values) {
        sum += value.get();
      }
      context.write(key, new LongWritable(sum));
      context.getCounter(Counters.DISTINCT_CNT).increment(1);
    }

   @Override
   public void cleanup(Context context) {
   }
   
   }
  public static class WordCountCombiner extends Reducer<Text, LongWritable, Text, LongWritable> {

    @Override
    public void setup(Context context) throws IOException {
    }
 
   public void reduce(Text key, Iterable<LongWritable> values, Context context) 
      throws IOException, InterruptedException{ 
      
      int sum = 0;
      for (LongWritable value : values) {
        sum += value.get();
      }
      context.write(key, new LongWritable(sum));
      // context.getCounter(Counters.DISTINCT_CNT).increment(1); seule différence avec le Reducer
    }

   @Override
   public void cleanup(Context context) {
   }
   
  }
}
