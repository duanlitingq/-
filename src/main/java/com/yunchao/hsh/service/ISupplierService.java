package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.Supplier;

import java.util.HashMap;
import java.util.List;

public interface ISupplierService {

    PageInfo<SupplierResp> getPage(HashMap<String, Object> map, int pageNum, int pageSize);

    public SupplierResp findById(Long id);

    public void insert(Supplier su);

    public void update(Supplier su);

    public void updateStatus(Supplier su);

    void batchUpdateStatus(List<Supplier> list, Integer status);

    //修改店铺金额
    void updateTotalMoney(SupplierResp supplierResp);

}
