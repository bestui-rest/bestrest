package com.jvm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Date;

import com.bean.User;

public class WeakR {
@Myann(value="yyyy-MM-dd",name="2")
private Date date;
public static void main(String[] args) {
	User user=new User();
	WeakReference<User> weakr=new WeakReference<User>(user);
    try {
		Field f=WeakR.class.getDeclaredField("date");
		if(f.isAnnotationPresent(Myann.class)){
			System.out.println(f.getAnnotation(Myann.class).name());
		}
	} catch (NoSuchFieldException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SecurityException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}


}
@Target(value={ElementType.FIELD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@interface Myann{
	String value() ;
	String name();
}


