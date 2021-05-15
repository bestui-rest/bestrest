package entity;

import java.util.List;

public class Admin {
	private int id;
	private String username;
	private String pwd;
	private String name;
	private List<Role> roles;//关联数据--角色
	public Admin() {
		super();
	}
	public Admin(String username, String pwd, String name) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.name = name;
	}
	public Admin(int id, String username, String pwd, String name) {
		super();
		this.id = id;
		this.username = username;
		this.pwd = pwd;
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
	
	
	
}
