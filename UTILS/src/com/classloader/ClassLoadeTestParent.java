package com.classloader;

public class ClassLoadeTestParent {

	{
	 System.out.println("父类实例化代码块");
	}
	
	static{
		System.out.println("父类静态代码块");
	}
	public ClassLoadeTestParent(String str){
		System.out.println("父类构造方法");
	}
	public void say(){
		System.out.println(this);
	}
	
	
}
