package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.model.SupplierGoods;

import java.util.HashMap;
import java.util.List;

public interface ISupplierGoodsService {

    PageInfo<SupplierGoodsResp> findPage(HashMap<String, Object> map, Integer pageNum, Integer pageSize);

    public SupplierGoodsResp findById(Long id);

    public void insert(SupplierGoods su);

    public void update(SupplierGoods su);

    public void updateStatus(SupplierGoods su);

    void batchUpdateStatus(List<SupplierGoods> list);
}
