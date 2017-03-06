package dns;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import dns.bean.DmResult;

public class NewCombiner extends Reducer<Text,DmResult,Text,DmResult>{

	@Override 
	public void reduce(Text k, Iterable<DmResult> val, Context context)  
            	   throws IOException, InterruptedException {
		int i=0;		
		DmResult t =new DmResult();		
    	for(DmResult iter : val)	   	
    	{
    		i++;
    		t= iter;    
    	}
    	
    	if(t.getCacheConHitCount()!=0)
			t.setCacheConHitCount(i);
		else if(t.getCMCCConHitCount()!=0)
			t.setCMCCConHitCount(i);
		else if(t.getDirectConHitCount()!=0)
			t.setDirectConHitCount(i);
		else if(t.getCTTConHitCount()!=0)
			t.setCTTConHitCount(i);	    		
		if(t.getCacheCount()!=0)
			t.setCacheCount(i);
		else if(t.getCDNCount()!=0)
			t.setCDNCount(i);
		else if(t.getIDCCount()!=0)
			t.setIDCCount(i); 
    	
    	context.write(k,t);
    	
	}
}
