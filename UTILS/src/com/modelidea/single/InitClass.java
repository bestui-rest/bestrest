package com.modelidea.single;

public class InitClass {
	private static InitClass initClass=new InitClass();
//˽�л�ʵ������
private InitClass(){
	
}
public static InitClass getInstance(){
	return initClass;
}


}
