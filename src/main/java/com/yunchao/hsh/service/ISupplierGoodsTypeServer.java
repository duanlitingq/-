package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.SupplierGoodsType;

import java.util.List;
import java.util.Map;

public interface ISupplierGoodsTypeServer {

    void insert(SupplierGoodsType supplierGoodsType);

    void update(SupplierGoodsType supplierGoodsType);

    PageInfo<SupplierGoodsType> listPage(Map<String,Object> map,Integer pageNum,Integer  pageSize);

    void updateStatus(Map<String,Object> map);

    SupplierGoodsType getSupplierGoodsType(Long id);

    /**
     * 根据名称，店铺编号，类别编号（!=）查询
     * @param name
     * @param parentId
     * @param typeId
     * @return
     */
    SupplierGoodsType getBuyName(String name, Long parentId, Long typeId);

    /**
     * 获取店铺商品类别列表List
     * @param parentId
     * @return
     */
    List<SupplierGoodsType> listGoodsType(Long parentId,Integer status);
}
