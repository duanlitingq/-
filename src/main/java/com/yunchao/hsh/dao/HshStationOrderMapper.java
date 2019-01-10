package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshStationOrder;
import com.yunchao.hsh.model.HshStationOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshStationOrderMapper {
    int countByExample(HshStationOrderExample example);

    int deleteByExample(HshStationOrderExample example);

    int deleteByPrimaryKey(Long orderId);

    int insert(HshStationOrder record);

    int insertSelective(HshStationOrder record);

    List<HshStationOrder> selectByExample(HshStationOrderExample example);

    HshStationOrder selectByPrimaryKey(Long orderId);

    int updateByExampleSelective(@Param("record") HshStationOrder record, @Param("example") HshStationOrderExample example);

    int updateByExample(@Param("record") HshStationOrder record, @Param("example") HshStationOrderExample example);

    int updateByPrimaryKeySelective(HshStationOrder record);

    int updateByPrimaryKey(HshStationOrder record);

    int updateById(HshStationOrder record);
}