package com.modelidea.template;
/*
 * 行为模式    模板模式 : 
 * 由父类规定   算法框架（  一系列动作行为及顺序   及公共方法    ）
 * 子类不改变算法骨架  实现指定的方法  即可
 */
public class CalCaseTask  extends BaseCalTask{
 
	public CalCaseTask(){
		super();
	}
	@Override
     public Boolean init() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean preTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delTask() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean finTask() {
		// TODO Auto-generated method stub
		return null;
	}
   
	public static void main(String[] args) {
		BaseCalTask bk=new CalCaseTask();
	}
	
}
