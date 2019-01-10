package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierWalletLogResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SupplierWalletLog;

import java.util.Map;

/**
 * 供应商出入账记录
 */
public interface ISupplierWalletLogService {
    PageInfo<SupplierWalletLogResp> findPage(Map<String, Object> map, Integer pageNum, Integer pageSize);

    void insert(SupplierWalletLog log);

    SupplierWalletLogResp getById(Long id);
}
