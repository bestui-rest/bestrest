package com.ist.t47.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.ist.t47.common.DESUtil;

public class MainTest {
public static void main(String[] args) {
//	try{
//  InputStream is =new FileInputStream(new File("F:/ll/20181124_B0017B212000002_01.txt"));
//    InputStreamReader isr = new InputStreamReader(is, "utf-8");
//    BufferedReader br = new BufferedReader(isr);
//    String msg;
//    String line="";
//    //
//    while((msg = br.readLine())!=null){
//  	  try {
//  		  //Ω‚√‹
//  		  msg = DESUtil.decrypt(msg,"12345678");
//  		  System.out.println(msg);
//}catch(Exception e){
//	e.printStackTrace();
//}
//  }
//}catch(Exception e){
//	e.printStackTrace();
//}
	String msg="0321088197009157750";
	String msh="i7ZLWdS67WNcrr5qFa4wwFgaBYUu/4l3";
	 System.out.println(DESUtil.decrypt(msh,"12345678"));;
}
}
