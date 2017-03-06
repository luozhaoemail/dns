package air;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

public class KeyPair implements WritableComparable<KeyPair> {
	//1.自定义排序  使对象具有比较性
	private int year;
	private int hot;
			
	/*public KeyPair(int year, int hot) {		
		this.year = year;
		this.hot = hot;
	}		序列化的类必须带有一个无参的构造函数,因为编译器不会自动识别构造函数的参数
	*/
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getHot() {
		return hot;
	}
	public void setHot(int hot) {
		this.hot = hot;
	}

	@Override
	public String toString() {
		//return "KeyPair [year=" + year + ", hot=" + hot + "]";
		return year+"\t"+hot;
	}

	@Override
	public void readFields(DataInput in) throws IOException {
		//hadoop使用rpc协议传输二进制数据,从二进制中读去对象，反序列化 
		this.year = in.readInt();
		this.hot = in.readInt();
	}
	
	@Override
	public void write(DataOutput out) throws IOException {
		// 序列化，把对象写到二进制流数据
		out.writeInt(this.year);
		out.writeInt(this.hot);
	}
	
	@Override
	public int compareTo(KeyPair o) {  //先比较年份，再比较温度
		int res = Integer.compare(this.year, o.getYear());
		if(res!=0)//不相等
			return res;	
		return Integer.compare(this.hot, o.getHot());//年份相等时再比较温度
	}
	

	public int hashCode(){
		return new Integer(year+hot).hashCode();
	}

}
