package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
//  -Xmx512m -Xss2m	-Xms500m  -XX:PermSize=128m -XX:MaxPermSize=120m -XX:+PrintGCDetails 
//方法区默认21M  堆默认64M
public class Tmp {
public static void main(String[] args) {
	System.out.println(Arrays.toString(args));
	try {
	      long total1 = Runtime.getRuntime().totalMemory();
	      long m2 = Runtime.getRuntime().freeMemory();
	      long max=Runtime.getRuntime().maxMemory();
	       System.out.println("111:" +total1/1024/1024);
	       System.out.println("1111:" +m2/1024/1024);
	       System.out.println("1111:" +max/1024/1024);
		URL url=new URL("https://www.baidu.com");
		URLConnection urlconnection=url.openConnection();
		byte[] array_tmp=new byte[1024];
		total1 = Runtime.getRuntime().totalMemory();
		m2 = Runtime.getRuntime().freeMemory()  ;
		   System.out.println("111:" +total1/1024/1024);
	       System.out.println("1111:" +m2/1024/1024);
		InputStream in=urlconnection.getInputStream();
		in.read(array_tmp);
	System.out.println(new String(array_tmp,"UTF-8"));
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
