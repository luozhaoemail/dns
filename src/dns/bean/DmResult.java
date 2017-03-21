package dns.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class DmResult implements Writable{
	private String DmName="";
	private String Domain="";
	private String Recode="";
	private String NetName="";
	private String Company="";
	private String ClassifyName="";
	private String ClassifyParentName="";
	
	private String ProvName="";
	private String Cdoe="";
	private  int CacheConHitCount=0;
	private  int CMCCConHitCount=0;
	private  int DirectConHitCount=0;
	private  int CTTConHitCount=0;
	private  int UserInfo=0;
	
	private int IDCCount=0;
	private int CacheCount=0;
	private int CDNCount=0;
	////////////
	private int CuccCount=0;//连通命中次数
	private int CTCount=0;//电信命中次数
	private int GATCount=0;//港澳台命中次数
	private int ForeignCount=0;//国外命中次数
	private int LocalOperatorCount=0;//本地移动次数
	private int OtherOperatorCount=0;//外地移动次数
	
	private double InNetProportion=0.0;	//网内占百分比
	private double OutNetProportion=0.0;	//网外占百分比
	
	public DmResult() {
		
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

	//------------------------------
	public String getProvName() {
		return ProvName;
	}
	public void setProvName(String provName) {
		ProvName = provName;
	}
	public String getCdoe() {
		return Cdoe;
	}
	public void setCdoe(String cdoe) {
		Cdoe = cdoe;
	}
	//------------------------------
	public int getCacheConHitCount() {
		return CacheConHitCount;
	}	
	public int getCMCCConHitCount() {
		return CMCCConHitCount;
	}
	public int getDirectConHitCount() {
		return DirectConHitCount;
	}

	public int getCTTConHitCount() {
		return CTTConHitCount;
	}		
	public void setCMCCConHitCount(int cMCCConHitCount) {
		CMCCConHitCount = cMCCConHitCount;
	}
	public void setDirectConHitCount(int directConHitCount) {
		DirectConHitCount = directConHitCount;
	}
	public void setCTTConHitCount(int cTTConHitCount) {
		CTTConHitCount = cTTConHitCount;
	}	
	public void setCacheConHitCount(int cacheConHitCount) {
		CacheConHitCount = cacheConHitCount;
	}
	
	//*******

	//------------------------------
	public int getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(int userInfo) {
		UserInfo = userInfo;
	}
	//------------------------------

	public int getIDCCount() {
		return IDCCount;
	}
	public int getCacheCount() {
		return CacheCount;
	}
	public int getCDNCount() {
		return CDNCount;
	}
	
	public void setIDCCount(int iDCCount) {
		IDCCount = iDCCount;
	}	
	public void setCacheCount(int cacheCount) {
		CacheCount = cacheCount;
	}	
	public void setCDNCount(int cDNCount) {
		CDNCount = cDNCount;
	}
	//******************************
	public int getCuccCount() {
		return CuccCount;
	}
	public void setCuccCount(int cuccCount) {
		CuccCount = cuccCount;
	}
	public int getCTCount() {
		return CTCount;
	}
	public void setCTCount(int cTCount) {
		CTCount = cTCount;
	}
	public int getGATCount() {
		return GATCount;
	}
	public void setGATCount(int gATCount) {
		GATCount = gATCount;
	}
	public int getForeignCount() {
		return ForeignCount;
	}
	public void setForeignCount(int foreignCount) {
		ForeignCount = foreignCount;
	}
	public int getLocalOperatorCount() {
		return LocalOperatorCount;
	}
	public void setLocalOperatorCount(int localOperatorCount) {
		LocalOperatorCount = localOperatorCount;
	}
	public int getOtherOperatorCount() {
		return OtherOperatorCount;
	}
	public void setOtherOperatorCount(int otherOperatorCount) {
		OtherOperatorCount = otherOperatorCount;
	}
	//******************************
	
	public double getInNetProportion() {
		return InNetProportion;
	}
	public void setInNetProportion(double inNetProportion) {
		InNetProportion = inNetProportion;
	}
	public double getOutNetProportion() {
		return OutNetProportion;
	}
	public void setOutNetProportion(double outNetProportion) {
		OutNetProportion = outNetProportion;
	}
	
	//------------------------------

/*	public String toString() {
		return DmName + ", " + Domain + ", " + Recode + ", " + NetName
				+ ", " + Company + ", " + ClassifyName + ", "
				+ ClassifyParentName + ", " + ProvName + ", " + Cdoe + ", "
				+ CacheConHitCount + ", " + CMCCConHitCount + ", " + DirectConHitCount
				+ ", " + CTTConHitCount + ", " + UserInfo + ", " + IDCCount
				+ ", " + CacheCount + ", " + CDNCount+"\r\n";
	}*/
	
	
	@Override
	public void readFields(DataInput in) throws IOException {
		DmName = in.readUTF();
		Domain = in.readUTF();
		Recode = in.readUTF();
		NetName = in.readUTF();
		Company = in.readUTF();
		ClassifyName = in.readUTF();
		ClassifyParentName = in.readUTF();
		
		ProvName = in.readUTF();
		Cdoe = in.readUTF();
		
		CacheConHitCount=in.readInt();
		CMCCConHitCount=in.readInt();
		DirectConHitCount=in.readInt();
		CTTConHitCount=in.readInt();
		UserInfo=in.readInt();
		
		IDCCount=in.readInt();
		CacheCount=in.readInt();
		CDNCount=in.readInt();
		
		CuccCount =in.readInt();
		CTCount =in.readInt();
		GATCount =in.readInt();
		ForeignCount =in.readInt();
		LocalOperatorCount =in.readInt();
		OtherOperatorCount =in.readInt();
	}

	@Override
	public String toString() {
		return "DmName=" + DmName + ", Domain=" + Domain
				+ ", Recode=" + Recode + ", NetName=" + NetName + ", Company="
				+ Company + ", ClassifyName=" + ClassifyName
				+ ", ClassifyParentName=" + ClassifyParentName + ", ProvName="
				+ ProvName + ", Cdoe=" + Cdoe + ", CacheConHitCount="
				+ CacheConHitCount + ", CMCCConHitCount=" + CMCCConHitCount
				+ ", DirectConHitCount=" + DirectConHitCount
				+ ", CTTConHitCount=" + CTTConHitCount + ", UserInfo="
				+ UserInfo + ", IDCCount=" + IDCCount + ", CacheCount="
				+ CacheCount + ", CDNCount=" + CDNCount + ", CuccCount="
				+ CuccCount + ", CTCount=" + CTCount + ", GATCount=" + GATCount
				+ ", ForeignCount=" + ForeignCount + ", LocalOperatorCount="
				+ LocalOperatorCount + ", OtherOperatorCount="
				+ OtherOperatorCount + ", InNetProportion=" + InNetProportion
				+ ", OutNetProportion=" + OutNetProportion + "\n";
	}


	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(DmName);
		out.writeUTF(Domain);
		out.writeUTF(Recode);
		out.writeUTF(NetName);
		out.writeUTF(Company);
		out.writeUTF(ClassifyName);
		out.writeUTF(ClassifyParentName);		
		out.writeUTF(ProvName);
		out.writeUTF(Cdoe);
		
		out.writeInt(CacheConHitCount);
		out.writeInt(CMCCConHitCount);
		out.writeInt(DirectConHitCount);
		out.writeInt(CTTConHitCount);
		out.writeInt(UserInfo);
		out.writeInt(IDCCount);
		out.writeInt(CacheCount);
		out.writeInt(CDNCount);		
		
		out.writeInt(CuccCount);
		out.writeInt(CTCount);
		out.writeInt(GATCount);
		out.writeInt(ForeignCount);
		out.writeInt(LocalOperatorCount);
		out.writeInt(OtherOperatorCount);
	}

}
