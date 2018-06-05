package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TUserMapper;
import com.pojo.TUser;
import com.service.UserService;

@Service 
public class UserServiceImpl implements UserService{
	@Autowired
	TUserMapper tUserMapper;
	
	public void insert_user(TUser tUser){
		tUserMapper.insert(tUser);
	}
	
	public TUser queryByOpenid(String openid){
		return tUserMapper.selectByPrimaryKey(openid);
	}
}
