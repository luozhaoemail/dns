package dns.bean;

public class Result {
	private String DataTime;
	private String DmName;
	private String Domain;
	private String Recode;
	private String Company ;
	private String GroupCompany;
	private String NetName;
	private String TcpOpenInfo ;
	private String UdpOpenInfo ;
	private String ServiceType ;
	private String ClassifyParentName;
	private String ClassifyName;
	private String UserInfo;
	private String ProvinceIds;
	private String DNSCount;
	private String OutNetCount;
	private String InNetCount;
	private String CacheConHitCount;
	private String CMCCConHitCount;
	private String DirectConHitCount;
	private String CTTConHitCount ;
	private String IDCCount ;
	private String CacheCount ;
	private String CDNCount ;
	private String CuccCount ;
	private String CTCount ;
	private String GATCount ;
	private String ForeignCount ;
	private String LocalOperatorCount ;
	private String OtherOperatorCount ;
	private String InNetProportion ;
	private String OutNetProportion ;
	private String CacheConHitProportion ;
	private String CacheConHit4InNetProportion ;
	private String CacheConHit4DNSProportion ;
	private String CMCCConHitProportion ;
	private String CMCCConHit4InNetProportion ;
	private String CMCCConHit4DNSProportion ;
	private String CTTConHitProportion ;
	private String CTTConHit4InNetProportion;
	private String CTTConHit4DNSProportion;
	private String DirectConHitProportion ;
	private String DirectConHit4InNetProportion ;
	private String DirectConHit4DNSProportion ;
	private String InNetCount4InNetProportion;
	private String InNetCount4DNSProportion ;
	private String CacheZX4ServiceCount;
	private String CacheZX4Service;
	private String CacheHH4ServiceCount;
	private String CacheHH4Service;
	private String CacheWS4ServiceCount;
	private String CacheWS4Service ;
	private String CacheLX4ServiceCount;
	private String CacheLX4Service;
	private String CacheHW4ServiceCount;
	private String CacheHW4Service;
	private String CDNWS4DmSUM;
	private String CDNDL4DmSUM;
	private String CDNLX4DmSUM;
	private String CDNLC4DmSUM;
	private String CDNCQ4DmSUM;
	private String LocalSystemCount4IDCTrad;
	private String LocalSystemCount4IDCLoadIn;
	
	public Result(){}

	public Result(String dmName, String domain, String recode, String netName, String company,
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

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	public String getGroupCompany() {
		return GroupCompany;
	}

	public void setGroupCompany(String groupCompany) {
		GroupCompany = groupCompany;
	}

	public String getNetName() {
		return NetName;
	}

	public void setNetName(String netName) {
		NetName = netName;
	}

	public String getTcpOpenInfo() {
		return TcpOpenInfo;
	}

	public void setTcpOpenInfo(String tcpOpenInfo) {
		TcpOpenInfo = tcpOpenInfo;
	}

	public String getUdpOpenInfo() {
		return UdpOpenInfo;
	}

	public void setUdpOpenInfo(String udpOpenInfo) {
		UdpOpenInfo = udpOpenInfo;
	}

	public String getServiceType() {
		return ServiceType;
	}

	public void setServiceType(String serviceType) {
		ServiceType = serviceType;
	}

	public String getClassifyParentName() {
		return ClassifyParentName;
	}

	public void setClassifyParentName(String classifyParentName) {
		ClassifyParentName = classifyParentName;
	}

	public String getClassifyName() {
		return ClassifyName;
	}

	public void setClassifyName(String classifyName) {
		ClassifyName = classifyName;
	}

	public String getUserInfo() {
		return UserInfo;
	}

	public void setUserInfo(String userInfo) {
		UserInfo = userInfo;
	}

	public String getProvinceIds() {
		return ProvinceIds;
	}

	public void setProvinceIds(String provinceIds) {
		ProvinceIds = provinceIds;
	}

	public String getDNSCount() {
		return DNSCount;
	}

	public void setDNSCount(String dNSCount) {
		DNSCount = dNSCount;
	}

	public String getOutNetCount() {
		return OutNetCount;
	}

	public void setOutNetCount(String outNetCount) {
		OutNetCount = outNetCount;
	}

	public String getInNetCount() {
		return InNetCount;
	}

	public void setInNetCount(String inNetCount) {
		InNetCount = inNetCount;
	}

	public String getCacheConHitCount() {
		return CacheConHitCount;
	}

	public void setCacheConHitCount(String cacheConHitCount) {
		CacheConHitCount = cacheConHitCount;
	}

	public String getCMCCConHitCount() {
		return CMCCConHitCount;
	}

	public void setCMCCConHitCount(String cMCCConHitCount) {
		CMCCConHitCount = cMCCConHitCount;
	}

	public String getDirectConHitCount() {
		return DirectConHitCount;
	}

	public void setDirectConHitCount(String directConHitCount) {
		DirectConHitCount = directConHitCount;
	}

	public String getCTTConHitCount() {
		return CTTConHitCount;
	}

	public void setCTTConHitCount(String cTTConHitCount) {
		CTTConHitCount = cTTConHitCount;
	}

	public String getIDCCount() {
		return IDCCount;
	}

	public void setIDCCount(String iDCCount) {
		IDCCount = iDCCount;
	}

	public String getCacheCount() {
		return CacheCount;
	}

	public void setCacheCount(String cacheCount) {
		CacheCount = cacheCount;
	}

	public String getCDNCount() {
		return CDNCount;
	}

	public void setCDNCount(String cDNCount) {
		CDNCount = cDNCount;
	}

	public String getCuccCount() {
		return CuccCount;
	}

	public void setCuccCount(String cuccCount) {
		CuccCount = cuccCount;
	}

	public String getCTCount() {
		return CTCount;
	}

	public void setCTCount(String cTCount) {
		CTCount = cTCount;
	}

	public String getGATCount() {
		return GATCount;
	}

	public void setGATCount(String gATCount) {
		GATCount = gATCount;
	}

	public String getForeignCount() {
		return ForeignCount;
	}

	public void setForeignCount(String foreignCount) {
		ForeignCount = foreignCount;
	}

	public String getLocalOperatorCount() {
		return LocalOperatorCount;
	}

	public void setLocalOperatorCount(String localOperatorCount) {
		LocalOperatorCount = localOperatorCount;
	}

	public String getOtherOperatorCount() {
		return OtherOperatorCount;
	}

	public void setOtherOperatorCount(String otherOperatorCount) {
		OtherOperatorCount = otherOperatorCount;
	}

	public String getInNetProportion() {
		return InNetProportion;
	}

	public void setInNetProportion(String inNetProportion) {
		InNetProportion = inNetProportion;
	}

	public String getOutNetProportion() {
		return OutNetProportion;
	}

	public void setOutNetProportion(String outNetProportion) {
		OutNetProportion = outNetProportion;
	}

	public String getCacheConHitProportion() {
		return CacheConHitProportion;
	}

	public void setCacheConHitProportion(String cacheConHitProportion) {
		CacheConHitProportion = cacheConHitProportion;
	}

	public String getCacheConHit4InNetProportion() {
		return CacheConHit4InNetProportion;
	}

	public void setCacheConHit4InNetProportion(String cacheConHit4InNetProportion) {
		CacheConHit4InNetProportion = cacheConHit4InNetProportion;
	}

	public String getCacheConHit4DNSProportion() {
		return CacheConHit4DNSProportion;
	}

	public void setCacheConHit4DNSProportion(String cacheConHit4DNSProportion) {
		CacheConHit4DNSProportion = cacheConHit4DNSProportion;
	}

	public String getCMCCConHitProportion() {
		return CMCCConHitProportion;
	}

	public void setCMCCConHitProportion(String cMCCConHitProportion) {
		CMCCConHitProportion = cMCCConHitProportion;
	}

	public String getCMCCConHit4InNetProportion() {
		return CMCCConHit4InNetProportion;
	}

	public void setCMCCConHit4InNetProportion(String cMCCConHit4InNetProportion) {
		CMCCConHit4InNetProportion = cMCCConHit4InNetProportion;
	}

	public String getCMCCConHit4DNSProportion() {
		return CMCCConHit4DNSProportion;
	}

	public void setCMCCConHit4DNSProportion(String cMCCConHit4DNSProportion) {
		CMCCConHit4DNSProportion = cMCCConHit4DNSProportion;
	}

	public String getCTTConHitProportion() {
		return CTTConHitProportion;
	}

	public void setCTTConHitProportion(String cTTConHitProportion) {
		CTTConHitProportion = cTTConHitProportion;
	}

	public String getCTTConHit4InNetProportion() {
		return CTTConHit4InNetProportion;
	}

	public void setCTTConHit4InNetProportion(String cTTConHit4InNetProportion) {
		CTTConHit4InNetProportion = cTTConHit4InNetProportion;
	}

	public String getCTTConHit4DNSProportion() {
		return CTTConHit4DNSProportion;
	}

	public void setCTTConHit4DNSProportion(String cTTConHit4DNSProportion) {
		CTTConHit4DNSProportion = cTTConHit4DNSProportion;
	}

	public String getDirectConHitProportion() {
		return DirectConHitProportion;
	}

	public void setDirectConHitProportion(String directConHitProportion) {
		DirectConHitProportion = directConHitProportion;
	}

	public String getDirectConHit4InNetProportion() {
		return DirectConHit4InNetProportion;
	}

	public void setDirectConHit4InNetProportion(String directConHit4InNetProportion) {
		DirectConHit4InNetProportion = directConHit4InNetProportion;
	}

	public String getDirectConHit4DNSProportion() {
		return DirectConHit4DNSProportion;
	}

	public void setDirectConHit4DNSProportion(String directConHit4DNSProportion) {
		DirectConHit4DNSProportion = directConHit4DNSProportion;
	}

	public String getInNetCount4InNetProportion() {
		return InNetCount4InNetProportion;
	}

	public void setInNetCount4InNetProportion(String inNetCount4InNetProportion) {
		InNetCount4InNetProportion = inNetCount4InNetProportion;
	}

	public String getInNetCount4DNSProportion() {
		return InNetCount4DNSProportion;
	}

	public void setInNetCount4DNSProportion(String inNetCount4DNSProportion) {
		InNetCount4DNSProportion = inNetCount4DNSProportion;
	}

	public String getCacheZX4ServiceCount() {
		return CacheZX4ServiceCount;
	}

	public void setCacheZX4ServiceCount(String cacheZX4ServiceCount) {
		CacheZX4ServiceCount = cacheZX4ServiceCount;
	}

	public String getCacheZX4Service() {
		return CacheZX4Service;
	}

	public void setCacheZX4Service(String cacheZX4Service) {
		CacheZX4Service = cacheZX4Service;
	}

	public String getCacheHH4ServiceCount() {
		return CacheHH4ServiceCount;
	}

	public void setCacheHH4ServiceCount(String cacheHH4ServiceCount) {
		CacheHH4ServiceCount = cacheHH4ServiceCount;
	}

	public String getCacheHH4Service() {
		return CacheHH4Service;
	}

	public void setCacheHH4Service(String cacheHH4Service) {
		CacheHH4Service = cacheHH4Service;
	}

	public String getCacheWS4ServiceCount() {
		return CacheWS4ServiceCount;
	}

	public void setCacheWS4ServiceCount(String cacheWS4ServiceCount) {
		CacheWS4ServiceCount = cacheWS4ServiceCount;
	}

	public String getCacheWS4Service() {
		return CacheWS4Service;
	}

	public void setCacheWS4Service(String cacheWS4Service) {
		CacheWS4Service = cacheWS4Service;
	}

	public String getCacheLX4ServiceCount() {
		return CacheLX4ServiceCount;
	}

	public void setCacheLX4ServiceCount(String cacheLX4ServiceCount) {
		CacheLX4ServiceCount = cacheLX4ServiceCount;
	}

	public String getCacheLX4Service() {
		return CacheLX4Service;
	}

	public void setCacheLX4Service(String cacheLX4Service) {
		CacheLX4Service = cacheLX4Service;
	}

	public String getCacheHW4ServiceCount() {
		return CacheHW4ServiceCount;
	}

	public void setCacheHW4ServiceCount(String cacheHW4ServiceCount) {
		CacheHW4ServiceCount = cacheHW4ServiceCount;
	}

	public String getCacheHW4Service() {
		return CacheHW4Service;
	}

	public void setCacheHW4Service(String cacheHW4Service) {
		CacheHW4Service = cacheHW4Service;
	}

	public String getCDNWS4DmSUM() {
		return CDNWS4DmSUM;
	}

	public void setCDNWS4DmSUM(String cDNWS4DmSUM) {
		CDNWS4DmSUM = cDNWS4DmSUM;
	}

	public String getCDNDL4DmSUM() {
		return CDNDL4DmSUM;
	}

	public void setCDNDL4DmSUM(String cDNDL4DmSUM) {
		CDNDL4DmSUM = cDNDL4DmSUM;
	}

	public String getCDNLX4DmSUM() {
		return CDNLX4DmSUM;
	}

	public void setCDNLX4DmSUM(String cDNLX4DmSUM) {
		CDNLX4DmSUM = cDNLX4DmSUM;
	}

	public String getCDNLC4DmSUM() {
		return CDNLC4DmSUM;
	}

	public void setCDNLC4DmSUM(String cDNLC4DmSUM) {
		CDNLC4DmSUM = cDNLC4DmSUM;
	}

	public String getCDNCQ4DmSUM() {
		return CDNCQ4DmSUM;
	}

	public void setCDNCQ4DmSUM(String cDNCQ4DmSUM) {
		CDNCQ4DmSUM = cDNCQ4DmSUM;
	}

	public String getLocalSystemCount4IDCTrad() {
		return LocalSystemCount4IDCTrad;
	}

	public void setLocalSystemCount4IDCTrad(String localSystemCount4IDCTrad) {
		LocalSystemCount4IDCTrad = localSystemCount4IDCTrad;
	}

	public String getLocalSystemCount4IDCLoadIn() {
		return LocalSystemCount4IDCLoadIn;
	}

	public void setLocalSystemCount4IDCLoadIn(String localSystemCount4IDCLoadIn) {
		LocalSystemCount4IDCLoadIn = localSystemCount4IDCLoadIn;
	}

	@Override
	public String toString() {
		return "Result [DataTime=" + DataTime + ", UserInfo=" + UserInfo + ", DmName=" + DmName + ", Domain=" + Domain
				+ ", Recode=" + Recode + ", Company=" + Company + ", GroupCompany=" + GroupCompany + ", NetName="
				+ NetName + ", TcpOpenInfo=" + TcpOpenInfo + ", UdpOpenInfo=" + UdpOpenInfo + ", ServiceType="
				+ ServiceType + ", ClassifyParentName=" + ClassifyParentName + ", ClassifyName=" + ClassifyName
				+ ", ProvinceIds=" + ProvinceIds + ", DNSCount=" + DNSCount + ", OutNetCount=" + OutNetCount
				+ ", InNetCount=" + InNetCount + ", CacheConHitCount=" + CacheConHitCount + ", CMCCConHitCount="
				+ CMCCConHitCount + ", DirectConHitCount=" + DirectConHitCount + ", CTTConHitCount=" + CTTConHitCount
				+ ", IDCCount=" + IDCCount + ", CacheCount=" + CacheCount + ", CDNCount=" + CDNCount + ", CuccCount="
				+ CuccCount + ", CTCount=" + CTCount + ", GATCount=" + GATCount + ", ForeignCount=" + ForeignCount
				+ ", LocalOperatorCount=" + LocalOperatorCount + ", OtherOperatorCount=" + OtherOperatorCount
				+ ", InNetProportion=" + InNetProportion + ", OutNetProportion=" + OutNetProportion
				+ ", CacheConHitProportion=" + CacheConHitProportion + ", CacheConHit4InNetProportion="
				+ CacheConHit4InNetProportion + ", CacheConHit4DNSProportion=" + CacheConHit4DNSProportion
				+ ", CMCCConHitProportion=" + CMCCConHitProportion + ", CMCCConHit4InNetProportion="
				+ CMCCConHit4InNetProportion + ", CMCCConHit4DNSProportion=" + CMCCConHit4DNSProportion
				+ ", CTTConHitProportion=" + CTTConHitProportion + ", CTTConHit4InNetProportion="
				+ CTTConHit4InNetProportion + ", CTTConHit4DNSProportion=" + CTTConHit4DNSProportion
				+ ", DirectConHitProportion=" + DirectConHitProportion + ", DirectConHit4InNetProportion="
				+ DirectConHit4InNetProportion + ", DirectConHit4DNSProportion=" + DirectConHit4DNSProportion
				+ ", InNetCount4InNetProportion=" + InNetCount4InNetProportion + ", InNetCount4DNSProportion="
				+ InNetCount4DNSProportion + ", CacheZX4ServiceCount=" + CacheZX4ServiceCount + ", CacheZX4Service="
				+ CacheZX4Service + ", CacheHH4ServiceCount=" + CacheHH4ServiceCount + ", CacheHH4Service="
				+ CacheHH4Service + ", CacheWS4ServiceCount=" + CacheWS4ServiceCount + ", CacheWS4Service="
				+ CacheWS4Service + ", CacheLX4ServiceCount=" + CacheLX4ServiceCount + ", CacheLX4Service="
				+ CacheLX4Service + ", CacheHW4ServiceCount=" + CacheHW4ServiceCount + ", CacheHW4Service="
				+ CacheHW4Service + ", CDNWS4DmSUM=" + CDNWS4DmSUM + ", CDNDL4DmSUM=" + CDNDL4DmSUM + ", CDNLX4DmSUM="
				+ CDNLX4DmSUM + ", CDNLC4DmSUM=" + CDNLC4DmSUM + ", CDNCQ4DmSUM=" + CDNCQ4DmSUM
				+ ", LocalSystemCount4IDCTrad=" + LocalSystemCount4IDCTrad + ", LocalSystemCount4IDCLoadIn="
				+ LocalSystemCount4IDCLoadIn + "]";
	}
		
	
}
