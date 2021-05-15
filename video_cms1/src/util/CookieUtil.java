package util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static final String path = "/video_cms";
	
	//Ìí¼Ócookie
	public static void addCookie(String name,String value,
			int age,HttpServletResponse res) throws UnsupportedEncodingException{
		Cookie c = new Cookie(name,
				URLEncoder.encode(value,"UTF-8"));
		c.setPath(path);
		c.setMaxAge(age);
		res.addCookie(c);
	}
	
	//²éÕÒcookie
	public static String findCookie(String name,
			HttpServletRequest req) throws UnsupportedEncodingException{
		Cookie[] cs = req.getCookies();
		String value = null;
		if(cs!=null){
			for(Cookie c:cs){
				String cname = c.getName();
				if(cname.equals(name)){
					value = URLDecoder.decode(c.getValue(),"UTF-8");
					break;
				}
			}
		}
		return value;
	}
	
	//É¾³ýcookie
	public static void delCookie(String name,
			HttpServletResponse res){
		Cookie c = new Cookie(name,"");
		c.setPath(path);
		c.setMaxAge(0);
		res.addCookie(c);
	}
}




