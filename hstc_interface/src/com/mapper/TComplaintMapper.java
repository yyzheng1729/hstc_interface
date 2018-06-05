package com.mapper;

import com.pojo.TComplaint;
import com.pojo.TComplaintExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TComplaintMapper {
    int countByExample(TComplaintExample example);

    int deleteByExample(TComplaintExample example);

    int deleteByPrimaryKey(String complaintId);

    int insert(TComplaint record);

    int insertSelective(TComplaint record);

    List<TComplaint> selectByExample(TComplaintExample example);

    TComplaint selectByPrimaryKey(String complaintId);

    int updateByExampleSelective(@Param("record") TComplaint record, @Param("example") TComplaintExample example);

    int updateByExample(@Param("record") TComplaint record, @Param("example") TComplaintExample example);

    int updateByPrimaryKeySelective(TComplaint record);

    int updateByPrimaryKey(TComplaint record);
}