package com.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends HttpServlet {
@Override
protected void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	PrintWriter out = response.getWriter();
	String uri = request.getRequestURI();
	System.out.println(uri);
	String action = uri.substring(uri.lastIndexOf("/"),
			uri.lastIndexOf("."));
	System.out.println(action);
	if("/ajaxtest".equals(action)){
		String requesettype=request.getHeader("X-Requested-with");
		System.out.println(requesettype);
		try {
			System.out.println("begin");
			Thread.sleep(1000*10);
			System.out.println("");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.getWriter().print("sdfsdfsdfsd");
		response.getWriter().flush();
		response.getWriter().close();
	}
	if("/test".equals(action)){
		try {
			System.out.println("begin");
			Thread.sleep(1000*20);
			System.out.println("end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("MyJspsss.jsp").forward(request, response);
	}
	
	else if(action.endsWith("login")){
		//arg0.setCharacterEncoding("utf-8");
		//arg1.setContentType("text/html;charset=utf-8");
	 byte []g=request.getParameter("name1").getBytes("ISO-8859-1");
	 String gg=new String(g,"UTF-8");
	 System.out.println(gg);
	 System.out.println(request.getParameter("name2"));
	 response.getWriter().println(gg);
 }else if(action.endsWith("glgm")){      
	 response.setContentType("multipart/form-data");
     String name= URLEncoder.encode("桂林国民下发文件.zip", "UTF-8");
     response.setHeader("Content-Disposition", "attachment;filename="+name); 
     OutputStream os=response.getOutputStream();
     byte[] bt=new byte[1024*1024];
     FileInputStream fi=new FileInputStream("C:\\Users\\Administrator\\Desktop\\studing\\下发文件\\桂林国民下发文件.zip");
     int g;
     while((g=fi.read(bt))!=-1){
            	 os.write(bt, 0, g);
      }
    os.flush();   
    os.close();
 } 
	
}
}
