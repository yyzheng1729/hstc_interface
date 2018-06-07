package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TRecruitmentMapper;
import com.pojo.TRecruitment;
import com.pojo.TRecruitmentExample;
import com.pojo.TRecruitmentExample.Criteria;
import com.service.RecruitmentService;

@Service
public class RecruitmentServiceImpl implements RecruitmentService {
	
	@Autowired
	TRecruitmentMapper tRecruitmentMapper;
	
	public List<TRecruitment> displayAll(){
		return tRecruitmentMapper.selectByExampleWithBLOBs(null);
	}
	
	public void add(TRecruitment tRecruitment){
		tRecruitmentMapper.insertSelective(tRecruitment);
	}
	
	public void deleteById(String id){
		tRecruitmentMapper.deleteByPrimaryKey(id);
	}
	
	public TRecruitment queryById(String id){
		return tRecruitmentMapper.selectByPrimaryKey(id);
	}
	
	public void edit(TRecruitment tRecruitment){
		tRecruitmentMapper.updateByPrimaryKeySelective(tRecruitment);
	}
	
	public List<TRecruitment> query_id_title_litimg(){
		return tRecruitmentMapper.query_id_title_litimg();
	}
	
	public List<TRecruitment> sowingMapList(String type){
		return tRecruitmentMapper.queryLitimg(type);
	}
	public List<TRecruitment> querryByCollegeId(String collegeId){
		TRecruitmentExample example = new TRecruitmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCollegeIdEqualTo(collegeId);
		return tRecruitmentMapper.selectByExampleWithBLOBs(example);
	}
	
	public List<TRecruitment> queryByTitle(String title){
//		TRecruitmentExample example = new TRecruitmentExample();
//		Criteria criteria = example.createCriteria();
//		criteria.andTitleEqualTo(title);
//		return tRecruitmentMapper.selectByExampleWithBLOBs(example);
		return tRecruitmentMapper.fuzzyQueryByTitle(title);
	}
}
