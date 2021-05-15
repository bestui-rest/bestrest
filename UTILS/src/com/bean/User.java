package com.bean;

import java.util.ArrayList;

/**
 * @author Administrator
 *
 */
public class User implements Cloneable {
private String name;
private String age;
private int[] arr;
private int b;
private ArrayList<User> list;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public int[] getArr() {
	return arr;
}
public void setArr(int[] arr) {
	this.arr = arr;
}
public int getB() {
	return b;
}
public void setB(int b) {
	this.b = b;
}
public ArrayList<User> getList() {
	return list;
}
public void setList(ArrayList<User> list) {
	this.list = list;
}

@Override
public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
	   User obj=(User)super.clone();
	   obj.arr=obj.arr.clone();
	   return obj;
	}
}
