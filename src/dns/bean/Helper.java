package dns.bean;

public class Helper {	
	private long StartIP;
	private long EndIP;	
	private int HelperStatue; 
	private String HelperName;
	private int localId; 
	 
	public Helper() {
		// TODO Auto-generated constructor stub
	}
	
	public Helper(long startIP, long endIP, int helperStatue, String helperName, int localId) {
		super();
		StartIP = startIP;
		EndIP = endIP;
		HelperStatue = helperStatue;
		HelperName = helperName;
		this.localId = localId;
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

	public int getHelperStatue() {
		return HelperStatue;
	}

	public void setHelperStatue(int helperStatue) {
		HelperStatue = helperStatue;
	}

	public String getHelperName() {
		return HelperName;
	}

	public void setHelperName(String helperName) {
		HelperName = helperName;
	}

	public int getLocalId() {
		return localId;
	}

	public void setLocalId(int localId) {
		this.localId = localId;
	}

	@Override
	public String toString() {
		return "Helper [StartIP=" + StartIP + ", EndIP=" + EndIP + ", HelperStatue=" + HelperStatue + ", HelperName="
				+ HelperName + ", localId=" + localId + "]";
	}

		
	
	
	

}
