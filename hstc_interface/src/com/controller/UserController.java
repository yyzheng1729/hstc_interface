package com.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.pojo.TUser;
import com.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	/**
	 * ����΢�ŵ�¼�û���Ϣ
	 * @param openid
	 * @param nickName
	 * @param avatarUrl
	 * @param province
	 * @param city
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/insert_user", produces="application/json;charset=utf-8")
	@ResponseBody
	public String insert_user(String openid, String nickName, String avatarUrl, String province, String city ) throws IOException{
		
		nickName= new String(nickName.getBytes("ISO-8859-1"),"UTF-8");
		province= new String(province.getBytes("ISO-8859-1"),"UTF-8");
		city= new String(city.getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println(openid);
		System.out.println(nickName);
		System.out.println(avatarUrl);
		System.out.println(province);
		System.out.println(city);
		
		/**
		 * �Ȳ�ѯ���ݿ����Ƿ���ڴ��û��������򲻲��룬��û�У���ѵ�ǰ�û���Ϣ�������ݿ�
		 */
		TUser userone = userService.queryByOpenid(openid);

		if(userone == null){
			TUser tUser = new TUser();
			tUser.setAvatarurl(avatarUrl);
			tUser.setNickname(nickName);
			tUser.setOpenid(openid);
			tUser.setProvince(province);
			tUser.setCity(city);
			userService.insert_user(tUser);
			return "true";
		}else{
			return "true";
		}
	}
	
	@RequestMapping(value="/queryByOpenid",produces="application/json;charset=utf-8")
	@ResponseBody
	public String queryByOpenid(String openid)throws IOException{
		TUser userone = userService.queryByOpenid(openid);
		Gson gson = new Gson();
		return gson.toJson(userone);
	}
}
