package com.service;

import com.pojo.TUser;

public interface UserService {
	/**
	 * �������û���Ϣ
	 * @param tUser
	 */
	public void insert_user(TUser tUser);
	/**
	 * ����΢���û�openid��ѯ��ǰ�û���Ϣ
	 * @param openid
	 * @return
	 */
	public TUser queryByOpenid(String openid); 
}
