package com.modelidea.single;

public class InitClass {
	private static InitClass initClass=new InitClass();
//私有化实例方法
private InitClass(){
	
}
public static InitClass getInstance(){
	return initClass;
}


}
