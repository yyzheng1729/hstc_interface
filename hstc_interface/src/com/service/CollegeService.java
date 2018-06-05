package com.service;

import java.util.List;

import com.pojo.TCollege;

public interface CollegeService {
	/**
	 * 获取学院分类列表
	 * @return
	 */
	public List<TCollege> college();
	/**
	 * 根据id查询对应学院信息
	 * @param id
	 * @return
	 */
	public TCollege collegeName(String collegeId);
	/**
	 * 添加学院信息
	 * @param tCollege
	 */
	public void college_add(TCollege tCollege);
	/**
	 * 根据 collegeId 删除对应的学院信息
	 * @param collegeId
	 */
	public void college_delete(String collegeId);
	/**
	 * 更新学院信息
	 * @param tCollege
	 */
	public void college_edit(TCollege tCollege);
}
