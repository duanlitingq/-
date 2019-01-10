package com.yunchao.hsh.dao;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.WalletLogResp;
import com.yunchao.hsh.model.CustomerWalletLog;

import java.util.HashMap;
import java.util.List;

public interface CustomerWalletLogMapper {
    int delete(Long id);

    int insert(CustomerWalletLog record);

    int insertSelective(CustomerWalletLog record);

    CustomerWalletLog findById(Long id);

    int update(CustomerWalletLog record);

    List<CustomerWalletLog> findByCustomerId(Long id);

    List<CustomerWalletLog> findList(HashMap<String, Object> map);
    
    List<WalletLogResp> getByCustomerIdScoreDetailList(Long customerId);
    //获取用户余额明细
    List<WalletLogResp> getByCustomerIdBalanceList(Long customerId);
}