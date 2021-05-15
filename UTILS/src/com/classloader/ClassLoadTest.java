package com.classloader;

public class ClassLoadTest  extends ClassLoadeTestParent{

	{
		System.out.println("子类实例代码块");
	}
	static{
		System.out.println("子类静态代码夸");
	}
	public ClassLoadTest(){
		super("");
		System.out.println("子类构造方法");
	}
	
	public void sayHell(){
		System.out.println("sayHell");
	}
	public static void main(String[] args) {
		/*父类静态代码块
子类静态代码夸
子类main
父类实例化代码块
父类构造方法
子类实例代码块
子类构造方法
*/
		System.out.println("子类main");
		ClassLoadTest ct=new ClassLoadTest();
		ct.say();
		System.out.println(ct);
	//	ClassLoadeTestParent ctp=new ClassLoadeTestParent("");
	//ctp.say();
		ClassLoadeTestParent ctp=new ClassLoadTest();

	}
}
