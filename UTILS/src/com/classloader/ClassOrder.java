package com.classloader;

//类的加载过程
public class ClassOrder {
	private String baseName = "base";
	public ClassOrder(){
		System.out.println(this);
		this.printName();
	}
	public void printName(){
		System.out.println(baseName);
	}
	
	static class SubClass extends ClassOrder{
		private String subName = "sub";
		@Override
		public void printName() {
			System.out.println(subName);
		}
	}
	
	public static void main(String[] args) {
		new SubClass();
	}
}

//1.new SubClass();在创建子类的过程中首先创建父类对象，然后才
//创建子类对象
//2.创建父类即默认调用父类的构造方法ClassOrder();在构造方法中又
//调用了printName()方法，由于子类中重写printName(),则被调用子
//类中的printName()方法
//3.由于子类还没有构造，所以输出的name为null



