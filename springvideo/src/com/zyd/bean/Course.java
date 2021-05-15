package com.zyd.bean;

import com.sun.jmx.snmp.Timestamp;

public class Course {
private Integer id;
private String name;
private Integer direction_id;
private Integer content_id;
private String description;
private Double price;
private String picture;
private String url;
private String uploader;
private Timestamp uploadtime;
private Timestamp updatetime;
private String updater;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Integer getDirection_id() {
	return direction_id;
}
public void setDirection_id(Integer directionId) {
	direction_id = directionId;
}
public Integer getContent_id() {
	return content_id;
}
public void setContent_id(Integer contentId) {
	content_id = contentId;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Double getPrice() {
	return price;
}
public void setPrice(Double price) {
	this.price = price;
}
public String getPicture() {
	return picture;
}
public void setPicture(String picture) {
	this.picture = picture;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getUploader() {
	return uploader;
}
public void setUploader(String uploader) {
	this.uploader = uploader;
}
public Timestamp getUploadtime() {
	return uploadtime;
}
public void setUploadtime(Timestamp uploadtime) {
	this.uploadtime = uploadtime;
}
public Timestamp getUpdatetime() {
	return updatetime;
}
public void setUpdatetime(Timestamp updatetime) {
	this.updatetime = updatetime;
}
public String getUpdater() {
	return updater;
}
public void setUpdater(String updater) {
	this.updater = updater;
}
}
