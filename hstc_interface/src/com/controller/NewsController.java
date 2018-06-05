//package com.controller;
//
//import java.io.IOException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//
//import com.google.gson.Gson;
//import com.pojo.TNews;
//import com.service.NewsService;
//import com.utils.IDUtils;
//
//@Controller
//public class NewsController {
//	@Autowired
//	NewsService newsService;
//	
//	@RequestMapping(value="queryNews",produces="application/json;charset=utf-8")
//	@ResponseBody
//	public String queryNews(HttpServletRequest request,String pageNumber) throws IOException{
//		System.out.println("3=========================");
//		Gson gson = new Gson();
//		return gson.toJson("sdf");
//	}
//	
//	/**
//	 * 从前端UI界面获取相关的新闻内容，并插入到数据库中
//	 * 
//	 * @author yy
//	 * 
//	 * @param request HttpServletRequest, session HttpSession
//	 * 
//	 * @return 
//	 */
//	@RequestMapping(value="/insertNews")
//	@ResponseBody
//	public String insertNews(HttpServletRequest request1,HttpSession session) throws IOException{
//	
//		/*前端 UI 的 form 表单里面有用到图片上传的时候，需要在这里对 request 作以下处理*/
//		MultipartHttpServletRequest request = (MultipartHttpServletRequest)request1;
//		
//		String newsId = IDUtils.getID().toString();/*实体类自动生成 id*/
//		String newsTitle = request.getParameter("newsTitle");
//		String newsAuthor = request.getParameter("newsAuthor");
//		String newsTime = request.getParameter("newsTime");
//		String newsPosition = request.getParameter("newsPosition");
//		String newsCategory = request.getParameter("newsCategory");
//		String newsDetails = request.getParameter("text");/*新闻详情*/
//		String newsLitimg = (String)session.getAttribute("imgName");
//		
//		TNews tNews = new TNews();
//		tNews.setNewsTitle(newsTitle);
//		tNews.setNewsTime(newsTime);
//		tNews.setNewsDetails(newsDetails);
//		tNews.setNewsAuthor(newsAuthor);
//		tNews.setNewsCategory(newsCategory);
//		tNews.setNewsId(newsId);
//		tNews.setNewsPosition(newsPosition);
//		tNews.setNewsLitimg(newsLitimg);
//		
//		newsService.insertNews(tNews);
//		
//		System.out.println(tNews.toString());
//		
//		return "true";
//	}
//}
