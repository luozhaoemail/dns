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
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import dns.bean.TbWritable;
import dns.bean.UserIp;



public class RunJobDB {
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
						context.write(new LongWritable(ip), new Text(ss[0]+";"+prov+";"+code));
					}
				}	
			}//end if			
		}//end map		
			
		
	}
 	 	
 	public static class Red extends Reducer<LongWritable,Text,TbWritable,TbWritable>{
		 
		public void reduce(LongWritable k, Iterable<Text> val, Context context)  
	            	   throws IOException, InterruptedException {
	   
	    	for(Text t : val)
	    	{	    		 
	    		 String[] str = t.toString().split(";");
	    		 String ip=str[0];
	    		 String prov=str[1];
	    		 String com=str[2];	
	    		 context.write(new TbWritable(ip,prov,com),null);
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
		DBConfiguration.configureDB(conf,"com.mysql.jdbc.Driver","jdbc:mysql://localhost:3306/test","root", "root");
		Job job = Job.getInstance(conf,"dns");
				
		job.setJarByClass(RunJobDB.class);
		job.setMapperClass(Map.class);	
		job.setReducerClass(Red.class);
		
		job.setMapOutputKeyClass(LongWritable.class);
		job.setMapOutputValueClass(Text.class);	
		job.setOutputKeyClass(TbWritable.class);  
        job.setOutputValueClass(TbWritable.class);		
        
        job.setOutputFormatClass(DBOutputFormat.class);  // 输出格式
        
        FileInputFormat.addInputPath(job, new Path("file:\\C:\\opt\\data2.txt"));
		DBOutputFormat.setOutput(job, "tbout2", "ip", "prov", "com");// 输出到表+字段
        
		System.exit(job.waitForCompletion(true)?0:1);	  //运行job
	}
}
