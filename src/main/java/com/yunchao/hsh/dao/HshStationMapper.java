package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.StationResp;
import com.yunchao.hsh.model.HshStation;
import com.yunchao.hsh.model.HshStationExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HshStationMapper {
    int countByExample(HshStationExample example);

    int deleteByExample(HshStationExample example);

    int deleteByPrimaryKey(Long stationId);

    int insert(HshStation record);

    int insertSelective(HshStation record);

    List<HshStation> selectByExample(HshStationExample example);

    HshStation selectByPrimaryKey(Long stationId);

    int updateByExampleSelective(@Param("record") HshStation record, @Param("example") HshStationExample example);

    int updateByExample(@Param("record") HshStation record, @Param("example") HshStationExample example);

    int updateByPrimaryKeySelective(HshStation record);

    int updateByPrimaryKey(HshStation record);

    List<StationResp> getStationList();

    List<StationResp> getCondition(@Param("name")String name);
}