package org.backend.domain;

import java.util.Date;

public class Advertise {
	private int id;
	private String title;
	private String content;
	private Date date_time;
	public Advertise() {
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "Advertise [id=" + id + ", title=" + title + ", content="
				+ content + ", date_time=" + date_time + "]";
	}
	
}
