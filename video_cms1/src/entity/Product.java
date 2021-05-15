package entity;

import java.util.Date;

public class Product {
	private int id;
	private int course_id;
	private int lore_id;
	private String name;
	private String description;
	private String status;
	private double price;
	private String image;
	private String video;
	private String creater;
	private Date createtime;
	private String ctime;
	private String mender;
	private Date modifytime;
	private String mtime;
	
	private Course course;//private String courseName
	
	
	
	
	
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getMtime() {
		return mtime;
	}
	public void setMtime(String mtime) {
		this.mtime = mtime;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Product() {
		super();
	}
	public Product(int courseId, int loreId, String name, String description,
			String status, double price, String image, String video,
			String creater) {
		super();
		course_id = courseId;
		lore_id = loreId;
		this.name = name;
		this.description = description;
		this.status = status;
		this.price = price;
		this.image = image;
		this.video = video;
		this.creater = creater;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourse_id() {
		return course_id;
	}
	public void setCourse_id(int courseId) {
		course_id = courseId;
	}
	public int getLore_id() {
		return lore_id;
	}
	public void setLore_id(int loreId) {
		lore_id = loreId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getMender() {
		return mender;
	}
	public void setMender(String mender) {
		this.mender = mender;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
	
	
	
}
