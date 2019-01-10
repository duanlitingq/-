package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.IntegralRules;
import com.yunchao.hsh.model.IntegralRulesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IntegralRulesMapper {
    int countByExample(IntegralRulesExample example);

    int deleteByExample(IntegralRulesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(IntegralRules record);

    int insertSelective(IntegralRules record);

    List<IntegralRules> selectByExample(IntegralRulesExample example);

    IntegralRules selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") IntegralRules record, @Param("example") IntegralRulesExample example);

    int updateByExample(@Param("record") IntegralRules record, @Param("example") IntegralRulesExample example);

    int updateByPrimaryKeySelective(IntegralRules record);

    int updateByPrimaryKey(IntegralRules record);
}