package hadoop;

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
import org.apache.hadoop.util.GenericOptionsParser;

public class TxtDeal {

	public static class TokenMapp extends
		Mapper<Object,Text,Text,IntWritable>{
		
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
				
		String[] arg = new String[]{"file:///tmp/2.txt","file:///tmp/out"};
		//注意运行前要删除已有的output目录，否则会报错
		
		if(arg.length !=2){ //检测运行时的2个参数
			System.err.println("--------:wordcount out");
			System.exit(2);
		}
		
		Job job = new Job(conf,"Wordcount"); //job={task,task}d
		
		job.setJarByClass(TxtDeal.class);
		job.setMapperClass(TokenMapp.class);
		job.setCombinerClass(sumReduce.class);
		job.setReducerClass(sumReduce.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
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
