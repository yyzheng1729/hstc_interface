package com.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pojo.TComplaint;
import com.service.ComplaintService;
import com.utils.IDUtils;

@Controller
public class ComplaintController {
	@Autowired
	ComplaintService complaintService;
	
	@RequestMapping(value="/insertComplaint")
	@ResponseBody
	public String insertComplaint(String id, String openid)throws IOException{
		String complaintId = IDUtils.getID().toString();/*实体类自动生成 id*/
		TComplaint tComplaint = new TComplaint();
		tComplaint.setComplaintId(complaintId);
		tComplaint.setId(id);
		tComplaint.setOpenid(openid);
		complaintService.insertComplaint(tComplaint);
		return "true";
	}
}
