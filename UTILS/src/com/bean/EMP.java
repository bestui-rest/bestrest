package com.bean;

import java.sql.Date;

public class EMP {
	//-->SCOTT.EMP 本表字段
	private String ename;
	private String sal2;
	private float sal;
	private Date hiredate;
	private String job;
	//-->SCOTT.DEPT 额外关联补充字段
	private String dname;
	private String loc;
	 public  EMP(){
		
	}
	 public EMP(String str,Integer ints){
		 System.out.println(str+""+ints);
	 }
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getSal2() {
		return sal2;
	}
	public void setSal2(String sal2) {
		this.sal2 = sal2;
	}
	public float getSal() {
		return sal;
	}
	public void setSal(float sal) {
		this.sal = sal;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
	

}
