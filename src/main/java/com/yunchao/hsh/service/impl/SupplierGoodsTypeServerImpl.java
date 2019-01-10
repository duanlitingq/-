package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SupplierGoodsTypeMapper;
import com.yunchao.hsh.model.SupplierGoodsType;
import com.yunchao.hsh.service.ISupplierGoodsTypeServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺商品类别实现类
 */
@Service
@Transactional
public class SupplierGoodsTypeServerImpl implements ISupplierGoodsTypeServer {

    @Autowired
    private SupplierGoodsTypeMapper supplierGoodsTypeMapper;

    @Override
    public void insert(SupplierGoodsType supplierGoodsType) {
        init(supplierGoodsType);
        supplierGoodsTypeMapper.insert(supplierGoodsType);
    }

    private void init(SupplierGoodsType supplierGoodsType) {
        supplierGoodsType.setColumn1(" ");
        supplierGoodsType.setColumn2(0);
        supplierGoodsType.setColumn3(0L);
    }

    @Override
    public void update(SupplierGoodsType supplierGoodsType) {
        supplierGoodsTypeMapper.update(supplierGoodsType);
    }

    @Override
    public PageInfo<SupplierGoodsType> listPage(Map<String, Object> map,Integer pageNum,Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SupplierGoodsType> list = supplierGoodsTypeMapper.listPage(map) ;
        PageInfo<SupplierGoodsType> pageInfo = new PageInfo<SupplierGoodsType>(list) ;
        return pageInfo;
    }

    @Override
    public void updateStatus(Map<String, Object> map) {
        supplierGoodsTypeMapper.updateStatus(map);
    }

    @Override
    public SupplierGoodsType getSupplierGoodsType(Long id) {
        return supplierGoodsTypeMapper.getSupplierGoodsType(id);
    }

    @Override
    public SupplierGoodsType getBuyName(String name, Long parentId, Long typeId) {
        Map<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("parentId",parentId);
        map.put("id",typeId);
        return supplierGoodsTypeMapper.getBuyName(map);
    }

    @Override
    public List<SupplierGoodsType> listGoodsType(Long parentId,Integer status) {
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",parentId);
        map.put("status",status);
        return supplierGoodsTypeMapper.listGoodsType(map);
    }
}
