package dns;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import dns.bean.ConnInfo;
import dns.bean.DmResult;
import dns.bean.Domain;
import dns.bean.Helper;
import dns.bean.UserIp;
import dns.bean.UserMode;

public class Test2 {
		
	 
	@Test
	public void test() throws SQLException{	
		String line ="223.104.94.10|omgmta1.qq.com|20161114032001||2";
		String[] s = line.split("\\;");
		for(int i=0;i<s.length;i++)
			System.out.println("**"+s[i]+"**");
		System.out.println(s[3]+"---"+s[3].isEmpty()+(s[3]=="")+s[3].length());
		System.out.println(s.length);
	
	}
	
	public static void main(String[] args) throws Exception {
		long _ip,ip_; 	
		DmResult dr = new DmResult();
				
		BufferedReader br=new BufferedReader(new FileReader("/root/hive/data.txt"));
		BufferedWriter wr=new BufferedWriter(new FileWriter("/root/hive/ddddd.txt",true));
		
		String line;
		while((line = br.readLine()) != null) 
		{
			wr.write(dr.toString());
			wr.newLine();		
			wr.flush();	
		}//end while
		wr.close();
		br.close();
		}

}
