package com.mapper;

import com.pojo.TCollege;
import com.pojo.TCollegeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCollegeMapper {
    int countByExample(TCollegeExample example);

    int deleteByExample(TCollegeExample example);

    int deleteByPrimaryKey(String collegeId);

    int insert(TCollege record);

    int insertSelective(TCollege record);

    List<TCollege> selectByExample(TCollegeExample example);

    TCollege selectByPrimaryKey(String collegeId);

    int updateByExampleSelective(@Param("record") TCollege record, @Param("example") TCollegeExample example);

    int updateByExample(@Param("record") TCollege record, @Param("example") TCollegeExample example);

    int updateByPrimaryKeySelective(TCollege record);

    int updateByPrimaryKey(TCollege record);
}