package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SupplierGoodsMapper;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.model.SupplierGoods;
import com.yunchao.hsh.service.ISupplierGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Service
@Transactional
public class SupplierGoodsServiceImpl implements ISupplierGoodsService {

    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;
    @Override
    public PageInfo<SupplierGoodsResp> findPage(HashMap<String, Object> map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SupplierGoodsResp> list = supplierGoodsMapper.findPage(map) ;
        PageInfo<SupplierGoodsResp> pageInfo = new PageInfo<SupplierGoodsResp>(list) ;
        return pageInfo;
    }

    @Override
    public SupplierGoodsResp findById(Long id) {
        return supplierGoodsMapper.findById(id);
    }

    @Override
    public void insert(SupplierGoods su) {
        supplierGoodsMapper.insert(su);
    }

    @Override
    public void update(SupplierGoods su) {
        supplierGoodsMapper.update(su);
    }

    @Override
    public void updateStatus(SupplierGoods su) {
        supplierGoodsMapper.updateStatus(su);
    }

    @Override
    public void batchUpdateStatus(List<SupplierGoods> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SupplierGoods sg = list.get(i);
            supplierGoodsMapper.updateStatus(sg);
        }
    }
}
