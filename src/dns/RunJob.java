package dns;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import dns.bean.UserIp;



public class RunJob {
	public static ArrayList<UserIp> list;
 	public static ArrayList<Long> al= new ArrayList<Long>();  	
 	
 	public static class Map extends Mapper<LongWritable,Text,LongWritable,Text>{	 	
	
 		public static String reg ="(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b";
	 	long ip,ip2;
	 	String prov,code;
	 			
		@Override
		public void map(LongWritable key,Text value,Context context) 
					throws IOException, InterruptedException {
			
			String line = value.toString();
			String[] ss = line.split("\\|");			
			
			if(ss[0].matches(reg))
			{
				long ip = IPtoLong.ipToLong(ss[0]);					
				int index =Search.binarySearch(al,ip);	//查找小于ip的最大元素startip				
				if(index>=0)		
				{
					ip2 = list.get(index).getEndIP();				
					if(ip<ip2)
					{	
						prov = list.get(index).getProvName();
						code = list.get(index).getCode();
						//System.out.println(ss[0]+": "+str);
						context.write(new LongWritable(ip), new Text(ss[0]+" "+prov+" "+code));
					}
				}	
			}//end if			
		}//end map		
			
		
	}
 	
 	/**
 	 * ip去重思路：因为同一个ip（用户）会多次上网，只提取ip就会有重复。那么将ip转化为long值
 	 * 在shuffle阶段使用默认的分区、排序、分组就能将将同一个ip的value发送到对应的reduce
 	 * reduce得到一个ip的集合，里面是分组后的value集合，而且全是重复的值
 	 * 那么遍历迭代器时只需要输出一次就break出循环。这样每个reduce只取一个值，
 	 * 多个reduce就构成了不重复的输出
 	 */
 	public static class Red extends Reducer<LongWritable,Text,NullWritable,Text>{
		 
		public void reduce(LongWritable k, Iterable<Text> val, Context context)  
	            	   throws IOException, InterruptedException {
	   
	    	for(Text t : val)
	    	{
	    		context.write(NullWritable.get(),t);
	    		break;
	    	}
	    } 	    
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, SQLException {
		
		list = Datetest.loadUserIp();//加载数据库到集合，作为全局变量	
    	for(int i= 0;i<list.size();i++)
		{
			al.add(list.get(i).getStartIP());//提取ip1		
		}
		
		Configuration conf = new Configuration();		
		Job job = Job.getInstance(conf,"dns");
				
		job.setJarByClass(RunJob.class);
		job.setMapperClass(Map.class);	
		job.setReducerClass(Red.class);
		
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);	
		job.setOutputKeyClass(NullWritable.class);  
        job.setOutputValueClass(Text.class);		
		
		//相对路径
        //String[] arg = new String[]{"input/201611140320.txt","output/dns"};
	    //String[] arg = new String[]{"file:///root/hive/201611140320.txt","file:///root/hive/dns"};
        String[] arg = new String[]{"file:\\C:\\opt\\201611140320.txt","file:\\C:\\opt\\dns"};
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
