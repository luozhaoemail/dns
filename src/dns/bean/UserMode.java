package dns.bean;

public class UserMode{	
	private long StartIP;
	private long EndIP;
	//private String StrStartIP;
	//private String StrEndIP;
	private int UserInfo;
	
	public UserMode() {		
	}

	public UserMode(long startIP, long endIP,int userInfo){
		StartIP = startIP;
		EndIP = endIP;	
		UserInfo = userInfo;
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


	public int getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(int userInfo) {
		UserInfo = userInfo;
	}


	@Override
	public String toString() {
		return "UserMode [StartIP=" + StartIP + ", EndIP=" + EndIP +", UserInfo=" + UserInfo + "]";
	}
	
	
	
}
