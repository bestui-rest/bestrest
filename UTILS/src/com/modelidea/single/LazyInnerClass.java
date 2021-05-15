package com.modelidea.single;

public class LazyInnerClass{
	
	private static class GetLazy{
		private static LazyInnerClass lz=new LazyInnerClass();
	}
	private   LazyInnerClass(){
		
	}
	public static LazyInnerClass getLazy(){
		return LazyInnerClass.GetLazy.lz;	
	}
}