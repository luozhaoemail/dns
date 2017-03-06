package model;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;

public class Souip implements Writable {

	private int ID;
	private long StartIP; 
	private long EndIP;
	private String StrStartIP;
	private String StrEndIP;
	private int UserInfo;
	
	public Souip() {		
	}

	public Souip(int iD, long startIP, long endIP, String strStartIP, String strEndIP, int userInfo) {
	
		ID = iD;
		StartIP = startIP;
		EndIP = endIP;
		StrStartIP = strStartIP;
		StrEndIP = strEndIP;
		UserInfo = userInfo;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public long getStartIP() {
		return StartIP;
	}

	public void setStartIP(long startIP) {
		StartIP = startIP;
	}

	public long getEndIP() {
		return EndIP;
	}

	public void setEndIP(long endIP) {
		EndIP = endIP;
	}

	public String getStrStartIP() {
		return StrStartIP;
	}

	public void setStrStartIP(String strStartIP) {
		StrStartIP = strStartIP;
	}

	public String getStrEndIP() {
		return StrEndIP;
	}

	public void setStrEndIP(String strEndIP) {
		StrEndIP = strEndIP;
	}

	public int getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(int userInfo) {
		UserInfo = userInfo;
	}


	@Override
	public String toString() {
		return "Souip [ID=" + ID + ", StartIP=" + StartIP + ", EndIP=" + EndIP + ", StrStartIP=" + StrStartIP
				+ ", StrEndIP=" + StrEndIP + ", UserInfo=" + UserInfo + "]";
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		ID = in.readInt();
		StartIP = in.readLong();
		EndIP = in.readLong();
		StrStartIP = Text.readString(in);
		StrEndIP = Text.readString(in);
		UserInfo = in.readInt();
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeInt(ID);  
		out.writeLong(StartIP);
		out.writeLong(EndIP);
        Text.writeString(out, StrStartIP);  
        Text.writeString(out, StrEndIP);
        out.writeInt(UserInfo);       
		
	}
	
	
	
}
