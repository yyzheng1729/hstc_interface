package com.pojo;

import java.util.List;

public class Details {
	private String id;//��Ƹ��Ϣ���

    private String title;//����

    private String litimg;//����ͼ

    private String publishedTime;//����ʱ��

    private String author;//����
    
    private String details;//����
    
    private Boolean isCollect;//�жϵ�ǰ�û��Ƿ��ղ�
    
    private Boolean isComplaint;//�жϵ�ǰ�û��Ƿ�Ͷ��
    
    private Boolean hasComment;//�Ƿ�����������
    
    private List<TComment> commentList;//��ŵ�ǰ��Ƹ��Ϣ����������

	
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
