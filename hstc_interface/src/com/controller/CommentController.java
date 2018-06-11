package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pojo.TComment;
import com.service.CommentService;
import com.utils.IDUtils;

@Controller
public class CommentController {
	@Autowired
	CommentService commentService;
	
	@RequestMapping(value="/insertComment",produces="application/json;charset=utf-8")
	@ResponseBody
	public String insertComment(HttpServletRequest request) throws IOException{
		String commentId = IDUtils.getID().toString();/*实体类自动生成 id*/
		String id = request.getParameter("id");
		String openid = request.getParameter("openid");
		String content = request.getParameter("content");
		openid = new String(openid.getBytes("ISO-8859-1"), "utf-8");
		content = new String(content.getBytes("ISO-8859-1"), "utf-8");
		TComment tComment = new TComment();
		tComment.setCommentId(commentId);
		tComment.setOpenid(openid);
		tComment.setId(id);
		tComment.setContent(content);
		commentService.insertComment(tComment);
		//查询出当前招聘信息的评论内容
		List<TComment> list = commentService.queryCommentById(id);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
}
