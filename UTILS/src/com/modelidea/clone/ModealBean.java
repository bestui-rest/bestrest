package com.modelidea.clone;

public class ModealBean implements Cloneable {
private String str1;
private int[] arr=new int[]{1,2,35};

public String getStr1() {
	return str1;
}
public void setStr1(String str1) {
	this.str1 = str1;

}
public int[] getArr() {
	return arr;
}
public void setArr(int[] arr) {
	this.arr = arr;
}
@Override
	protected  Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}
