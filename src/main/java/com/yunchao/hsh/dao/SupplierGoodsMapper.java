package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.model.Supplier;
import com.yunchao.hsh.model.SupplierGoods;

import java.util.HashMap;
import java.util.List;

public interface SupplierGoodsMapper {
    List<SupplierGoodsResp> findPage(HashMap<String, Object> map);

    public SupplierGoodsResp findById(Long id);

    public void insert(SupplierGoods su);

    public void update(SupplierGoods su);

    public void updateStatus(SupplierGoods su);

}
