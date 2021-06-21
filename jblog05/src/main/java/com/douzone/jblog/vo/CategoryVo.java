package com.douzone.jblog.vo;

public class CategoryVo {
	private Long no;
	private String name;
	private String description;
	private String regDate;
	private String userId;
	private int totalPost;
	
	
	public int getTotalPost() {
		return totalPost;
	}
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}
	public Long getNo() {
		return no;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public String getRegDate() {
		return regDate;
	}
	public String getUserId() {
		return userId;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", description=" + description + ", regDate=" + regDate
				+ ", userId=" + userId + ", totalPost=" + totalPost + "]";
	}
	
	
	
}
