
package com.url;
/**
 *   HTTPS
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class URLGet {
public static void main(String[] args) throws IOException, KeyManagementException, NoSuchAlgorithmException {
	URL url =new URL("https://github.com/spring-projects/spring-framework/blob/3.2.x/spring-webmvc/src/main/java/org/springframework/web/servlet/DispatcherServlet.java");
	SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
	sslContext.init(null,null,null);
	SSLContext.setDefault(sslContext);
	URLConnection urlConnection =url.openConnection();
	InputStream in=urlConnection.getInputStream();
	InputStreamReader isr=new InputStreamReader(in);
	BufferedReader bf=new BufferedReader(isr);
	String a;
	while((a=bf.readLine())!=null){
		System.out.println(a);
	}
	isr.close();
	in.close();
	
}

}
