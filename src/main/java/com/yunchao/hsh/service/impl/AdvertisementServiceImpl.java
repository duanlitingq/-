package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.AdvertisementMapper;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.model.Advertisement;
import com.yunchao.hsh.service.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdz on 2018/11/7
 * Remarks:
 */
@Service
@Transactional
public class AdvertisementServiceImpl implements IAdvertisementService {

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Override
    public PageInfo<AdvertisementResp> getPage(Map<String, Object> params, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<AdvertisementResp> list = advertisementMapper.getPage(params) ;
        PageInfo<AdvertisementResp> pageInfo = new PageInfo<AdvertisementResp>(list) ;
        return pageInfo;
    }

    @Override
    public void update(Advertisement ad) {
        ad.setCreateTime(new Date());
        advertisementMapper.update(ad);
    }

    @Override
    public void save(Advertisement ad) {
        ad.setCreateTime(new Date());
        ad.setColumn1("");
        ad.setColumn2(0);
        ad.setColumn3(0l);
        advertisementMapper.save(ad);
    }

    @Override
    public List<AdvertisementResp> getList(Integer status) {
        return advertisementMapper.getList(status);
    }

    @Override
    public void doUpdateAdStatus(Long id, Integer status) {
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("status",status);
        advertisementMapper.doUpdateAdStatus(map);
    }

    @Override
    public void batchUpdateAdStatus(String adIds, Integer status) {
        Map<String,Object> map = new HashMap<>();
        String[] split = adIds.split(",");
        int len = split.length;
        for (int i = 0; i < len; i++) {
            String id = split[i];
            map.put("id",id);
            map.put("status",status);
            advertisementMapper.doUpdateAdStatus(map);
        }
    }

    @Override
    public AdvertisementResp getById(Long id) {
        return advertisementMapper.getById(id);
    }
}
