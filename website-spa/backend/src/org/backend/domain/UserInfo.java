package org.backend.domain;

import java.util.Date;

public class UserInfo {
	private int id;
	private String user;
	private String pwd;
	private String email;
	private Date date_time;
	public UserInfo() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", user=" + user + ", pwd=" + pwd
				+ ", email=" + email + ", date_time=" + date_time + "]";
	}
	
}
