package com.zyd.cont;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zyd.bean.User;
import com.zyd.service.UserService;

@Controller
@RequestMapping("/login")
public class Login {
	@Resource
	private UserService userservice;
@RequestMapping("tologin.do")
public String toLogin(){
	return "jsp/login";
}
@RequestMapping("tonull.do")
public String toNull(HttpServletRequest req){
	req.getSession().setAttribute("user", null);
	return "redirect:/main/toMain.do";
}
@RequestMapping("login.do")
public String Loginf(HttpServletRequest req,User user){
	if(userservice.testname(user,req)){
		return "redirect:/main/toMain.do";
	}
	else{
		req.getSession().setAttribute("mess", "用户名或密码错误");
		return "redirect:/login/tologin.do";
	}
	
}
}
