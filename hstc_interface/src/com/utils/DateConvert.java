package com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//转换时间的工具类
public class DateConvert {
	
	public Date toDateWithHour(String source) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toStringWithHour(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = simpleDateFormat.format(date);
		return time;
	}
	
	public Date toDate(String source) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return simpleDateFormat.parse(source);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String toString(Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String time = simpleDateFormat.format(date);
		return time;
	}
	
	public static void main(String[] args) throws ParseException {
		DateConvert convert = new DateConvert();
		
		String time = convert.toString(new Date());
		System.out.println(new Date().toString());
		System.out.println(time);
		
		Date date = convert.toDate("2018-03-31 20:44:25");
		System.out.println(date.toString());
	}

}
