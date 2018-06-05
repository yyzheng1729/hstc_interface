package com.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.TUser;
import com.service.UserService;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/insert_user", produces="application/json;charset=utf-8")
	@ResponseBody
	public String insert_user(String openid, String nickName, String avatarUrl) throws IOException{
		
		nickName= new String(nickName.getBytes("ISO-8859-1"),"UTF-8");
		
		/**
		 * �Ȳ�ѯ���ݿ����Ƿ���ڴ��û��������򲻲��룬��û�У���ѵ�ǰ�û���Ϣ�������ݿ�
		 */
		TUser userone = userService.queryByOpenid(openid);

		if(userone == null){
			TUser tUser = new TUser();
			tUser.setAvatarurl(avatarUrl);
			tUser.setNickname(nickName);
			tUser.setOpenid(openid);
			userService.insert_user(tUser);
			return "true";
		}else{
			return "ture";
		}
	}
}
