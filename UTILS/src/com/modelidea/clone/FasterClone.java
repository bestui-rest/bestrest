package com.modelidea.clone;
/**
 * 原型模式： 将一个对象作为原型，通过对其进行复制而克隆出多个和原型类似的新实例
 * @author Administrator
 *
 */
public class FasterClone {
public static void main(String[] args) {
	 ModealBean modealbean=new ModealBean();
	long t1=System.currentTimeMillis();

	for (int i=0 ;i<10000;i++) {
		ModealBean  b=new ModealBean();
	   
	}
	long t2=System.currentTimeMillis();
	System.out.println(t2-t1);
	
	for (int i=0 ;i<10000;i++) {
		   try {
			   ModealBean b=(ModealBean)modealbean.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
		}
	long t3=System.currentTimeMillis();
	System.out.println(t3-t2);
}
}
