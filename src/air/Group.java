package air;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class Group extends WritableComparator {
	
	public Group(){
		super(KeyPair.class,true);//通过构造起来获取参数值
	}
	
	public int compare(WritableComparable a,WritableComparable b){
		
		KeyPair o1 = (KeyPair)a;
		KeyPair o2 = (KeyPair)b;
		//3.自定义分组：年份相同的分到一起
		return  Integer.compare(o1.getYear(), o2.getYear()); //如果返回0,则年份相等
	}
}
