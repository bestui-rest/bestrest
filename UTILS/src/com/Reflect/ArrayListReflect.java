package com.Reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class ArrayListReflect {
public static void main(String[] args) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
	System.out.println("\u4EA4\u6613\u53D1\u751F\u5730");
	ArrayList<String> str=new ArrayList<String>();
	str.add("seq1");
	str.add("seq2");
	str.add("seq3");
	System.out.println(str);
	
	System.out.println("44".matches(".*[0-9]+.*"));
	
	System.out.println("tyttt".indexOf("y"));
	Method m=ArrayList.class.getDeclaredMethod("add", Object.class);
	m.invoke(str, 16);
	System.out.println(str);
	
}
}
