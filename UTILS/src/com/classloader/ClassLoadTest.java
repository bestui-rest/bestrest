package com.classloader;

public class ClassLoadTest  extends ClassLoadeTestParent{

	{
		System.out.println("����ʵ�������");
	}
	static{
		System.out.println("���ྲ̬�����");
	}
	public ClassLoadTest(){
		super("");
		System.out.println("���๹�췽��");
	}
	
	public void sayHell(){
		System.out.println("sayHell");
	}
	public static void main(String[] args) {
		/*���ྲ̬�����
���ྲ̬�����
����main
����ʵ���������
���๹�췽��
����ʵ�������
���๹�췽��
*/
		System.out.println("����main");
		ClassLoadTest ct=new ClassLoadTest();
		ct.say();
		System.out.println(ct);
	//	ClassLoadeTestParent ctp=new ClassLoadeTestParent("");
	//ctp.say();
		ClassLoadeTestParent ctp=new ClassLoadTest();

	}
}
