package model;

import java.util.ArrayList;

import org.junit.Test;


public class Test2 {
	
	@Test
	public void test() {//邮箱校验
		String ip = "223.104.24.34";
				
		String reg ="(?:(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\.){3}(?:[01]?\\d{1,2}|2[0-4]\\d|25[0-5])\\b";  //精确
		System.out.println("dizhi："+ip.matches(reg));
		 
	}

	@Test
	public void test2() {//邮箱校验
		 ArrayList a= new ArrayList();
		 a.add("aaa");
		 a.add("kkkk");
		 
		 //ArrayList al= new ArrayList();
		 ArrayList al;
		 al=a;
		 System.out.println(al);
		 
		
		 
	}
}
/**
[abc] a、b 或 c（简单类） 
[^abc] 任何字符，除了 a、b 或 c（否定） 
[a-zA-Z] a 到 z 或 A 到 Z，两头的字母包括在内（范围 
. 任何字符（与行结束符可能匹配也可能不匹配） 
\d 数字：[0-9]  注意：\d是一个整体，要加上转义字符：\\d
\D 非数字： [^0-9] 
\s 空白字符：[ \t\n\x0B\f\r] 
\S 非空白字符：[^\s] 
\w 单词字符：[a-zA-Z_0-9] 
\W 非单词字符：[^\w] 
X? 表示X出现一次或一次也没有 
X* 表示X出现零次或多次 
X+ 表示X出现一次或多次 
X{n} 表示X出现恰好 n 次 
X{n,} 表示X出现至少 n 次 
X{n,m} 表示X出现至少 n 次，但是不超过 m 次 
\b 单词边界 
\B 非单词边界 


 */