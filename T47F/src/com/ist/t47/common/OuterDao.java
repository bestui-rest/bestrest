package com.ist.t47.common;

import java.io.Serializable;
import java.math.BigDecimal;

public class OuterDao implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String people_id;
	private String xm;
	private String sfzh;
	private String kh_id;
	private BigDecimal zje;
	private BigDecimal lccpzjz;
	private String zhxx;
	private String jyjl;
	private String lccp;
	private String acct_num;
	private String trans_num;
	public String getAcct_num() {
		return acct_num;
	}

	public void setAcct_num(String acctNum) {
		acct_num = acctNum;
	}

	public String getTrans_num() {
		return trans_num;
	}

	public void setTrans_num(String transNum) {
		trans_num = transNum;
	}
	private String verification_dt;
	private String verification_batch;
	private String verification_seq;
	public OuterDao() {
		super();
	}
	
	public String getPeople_id() {
		return people_id;
	}

	public void setPeople_id(String peopleId) {
		people_id = peopleId;
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

	public String getKh_id() {
		return kh_id;
	}

	public void setKh_id(String khId) {
		kh_id = khId;
	}

	public BigDecimal getZje() {
		return zje;
	}

	public void setZje(BigDecimal zje) {
		this.zje = zje;
	}

	public BigDecimal getLccpzjz() {
		return lccpzjz;
	}

	public void setLccpzjz(BigDecimal lccpzjz) {
		this.lccpzjz = lccpzjz;
	}

	public String getZhxx() {
		return zhxx;
	}

	public void setZhxx(String zhxx) {
		this.zhxx = zhxx;
	}

	public String getJyjl() {
		return jyjl;
	}

	public void setJyjl(String jyjl) {
		this.jyjl = jyjl;
	}

	public String getLccp() {
		return lccp;
	}

	public void setLccp(String lccp) {
		this.lccp = lccp;
	}

	public String getVerification_dt() {
		return verification_dt;
	}

	public void setVerification_dt(String verificationDt) {
		verification_dt = verificationDt;
	}

	public String getVerification_batch() {
		return verification_batch;
	}

	public void setVerification_batch(String verificationBatch) {
		verification_batch = verificationBatch;
	}

	public String getVerification_seq() {
		return verification_seq;
	}

	public void setVerification_seq(String verificationSeq) {
		verification_seq = verificationSeq;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OuterDao [people_id=");
		builder.append(people_id);
		builder.append(", xm=");
		builder.append(xm);
		builder.append(", kh_id=");
		builder.append(kh_id);
		builder.append(", zje=");
		builder.append(zje);
		builder.append(", lccpzjz=");
		builder.append(lccpzjz);
		builder.append(", zhxx=");
		builder.append(zhxx);
		builder.append(", jyjl=");
		builder.append(jyjl);
		builder.append(", lccp=");
		builder.append(lccp);
		builder.append(", verification_dt=");
		builder.append(verification_dt);
		builder.append(", verification_batch=");
		builder.append(verification_batch);
		builder.append(", verification_seq=");
		builder.append(verification_seq);
		builder.append("]");
		return builder.toString();
	}
	
	
}
