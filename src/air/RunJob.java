package air;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
/**
需求
1.计算在1949-1955年，每年温度最高的时间  
2.计算在1949-1955年，每年温度最高前十天  
思路：  
1.按照年份升序，同时每一年中温度降序排序  
2.按照年份分组，每一年对应一个reduce任务  
mapper输出，key为封装对象。    
目的：  
自定义排序  
自定义分区  
自定义分组 
 */

public class RunJob {

	public static class Map extends Mapper<LongWritable,Text,KeyPair,Text>{
		
		public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		public void map(LongWritable key,Text value,Context context) 
					throws IOException, InterruptedException {
			String line =  value.toString();
			String[] ss = line.split("\\|");
			if(ss.length==2)
			{	
				try {
					Date date = SDF.parse(ss[0]);
					Calendar c = Calendar.getInstance();
					c.setTime(date);					
					int year = c.get(1);
					String hot = ss[1].substring(0, ss[1].lastIndexOf("°C"));					
					//KeyPair kp = new KeyPair(year,Integer.valueOf(hot));
					KeyPair kp = new KeyPair();
					kp.setYear(year);
					kp.setHot(Integer.valueOf(hot));
					context.write(kp, value);
					
				} catch (ParseException e) {					
					e.printStackTrace();
				}
				
			}
			
		}
	}
	
	public static class Redu extends Reducer<KeyPair,Text,KeyPair,Text>{
	 
	    protected void reduce(KeyPair kp, Iterable<Text> iter, Context context)  
	            	   throws IOException, InterruptedException {
	    	for(Text t : iter)
	    		context.write(kp, t);	       
	    }  
}	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf,"air");
		job.setJarByClass(RunJob.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Redu.class);
		
		job.setMapOutputKeyClass(KeyPair.class);
		job.setMapOutputValueClass(Text.class);	
		//job.setOutputKeyClass(KeyPair.class);  
        //job.setOutputValueClass(Text.class);  
       
        job.setNumReduceTasks(3);//设置reduce任务的个数，默认是一个
        job.setSortComparatorClass(Sort.class);//1.自定义排序
        job.setPartitionerClass(FirstParitition.class);//2.自定义分区
        job.setGroupingComparatorClass(Group.class);//3.自定义分组
     
        String[] arg = new String[]{"input/air.txt","output/air"};
        
        //自动删除output
  		Path path = new Path(arg[1]);
  		FileSystem fs = path.getFileSystem(conf);
  		if(fs.exists(path))
  			fs.delete(path,true);
  		
        FileInputFormat.addInputPath(job, new Path(arg[0]));  //为job设置输入路径
		FileOutputFormat.setOutputPath(job,new Path(arg[1])); //为job设置输出路径
		System.exit(job.waitForCompletion(true)?0:1);	  //运行job
	}
}
