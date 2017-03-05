package mr;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

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

public class TestQQ {
	
	public static class Map extends Mapper<LongWritable,Text,Text,Text>{
		
		public void map(LongWritable key,Text value,Context context) 
					throws IOException, InterruptedException {
			
			String line = value.toString();
			String[] ss = line.split("\\s+");//\s表示空格,回车,换行等空白符,+号表示一个或多个
			context.write(new Text(ss[0]),new Text(ss[1]));//key=vaule
			context.write(new Text(ss[1]),new Text(ss[0]));
		}
	}
	
	public static class Redu extends Reducer<Text,Text,Text,Text>{
	 
	    protected void reduce(Text key, Iterable<Text> iter, Context context)  
	            	   throws IOException, InterruptedException {
	    	
	        Set<String> set = new HashSet<String>();  
	        for (Text text : iter) 
	            set.add(text.toString());  
	    
	        if (set.size() > 1)
	        {  
	            for (Iterator i = set.iterator(); i.hasNext();) 
	            {	            	
	                String name = (String) i.next();  
	                
	                for (Iterator j = set.iterator(); j.hasNext();) 
	                { 	                    
	                	String other = (String) j.next();  	                    
	                    if (!name.equals(other)) 
	                        context.write(new Text(name), new Text(other));  	                    
	                }  
	            }  
	        }  
	    }  
}	
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Configuration conf = new Configuration();		
		Job job = Job.getInstance(conf,"qq");
		
		job.setJarByClass(TestQQ.class);
		job.setMapperClass(Map.class);
		job.setReducerClass(Redu.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);  
        job.setOutputValueClass(Text.class);  

        //job.setNumReduceTasks(1);//设置reduce任务的个数，默认是一个
     
        String[] arg = new String[]{"hdfs://node1:9000/input/qq.txt","hdfs://node1:9000/output"};
        
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
