package dns.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class TxtLine implements Writable {
	private String uip;//用户ip
    private String web;//访问网站
    private String dip;//访问ip

	public TxtLine() {
		 //在反序列化时，反射机制需要调用无参构造方法，所以显式定义了一个无参构造方法
	}	
	
	
	public TxtLine(String uip, String web, String dip) {
		super();
		this.uip = uip;
		this.web = web;
		this.dip = dip;
	}



	public String getUip() {
		return uip;
	}

	public void setUip(String uip) {
		this.uip = uip;
	}
	
	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getDip() {
		return dip;
	}

	public void setDip(String dip) {
		this.dip = dip;
	}

	
	@Override
	public String toString() {
		return "TxtLine [uip=" + uip + ", web=" + web + ", dip=" + dip + "]";
	}


	@Override
	public void readFields(DataInput in) throws IOException {
		uip =in.readUTF();
		web =in.readUTF();
		dip =in.readUTF();		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(uip);
		out.writeUTF(web);
		out.writeUTF(dip);		
	}

}
