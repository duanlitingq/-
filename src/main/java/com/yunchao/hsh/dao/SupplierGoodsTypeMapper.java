package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.SupplierGoodsType;

import java.util.List;
import java.util.Map;

public interface SupplierGoodsTypeMapper {

    void insert(SupplierGoodsType supplierGoodsType);

    void update(SupplierGoodsType supplierGoodsType);

    List<SupplierGoodsType> listPage(Map<String,Object> map);

    void updateStatus(Map<String,Object> map);

    SupplierGoodsType getSupplierGoodsType(Long id);


    SupplierGoodsType getBuyName(Map<String, Object> map);

    List<SupplierGoodsType> listGoodsType(Map<String, Object> map);
}
