package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import util.DBUtil;

import entity.User;

public class UserDAOImpl implements UserDAO{

	public List<User> findAll(int page,int pageSize) throws Exception {
		List<User> list = new ArrayList<User>();
		Connection con = DBUtil.getConnection();
		String sql = "select * from user limit ?,?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,(page-1)*pageSize);
		ps.setInt(2,pageSize);
		ResultSet rs = ps.executeQuery();
		User user = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		while(rs.next()){
			user = new User();
			user.setId(rs.getInt("id"));
			user.setUsername(rs.getString("username"));
			user.setEmail(rs.getString("email"));
			user.setRegisttime(rs.getTimestamp("registtime"));
			Date registtime = rs.getTimestamp("registtime");
			if(registtime==null){
				user.setRtime("");
			}else{
				user.setRtime(sf.format(registtime));
			}
			//registtime-->simpleDateFormat
			//setRtime("")
			list.add(user);
		}
		DBUtil.close(con);
		return list;
	}

	public int getTotalPages(int pageSize) throws Exception {
		Connection con  = DBUtil.getConnection();
		String sql = "select count(*) from user";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		int rows = 0;
		if(rs.next()){
			rows = rs.getInt(1);
		}
		DBUtil.close(con);
		if(rows%pageSize==0){
			return rows/pageSize;
		}else{
			return rows/pageSize+1;
		}
	}

}



