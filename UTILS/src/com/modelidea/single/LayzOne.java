package com.modelidea.single;

public class LayzOne {
private static volatile LayzOne layzOne;
//˽�л�ʵ������
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
