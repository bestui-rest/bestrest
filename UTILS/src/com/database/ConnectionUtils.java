package com.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.bean.UserBean;

public class ConnectionUtils {
private static Connection con=null;
private ConnectionUtils(){
	
}
public ConnectionUtils(Boolean b){
	
}
static {
	try{
		 Properties pp= new Properties();
		 InputStream in=ConnectionUtils.class.getResourceAsStream("db.properties");
		 pp.load(in);
		 String prefix=pp.getProperty("DBTYPE")+"_";
		 Class.forName(pp.getProperty(prefix+"DRIVER"));
		 con=DriverManager.getConnection(pp.getProperty(prefix+"URL"), pp.getProperty(prefix+"USER"), pp.getProperty(prefix+"PWD"));
	}catch(Exception e){
		e.printStackTrace();
	}
}
public static Connection getConnection(){
	return con;
}
public static void main(String[] args){
	System.out.println(ConnectionUtils.getNewOneConection());
}
public static Connection getNewOneConection() {
	 Connection conn=null ;
	 try{
		 Properties pp= new Properties();
		 InputStream in=ConnectionUtils.class.getResourceAsStream("db.properties");
		 pp.load(in);
		 String prefix=pp.getProperty("DBTYPE")+"_";
		 Class.forName(pp.getProperty(prefix+"DRIVER"));
		 conn=DriverManager.getConnection(pp.getProperty(prefix+"URL"), pp.getProperty(prefix+"USER"), pp.getProperty(prefix+"PWD"));
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return conn;
}
public ArrayList<UserBean> getlist() throws SQLException{
	ArrayList list=new ArrayList();
	String sql="SELECT OBJ_NAME,PARTY_ID,ORGANKEY,CREATE_DT,S.ISUSE,S.PARTY_CLASS_CD,S.LIST_TYPE FROM T07_BLACKLIST_DQS S WHERE ROWNUM<=4007";
	PreparedStatement ps =con.prepareStatement(sql);
	ResultSet rs=ps.executeQuery();
	while(rs.next()){
		UserBean u=new UserBean();
		u.setObj_name(rs.getString(1));
		u.setParty_id(rs.getString(2));
		u.setOrgankey(rs.getString(3));
		u.setIsuse(rs.getString(5));
		u.setCreate_dt(rs.getString(4));
		u.setParty_class_cd(rs.getString(6));
		u.setList_type(rs.getString(7));
		list.add(u);
	}
	rs.close();
	ps.close();
	return list;
}
}
