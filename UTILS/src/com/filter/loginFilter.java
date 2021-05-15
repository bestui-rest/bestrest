package com.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class loginFilter implements Filter{
 
	public loginFilter(){
		System.out.println("loginFilter สตภýปฏ");
	}
	public void destroy() {
		// TODO Auto-generated method stub
		
		System.out.println("filter destroy");
	}
	public String asciToU8(String name) throws UnsupportedEncodingException{
		
		String str=new String(name.getBytes("ISO-8859-1"),"UTF-8");
		return str;
	}
		 
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("do filter ");
		//HttpServletRequestWrapper 
		arg0=new Request((HttpServletRequest)arg0);
		//arg0.getParameterValues(arg0)
		String str=arg0.getParameter("username");
	  // System.out.println(asciToU8(str));
	   System.out.println(str);
		arg2.doFilter(arg0, arg1);
		System.out.println("do filter after ");
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("filter init");
	}
  
	 class Request extends HttpServletRequestWrapper {
        
		public Request(HttpServletRequest request) {
			super(request);
			// TODO Auto-generated constructor stub
		}
		@Override
		public String getParameter(String name) {
			// TODO Auto-generated method stub
			String str=super.getParameter(name);
			try {
				return asciToU8(str);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
    @Override 
    public String[] getParameterValues(String name){
    	String arg[]=super.getParameterValues(name);
    	
    	
    	
    	return new String[]{};
    }
	 }
}
