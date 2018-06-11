package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.pojo.Details;
import com.pojo.TCollect;
import com.pojo.TComment;
import com.pojo.TComplaint;
import com.pojo.TRecruitment;
import com.pojo.TUser;
import com.service.CollecteService;
import com.service.CollegeService;
import com.service.CommentService;
import com.service.ComplaintService;
import com.service.RecruitmentService;
import com.service.UserService;
import com.utils.DateConvert;
import com.utils.IDUtils;

@Controller
public class RecruitmentController {
	@Autowired
	RecruitmentService recruitmentService;
	@Autowired
	CollegeService collegeService;
	@Autowired
	UserService userService;
	@Autowired
	CollecteService collecteService;
	@Autowired
	ComplaintService complaintServce;
	@Autowired
	CommentService commentService;
	
	/**
	 * 获取前20条招聘信息接口
	 * 
	 * @param pagenum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/wxRecruitmentList", produces="application/json;charset=utf-8")
	@ResponseBody
	public String wxRecruitmentList(int pagenum) throws IOException{
		PageHelper.startPage(pagenum,20);
		List<TRecruitment> list = recruitmentService.query_id_title_litimg();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	/**
	 * 获取首页轮播图图片列表
	 * @param type
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/sowingMapList",produces="application/json;charset=utf-8")
	@ResponseBody
	public String sowingMapList() throws IOException{
		String type="首页轮播";
		List<TRecruitment> imgList = recruitmentService.sowingMapList(type);
		Gson gson = new Gson();
		return gson.toJson(imgList);
	}
	
	/**
	 * 根据学院编号查询属于该学院下的招聘信息
	 * @param collegeId
	 * @param pagenum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/querryByCollegeId",produces="application/json;charset=utf-8")
	@ResponseBody
	public String querryByCollegeId(String collegeId,int pagenum) throws IOException{
		PageHelper.startPage(pagenum,10);
		List<TRecruitment> list = recruitmentService.querryByCollegeId(collegeId);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
		
	/**
	 * 添加招聘信息
	 * @param request1
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/add")
	@ResponseBody
	public String add(HttpServletRequest request1, HttpSession session) throws IOException {
		/*前端 UI 的 form 表单里面有用到图片上传的时候，需要在这里对 request 作以下处理*/
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)request1;
		
		String id = IDUtils.getID().toString();/*实体类自动生成 id*/
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String collegeId = request.getParameter("collegeId");
		String type = "普通信息";//小程序用户发布的招聘信息默认为“普通信息”
		String details = request.getParameter("details");
		String litimg = (String)session.getAttribute("imgName");
		DateConvert convert = new DateConvert();
		String time = convert.toString(new Date());

		TRecruitment tRecruitment = new TRecruitment();
		tRecruitment.setId(id);
		tRecruitment.setTitle(title);
		tRecruitment.setAuthor(author);
		tRecruitment.setCollegeId(collegeId);
		tRecruitment.setDetails(details);
		tRecruitment.setLitimg(litimg);
		tRecruitment.setPublishedTime(time);
		tRecruitment.setType(type);
		
		recruitmentService.add(tRecruitment);
		
		return "true";
	}
	
	/**
	 * 根据作者查询招聘信息
	 * @param author
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/queryMyPubulis",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryMyPubulis(String openid)throws IOException{

		//根据用户编号从用户表中查询出用户的名字
		TUser tUser = new TUser();
		tUser.setOpenid(openid);
		TUser userInfo = userService.queryByOpenid(openid);
		
		//根据用户名查询用户发布的信息
		String author = userInfo.getNickname();
		List<TRecruitment> list = recruitmentService.queryByAuthor(author);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	/**
	 * 删除当前行的信息
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteById")
	@ResponseBody
	public String deleteById(String id) throws IOException{
		recruitmentService.deleteById(id);
		return "true";
	}
	
	/**
	 * 根据关键字搜索
	 * @param title
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/queryByTitle",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryByTitle(String title)throws IOException{
		//当放到服务器上时，需要把编码转换注释掉
		title = new String(title.getBytes("ISO-8859-1"), "utf-8");
		List<TRecruitment> list = recruitmentService.queryByTitle(title);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	/**
	 * 根据当前招聘信息编号和用户编号
	 * 查询当前招聘信息的详情内容
	 * 并查询当前用户是否对该文章有收藏和投诉的行为
	 * 并查询当前文章的所有评论内容
	 * 
	 * @param id
	 * @param openid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/queryDetails",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryById(String id, String openid) throws IOException{
		System.out.println(id);
		System.out.println(openid);
		//新建一个 javabean 把数据存起来
		Details details = new Details();
		//获取当前招聘信息的详情
		TRecruitment list = recruitmentService.queryById(id);
		//把招聘信息的详情放到 Details 类里面
		details.setId(list.getId());
		details.setTitle(list.getTitle());
		details.setLitimg(list.getLitimg());
		details.setPublishedTime(list.getPublishedTime());
		details.setAuthor(list.getAuthor());
		details.setDetails(list.getDetails());
		//查询当前用户是否有收藏当前招聘信息
		TCollect tCollect = collecteService.queryByIdAndOpenid(id, openid);
		if( tCollect == null ){
			System.out.println("============没有收藏记录==========");
			details.setIsCollect(false);
		}else{
			System.out.println(tCollect);
			details.setIsCollect(true);
		}
		//查询当前用户是否已经投诉过此文章
		TComplaint tComplaint = complaintServce.queryByIdAndOpenid(id, openid);
		if( tComplaint == null ){ 
			details.setIsComplaint(false);
		}else{
			details.setIsComplaint(true);
		}
		//查询出当前招聘信息的评论内容
		List<TComment> tComment = commentService.queryCommentById(id);
		if(tComment !=null && !tComment.isEmpty()){
			details.setHasComment(true);
			details.setCommentList(tComment);
		}else{
			details.setHasComment(false);
		}
		Gson gson = new Gson();
		return gson.toJson(details);
	}

}
