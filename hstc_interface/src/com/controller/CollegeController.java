package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.pojo.Pag;
import com.pojo.TCollege;
import com.service.CollegeService;

@Controller
public class CollegeController {
	@Autowired
	CollegeService collegeService;
	/**
	 * 展示学院信息（分页）
	 * @param pagenum
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/college_display", produces="application/json;charset=utf-8")
	@ResponseBody
	public String college_display(int pagenum) throws IOException{
		PageHelper.startPage(pagenum,5);
		List<TCollege> list = collegeService.college();
		PageInfo page = new PageInfo(list);
		int totalPage = page.getPages();//总页数
		long totalData = page.getTotal();//总数据
		
		Pag pag = new Pag();
		pag.setTotalPage(totalPage);
		pag.setTotalData(totalData);
		pag.settCollege(list);
		
		Gson gson = new Gson();
		return gson.toJson(pag);
	}
	/**
	 * 上传图片到服务器后，把相关的学院信息添加到数据库中
	 * @param request1
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/college_add")
	@ResponseBody
	public String college_add(HttpServletRequest request1, HttpSession session) throws IOException {
		/*前端 UI 的 form 表单里面有用到图片上传的时候，需要在这里对 request 作以下处理*/
		MultipartHttpServletRequest request = (MultipartHttpServletRequest)request1;
		String collegeId = request.getParameter("collegeId"); //学院 id 
		String collegeName = request.getParameter("collegeName"); //学院名称
		String litimg = (String)session.getAttribute("imgName"); //上传的图片路径
		System.out.println(collegeId);
		System.out.println(collegeName);
		System.out.println(litimg);
		TCollege tCollege = new TCollege();
		tCollege.setCollegeId(collegeId);
		tCollege.setCollegeName(collegeName);
		tCollege.setCollegeImg(litimg);
		collegeService.college_add(tCollege);
		return "true";
	}
	
	/**
	 * 根据学院编号删除对应的学院信息
	 * @param collegeId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/college_delete")
	@ResponseBody
	public String college_delete(String collegeId) throws IOException {
		collegeService.college_delete(collegeId);
		return "true";
	}
	
	/**
	 * 根据学院编号查询对应的学院信息
	 * @param collegeId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/queryBycollegeId", produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryBycollegeId(String collegeId)throws IOException{
		TCollege tCollege = collegeService.collegeName(collegeId);
		Gson gson = new Gson();
		return gson.toJson(tCollege);
	}
	
	@RequestMapping(value="/college_edit")
	@ResponseBody
	public String college_edit(HttpServletRequest request) throws IOException{
		String collegeId = request.getParameter("collegeId");
		String collegeName = request.getParameter("collegeName");
		
		TCollege tCollege = new TCollege();
		tCollege.setCollegeId(collegeId);
		tCollege.setCollegeName(collegeName);
		
		collegeService.college_edit(tCollege);
		
		return "true"; 
	}
}
