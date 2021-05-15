package service;

import java.util.List;

import dao.UserDAO;
import dao.UserDAOImpl;

import entity.User;

public class UserService {
	UserDAO dao = new UserDAOImpl();
	
	public List<User> findAll(int page,int pageSize) throws Exception{
		return dao.findAll(page,pageSize);
	}
	
	public int getTotalPages(int pageSize) throws Exception{
		return dao.getTotalPages(pageSize);
	}
}






