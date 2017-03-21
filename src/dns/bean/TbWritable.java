package dns.bean;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.lib.db.DBWritable;

public class TbWritable implements Writable, DBWritable{
	private String ip;
	private String prov;
	private String com;	
	
	public TbWritable(){}
			
	public TbWritable(String ip, String prov, String com) {
		super();
		this.ip = ip;
		this.prov = prov;
		this.com = com;
	}

	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getProv() {
		return prov;
	}
	public void setProv(String prov) {
		this.prov = prov;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}



	@Override
	public void readFields(ResultSet rs) throws SQLException {
		ip = rs.getString(1);
		prov = rs.getString(2);
		com = rs.getString(3);
	}

	@Override
	public void write(PreparedStatement pst) throws SQLException {
		pst.setString(1,ip);
		pst.setString(2,prov);
		pst.setString(3,com);
		
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		ip =in.readUTF();
		prov =in.readUTF();
		com =in.readUTF();
		
	}

	@Override
	public void write(DataOutput out) throws IOException {
		out.writeUTF(ip);
		out.writeUTF(prov);
		out.writeUTF(com);
	}

}
