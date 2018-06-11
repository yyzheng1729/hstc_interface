package com.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pojo.TCollect;
import com.service.CollecteService;
import com.service.UserService;
import com.utils.IDUtils;

@Controller
public class CollectController {
	@Autowired
	CollecteService collecteService;
	@Autowired
	UserService userService;
	
	/**
	 * 根据用户编号查询用户所有的收藏
	 * @param openid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/queryMyCollect",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryMyCollect(String openid)throws IOException{
		List<TCollect> list = collecteService.queryMyCollect(openid);
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	/**
	 * 根据收藏记录的编号删除收藏记录
	 * @param collectId
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteByCollectId")
	@ResponseBody
	public String deleteByCollectId(String collectId)throws IOException{
		collecteService.deleteByCollectId(collectId);
		return "true";
	}

	/**
	 * 取消收藏，删除收藏记录
	 * @param id
	 * @param openid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/deleteByIdAndOpenid")
	@ResponseBody
	public String deleteByIdAndOpenid(String id, String openid)throws IOException{
		collecteService.deleteByIdAndOpenid(id, openid);
		return "true";
	}
	
	/**
	 * 用户收藏成功，插入新的收藏记录
	 * @param id
	 * @param openid
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/insertCollect")
	@ResponseBody
	public String insertCollect(String id, String openid) throws IOException{
		String collectId = IDUtils.getID().toString();/*实体类自动生成 id*/
		TCollect tCollect = new TCollect();
		tCollect.setCollectId(collectId);
		tCollect.setId(id);
		tCollect.setOpenid(openid);
		collecteService.insertCollect(tCollect);
		return "true";
	}
}
