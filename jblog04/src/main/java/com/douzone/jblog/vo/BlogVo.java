package com.douzone.jblog.vo;

public class BlogVo {
	private String id;
	private String title;
	private String logo;
	
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getLogo() {
		return logo;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", title=" + title + ", logo=" + logo + "]";
	}
	
	
}
