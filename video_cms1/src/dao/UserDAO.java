package dao;

import java.util.List;

import entity.User;

public interface UserDAO {
	List<User> findAll(int page,int pageSize) throws Exception;
	//��ȡ��ҳ��
	int getTotalPages(int pageSize) throws Exception;
}






