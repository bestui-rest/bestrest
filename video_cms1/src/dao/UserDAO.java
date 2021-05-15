package dao;

import java.util.List;

import entity.User;

public interface UserDAO {
	List<User> findAll(int page,int pageSize) throws Exception;
	//获取总页数
	int getTotalPages(int pageSize) throws Exception;
}






