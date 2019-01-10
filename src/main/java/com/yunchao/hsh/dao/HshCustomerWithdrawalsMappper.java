package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.HshCustomerWithdrawals;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/12/11 10:38
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public interface HshCustomerWithdrawalsMappper {

    Double sumCashCustomerPirce();

    int saveWithdrawals(HshCustomerWithdrawals hshCustomerWithdrawals);

    List<HshCustomerWithdrawals> findAll();

    int updateState(long id);
}
