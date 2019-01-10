package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.HshCustomerWithdrawalsMappper;
import com.yunchao.hsh.dao.WalletMapper;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.HshCustomerWithdrawals;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.ICustomerWithdrawalsService;
import com.yunchao.hsh.utils.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户提现
 *
 * @Authher:Zhai Qing
 * @Date: 2018/12/11 10:42
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Service
@Transactional
public class CustomerWithdrawalsServiceImpl extends BaseService implements ICustomerWithdrawalsService {

    @Autowired
    private HshCustomerWithdrawalsMappper withdrawalsMappper;
    @Autowired
    private WalletMapper walletMapper;
    @Override
    public double sumCashCustomerPirce() {
        Double countNum = this.withdrawalsMappper.sumCashCustomerPirce();
        if (countNum != null) {
            return countNum;
        }
        return 0;
    }

    @Override
    public int saveWithdrawals(HshCustomerWithdrawals hshCustomerWithdrawals) {
      return   withdrawalsMappper.saveWithdrawals(hshCustomerWithdrawals);
    }

    @Override
    public PageInfo<HshCustomerWithdrawals> getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //获取所有提现列表
        List<HshCustomerWithdrawals> hshCustomerWithdrawals = withdrawalsMappper.findAll();
        PageInfo<HshCustomerWithdrawals> page=new PageInfo<HshCustomerWithdrawals>(hshCustomerWithdrawals);
        return page;
    }

    @Override
    public HshCustomerWithdrawals findTotal() {
        HshCustomerWithdrawals customerWithdrawals=new HshCustomerWithdrawals();
        //获取已提现金额
        Double countNum = this.withdrawalsMappper.sumCashCustomerPirce();
        if (countNum!=null){
            customerWithdrawals.setTotalWithdrawals(countNum);
        }else {
            customerWithdrawals.setTotalWithdrawals(0.0);
        }
        //获取个人总金额
        Double aDouble = walletMapper.sumCustomerPrice();
        if (aDouble!=null){
            customerWithdrawals.setTotalMoney(aDouble);
        }else {
            customerWithdrawals.setTotalMoney(0.0);
        }
        return customerWithdrawals;
    }

    @Override
    public int updateState(Long id) {
        return withdrawalsMappper.updateState(id);
    }
}
