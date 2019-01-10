package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierTypeResp;
import com.yunchao.hsh.model.SupplierType;

import java.util.HashMap;
import java.util.List;

public interface ISupplierTypeService {
    void batchUpdateStatus(List<SupplierTypeResp> list, Integer status);

    SupplierTypeResp findById(Long valueOf);

    void updateStatus(SupplierType su);

    void insert(SupplierType su);

    void update(SupplierType su);

    PageInfo<SupplierTypeResp> getPage(HashMap<String, Object> map, int pageNum, int pageSize);

    SupplierTypeResp findByName(String name, Long id);

    List<SupplierTypeResp> findList(Integer status);
}
