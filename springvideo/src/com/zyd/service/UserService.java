package com.zyd.service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.zyd.bean.User;
import com.zyd.dao.UserDao;

@Service
public class UserService {
@Resource
private UserDao userdao;
public  boolean  testEmail(String email){
 User user=userdao.findByEmail(email);
 if(user!=null){
	 return true;
 }else{
	 return false;
 }
}
public void inset(User user){
	userdao.insert(user);
	int id=user.getId();

}
public boolean testname(User user,HttpServletRequest req){
	User user02=userdao.findByemAndpwd(user);
	if(user02!=null){
		req.getSession().setAttribute("user", user02);
		return true;
	}else{
		return false;
	}
}


}
