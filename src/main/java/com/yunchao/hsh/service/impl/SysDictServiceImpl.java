package com.yunchao.hsh.service.impl;

import com.yunchao.hsh.dao.SysDictMapper;
import com.yunchao.hsh.model.SysDict;
import com.yunchao.hsh.model.SysDictExample;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 字典维护表
 */
@Service
@Transactional
public class SysDictServiceImpl extends BaseService implements ISysDictService {

    @Autowired
    private SysDictMapper sysDictMapper;

    @Override
    public List<SysDict> findDictList() {
        SysDictExample example = new SysDictExample();
        SysDictExample.Criteria criteria = example.createCriteria();
        criteria.andCodeEqualTo("UNIT");
        List<SysDict> list = this.sysDictMapper.selectByExample(example);
        return list;
    }
}
