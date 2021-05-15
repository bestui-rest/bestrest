package com.thread;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.bean.UserBean;
import com.database.ConnPool;

public class ThreadT2 extends Thread {
private static final int STEP=1000;
private volatile int beginnum=1;	
private volatile int endnum=beginnum+STEP;


public void run() {
	ArrayList list= getArrayList(Thread.currentThread().getName());
	if(list==null||list.size()<1){
		return;
	}

	run();
}

public ArrayList<UserBean> getArrayList(String name){
	//Connection conn=ConnPool.getConnection();

	 synchronized (this) {
		System.out.println(name+"\t"+this.beginnum+"\t"+this.endnum);
	/*	String sql=" SELECT ACCT_NUM,CARD_NO,TOKEN_NO,NUMBS  FROM( SELECT ACCT_NUM,CARD_NO,TOKEN_NO,ROWNUM AS NUMBS FROM (SELECT ACCT_NUM,CARD_NO,TOKEN_NO FROM T47_TRANSACTION A WHERE LENGTH(A.ACCT_NUM)<20 ORDER BY A.TRANSACTIONKEY   ) ) "
				+ " WHERE NUMBS>="+beginnum+ " AND NUMBS<"+endnum;		*/
		beginnum=beginnum+STEP;
		endnum=endnum+STEP;
		try {
			sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	return null;
}

public static void main(String[] args) {
	
	for(int i=0;i<20;i++){
		ThreadT2 th=new ThreadT2();
	    th.start();
	}
}
}
