package com.pojo;

public class TCollege {
    private String collegeId;

    private String collegeName;

    private String collegeImg;

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId == null ? null : collegeId.trim();
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName == null ? null : collegeName.trim();
    }

    public String getCollegeImg() {
        return collegeImg;
    }

    public void setCollegeImg(String collegeImg) {
        this.collegeImg = collegeImg == null ? null : collegeImg.trim();
    }
}