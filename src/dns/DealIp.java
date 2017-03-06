package dns;

import java.util.ArrayList;
import dns.bean.ConnInfo;
import dns.bean.DmResult;
import dns.bean.Helper;
import dns.bean.UserIp;
import dns.bean.UserMode;

public class DealIp {
	
	public DealIp() {
		
	}
	/**2
	 * 查找用户ip,得到省份和运营商
	 */
	public static DmResult getPro_Com(long ip,ArrayList<UserIp> ulist,ArrayList<Long> uip1,DmResult dr){
		int i =Search.binarySearch(uip1,ip);//先比较satrtIp		
		if(i>=0)		
		{
			long ip2_ = ulist.get(i).getEndIP();//比较endIp			
			if(ip < ip2_)
			{	
				dr.setProvName(ulist.get(i).getProvName());
				dr.setCdoe(ulist.get(i).getCode());					
			}
		}		
		return dr;
	}
	
	/**3
	 * 查找引入方式：1直连引入 2铁通引入 4移动引入 8缓存引入
	 */	
	public static DmResult getConnect(long ip, ArrayList<ConnInfo> conlist,ArrayList<Long> cip1,DmResult dr){
		int x =Search.binarySearch(cip1,ip);//先比较satrtIp		
		if(x >= 0)	
		{
			long ip2 = conlist.get(x).getEndIP();//比较endIp			
			if(ip<ip2)
			{	
				int i = conlist.get(x).getConnectInfo();
				switch(i)
				{				
					case 1:
						dr.setDirectConHitCount(1);//查找到数据，命中次数加1							
						break;
					case 2:
						dr.setCTTConHitCount(1);
						break;
					case 4:
						dr.setCMCCConHitCount(1);
						break;
					case 8:
						dr.setCacheConHitCount(1);
						break;
					default:break;
				}
			}
		}
		return dr;
	}
	
	/**4
	 * 查找网络制式 1：4G,2:GPRS,3:WAP,4:固网
	 */
	public static DmResult getMode(long ip,ArrayList<UserMode> modelist,ArrayList<Long> modeip1,DmResult dr){
		int y =Search.binarySearch(modeip1,ip);//先比较satrtIp		
		if(y >= 0)	
		{
			long ip2 = modelist.get(y).getEndIP();//比较endIp			
			if(ip<ip2)
			{	
				dr.setUserInfo(modelist.get(y).getUserInfo());
			}				
		}
		return dr;
	}
	
	/**5
	 * 厂商表 1：IDC,2:缓存,3:CDN  
	 */
	public static DmResult getHelper(long ip,ArrayList<Helper> helplist,ArrayList<Long> heip1,DmResult dr){
		int z =Search.binarySearch(heip1,ip);//先比较satrtIp		
		if(z >= 0)	
		{
			long ip2 = helplist.get(z).getEndIP();//比较endIp			
			if(ip<ip2)
			{	
				int i = helplist.get(z).getHelperStatue();
				switch(i)
				{				
					case 1:
						dr.setIDCCount(1);//查找到数据，命中次数加1	
						break;
					case 2:
						dr.setCacheCount(1);;
						break;
					case 3:
						dr.setCDNCount(1);
						break;					
					default:break;
				}
			}				
		}	
		return dr;
	}
}
