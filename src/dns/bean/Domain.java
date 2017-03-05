package dns.bean;

public class Domain {	
	private String DmName;
	private String Domain;
	private String Recode;
	private String NetName;
	private String Company;
	private String ClassifyName;
	private String ClassifyParentName;
	
	public Domain() {
		// TODO Auto-generated constructor stub
	}

	public Domain(String dmName, String domain, String recode, String netName, String company,
			String classifyName, String classifyParentName) {			
		DmName = dmName;
		Domain = domain;
		Recode = recode;
		NetName = netName;
		Company = company;
		ClassifyName = classifyName;
		ClassifyParentName = classifyParentName;
	}

	public String getDmName() {
		return DmName;
	}

	public void setDmName(String dmName) {
		DmName = dmName;
	}

	public String getDomain() {
		return Domain;
	}

	public void setDomain(String domain) {
		Domain = domain;
	}

	public String getRecode() {
		return Recode;
	}

	public void setRecode(String recode) {
		Recode = recode;
	}

	public String getNetName() {
		return NetName;
	}

	public void setNetName(String netName) {
		NetName = netName;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getClassifyName() {
		return ClassifyName;
	}

	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}

	public String getClassifyParentName() {
		return ClassifyParentName;
	}

	public void setClassifyParentName(String classifyParentName) {
		ClassifyParentName = classifyParentName;
	}

	@Override
	public String toString() {
		return "Domain [DmName=" + DmName + ", Domain=" + Domain + ", Recode=" + Recode + ", NetName="
				+ NetName + ", Company=" + Company + ", ClassifyName=" + ClassifyName + ", ClassifyParentName="
				+ ClassifyParentName + "]";
	}
	
	

}
