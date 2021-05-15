package com.ist.t47.common;

public class InnerDao {
	private String people_id="";
	private String xm="";
	private String sfzh="";
	private String hzsfzh="";
	private String verification_dt="";
	private String verification_batch="";
	private String verification_seq="";
	
	public String getVerification_seq() {
		return verification_seq;
	}

	public void setVerification_seq(String verification_seq) {
		this.verification_seq = verification_seq;
	}

	public String getVerification_batch() {
		return verification_batch;
	}

	public void setVerification_batch(String verification_batch) {
		this.verification_batch = verification_batch;
	}

	public String getVerification_dt() {
		return verification_dt;
	}
	
	public void setVerification_dt(String verification_dt) {
		this.verification_dt = verification_dt;
	}
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
}
