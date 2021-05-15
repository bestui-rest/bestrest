package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

import entity.Admin;
import entity.Role;

public class AdminDAOImpl implements AdminDAO {

	public List<Admin> findAll(int page,int pageSize) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select * from admin limit ?,?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,(page-1)*pageSize);
		ps.setInt(2,pageSize);
		ResultSet rs = ps.executeQuery();
		List<Admin> list = getAdmins(rs, con);
		DBUtil.close(con);
		return list;
	}

	public List<Admin> getAdmins(ResultSet rs,Connection con) 
		throws SQLException{
		List<Admin> list = new ArrayList<Admin>();
		Admin admin = null;
		String sql2 = "";
		PreparedStatement ps2 = null;
		ResultSet rs2 = null;
		while(rs.next()){
			admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setUsername(rs.getString("username"));
			admin.setPwd(rs.getString("pwd"));
			admin.setName(rs.getString("name"));
			sql2 = "select r.* from admin_role ar join " +
					"role r on ar.role_id=r.id where " +
					"ar.admin_id=?";
			ps2 = con.prepareStatement(sql2);
			ps2.setInt(1,rs.getInt("id"));
			rs2 = ps2.executeQuery();
			List<Role> roles = new ArrayList<Role>();
			Role role = null;
			while(rs2.next()){
				role = new Role();
				role.setId(rs2.getInt("id"));
				role.setName(rs2.getString("name"));
				roles.add(role);
			}
			admin.setRoles(roles);
			list.add(admin);
		}
		return list;
	}

	public int getTotalPages(int pageSize) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select count(*) from admin";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		int rows = rs.getInt(1);
		DBUtil.close(con);
		if(rows%pageSize==0){
			return rows/pageSize;
		}else{
			return rows/pageSize+1;
		}
	}

	public List<Role> findRoles() throws Exception {
		List<Role> list = new ArrayList<Role>();
		Connection con = DBUtil.getConnection();
		String sql = "select * from role";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		Role role = null;
		while(rs.next()){
			role = new Role();
			role.setId(rs.getInt("id"));
			role.setName(rs.getString("name"));
			list.add(role);
		}
		DBUtil.close(con);
		return list;
	}

	public int addAdmin(Admin admin) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "insert into admin values (null,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
		ps.setString(1,admin.getUsername());
		ps.setString(2,admin.getPwd());
		ps.setString(3,admin.getName());
		ps.executeUpdate();
		ResultSet rs = ps.getGeneratedKeys();
		rs.next();
		int adminId = rs.getInt(1);
		DBUtil.close(con);
		return adminId;
	}

	public void addAdminRole(int adminId, String[] roleIds) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "insert into admin_role values (null,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		for(String roleId:roleIds){
			ps.setInt(1, adminId);
			ps.setInt(2,Integer.parseInt(roleId));
			ps.executeUpdate();
		}
		DBUtil.close(con);
		
	}

	public void delAdmin(int id) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "delete from admin where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		DBUtil.close(con);
	}

	public void delAdminRole(int adminId) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "delete from admin_role where admin_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,adminId);
		ps.executeUpdate();
	}

	public Admin findById(int id) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select * from admin where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		ResultSet rs = ps.executeQuery();
		List<Admin> list = getAdmins(rs, con);
		Admin admin = null;
		if(list.size()>0){
			admin = list.get(0);
		}
		DBUtil.close(con);
		return admin;
	}

	public void updateAdmin(Admin admin) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "update admin set username=?," +
				"name=?,pwd=? where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,admin.getUsername());
		ps.setString(2,admin.getName());
		ps.setString(3,admin.getPwd());
		ps.setInt(4,admin.getId());
		ps.executeUpdate();
		DBUtil.close(con);
	}

	public Admin findByUsername(String username) throws Exception {
		Connection con = DBUtil.getConnection();
		String sql = "select * from admin where username=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,username);
		ResultSet rs = ps.executeQuery();
		Admin admin = null;
		if(rs.next()){
			admin = new Admin();
			admin.setId(rs.getInt("id"));
			admin.setUsername(username);
			admin.setName(rs.getString("name"));
			admin.setPwd(rs.getString("pwd"));
		}
		DBUtil.close(con);
		return admin;
	}
	
	
	
}




