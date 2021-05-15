package com.ist.t47.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory ssf;
	
	static{
		////加载配置文件
		SqlSessionFactoryBuilder ssfb = 
			new SqlSessionFactoryBuilder();
		ssf = ssfb.build(MyBatisUtil.class.getResourceAsStream("iBatis.xml"));
	}
	//获取SqlSession
	public static SqlSession getSession(){
		return ssf.openSession();
	}
	public static void main(String[] args) {
	
	}
}
