package org.backend.domain;

import java.util.Date;

public class ScrollShow {
	private int id;
	private String title;
	private String content;
	private String image;
	private Date date_time;
	public ScrollShow() {
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Date getDate_time() {
		return date_time;
	}
	public void setDate_time(Date date_time) {
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "ScrollShow [id=" + id + ", title=" + title + ", content="
				+ content + ", image=" + image + ", date_time=" + date_time
				+ "]";
	}
	
}
