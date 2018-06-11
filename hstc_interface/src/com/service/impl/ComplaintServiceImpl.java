package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.mapper.TComplaintMapper;
import com.pojo.TComplaint;
import com.pojo.TComplaintExample;
import com.pojo.TComplaintExample.Criteria;
import com.service.ComplaintService;

@Controller
public class ComplaintServiceImpl implements ComplaintService {
	@Autowired
	TComplaintMapper tComplaintMapper;
	
	public TComplaint queryByIdAndOpenid(String id, String openid){
		TComplaintExample example = new TComplaintExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		criteria.andOpenidEqualTo(openid);
		List<TComplaint> selectByExample = tComplaintMapper.selectByExample(example);
		if( selectByExample != null && !selectByExample.isEmpty()){
			return selectByExample.get(0);
		}else{
			return null;
		}
	}
	
	public void insertComplaint(TComplaint tComplaint){
		System.out.println(tComplaint.getComplaintId());
		System.out.println(tComplaint.getId());
		System.out.println(tComplaint.getOpenid());
		tComplaintMapper.insert(tComplaint);
	}
}
