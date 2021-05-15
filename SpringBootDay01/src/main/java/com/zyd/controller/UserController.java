package com.zyd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyd.dao.UserDao;
import com.zyd.entity.User;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
    private UserDao userDao;	
	@RequestMapping("/showUser")
	@ResponseBody
	public String showUser(){
		User user=new User();
		user.setUsername("admin");
		return userDao.selectUser(user).toString();
	}
	@RequestMapping("/toLogin")
	public String toLogin(){
	  return "NewFile" ;
	}
	@RequestMapping("/toLogindo.do")
	public String toLogindo(){
	  return "login/login" ;
	}
	@RequestMapping("/toShadow.do")
	public String toShadow(){
		  return "shadow" ;
	}
}
