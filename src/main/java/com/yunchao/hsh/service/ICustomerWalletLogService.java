package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.WalletLogResp;
import com.yunchao.hsh.model.CustomerWalletLog;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 用户钱包出入账记录
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7 0007 15:59
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface ICustomerWalletLogService {
    int delete(Long id);

    int save(CustomerWalletLog record);

    CustomerWalletLog getById(Long id);

    int update(CustomerWalletLog record);

    List<CustomerWalletLog> getByCustomerId(Long id);

    List<CustomerWalletLog> getList(HashMap<String, Object> map);

    PageInfo<CustomerWalletLog> getPage(HashMap<String, Object> map, int pageNum, int pageSize);

    List<WalletLogResp> getByCustomerIdScoreDetailList(Long customerId);
    PageInfo<WalletLogResp> getPageIntegral(long id, int page, int pageSize);
    List<WalletLogResp> getByCustomerIdBalanceList(long customerId);
}
