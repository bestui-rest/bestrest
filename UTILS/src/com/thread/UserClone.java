package com.thread;

import com.bean.User;

public class UserClone {
public static void main(String[] args) {

	User user=new User();
	user.setB(20);
	int arr[]={2,68,98};
	user.setArr(arr);
	user.setArr(arr);
	user.setName("ll");
	User user1=new User();
	user1.setB(89);
	user1.setArr(arr);
	
	
	try {
		User user2=(User)user.clone();
		System.out.println(user.hashCode());
		System.out.println(user2.hashCode());
		user.getArr()[1]=15;
		user.setName("kk");
		System.out.println(user2.getArr()[1]);
		System.out.println(user2.getName());
	} catch (CloneNotSupportedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
