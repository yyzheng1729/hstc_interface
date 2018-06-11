package com.service;

import java.util.List;

import com.pojo.TComment;

public interface CommentService {
	/**
	 * 根据当前招聘信息的编号，找出所有的评论信息
	 * @param id
	 * @return
	 */
	public List<TComment> queryCommentById(String id);
	
	/**
	 * 插入评论信息
	 * @param tComment
	 * @return
	 */
	public void insertComment(TComment tComment);
}
