package com.service;

import java.util.List;

import com.pojo.TCollect;

public interface CollecteService {
	/**
	 * 查询所有的收藏信息
	 * @return
	 */
	public List<TCollect> display_collection();
	/**
	 * 根据收藏编号删除收藏信息
	 * @param collectid
	 */
	public void deleteByCollectId(String collectId);
	/**
	 * 查询当前用户的收藏记录
	 */
	public List<TCollect> queryMyCollect(String openid);
	/**
	 * 根据用户编号和文章编号，查询是否存在此用户的收藏记录
	 */
	public TCollect queryByIdAndOpenid(String id, String openid);
	
	/**
	 * 根据用户编号和文章编号,删除收藏记录
	 * @param id
	 * @param openid
	 */
	public void deleteByIdAndOpenid(String id, String openid);
	
	/**
	 * 新增收藏记录
	 * @param tCollect
	 */
	public void insertCollect(TCollect tCollect);
}
