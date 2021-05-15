package dao;

import java.util.List;

import entity.Admin;
import entity.Role;

public interface AdminDAO {
	List<Admin> findAll(int page,int pageSize) throws Exception;
	int getTotalPages(int pageSize) throws Exception;
	//查询所有的角色
	List<Role> findRoles() throws Exception;
	//添加管理员
	int addAdmin(Admin admin) throws Exception;
	//添加管理员与角色的关联数据 admin_id role_id 
	//Map<String,Integer> map
	void addAdminRole(int admin_id,String[] roleIds) throws Exception;
	//删除管理员
	void delAdmin(int id) throws Exception;
	//删除管理员与角色的关联数据
	void delAdminRole(int adminId) throws Exception;
	//根据id查找管理员
	Admin findById(int id) throws Exception;
	//更新Admin
	void updateAdmin(Admin admin) throws Exception;
	Admin findByUsername(String username) throws Exception;

}





