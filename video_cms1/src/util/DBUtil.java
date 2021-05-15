package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	//获取连接
	public static Connection getConnection(){
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
	    	"jdbc:mysql://localhost:3306/video_cms","root","123456");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//关闭连接
	public static void close(Connection con){
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getConnection());
	}
	
}



