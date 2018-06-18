package com.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.TComplaint;
import com.pojo.TRecruitment;
import com.service.ComplaintService;
import com.service.RecruitmentService;
import com.utils.IDUtils;

@Controller
public class ComplaintController {
	@Autowired
	ComplaintService complaintService;
	@Autowired
	RecruitmentService recruitmentService;
	
	
	@RequestMapping(value="/insertComplaint")
	@ResponseBody
	public String insertComplaint(String id, String openid)throws IOException{
		String complaintId = IDUtils.getID().toString();/*实体类自动生成 id*/
		TComplaint tComplaint = new TComplaint();
		tComplaint.setComplaintId(complaintId);
		tComplaint.setId(id);
		tComplaint.setOpenid(openid);
		complaintService.insertComplaint(tComplaint);
		
		TRecruitment tr1 = recruitmentService.queryById(id);
		String count1 = tr1.getComplaintCount();
		int count1_int = Integer.parseInt(count1);
		String count2 = Integer.toString(count1_int+1);
		
		TRecruitment tr2 = new TRecruitment();
		tr2.setId(id);
		tr2.setComplaintCount(count2);
		recruitmentService.edit(tr2);
		
		return "true";
	}
}
