package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TCollegeMapper;
import com.pojo.TCollege;
import com.service.CollegeService;

@Service
public class CollegeServiceImpl implements CollegeService {
	@Autowired
	TCollegeMapper tCollegeMapper;
	
	public List<TCollege> college(){
		return tCollegeMapper.selectByExample(null);
	}
	
	public TCollege collegeName(String collegeId){
		return tCollegeMapper.selectByPrimaryKey(collegeId);
	}
	
	public void college_add(TCollege tCollege){
		tCollegeMapper.insert(tCollege);
	}
	
	public void college_delete(String collegeId){
		tCollegeMapper.deleteByPrimaryKey(collegeId);
		System.out.println(tCollegeMapper.deleteByPrimaryKey(collegeId)+"=====================");
	}
	
	public void college_edit(TCollege tCollege){
		tCollegeMapper.updateByPrimaryKeySelective(tCollege);
	}

}
