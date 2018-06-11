package com.service;

import com.pojo.TComplaint;

public interface ComplaintService {
	/**
	 * 根据用户编号和招聘信息编号，查询当前用户是否投诉了当前招聘信息
	 * @param id
	 * @param openid
	 * @return
	 */
	public TComplaint queryByIdAndOpenid(String id, String openid);
	
	/**
	 * 插入投诉信息
	 * @param tComplaint
	 */
	public void insertComplaint(TComplaint tComplaint);
}
