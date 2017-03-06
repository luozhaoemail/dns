package dns;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

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

import dns.bean.ConnInfo;
import dns.bean.DmResult;
import dns.bean.Domain;
import dns.bean.Helper;
import dns.bean.UserIp;
import dns.bean.UserMode;

public class RunJob3 {
	public static HashMap<String,Domain>dmap;//1 域名集合 dmname_info
	
	public static ArrayList<UserIp> ulist;//2 运行商+归属地集合 locinfo_new+locinfo_new_index 连接两张表
 	public static ArrayList<Long> uip1= new ArrayList<Long>();//Startip集合
 	
	public static ArrayList<ConnInfo> conlist;//3 ip的引入方式集合 ip_connectinfo
 	public static ArrayList<Long> cip1= new ArrayList<Long>();//Startip集合
 	
 	public static ArrayList<UserMode> modelist;//4  网络制式集合 mfb_ip_userinfo 
 	public static ArrayList<Long> modeip1= new ArrayList<Long>();//Startip集合
 	
 	public static ArrayList<Helper> helplist;//5  mfb_localhelper 厂商表 1：IDC,2:缓存,3:CDN
 	public static ArrayList<Long> heip1= new ArrayList<Long>();//Startip集合 	
 
  	
 	public static class Map extends Mapper<LongWritable,Text,Text,DmResult>{ 		
 		
	 	@Override 
	 	public void map(LongWritable key,Text value,Context context) 
					throws IOException, InterruptedException {
	 		String reg ="(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b";
	 		DmResult dr = new DmResult();
	 		
	 		/**数据格式：源Ip|域名|时间|目的IP|保留字段
			 **		   s[0] s[1]     s[3] 
			 **/
			String line = value.toString();
			String[] s = line.split("\\|");
			long ip_;
			
			if(s.length==5 && s[0].matches(reg))
			{
				long _ip = IPtoLong.ipToLong(s[0]);//先把ip转化成数字	
				
				if(s[3].length() != 0)
				{	
					String[] ips = s[3].split("\\;");						
					if(ips[0].matches(reg))
						ip_ = IPtoLong.ipToLong(ips[0]);					
					
					/*if(s[3].contains(";"))//如果有多个ip1;ip2;1p3 只取第一个	
					{
						String[] ips = s[3].split("\\;");						
						if(ips[0].matches(reg))
							ip_ = IPtoLong.ipToLong(ips[0]);
					}
					else
					{	
						if(s[3].matches(reg))
							ip_ = IPtoLong.ipToLong(s[3]);						
					}*/
				}//end if
				
				if(dmap.containsKey(s[1]))//DNS处理
				{
					Domain domain = dmap.get(s[1]);		 //1 查找域名
					dr.setDmName(domain.getDmName());
					dr.setDomain(domain.getDomain());
					dr.setRecode(domain.getRecode());
					dr.setNetName(domain.getNetName());
					dr.setCompany(domain.getCompany());
					dr.setClassifyName(domain.getClassifyName());
					dr.setClassifyParentName(domain.getClassifyParentName());								
					
					dr=DealIp.getPro_Com(_ip, ulist, uip1, dr);//2 查找用户ip,得到省份和运营商 
					dr=DealIp.getConnect(_ip, conlist, cip1, dr);//3 查找引入方式：1直连引入 2铁通引入 4移动引入 8缓存引入 				
					dr=DealIp.getMode(_ip, modelist, modeip1, dr);//4 查找网络制式 1：4G,2:GPRS,3:WAP,4:固网	
					dr=DealIp.getHelper(_ip, helplist, heip1, dr);//5 厂商表 1：IDC,2:缓存,3:CDN						
					
					try{
						context.write(new Text(s[1]), dr);							
					}catch(Exception e){
						System.out.println(s[0]+"  "+s[1]+"----"+dr);
					}
				}
								
			}//end if
			
	}//end map	
		
	}
 	
 	
 	public static class Redu extends Reducer<Text,DmResult,NullWritable,DmResult>{
 		
 		@Override 
		public void reduce(Text k, Iterable<DmResult> val, Context context)  
	            	   throws IOException, InterruptedException {
 			int i=0;		
 			DmResult t =new DmResult();		
 	    	for(DmResult iter : val)	   	
 	    	{
 	    		i++;
 	    		t= iter;    
 	    	} 	    	
 	    	if(t.getCacheConHitCount()!=0)
 				t.setCacheConHitCount(i);
 			else if(t.getCMCCConHitCount()!=0)
 				t.setCMCCConHitCount(i);
 			else if(t.getDirectConHitCount()!=0)
 				t.setDirectConHitCount(i);
 			else if(t.getCTTConHitCount()!=0)
 				t.setCTTConHitCount(i);	    		
 			if(t.getCacheCount()!=0)
 				t.setCacheCount(i);
 			else if(t.getCDNCount()!=0)
 				t.setCDNCount(i);
 			else if(t.getIDCCount()!=0)
 				t.setIDCCount(i); 
 	    	context.write(NullWritable.get(),t);
	    } 	    
	}
		
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, SQLException {
		/**加载数据库到集合，作为全局变量	
		 */
 		long t1=System.currentTimeMillis();
		dmap =Datetest.loadDomain();//1
		ulist = Datetest.loadUserIp();//2
    	for(int i= 0;i<ulist.size();i++)
		{
			uip1.add(ulist.get(i).getStartIP());//提取ip1		
		}
    	conlist = Datetest.loadConnect();//3
    	for(int i= 0;i<conlist.size();i++)
		{
			cip1.add(conlist.get(i).getStartIP());//提取ip1		
		}
    	modelist = Datetest.loadUserMode();//4
    	for(int i= 0;i<modelist.size();i++)
		{
    		modeip1.add(modelist.get(i).getStartIP());//提取ip1		
		}
    	helplist = Datetest.loadHelper();//5
    	Datetest.close();//关闭数据库
    	for(int i= 0;i<helplist.size();i++)
		{
    		heip1.add(helplist.get(i).getStartIP());//提取ip1		
		}
    	long t2=System.currentTimeMillis();
    	System.out.println("加载数据库完毕！！！！用时："+(t2-t1));
		
    	/**
    	 * 配置job参数
    	 */
		Configuration conf = new Configuration();		
		Job job = Job.getInstance(conf,"dns");
				
		job.setJarByClass(RunJob3.class);
		job.setMapperClass(Map.class);	
		job.setReducerClass(Redu.class);
		
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(DmResult.class);	
		job.setOutputKeyClass(NullWritable.class);  
        job.setOutputValueClass(DmResult.class);		
        //job.setCombinerClass(NewCombiner.class);
        
		//String[] arg = new String[]{"input/data2.txt","output/dns"};201611140320
	   //String[] arg = new String[]{"file:///root/hive/data2.txt","file:///root/hive/dns"};
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
