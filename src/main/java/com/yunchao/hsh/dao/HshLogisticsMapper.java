package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.model.HshLogisticsExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HshLogisticsMapper {

    int countByExample(HshLogisticsExample example);

    int deleteByExample(HshLogisticsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HshLogistics record);

    int insertSelective(HshLogistics record);

    List<HshLogistics> selectByExample(HshLogisticsExample example);

    List<HshLogistics> selectAllLogistics(Long id);

    HshLogistics selectByPrimaryKey(Long id);

    HshLogistics selectBycustomer(Long customerId);

    int updateByExampleSelective(@Param("record") HshLogistics record, @Param("example") HshLogisticsExample example);

    int updateByExample(@Param("record") HshLogistics record, @Param("example") HshLogisticsExample example);

    int updateByPrimaryKeySelective(HshLogistics record);

    int updateByPrimaryKey(HshLogistics record);
     HshLogistics getDefault(HshLogistics hshLogistics);
}