package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.Wallet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 用户钱包service
 * @Author: 隗鹏
 * @CreateDate: 2018/11/8 0008 10:24
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface IWalletService {
    int delete(Long id);

    int save(Wallet record);

    Wallet getById(Long id);

    Wallet getByCustomerId(Long id);

    List<Wallet> getList(HashMap<String, Object> map);

    int update(Wallet record);

    PageInfo<Wallet> getPage(Map<String, Object> map, int
            pageNum, int pageSize);

    double sumCustomerPrice();
}
