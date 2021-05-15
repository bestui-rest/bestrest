package com.ist.t47.util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MyBatisUtil {
	
	private static SqlSessionFactory ssf;
	
	static{
		////���������ļ�
		SqlSessionFactoryBuilder ssfb = 
			new SqlSessionFactoryBuilder();
		ssf = ssfb.build(MyBatisUtil.class.getResourceAsStream("iBatis.xml"));
	}
	//��ȡSqlSession
	public static SqlSession getSession(){
		return ssf.openSession();
	}
	public static void main(String[] args) {
	
	}
}
