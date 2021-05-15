package com.Reflect;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.util.Arrays;

import com.bean.User;

public class ReflectReturnType {
public static void main(String[] args) {
ClassLoader cr;
try {
	Class c=Class.forName("com.bean.User");
	User user=new User();
	Field[] f=c.getDeclaredFields();
	for(Field g:f){
		String str="set"+g.getName().substring(0, 1).toUpperCase()+g.getName().substring(1);
		System.out.println(str);
		Method ggg;
		try {
			ggg = c.getDeclaredMethod(str,g.getType());			
			try {
			System.out.println(g.getType().getName());
			     
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		
	}
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}

}
}
