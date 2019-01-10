package com.yunchao.hsh.service.impl;

import com.yunchao.hsh.dao.IntegralRulesMapper;
import com.yunchao.hsh.model.IntegralRules;
import com.yunchao.hsh.service.IIntegralRulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class IIntegralRulesServiceImpl implements IIntegralRulesService {

    @Autowired
    private IntegralRulesMapper integralRulesMapper;

    @Override
    public int addIntegralRules(IntegralRules integralRules) {
        return integralRulesMapper.insertSelective(integralRules);
    }

    @Override
    public int uplIntegralRules(IntegralRules integralRules) {
        return integralRulesMapper.updateByPrimaryKeySelective(integralRules);
    }

    @Override
    public int delIntegralRules(Long id) {
        return integralRulesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<IntegralRules> selIntegralRules() {
        return integralRulesMapper.selectByExample(null);
    }

    @Override
    public IntegralRules selOneIntegralRules(Long id) {
        return  integralRulesMapper.selectByPrimaryKey(id);
    }
}
