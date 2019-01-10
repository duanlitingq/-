package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.HshActivityWelfareMapper;
import com.yunchao.hsh.model.HshActivityWelfare;
import com.yunchao.hsh.model.HshActivityWelfareExample;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.IActivityWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/28 15:55
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Service
@Transactional
public class ActivityWelfareServiceImpl extends BaseService implements IActivityWelfareService {

    @Autowired
    private HshActivityWelfareMapper activityWelfareMapper;

    @Override
    public PageInfo<HshActivityWelfare> getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        HshActivityWelfareExample example = new HshActivityWelfareExample();
        List<HshActivityWelfare> list = this.activityWelfareMapper.selectByExample(example);
        PageInfo<HshActivityWelfare> info = new PageInfo<HshActivityWelfare>(list);
        return info;
    }

    @Override
    public void save(HshActivityWelfare welfare) {
        this.activityWelfareMapper.insertSelective(welfare);
    }

    @Override
    public void update(HshActivityWelfare welfare) {
        this.activityWelfareMapper.updateByPrimaryKeySelective(welfare);
    }

    @Override
    public void doUpdateAdStatus(Long id, Integer status) {
        HshActivityWelfare record = new HshActivityWelfare();
        record.setId(id);
        record.setWelfareStatus(Byte.valueOf(status.toString()));
        this.activityWelfareMapper.updateByPrimaryKey(record);
    }

    @Override
    public HshActivityWelfare getById(Long id) {
        HshActivityWelfare welfare = this.activityWelfareMapper.selectByPrimaryKey(id);
        return welfare;
    }

    @Override
    public List<HshActivityWelfare> selectAll() {
        HshActivityWelfareExample example = new HshActivityWelfareExample();
        example.createCriteria().andWelfareStatusEqualTo((byte) 1);
        List<HshActivityWelfare> hshActivityWelfares = this.activityWelfareMapper.selectByExample(example);
        return hshActivityWelfares;
    }

    @Override
    public HshActivityWelfare selectById(Long cusNum2) {
        HshActivityWelfare welfare = this.activityWelfareMapper.selectByPrimaryKey(cusNum2);
        return welfare;
    }
}
