package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.WalletMapper;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.IWalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: java类作用描述
 * @Author: 隗鹏
 * @CreateDate: 2018/11/8 0008 10:25
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Service
@Transactional
public class WalletServiceImpl implements IWalletService {
    @Autowired
    private WalletMapper walletMapper;

    @Override
    public int delete(Long id) {
        return walletMapper.delete(id);
    }

    @Override
    public int save(Wallet record) {
        return walletMapper.insert(record);
    }

    @Override
    public Wallet getById(Long id) {
        return walletMapper.findById(id);
    }

    @Override
    public Wallet getByCustomerId(Long id) {
        return walletMapper.findByCustomerId(id);
    }

    @Override
    public List<Wallet> getList(HashMap<String, Object> map) {
        return walletMapper.findList(map);
    }

    @Override
    public int update(Wallet record) {
        return walletMapper.update(record);
    }

    @Override
    public PageInfo<Wallet> getPage(Map<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Wallet> list = walletMapper.findList(map);
        PageInfo<Wallet> pageInfo = new PageInfo<Wallet>(list);
        return pageInfo;
    }

    @Override
    public double sumCustomerPrice() {
        return this.walletMapper.sumCustomerPrice();
    }

    public void updateAndSaveLog(int type, int paymode, Customer customer, Wallet wallet, Double count) {
//        if (type == Constant.IN) {//支出
//            if (paymode == Constant.BALANCE) {//余额
//                wallet.setBurse(ArithUtil.sub(wallet.getBurse(), count));
//                wallet.setAmountOutBurse(ArithUtil.add(count, wallet.getAmountOutBurse()));//总支出余额
//            }
//            if (paymode == Constant.WECHAT) {//微信
//
//            }
//            if (paymode == Constant.SCORE) {//积分
//                wallet.setScore(wallet.getScore() - Integer.parseInt(count.toString()));
//                wallet.setAmountOutScore(wallet.getScore() + Integer.parseInt(count.toString()));//总支出积分
//            }
//            return false;
//        }
    }
}
