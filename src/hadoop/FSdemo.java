package hadoop;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
/**
 * HDFS基本编程接口练习： org.apache.hadoop.fs定义了抽象的文件系统API
 * 实现在hdfs的一个目录下读取所有文中，查找符合特定字符串的行，并输出到本地文件系统
 * 中，如果输出文件过大，新建文件保存。
 * @author root
 *
 */
public class FSdemo {

	public static void main(String[] args) throws IOException { 
		// input为hdfs:/user/root/input相对路径 ，程序在本地上自动创建/root/hehe目录
		String[] arg = new String[]{"input","/root/hehe","java","20"};
		
		Configuration conf = new Configuration();//通过键值对保存配置参数
		//FileSystem是与文件系统交互的抽象类
		FileSystem hdfs = FileSystem.get(conf);//hdfs 1
		FileSystem local = FileSystem.getLocal(conf);//local FileSystem	    
				
		 //Path类编码文件或目录的路径
		Path indir = new Path(arg[0]);//输入目录 2
		Path outdir = new Path(arg[1]);//输出目录
		int singlenum = Integer.parseInt(arg[3]);//规定每个文件存多少行
		
		//FileStatus类存放文件和目录信息
		FileStatus[] inputFiles = hdfs.listStatus(indir);//获取目录信息 3
		
		FSDataOutputStream out =null;// wraps a OutputStream in a DataOutputStream.
		FSDataInputStream in =null;// wraps a FSInputStream in a DataInputStream and buffers input through a BufferedInputStream.
				
		byte[] buf;//缓存
		
		int filenum =1;//输出文件名从1开始编号
		int linenum=0;//标记查找到符合内容的一行
		
		//命令行模式下用main函数的args[]接收参数
		/*if(args.length!=4){
			System.err.println("输入格式错误<dfs path><local path><match str><singleline>");
			return;
		}*/		
		
		if(local.exists(outdir))
			local.delete(outdir,true);//delete(path,recursive)
		
		for(int i=0; i<inputFiles.length; i++)
		{
			if(inputFiles[i].isDirectory() == true)//只读取第一层目录下的文件，忽略子目录
				continue;
			System.out.println(inputFiles[i].getPath().getName());
			
			in = hdfs.open(inputFiles[i].getPath());// 4
			Scanner scan = new Scanner(in);
			while(scan.hasNext())
			{
				String str = scan.nextLine();
				
				//indexof(substr)返回查找子串的开始位置
				if(str.indexOf(arg[2]) == -1)//如果该行没有匹配字符串，跳过这行
					continue;
				
				linenum++;//查到了就将行标志位置为1					
				if(linenum == 1)//接下来需要新建文件了
				{
					outdir =new Path(arg[1]+File.separator+filenum);
					out = local.create(outdir);
					filenum++;
				}				
				buf =(str+"\n").getBytes();//将匹配的一行写入缓存，再写到文件中去
				out.write(buf,0,buf.length);
				
				if(linenum == singlenum)//如果输出文件过大，新建文件保存。
				{
					out.close();
					linenum = 0;//置0后表示没读，下次循环重新读取
				}
			}//end while
			
			scan.close();
			in.close();
		}//end for
		
		if(out!=null)
			out.close();		
	}//end main

}
