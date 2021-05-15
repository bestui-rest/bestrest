package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestControllE  extends HttpServlet {

	public TestControllE(){
		System.out.println("servletEE สตภýปฏ");
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		File file=new File("D:\\eclipse4.7-workspace\\UTILS\\WebContent\\");
		String[] str=file.list();
		arg1.setCharacterEncoding("utf-8");
		StringBuffer bu=new StringBuffer("");
		for(String strs :str) {
			bu.append("'"+strs+"',");
		}
		bu=new StringBuffer(bu.substring(0, bu.lastIndexOf(",")));

		arg1.getWriter().write(bu.toString());
		arg1.getWriter().close();	
	    System.out.println(" TestTT ok");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("dogetEE ");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
  System.out.println("dopostEE");
	}
	@Override
	public void init() throws ServletException {
System.out.println("servletEE init");
	}
	@Override
	public void init(ServletConfig config) throws ServletException {
System.out.println("servletEE init ServletConfig");

	}
 @Override
   public void destroy() {
	// TODO Auto-generated method stub
	System.out.println("servletEE destroy ");
   }
 
 


 
}
