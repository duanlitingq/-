package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.HshCustomerWithdrawals;

import java.util.HashMap;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/12/11 10:41
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public interface ICustomerWithdrawalsService {

    double sumCashCustomerPirce();
    int saveWithdrawals(HshCustomerWithdrawals hshCustomerWithdrawals);
    PageInfo<HshCustomerWithdrawals> getPage(int pageNum, int pageSize);
    HshCustomerWithdrawals findTotal();
    int updateState(Long id);

}
