package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SupplierWalletLogResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SupplierWalletLog;

import java.util.List;
import java.util.Map;

/**
 * 供应商余额出入
 */
public interface SupplierWalletLogMapper {


    void insert(SupplierWalletLog swLog);

    List<SupplierWalletLogResp> findPage(Map<String, Object> map);

    /***
     * @Description: TODO
     * @param id
     * @return com.yunchao.hsh.dto.resp.SupplierWalletLogResp
     * @throws
     * @author wdz
     * @date 2019/1/3 13:19 
     */
    SupplierWalletLogResp getById(Long id);
}
