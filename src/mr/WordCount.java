package mr;

import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;



public class WordCount {

	public static class TokenMapp extends
		Mapper<Object,Text,Text,IntWritable>{
		/**
		 * IntWritable,Text均是 Hadoop 中实现的用于封装 Java数据类型的类，这些类实现了WritableComparable接口，
         * 都能够被串行化从而便于在分布式环境中进行数据交换，你可以将它们分别视为int,String 的替代品。
         * 声明one常量和word用于存放单词的变量
         */
	    private final static IntWritable one = new IntWritable(1);
		private Text word = new Text();
				
		public void map(Object key,Text value,Context con){ //映射一个单个的输入k/v对到一个中间的k/v对
			//Context：收集Mapper输出的<k,v>对。
			StringTokenizer itr = new StringTokenizer(value.toString());//StringTokenizer函数对字符串进行分隔
			
			while(itr.hasMoreTokens()){
				word.set(itr.nextToken());
				
				try {
					con.write(word, one); //key=value 增加一个(k,v)对到context,通过write方法把单词存入word中
					
				} catch (IOException e) {					
					e.printStackTrace();
				} catch (InterruptedException e) {				
					e.printStackTrace();
				}
			}
		}
		
		}//inner class
	
	public static class sumReduce extends
		Reducer<Text,IntWritable,Text,IntWritable>{
			
		private IntWritable result = new IntWritable();
		
		//中k/v来自于map函数中的context,value是迭代器形式的，context记录key/value
		public void reduce(Text key,Iterable<IntWritable> value,Context con){
			
			int sum = 0;
			for(IntWritable val : value){
				sum += val.get();
			}
			
			result.set(sum);
			
			try {
				con.write(key, result);
				
			} catch (IOException e) {				
				e.printStackTrace();
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
			
		}
			
		}//inner class
	
	public static void main(String[] args) throws Exception{
		
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf,"wc");
	
		job.setJarByClass(WordCount.class);
		job.setMapperClass(TokenMapp.class);
		job.setReducerClass(sumReduce.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(IntWritable.class);  

        //job.setNumReduceTasks(1);//设置reduce任务的个数，默认是一个
     
        //String[] arg = new String[]{"hdfs://mycluster:8020/input/1.txt","hdfs://mycluster:8020/output/qq"};
        String[] arg = new String[]{"file:\\C:\\opt\\1.txt","file:\\C:\\opt\\1"};
        
        //自动删除output
  		Path path = new Path(arg[1]);
  		FileSystem fs = path.getFileSystem(conf);
  		if(fs.exists(path))
  			fs.delete(path,true);
  		
        FileInputFormat.addInputPath(job, new Path(arg[0]));  //为job设置输入路径
		FileOutputFormat.setOutputPath(job,new Path(arg[1])); //为job设置输出路径
		System.exit(job.waitForCompletion(true)?0:1);	  //运行job	
			
	}//main
	
}
