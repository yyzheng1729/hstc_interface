package com.pojo;

import java.util.List;

public class Pag {
	private int totalPage;
	private long totalData;
	private List<TRecruitment> tRecruitment;
	private List<TCollege> tCollege;

	public List<TCollege> gettCollege() {
		return tCollege;
	}
	public void settCollege(List<TCollege> tCollege) {
		this.tCollege = tCollege;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public long getTotalData() {
		return totalData;
	}
	public void setTotalData(long totalData) {
		this.totalData = totalData;
	}
	public List<TRecruitment> gettRecruitment() {
		return tRecruitment;
	}
	public void settRecruitment(List<TRecruitment> tRecruitment) {
		this.tRecruitment = tRecruitment;
	}
	
}
