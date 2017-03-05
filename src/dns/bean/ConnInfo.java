package dns.bean;

public class ConnInfo {

	private long StartIP;
	private long EndIP;	
	private int ConnectInfo;	
	
	 
	public ConnInfo() {
		// TODO Auto-generated constructor stub
	}

	public ConnInfo(long startIP, long endIP, int connectInfo) {
		super();
		StartIP = startIP;
		EndIP = endIP;
		ConnectInfo = connectInfo;
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

	public int getConnectInfo() {
		return ConnectInfo;
	}

	public void setConnectInfo(int connectInfo) {
		ConnectInfo = connectInfo;
	}

	@Override
	public String toString() {
		return "ConnInfo [StartIP=" + StartIP + ", EndIP=" + EndIP + ", ConnectInfo=" + ConnectInfo + "]";
	}

		

}
