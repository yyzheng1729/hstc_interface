//package com.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.mapper.TNewsMapper;
//import com.pojo.TNews;
//import com.service.NewsService;
//
//@Service
//public class NewsServiceImpl implements NewsService{
//	@Autowired
//	TNewsMapper tNewsMapper;
//	
//	/**
//	 * 将新的新闻信息插入数据库
//	 * 
//	 * @param tNews
//	 */
//	public void insertNews(TNews tNews){
//		tNewsMapper.insertSelective(tNews);
//	}
//}
