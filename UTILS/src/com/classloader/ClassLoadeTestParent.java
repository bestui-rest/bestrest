package com.classloader;

public class ClassLoadeTestParent {

	{
	 System.out.println("����ʵ���������");
	}
	
	static{
		System.out.println("���ྲ̬�����");
	}
	public ClassLoadeTestParent(String str){
		System.out.println("���๹�췽��");
	}
	public void say(){
		System.out.println(this);
	}
	
	
}
