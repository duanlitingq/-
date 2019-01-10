package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.CustomerWalletLogMapper;
import com.yunchao.hsh.dto.resp.PageResultResp;
import com.yunchao.hsh.dto.resp.WalletLogResp;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.utils.superdir.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

/**
 * @Description: 用户钱包出入账记录
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7 0007 16:00
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Service
@Transactional
public class CustomerWalletLogServiceImpl implements ICustomerWalletLogService {

    @Autowired
    private CustomerWalletLogMapper customerWalletLogMapper;

    @Override
    public int delete(Long id) {
        return customerWalletLogMapper.delete(id);
    }

    @Override
    public int save(CustomerWalletLog record) {
        return customerWalletLogMapper.insert(record);
    }

    @Override
    public CustomerWalletLog getById(Long id) {
        return customerWalletLogMapper.findById(id);
    }

    @Override
    public int update(CustomerWalletLog record) {
        return customerWalletLogMapper.update(record);
    }

    @Override
    public List<CustomerWalletLog> getByCustomerId(Long id) {
        return customerWalletLogMapper.findByCustomerId(id);
    }

    @Override
    public List<CustomerWalletLog> getList(HashMap<String, Object> map) {

        return customerWalletLogMapper.findList(map);
    }

    @Override
    public PageInfo<CustomerWalletLog> getPage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CustomerWalletLog> list = customerWalletLogMapper.findList(map);
        PageInfo<CustomerWalletLog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public List<WalletLogResp> getByCustomerIdScoreDetailList(Long customerId) {
        return this.customerWalletLogMapper.getByCustomerIdScoreDetailList(customerId);
    }

    @Override
    public PageInfo<WalletLogResp> getPageIntegral(long id, int page, int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<WalletLogResp> customerIdBalanceList = customerWalletLogMapper.getByCustomerIdBalanceList(id);
        PageInfo<WalletLogResp> info=new PageInfo<>(customerIdBalanceList);
        return info;
    }

    @Override
    public List<WalletLogResp> getByCustomerIdBalanceList(long customerId) {
        return  customerWalletLogMapper.getByCustomerIdBalanceList(customerId);
    }
}
