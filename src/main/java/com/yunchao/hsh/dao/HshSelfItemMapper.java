package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.ScoreItemResp;
import com.yunchao.hsh.model.HshSelfItem;
import com.yunchao.hsh.model.HshSelfItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HshSelfItemMapper {
    int countByExample(HshSelfItemExample example);

    int deleteByExample(HshSelfItemExample example);

    int deleteByPrimaryKey(Long itemId);

    int insert(HshSelfItem record);

    int insertSelective(HshSelfItem record);

    List<HshSelfItem> selectByExample(HshSelfItemExample example);

    HshSelfItem selectByPrimaryKey(Long itemId);

    int updateByExampleSelective(@Param("record") HshSelfItem record, @Param("example") HshSelfItemExample example);

    int updateByExample(@Param("record") HshSelfItem record, @Param("example") HshSelfItemExample example);

    int updateByPrimaryKeySelective(HshSelfItem record);

    int updateByPrimaryKey(HshSelfItem record);

    ScoreItemResp getByItemId(Long itemId);

    List<ScoreItemResp> findLessOrEquelsScore(@Param("itemIntegral") Double currentScore);

    List<ScoreItemResp> selectByExampleScoreResp(HshSelfItemExample example);
}