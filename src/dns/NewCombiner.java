package dns;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import dns.bean.DmResult;

public class NewCombiner extends Reducer<Text,DmResult,Text,DmResult>{

	@Override 
	public void reduce(Text k, Iterable<DmResult> val, Context context)  
            	   throws IOException, InterruptedException {
		int i=0;			
    	for(DmResult iter : val)	   	
    		i++;
    	
    	for(DmResult t : val)	   	
    	{
    		Text tt =new Text(k+";"+i);    		
    		context.write(tt,t);	
    		System.out.println("NewCombiner执行完了++++++++++++++++"+tt.toString());
    		break;
    	}
    	
    	
	}
}
