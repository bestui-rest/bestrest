package com.ist.t47.common;

public class RequestObject {
	private String people_id="";
	private String xm="";
	private String sfzh="";
	private String hzsfzh="";
	public String getPeople_id() {
		return people_id;
	}
	public void setPeople_id(String people_id) {
		this.people_id = people_id;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getHzsfzh() {
		return hzsfzh;
	}
	public void setHzsfzh(String hzsfzh) {
		this.hzsfzh = hzsfzh;
	}
	@Override
	public String toString() {
		return "RequestObject [people_id=" + people_id + ", xm=" + xm + ", sfzh=" + sfzh + ", hzsfzh=" + hzsfzh + "]";
	}
	
	

}
