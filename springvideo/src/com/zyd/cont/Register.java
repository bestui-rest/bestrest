package com.zyd.cont;

import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zyd.bean.User;
import com.zyd.service.UserService;

@Controller
@RequestMapping("register")
public class Register {
	@Resource
	private UserService uerservice;
	@RequestMapping("register.do")
	public String toRegister(){
		return "jsp/register";
	}
	@RequestMapping("testemail.do")
	@ResponseBody
		public boolean validEmain(@RequestParam("email")String eamil) throws Exception{
			boolean b=uerservice.testEmail(eamil);
			try{
				Thread.sleep(1000);
			}catch(Exception e){
				throw e;
			}
			return b;
		}
	@RequestMapping("vm.do")
	@ResponseBody
	public String validEmain2(HttpServletRequest request){
	String hj[]={"1","2","3","4","5","6","7","8","9","0"};
	Random rd=new Random();
	StringBuffer sf=new StringBuffer();
	for(int i=0;i<4;i++){
		sf.append(hj[rd.nextInt(10)]);
	}
	request.getSession().setAttribute("vmvalue", sf.toString());
	System.out.println(sf.toString());
	return sf.toString();
	}
	@RequestMapping("testvm.do")
	@ResponseBody
	public boolean validEmain3(HttpServletRequest request,@RequestParam("value")String value){
     String str=(String)request.getSession().getAttribute("vmvalue");
     
		if(str.equals(value))
			return true;
		else
			return false;
	}
	@RequestMapping("submit.do")
	public String validEmain4(User user,HttpServletRequest req){
   req.getSession().setAttribute("user", user);
   req.getSession().setAttribute("vmvalue",null);
   uerservice.inset(user);
     return "redirect:/main/toMain.do";
	}
}
