package com.classloader;

//��ļ��ع���
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

//1.new SubClass();�ڴ�������Ĺ��������ȴ����������Ȼ���
//�����������
//2.�������༴Ĭ�ϵ��ø���Ĺ��췽��ClassOrder();�ڹ��췽������
//������printName()������������������дprintName(),�򱻵�����
//���е�printName()����
//3.�������໹û�й��죬���������nameΪnull



