package com.mapper;

import com.pojo.TRecruitment;
import com.pojo.TRecruitmentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRecruitmentMapper {
    int countByExample(TRecruitmentExample example);

    int deleteByExample(TRecruitmentExample example);

    int deleteByPrimaryKey(String id);

    int insert(TRecruitment record);

    int insertSelective(TRecruitment record);

    List<TRecruitment> selectByExampleWithBLOBs(TRecruitmentExample example);

    List<TRecruitment> selectByExample(TRecruitmentExample example);

    TRecruitment selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TRecruitment record, @Param("example") TRecruitmentExample example);

    int updateByExampleWithBLOBs(@Param("record") TRecruitment record, @Param("example") TRecruitmentExample example);

    int updateByExample(@Param("record") TRecruitment record, @Param("example") TRecruitmentExample example);

    int updateByPrimaryKeySelective(TRecruitment record);

    int updateByPrimaryKeyWithBLOBs(TRecruitment record);

    int updateByPrimaryKey(TRecruitment record);
    
    List<TRecruitment> query_id_title_litimg();
    
    List<TRecruitment> queryLitimg(String type);
}