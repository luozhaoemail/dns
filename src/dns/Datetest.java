package dns;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;

import dns.bean.ConnInfo;
import dns.bean.Domain;
import dns.bean.Helper;
import dns.bean.UserIp;
import dns.bean.UserMode;

public class Datetest {
	public static ConnectionDB db = new ConnectionDB();
	/*public static ResultSet rs;	
	public static void close(){
		db.closeAll();
	}*/
	 
	@Test
	public void test() throws SQLException{
		/*
		Iterator it = rs.iterator();
		while(it.hasNext())
			System.out.println(it.next());*/
		
		ArrayList<UserMode> rs=loadUserMode();
		for(int i=0;i<rs.size();i++)
			System.out.println(rs.get(i));
		
	}
	
	/**locinfo_new+locinfo_new_index+connectinfo 连接三张表
	 * 读出ip所在的省份，运营商，引入方式，默认是ip升序的
	 * connectinfo只5380行，locinfo_new有17344行，且只有5366行和locinfo_new是一样的
	 */
	public static List<Object> loadIPinfo() throws SQLException {  
		
		ArrayList<UserIp> list = new ArrayList<UserIp>(); 		
	    return db.excuteQuery("select a.startip,a.endip,a.provcode,a.cominfo,a.provname,a.code,t3.connectinfo "
	    		+ " from (select t1.*,t2.code from mfb_ip_locinfo_new t1 join mfb_ip_locinfo_cominfo_index t2 on t1.ComInfo=t2.ComInfo "
	    		+ " ) as a join mfb_ip_connectinfo t3 on a.startip=t3.startip limit 10");	  
	 }
		
	/**1,2
	 * locinfo_new + locinfo_new_index 连接两张表
	 * 读出各省的运营商ip区间，默认是ip升序的
	 */
	public static ArrayList<UserIp> loadUserIp() throws SQLException {	       
	
		ArrayList<UserIp> list = new ArrayList<UserIp>(); 
		
	    ResultSet rs = db.executeQueryRS("select t1.startip,t1.endip,t1.provcode,t1.cominfo,t1.provname,t2.code "
	    		+ " from mfb_ip_locinfo_new t1,mfb_ip_locinfo_cominfo_index t2 "
	    		+ " where t1.ComInfo=t2.ComInfo ");	  
	    while(rs.next())
		{	    	
	    	long StartIP = rs.getLong("StartIP");
	    	long EndIP = rs.getLong("EndIP");
	    	int ProvCode = rs.getInt("ProvCode");
	    	int ComInfo = rs.getInt("ComInfo");
	    	String ProvName = rs.getString("ProvName");
	    	String Code = rs.getString("Code");	    		    	
	    	list.add(new UserIp(StartIP,EndIP,ProvCode,ComInfo,ProvName,Code));   	
		}
	    rs.close();	  
	    return list;	    
	}
	
	
	/**3
	 * 读取mfb_ip_connectinfo 读出ip的引入方式
	 */
	public static ArrayList<ConnInfo> loadConnect() throws SQLException { 
		
		ArrayList<ConnInfo> list = new ArrayList<ConnInfo>(); 
		
	    ResultSet rs = db.executeQueryRS("select StartIP,EndIP,ConnectInfo from mfb_ip_connectinfo");	  
	    while(rs.next())
		{	    	
	    	long StartIP = rs.getLong("StartIP");
	    	long EndIP = rs.getLong("EndIP");	    	
	    	int ConnectInfo = rs.getInt("ConnectInfo");  	
	    	list.add(new ConnInfo(StartIP,EndIP,ConnectInfo));  
		}
	    rs.close();	   
	    return list;	    
	}
	
	
	/**4
	 * 读取mfb_dmname_info域名信息表
	 */
	public static HashMap<String,Domain> loadDomain() throws SQLException {	       
		
	    HashMap<String,Domain> hm = new HashMap<String,Domain>(); 
		
	    ResultSet rs = db.executeQueryRS("select DmName,Domain,Recode,NetName,Company,ClassifyName,"
	    		+ "ClassifyParentName from mfb_dmname_info");	  
	    while(rs.next())
		{	    	
	    	String DmName =rs.getString("DmName");
	    	String Domain=rs.getString("Domain");
	    	String Recode=rs.getString("Recode");
	    	String NetName=rs.getString("NetName");
	    	String Company=rs.getString("Company");
	    	String ClassifyName=rs.getString("ClassifyName");
	    	String ClassifyParentName=rs.getString("ClassifyParentName");  	
	    	hm.put(DmName,new Domain(DmName,Domain,Recode,NetName,Company,ClassifyName,ClassifyParentName));   	
		}
	    rs.close();	
	    return hm;	    
	}
	
		
	/**5
	 * 读取mfb_ip_userinfo网络制式表 1：4G,2:GPRS,3:WAP,4:固网
	 */
	public static ArrayList<UserMode> loadUserMode() throws SQLException {	       
		
	    ArrayList<UserMode> list = new ArrayList<UserMode>(); 
		
	    ResultSet rs = db.executeQueryRS("select StartIP,EndIP,UserInfo from mfb_ip_userinfo order by StartIP asc");	  
	    while(rs.next())
		{	    	
	    	long StartIP = rs.getLong("StartIP");
	    	long EndIP = rs.getLong("EndIP");	    	
	    	int UserInfo = rs.getInt("UserInfo");  	
	    	list.add(new UserMode(StartIP,EndIP,UserInfo));   	
		}    
	    rs.close();	 
	    return list;	    
	}
	
	/**6
	 * 读取mfb_localhelper厂商表 1：IDC,2:缓存,3:CDN
	 */
	public static ArrayList<Helper> loadHelper() throws SQLException {	      
		
	    ArrayList<Helper> list = new ArrayList<Helper>(); 
		
	    ResultSet rs = db.executeQueryRS("select StartIP,EndIP,HelperStatue,HelperName,localId "
	    		+ " from mfb_localhelper order by StartIP asc");	  
	    while(rs.next())
		{	    	
	    	long StartIP = rs.getLong("StartIP");
	    	long EndIP = rs.getLong("EndIP");	    	
	    	int HelperStatue = rs.getInt("HelperStatue");  
	    	String HelperName=rs.getString("HelperName");
	    	int localId = rs.getInt("localId"); 
	    	list.add(new Helper(StartIP,EndIP,HelperStatue,HelperName,localId));   	
		}
	    rs.close();	   
	    return list;	    		    
	}
}
