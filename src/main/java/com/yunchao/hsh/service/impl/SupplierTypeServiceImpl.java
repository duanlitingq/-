package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SupplierTypeMapper;
import com.yunchao.hsh.dto.resp.SupplierTypeResp;
import com.yunchao.hsh.model.Supplier;
import com.yunchao.hsh.model.SupplierType;
import com.yunchao.hsh.service.ISupplierTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class SupplierTypeServiceImpl implements ISupplierTypeService {

    @Autowired
    private SupplierTypeMapper supplierTypeMapper;

    @Override
    public void batchUpdateStatus(List<SupplierTypeResp> list, Integer status) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            SupplierType st = list.get(i);
            st.setStatus(status);
            supplierTypeMapper.updateStatus(st);
        }
    }

    @Override
    public SupplierTypeResp findById(Long id) {
        return supplierTypeMapper.findById(id);
    }

    @Override
    public void updateStatus(SupplierType su) {
        supplierTypeMapper.updateStatus(su);
    }

    @Override
    public void insert(SupplierType su) {
        supplierTypeMapper.insert(su);
    }

    @Override
    public void update(SupplierType su) {
        supplierTypeMapper.update(su);
    }

    @Override
    public PageInfo<SupplierTypeResp> getPage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SupplierTypeResp> list = supplierTypeMapper.findPage(map) ;
        PageInfo<SupplierTypeResp> pageInfo = new PageInfo<SupplierTypeResp>(list) ;
        return pageInfo;
    }

    @Override
    public SupplierTypeResp findByName(String name, Long id) {
        SupplierType st = new SupplierType();
        st.setId(id);
        st.setName(name);
        return supplierTypeMapper.findByName(st);
    }

    @Override
    public List<SupplierTypeResp> findList(Integer status) {
        return supplierTypeMapper.findList(status);
    }
}
