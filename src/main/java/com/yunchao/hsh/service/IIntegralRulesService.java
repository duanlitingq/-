package com.yunchao.hsh.service;

import com.yunchao.hsh.model.IntegralRules;

import java.util.List;

public interface IIntegralRulesService {
    //添加规则
    int addIntegralRules(IntegralRules integralRules);
    //修改规则
    int  uplIntegralRules(IntegralRules integralRules);
    //删除
    int  delIntegralRules(Long id);
    //查询
    List<IntegralRules> selIntegralRules();
    //查询一条数据
    IntegralRules selOneIntegralRules(Long id);
}
