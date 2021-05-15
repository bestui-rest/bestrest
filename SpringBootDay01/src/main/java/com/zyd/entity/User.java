package com.zyd.entity;

public class User {

    private String username;
    private String password;
    private String organkey;
 
    
    public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getOrgankey() {
		return organkey;
	}


	public void setOrgankey(String organkey) {
		this.organkey = organkey;
	}


	@Override
    public String toString() {
        return "User{" +

                ", userName='" + username + '\'' +
                ", passWord='" + password + '\'' +
                ", organkey='" + organkey + '\'' +
                '}';
    }

}
