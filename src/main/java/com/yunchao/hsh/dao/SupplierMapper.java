package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.Supplier;

import java.util.HashMap;
import java.util.List;

public interface SupplierMapper {

    List<SupplierResp> findPage(HashMap<String, Object> map);

    public SupplierResp findById(Long id);

    public void insert(Supplier su);

    public void update(Supplier su);

    public void updateStatus(Supplier su);

    //修改店铺金额
    void updateTotalMoney(SupplierResp supplier);
}
