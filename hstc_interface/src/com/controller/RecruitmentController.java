package com.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.mapper.TRecruitmentMapper;
import com.pojo.TCollege;
import com.pojo.TRecruitment;
import com.pojo.TRecruitmentCompletion;
import com.service.CollegeService;
import com.service.RecruitmentService;
import com.utils.IDUtils;

@Controller
public class RecruitmentController {
	@Autowired
	RecruitmentService recruitmentService;
	@Autowired
	CollegeService collegeService;

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
	public String sowingMapList(String type) throws IOException{

		type = new String(type.getBytes("ISO-8859-1"), "utf-8");

		List<TRecruitment> imgList = recruitmentService.sowingMapList(type);
		Gson gson = new Gson();
		return gson.toJson(imgList);
	}
	
	/**
	 * 获取学院分类信息
	 * @param rquest
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/college",produces="application/json;charset=utf-8")
	@ResponseBody
	public String college(HttpServletRequest rquest) throws IOException{
		List<TCollege> list = collegeService.college();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	@RequestMapping(value="/querryByCollegeId",produces="application/json;charset=utf-8")
	@ResponseBody
	public String querryByCollegeId(String collegeId,int pagenum) throws IOException{
		PageHelper.startPage(pagenum,10);
		List<TRecruitment> list = recruitmentService.querryByCollegeId(collegeId);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	
	/**
	 * 根据 id 值查找当前招聘信息的详情
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="queryById",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryById(HttpServletRequest request, String id) throws IOException{
		TRecruitment tRecruitment = recruitmentService.queryById(id);
		TCollege tCollege = collegeService.collegeName(tRecruitment.getCollegeId());
		TRecruitmentCompletion tRecruitmentCompletion = new TRecruitmentCompletion();
		tRecruitmentCompletion.settRecruitment(tRecruitment);
		tRecruitmentCompletion.setCollegeName(tCollege.getCollegeName());
		Gson gson = new Gson();
		return gson.toJson(tRecruitmentCompletion);
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
		String collegeId = request.getParameter("college");
		String type = request.getParameter("type");
		String details = request.getParameter("details");
		String litimg = (String)session.getAttribute("imgName");
		String time = new Date().toString();
		
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
	 * 删除当前行的信息
	 * @param request
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="deleteById")
	@ResponseBody
	public String deleteById(HttpServletRequest request, String id) throws IOException{
		recruitmentService.deleteById(id);
		return "true";
	}

}
