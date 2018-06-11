package com.pojo;

import java.util.List;

public class Details {
	private String id;//招聘信息编号

    private String title;//标题

    private String litimg;//缩略图

    private String publishedTime;//发布时间

    private String author;//作者
    
    private String details;//详情
    
    private Boolean isCollect;//判断当前用户是否收藏
    
    private Boolean isComplaint;//判断当前用户是否投诉
    
    private Boolean hasComment;//是否有评论内容
    
    private List<TComment> commentList;//存放当前招聘信息的评论内容

	
	public Boolean getHasComment() {
		return hasComment;
	}

	public void setHasComment(Boolean hasComment) {
		this.hasComment = hasComment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLitimg() {
		return litimg;
	}

	public void setLitimg(String litimg) {
		this.litimg = litimg;
	}

	public String getPublishedTime() {
		return publishedTime;
	}

	public void setPublishedTime(String publishedTime) {
		this.publishedTime = publishedTime;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Boolean getIsCollect() {
		return isCollect;
	}

	public void setIsCollect(Boolean isCollect) {
		this.isCollect = isCollect;
	}

	public Boolean getIsComplaint() {
		return isComplaint;
	}

	public void setIsComplaint(Boolean isComplaint) {
		this.isComplaint = isComplaint;
	}

	public List<TComment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<TComment> commentList) {
		this.commentList = commentList;
	}
    
}
