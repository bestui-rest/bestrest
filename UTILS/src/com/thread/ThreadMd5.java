package com.thread;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bean.UserBean;
import com.database.ConnPool;
import com.database.ConnectionUtils;
import com.utils.MessageDigest;

public class ThreadMd5 implements Runnable {
private static final int STEP=10000;
private volatile int page=-1;


public void run() {
	MessageDigest md=new MessageDigest();
	ArrayList<UserBean> list= getArrayList(Thread.currentThread().getName());
	if(list==null||list.size()<1){
		return;
	}
	//File f=new File ("F:\\"+Thread.currentThread().getName()+".txt");
	File f=new File ("F:\\"+11+".txt");
	FileOutputStream fo=null;
	OutputStreamWriter isr=null;
	try {
		fo=new FileOutputStream(f,true);
		isr=new OutputStreamWriter(fo);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   for (UserBean user:list){
	  String acct_num=user.getAcct_num();
	  String stacct_num=md.getMD5Digest(acct_num);
	  String trans1=user.getTransactionkey();
	  String tras1=md.getMD5Digest(trans1);
	  try {
		isr.write(Thread.currentThread().getName()+"::"+trans1+"::"+tras1+"\n");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
   }
   try {
		isr.flush();
		isr.close();
		fo.flush();
		fo.close();
	
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
   //µÝ¹éµ÷ÓÃ  
	run();
}

public ArrayList<UserBean> getArrayList(String name){
//Connection conn=ConnectionUtils.getConnection();
Connection conn=ConnPool.getConnection();
ArrayList<UserBean> uh=null;
int beginnum;	
int endnum;
synchronized (ThreadMd5.class) {
page++;
}
beginnum=page*STEP+1;
endnum=(page+1)*STEP+1;
	String sql=" SELECT ACCT_NUM,CARD_NO,TOKEN_NO,TRANSACTIONKEY,NUMBS  FROM( SELECT ACCT_NUM,CARD_NO,TRANSACTIONKEY,TOKEN_NO,ROWNUM AS NUMBS FROM (SELECT ACCT_NUM,CARD_NO,A.TRANSACTIONKEY,TOKEN_NO FROM T47_TRANSACTION_UH A WHERE LENGTH(A.ACCT_NUM)<20 ORDER BY A.TRANSACTIONKEY   ) ) "
				+ " WHERE NUMBS>="+beginnum+ " AND NUMBS<"+endnum;	
	System.out.println(name+"\t"+sql);
	try {
			ResultSet rs=conn.prepareStatement(sql).executeQuery();
			 uh=new ArrayList<UserBean>();
			while (rs.next()){
				UserBean usrbe=new UserBean();
				usrbe.setAcct_num(rs.getString(1));
				usrbe.setCard_no(rs.getString(2));
				usrbe.setTransactionkey(rs.getString(4));
				uh.add(usrbe);
			}
			rs.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	return uh;
}

public static void main(String[] args) {
	ThreadMd5 th=new ThreadMd5();
	for(int i=0;i<10;i++){
		Thread t=new Thread(th,"thread"+i);
	    t.start();
	}
}

}
