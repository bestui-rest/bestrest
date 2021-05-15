package com.database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

public class ConnPool {
	private static BasicDataSource dataSource = new BasicDataSource();
	static {
		try{
			 Properties pp= new Properties();
			 InputStream in=ConnectionUtils.class.getResourceAsStream("db.properties");
			 pp.load(in);
			   String prefix=pp.getProperty("DBTYPE")+"_";
				//设置基本项
				dataSource.setDriverClassName(pp.getProperty(prefix+"DRIVER"));
				dataSource.setUrl(pp.getProperty(prefix+"URL"));
				dataSource.setUsername(pp.getProperty(prefix+"USER"));
				dataSource.setPassword(pp.getProperty(prefix+"PWD"));		
				dataSource.setInitialSize(5);
				// * 最大活动数
				dataSource.setMaxActive(10);

		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
public /*synchronized */ static Connection getConnection() {
	Connection conn=null;
	try {
		conn= dataSource.getConnection();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return conn;
	
}

public static void main(String[] args) {
	for (int i=0;i<20;i++){
	try {
		System.out.println("第"+i+"个");
		Connection	conn=dataSource.getConnection();
		conn.close();  //放回连接池
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
}
