package com.zyd.cont;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zyd.bean.User;
@Controller
public class LanJIe extends HandlerInterceptorAdapter {

@Override
public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
	   User user=(User)request.getSession().getAttribute("user");
	   System.out.println("拦截");
	if(user==null){
	 String head=request.getHeader("X-Requested-with");
	 System.out.println(head);
	 if("XMLHttpRequest".equals(head)){
		 response.getWriter().print("ajax");
		 //ajax请求会进入拦截器  但是ajax返回的状态码是302   ajax不会自己进行跳转
	 }else{
       response.sendRedirect(request.getContextPath()+"/login/tologin.do");
	 }
       return false;
	}else{
		return true;
	}
	//return super.preHandle(request, response, handler);
}

}
