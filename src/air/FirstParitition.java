package air;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class FirstParitition extends Partitioner<KeyPair,Text>{

	@Override
	public int getPartition(KeyPair key, Text value, int num) {//num为reduce的数量
		//2.自定义分区 按照年份分区，年份相同的输出到同一个reduce
		return (key.getYear()*127) % num;
	}

}
