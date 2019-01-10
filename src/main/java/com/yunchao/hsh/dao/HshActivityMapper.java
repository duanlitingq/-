package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.req.ActivityReqs;
import com.yunchao.hsh.dto.resp.ActivityItemResp;
import com.yunchao.hsh.model.HshActivity;
import com.yunchao.hsh.model.HshActivityExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HshActivityMapper {
    int countByExample(HshActivityExample example);

    int deleteByExample(HshActivityExample example);

    int deleteByPrimaryKey(Long activityId);

    int insert(HshActivity record);

    int insertSelective(HshActivity record);

    List<HshActivity> selectByExample(HshActivityExample example);

    HshActivity selectByPrimaryKey(Long activityId);

    int updateByExampleSelective(@Param("record") HshActivity record, @Param("example") HshActivityExample example);

    int updateByExample(@Param("record") HshActivity record, @Param("example") HshActivityExample example);

    int updateByPrimaryKeySelective(HshActivity record);

    int updateByPrimaryKeyPrice(ActivityReqs record);

    int updateByPrimaryKey(HshActivity record);

    ActivityItemResp getByActivityId(@Param("activityId") Long activityId);

    void updateByActivityId(@Param("activityId") Long activityId);
}