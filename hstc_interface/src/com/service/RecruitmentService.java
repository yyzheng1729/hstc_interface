package com.service;

import java.util.List;

import com.pojo.TRecruitment;

public interface RecruitmentService {
	/**
	 * 从数据库获取所有的招聘信息
	 * @return
	 */
	public List<TRecruitment> displayAll();
	/**
	 * 增加招聘信息
	 */
	public void add(TRecruitment tRecruitment);
	/**
	 * 根据id删除对应信息
	 * @param id
	 */
	public void deleteById(String id);
	/**
	 * 根据id查询对应信息
	 * @param id
	 * @return
	 */
	public TRecruitment queryById(String id);
	/**
	 * 更新招聘信息
	 * @param tRecruitment
	 */
	public void edit(TRecruitment tRecruitment);
	
	/**
	 * 查询招聘信息列表中的“编号，标题，缩略图”
	 * @return
	 */
	public List<TRecruitment> query_id_title_litimg();
	/**
	 * 查询出 type="首页轮播" 的图片链接
	 * @param type
	 * @return
	 */
	public List<TRecruitment> sowingMapList(String type);
	/**
	 * 根据学院编号，查找出当前学院的招聘信息
	 * @param collegeId
	 * @return
	 */
	public List<TRecruitment> querryByCollegeId(String collegeId);
	/**
	 * 根据招聘信息标题查找招聘信息
	 * @param title
	 * @return
	 */
	public List<TRecruitment> queryByTitle(String title);
}
