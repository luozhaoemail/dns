package dns;

import java.util.ArrayList;

public class Search {
		
	// list默认递增，查找第一个比des大的数
	public static int binarySearch(ArrayList<Long> aa,Long ip){ 
		if(ip < aa.get(0))
			return -1;	
		
		if(ip > aa.get(aa.size()-1))				
			return aa.size()-1;
		
		int low = 0;
        int high = aa.size()-1;
        int res = high;
        while(low <= high) 
        {  	                  
        	 int middle = low +(high-low)/2;
        	 
        	 if(ip < aa.get(middle)) 
             {             	
             	res = middle;
                high = middle-1;         	 
             }
             else
             {      
                low = middle+1;
             }
        }  
        return res-1; 				
   }  
	/*
	public static boolean find(ArrayList<Long> al,long ip,ArrayList<Object> aaa){
		
		int index = binarySearch(al,ip);//先比较satrtIp		
		if(index>=0)		
		{
			long ip2 = aaa.get(index).getEndIP();//比较endIp			
			if(_ip<ip2)
			{	return true;
				break;
			}
			return false;
		}
		
		return false;
	}
	*/
}
