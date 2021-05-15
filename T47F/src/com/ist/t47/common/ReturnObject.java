package com.ist.t47.common;


public class ReturnObject {
	private String people_id;
	private String xm;
	private String sfzh;
	private String kh_id;
	private String zje;
	private String lccpzjz;
	private String zhxx;
	private String jyjl;
	private String lccp;
	public ReturnObject() {
		super();
	}
	public ReturnObject(String people_id, String xm, String sfzh,String kh_id,String zje,String lccpzjz,String zhxx,String jyjl,
			String lccp) {
		super();
		this.people_id = people_id;
		this.xm = xm;
		this.sfzh = sfzh;
		this.kh_id=kh_id;
		this.zje=zje;
		this.lccpzjz=lccpzjz;
		this.zhxx=zhxx;
		this.jyjl=jyjl;
		this.lccp=lccp;

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
	public String getZje() {
		return zje;
	}
	public void setZje(String zje) {
		this.zje = zje;
	}
	public String getLccpzjz() {
		return lccpzjz;
	}
	public void setLccpzjz(String lccpzjz) {
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ReturnObject [people_id=");
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
		builder.append("]");
		return builder.toString();
	}
	
	
	
	

}
