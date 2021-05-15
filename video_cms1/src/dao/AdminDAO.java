package dao;

import java.util.List;

import entity.Admin;
import entity.Role;

public interface AdminDAO {
	List<Admin> findAll(int page,int pageSize) throws Exception;
	int getTotalPages(int pageSize) throws Exception;
	//��ѯ���еĽ�ɫ
	List<Role> findRoles() throws Exception;
	//��ӹ���Ա
	int addAdmin(Admin admin) throws Exception;
	//��ӹ���Ա���ɫ�Ĺ������� admin_id role_id 
	//Map<String,Integer> map
	void addAdminRole(int admin_id,String[] roleIds) throws Exception;
	//ɾ������Ա
	void delAdmin(int id) throws Exception;
	//ɾ������Ա���ɫ�Ĺ�������
	void delAdminRole(int adminId) throws Exception;
	//����id���ҹ���Ա
	Admin findById(int id) throws Exception;
	//����Admin
	void updateAdmin(Admin admin) throws Exception;
	Admin findByUsername(String username) throws Exception;

}





