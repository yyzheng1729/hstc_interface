package com.mapper;

import com.pojo.TComment;
import com.pojo.TCommentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCommentMapper {
    int countByExample(TCommentExample example);

    int deleteByExample(TCommentExample example);

    int deleteByPrimaryKey(String commentId);

    int insert(TComment record);

    int insertSelective(TComment record);

    List<TComment> selectByExampleWithBLOBs(TCommentExample example);

    List<TComment> selectByExample(TCommentExample example);

    TComment selectByPrimaryKey(String commentId);

    int updateByExampleSelective(@Param("record") TComment record, @Param("example") TCommentExample example);

    int updateByExampleWithBLOBs(@Param("record") TComment record, @Param("example") TCommentExample example);

    int updateByExample(@Param("record") TComment record, @Param("example") TCommentExample example);

    int updateByPrimaryKeySelective(TComment record);

    int updateByPrimaryKeyWithBLOBs(TComment record);

    int updateByPrimaryKey(TComment record);
}