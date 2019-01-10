package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshActivityWelfare;
import com.yunchao.hsh.model.HshActivityWelfareExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HshActivityWelfareMapper {
    int countByExample(HshActivityWelfareExample example);

    int deleteByExample(HshActivityWelfareExample example);

    int deleteByPrimaryKey(Long id);

    int insert(HshActivityWelfare record);

    int insertSelective(HshActivityWelfare record);

    List<HshActivityWelfare> selectByExample(HshActivityWelfareExample example);

    HshActivityWelfare selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") HshActivityWelfare record, @Param("example") HshActivityWelfareExample example);

    int updateByExample(@Param("record") HshActivityWelfare record, @Param("example") HshActivityWelfareExample example);

    int updateByPrimaryKeySelective(HshActivityWelfare record);

    int updateByPrimaryKey(HshActivityWelfare record);
}