package com.douzone.jblog.vo;

public class PostVo {
	private Long no;
	private String title;
	private String contents;
	private String regDate;
	private Long categoryNo;
	//user정보...가 필요하나?
	
	public void setNo(Long no) {
		this.no = no;
	}
	public Long getNo() {
		return no;
	}
	public String getTitle() {
		return title;
	}
	public String getContents() {
		return contents;
	}
	public String getRegDate() {
		return regDate;
	}
	public Long getCategoryNo() {
		return categoryNo;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public void setCategoryNo(Long categoryNo) {
		this.categoryNo = categoryNo;
	}
	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", contents=" + contents + ", regDate=" + regDate
				+ ", categoryNo=" + categoryNo + "]";
	}
	
	
}
