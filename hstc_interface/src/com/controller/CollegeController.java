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
	
}
