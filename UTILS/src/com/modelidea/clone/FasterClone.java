package com.modelidea.clone;
/**
 * ԭ��ģʽ�� ��һ��������Ϊԭ�ͣ�ͨ��������и��ƶ���¡�������ԭ�����Ƶ���ʵ��
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
