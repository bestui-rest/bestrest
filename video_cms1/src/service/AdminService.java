package service;

import java.util.List;

import dao.AdminDAO;
import dao.AdminDAOImpl;

import entity.Admin;
import entity.Role;

public class AdminService {
	AdminDAO dao = new AdminDAOImpl();
	//获取所有admin数据
	public List<Admin> findAll(int page,int pageSize) throws Exception{
		return dao.findAll(page,pageSize);
	}
	
	public int getTotalPages(int pageSize) throws Exception{
		return dao.getTotalPages(pageSize);
	}
	
	public List<Role> findRoles() throws Exception{
		return dao.findRoles();
	}
	
	public void addAdmin(Admin admin,String[] roleIds) throws Exception{
		int adminId = dao.addAdmin(admin);
		dao.addAdminRole(adminId, roleIds);
	}
	
	public void delAdmin(int id) throws Exception{
		dao.delAdmin(id);
		dao.delAdminRole(id);
	}
	
	public Admin findById(int id) throws Exception{
		return dao.findById(id);
	}
	
	public void updateAdmin(Admin admin,String[] roleIds) throws Exception{
		dao.updateAdmin(admin);
		dao.delAdminRole(admin.getId());
		dao.addAdminRole(admin.getId(), roleIds);
	}
	
	public Admin findByUsername(String username) throws Exception{
		return dao.findByUsername(username);
	}
}





