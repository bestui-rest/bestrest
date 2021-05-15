package com.modelidea.single;

public class LayzOne {
private static volatile LayzOne layzOne;
//私有化实例方法
private LayzOne(){
	
}
//
public static LayzOne getInstance(){
	if(layzOne == null) {
		synchronized(LayzOne.class){
			if(layzOne == null ){
				layzOne=new LayzOne();
			}
		}
	}
	return layzOne;
}

}
