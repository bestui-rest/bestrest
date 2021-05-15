package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestControll  extends HttpServlet {
  // father 	GenericServlet 
	public TestControll(){
		System.out.println("servlet สตภýปฏ");
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service");
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doget ");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
  System.out.println("dopost");
	}
	@Override
	public void init() throws ServletException {
System.out.println("servlet init");
	}
 @Override
public void destroy() {
	// TODO Auto-generated method stub
	System.out.println("servlet destroy ");
}
}
