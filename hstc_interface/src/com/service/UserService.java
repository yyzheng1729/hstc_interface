package com.service;

import com.pojo.TUser;

public interface UserService {
	/**
	 * 插入新用户信息
	 * @param tUser
	 */
	public void insert_user(TUser tUser);
	/**
	 * 根据微信用户openid查询当前用户信息
	 * @param openid
	 * @return
	 */
	public TUser queryByOpenid(String openid); 
}
