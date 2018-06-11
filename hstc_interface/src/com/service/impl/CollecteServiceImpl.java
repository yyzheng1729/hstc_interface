package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mapper.TCollectMapper;
import com.pojo.TCollect;
import com.pojo.TCollectExample;
import com.pojo.TCollectExample.Criteria;
import com.service.CollecteService;

@Service
public class CollecteServiceImpl implements CollecteService {
	@Autowired
	TCollectMapper tCollectMapper;
	
	public List<TCollect> display_collection(){
		return tCollectMapper.selectByExample(null);
	}
	public void deleteByCollectId(String collectId){
		tCollectMapper.deleteByPrimaryKey(collectId);
	}
	public List<TCollect> queryMyCollect(String openid){
		return tCollectMapper.TCollectResultMap(openid);
	}
	public TCollect queryByIdAndOpenid(String id, String openid){
		TCollectExample example = new TCollectExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		criteria.andOpenidEqualTo(openid);
		List<TCollect> selectByExample = tCollectMapper.selectByExample(example);
		if( selectByExample != null && !selectByExample.isEmpty()){
			return selectByExample.get(0);
		}else{
			return null;
		}
	}
	public void deleteByIdAndOpenid(String id, String openid){
		TCollectExample example = new TCollectExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		criteria.andOpenidEqualTo(openid);
		tCollectMapper.deleteByExample(example);
	}
	
	public void insertCollect(TCollect tCollect){
		tCollectMapper.insert(tCollect);
	}
}
