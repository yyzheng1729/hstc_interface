package com.utils;


import java.util.Random;

//用来生成id生成
public class IDUtils {
	public static Long getID() {
		//取当前时间的长整形值包含毫秒
		long millis = System.currentTimeMillis();
		//加上两位随机数
		Random random = new Random();
		int end2 = random.nextInt(99);
		//如果不足两位前面补0
		String str = String.valueOf(millis).substring(0, 9) + String.format("%02d", end2);
		long id = new Long(str);
		return id;
	}
	
	public static void main(String[] args) {
		System.out.println(getID());
	}
}
