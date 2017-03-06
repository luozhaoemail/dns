package dns.bean;

public class UserIp {	
	private long StartIP=0L;
	private long EndIP=0L;
	private int ProvCode=0;
	private int ComInfo=0;
	private String ProvName="";
	private String Code="";
	
	
	public UserIp() {
		
	}
	
	
	public UserIp(long startIP, long endIP, int provCode, int comInfo, String provName, String code) {
		super();		
		StartIP = startIP;
		EndIP = endIP;
		ProvCode = provCode;
		ComInfo = comInfo;
		ProvName = provName;
		Code = code;
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

	public int getProvCode() {
		return ProvCode;
	}

	public void setProvCode(int provCode) {
		ProvCode = provCode;
	}

	public int getComInfo() {
		return ComInfo;
	}

	public void setComInfo(int comInfo) {
		ComInfo = comInfo;
	}

	public String getProvName() {
		return ProvName;
	}

	public void setProvName(String provName) {
		ProvName = provName;
	}


	
	public String getCode() {
		return Code;
	}


	public void setCode(String code) {
		Code = code;
	}

	@Override
	public String toString() {
		return "StartIP=" + StartIP + ", EndIP=" + EndIP + ", ProvCode=" + ProvCode + ", ComInfo="
				+ ComInfo + ", ProvName=" + ProvName + ", Code=" + Code;
	}

	

}
