package dns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import dns.bean.ConnInfo;
import dns.bean.DmResult;
import dns.bean.Domain;
import dns.bean.Helper;
import dns.bean.UserIp;
import dns.bean.UserMode;

public class Test {
	public static HashMap<String,Domain>dmap;//1 域名集合 dmname_info
	
	public static ArrayList<UserIp> ulist;//2 运行商+归属地集合 locinfo_new+locinfo_new_index 连接两张表
 	public static ArrayList<Long> uip1= new ArrayList<Long>();//Startip集合
 	
	public static ArrayList<ConnInfo> conlist;//3 ip的引入方式集合 ip_connectinfo
 	public static ArrayList<Long> cip1= new ArrayList<Long>();//Startip集合
 	
 	public static ArrayList<UserMode> modelist;//4  网络制式集合 mfb_ip_userinfo 
 	public static ArrayList<Long> modeip1= new ArrayList<Long>();//Startip集合
 	
 	public static ArrayList<Helper> helplist;//5  mfb_localhelper 厂商表 1：IDC,2:缓存,3:CDN
 	public static ArrayList<Long> heip1= new ArrayList<Long>();//Startip集合 
 	
 	
public static void main(String[] args) throws Exception {
	long _ip,ip_; 	
	DmResult dr = new DmResult();
	
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
	for(int i= 0;i<helplist.size();i++)
	{
		heip1.add(helplist.get(i).getStartIP());//提取ip1		
	}  
	long t2=System.currentTimeMillis();
	System.out.println("加载数据库完毕！！！！用时："+(t2-t1));
		
	BufferedReader br=new BufferedReader(new FileReader("/root/hive/data.txt"));
	BufferedWriter wr=new BufferedWriter(new FileWriter("/root/hive/ddddd.txt",true));
	
	String line;
	while((line = br.readLine()) != null) 
	{
		String[] s = line.split("\\|");
		_ip = IPtoLong.ipToLong(s[0]);//先把ip转化成数字			
		if(s[3].contains(";"))//如果有多个ip1;ip2;1p3 只取第一个		
			ip_ = IPtoLong.ipToLong(s[3].substring(0,s[3].indexOf(";")));			
		else
			ip_ = IPtoLong.ipToLong(s[3]);
			
		
		//查找域名 1
		if(dmap.containsKey(s[1]))
		{
			Domain domain = dmap.get(s[1]);		
			dr.setDmName(domain.getDmName());
			dr.setDomain(domain.getDomain());
			dr.setRecode(domain.getRecode());
			dr.setNetName(domain.getCompany());
			dr.setCompany(domain.getCompany());
			dr.setClassifyName(domain.getClassifyName());
			dr.setClassifyParentName(domain.getClassifyParentName());	
		}	
					
		//查找用户ip,得到省份和运营商 2
		int j =Search.binarySearch(uip1,_ip);//先比较satrtIp		
		if(j>=0)		
		{
			long ip2 = ulist.get(j).getEndIP();//比较endIp			
			if(_ip<ip2)
			{	
				//System.out.println("执行到此----------------"+dr.toString());
				dr.setProvName(ulist.get(j).getProvName());
				dr.setCdoe(ulist.get(j).getCode());					
			}
		}
		
		/**3
		 * 查找引入方式：1直连引入 2铁通引入 4移动引入 8缓存引入
		 */			
		int x =Search.binarySearch(cip1,_ip);//先比较satrtIp		
		if(x >= 0)	
		{
			long ip2 = conlist.get(x).getEndIP();//比较endIp			
			if(_ip<ip2)
			{	
				int i = conlist.get(x).getConnectInfo();
				switch(i)
				{				
					case 1:
						dr.setDirectConHitCount();//自加++
						break;
					case 2:
						dr.setCTTConHitCount();
						break;
					case 4:
						dr.setCMCCConHitCount();
						break;
					case 8:
						dr.setCacheConHitCount();
						break;
					default:break;
				}
			}
		}
		
		/**4
		 * 查找网络制式 1：4G,2:GPRS,3:WAP,4:固网
		 */
		int y =Search.binarySearch(modeip1,_ip);//先比较satrtIp		
		if(y >= 0)	
		{
			long ip2 = modelist.get(y).getEndIP();//比较endIp			
			if(_ip<ip2)
			{	
				dr.setUserInfo(modelist.get(y).getUserInfo());
			}				
		}
		
		/**5
		 * 厂商表 1：IDC,2:缓存,3:CDN  
		 */
		int z =Search.binarySearch(heip1,_ip);//先比较satrtIp		
		if(z >= 0)	
		{
			long ip2 = helplist.get(z).getEndIP();//比较endIp			
			if(_ip<ip2)
			{	
				int i = helplist.get(z).getHelperStatue();
				switch(i)
				{				
					case 1:
						dr.setIDCCount();//自加++
						break;
					case 2:
						dr.setCacheCount();;
						break;
					case 3:
						dr.setCDNCount();
						break;					
					default:break;
				}
			}				
		}
		
	wr.write(dr.toString());
	wr.newLine();		
	wr.flush();	
		
		
	}///end while
	wr.close();
	br.close();
	}

}
