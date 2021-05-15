package com.zyd.bean;

import java.sql.Timestamp;

public class Items {
private Integer id;
private Integer c_id;
private String c_name;
private String c_picture;
private Double c_price;
private Timestamp add_time;
private Timestamp remove_time;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public Integer getC_id() {
	return c_id;
}
public void setC_id(Integer cId) {
	c_id = cId;
}
public String getC_name() {
	return c_name;
}
public void setC_name(String cName) {
	c_name = cName;
}
public String getC_picture() {
	return c_picture;
}
public void setC_picture(String cPicture) {
	c_picture = cPicture;
}
public Double getC_price() {
	return c_price;
}
public void setC_price(Double cPrice) {
	c_price = cPrice;
}
public Timestamp getAdd_time() {
	return add_time;
}
public void setAdd_time(Timestamp addTime) {
	add_time = addTime;
}
public Timestamp getRemove_time() {
	return remove_time;
}
public void setRemove_time(Timestamp removeTime) {
	remove_time = removeTime;
}
}
