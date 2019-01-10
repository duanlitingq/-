package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SupplierWalletLogMapper;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.dto.resp.SupplierWalletLogResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SupplierWalletLog;
import com.yunchao.hsh.service.ISupplierWalletLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SupplierWalletLogServiceImpl implements ISupplierWalletLogService {
    @Autowired
    private SupplierWalletLogMapper supplierWalletLogMapper;
    @Override
    public PageInfo<SupplierWalletLogResp> findPage(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SupplierWalletLogResp> list = supplierWalletLogMapper.findPage(map) ;
        PageInfo<SupplierWalletLogResp> pageInfo = new PageInfo<SupplierWalletLogResp>(list) ;
        return pageInfo;
    }

    @Override
    public void insert(SupplierWalletLog log) {
        supplierWalletLogMapper.insert(log);
    }

    @Override
    public SupplierWalletLogResp getById(Long id) {
        return supplierWalletLogMapper.getById(id);
    }
}
