package com.yunchao.hsh.service.impl;

import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dao.*;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.ISettlementService;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.IDUtils;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class SettlementServiceImpl implements ISettlementService {

    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;
    @Autowired
    private SupplierOrderMapper supplierOrderMapper;
    @Autowired
    private SupplierOrderItemMapper supplierOrderItemMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private CustomerWalletLogMapper customerWalletLogMapper;
    @Autowired
    private IntegralRulesMapper integralRulesMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierWalletLogMapper supplierWalletLogMapper;

    //单独支付
    @Override
    public Result singlePayment(Result result,Customer customer, Long orderId, Double haggle, Double integral, Long addressId) throws Exception {
        Long userId = customer.getId();
        SupplierOrder order = new SupplierOrder();
        order.setId(orderId);
        order.setUserId(userId);
        SupplierOrderResp orderResp = supplierOrderMapper.getByUserOrderId(order);
        Integer paryStatus = orderResp.getPayStatus();
        Double actMoney = 0.0;
        if(paryStatus != 2){
            if(ObjectUtils.isNotEmpty(haggle) && haggle > 0){
                if(ObjectUtils.isNotEmpty(integral) && integral > 0){
                    if(haggle >= integral){
                        order.setAddressId(addressId);
                        order.setHagglePrice(haggle);
                        order.setIntegral(integral);
                        Wallet wallet = walletMapper.findByCustomerId(userId);
                        Double score = wallet.getScore();
                        if(score > integral){
                            //更新用户钱包数据
                            walletMapper.updateByCustomerIdWallet(BigDecimalUtils.subtract(score,integral).doubleValue(),userId);
                           insertCustomerWalletLog(userId,order.getTradeNo());
                           actMoney = haggle;
                        }else{
                            result.setF("用户积分不足");
                        }
                    }else{
                        result.setF("订单积分支付与议价金额数据有误");
                    }
                }else{
                    order.setAddressId(addressId);
                    order.setHagglePrice(haggle);
                    order.setIntegral(0.0);
                    actMoney = haggle;
                }
            }else{
                Double orderMoney = orderResp.getOrderMoney();
                if(ObjectUtils.isNotEmpty(integral) && integral > 0){
                    if(orderMoney >= integral){
                        order.setAddressId(addressId);
                        order.setHagglePrice(0.0);
                        order.setIntegral(integral);
                        Wallet wallet = walletMapper.findByCustomerId(userId);
                        Double score = wallet.getScore();
                        if(score > integral){
                            //更新用户钱包数据
                            walletMapper.updateByCustomerIdWallet(BigDecimalUtils.subtract(score,integral).doubleValue(),userId);
                            insertCustomerWalletLog(userId,order.getTradeNo());
                            actMoney = haggle;
                        }else{
                            result.setF("用户积分不足");
                        }
                    }else{
                        result.setF("订单积分支付与订单金额数据有误");
                    }
                }else{
                    order.setAddressId(addressId);
                    order.setHagglePrice(0.0);
                    order.setIntegral(0.0);
                    actMoney = haggle;
                }
            }
            IntegralRules integralRules = integralRulesMapper.selectByPrimaryKey(2L);
            //规则之后的金额
            BigDecimal multiply = BigDecimalUtils.multiply(actMoney, integralRules.getIntegral());
            //总金额-供应商的金额=平台收取金额
            BigDecimal commission = BigDecimalUtils.subtract(actMoney, multiply.doubleValue());
            //店铺余额修改
            SupplierResp supplier = supplierMapper.findById(order.getSupplierId());
            supplier.setTotalMoney(BigDecimalUtils.add(supplier.getTotalMoney(), multiply.doubleValue()).doubleValue());
            supplierMapper.updateTotalMoney(supplier);
            //添加记录
            insertSupplierWalletLog(multiply.doubleValue(),supplier.getId(),orderId,userId);
            result.setS("","支付成功");
        }else{
            result.setF("订单已支付");
        }
        return result;
    }

    //店铺出入账记录
    private void insertSupplierWalletLog(Double money, Long supplierId,Long orderId,Long userId) {
        SupplierWalletLog log = new SupplierWalletLog();
        log.setSupplierId(supplierId);
        log.setRemark("收入");
        log.setOrderId(orderId);
        log.setInOrOut(1);
        log.setFrontUserId(userId);
        log.setCreateTime(new Date());
        log.setChangeMoney(+money);
        log.setColumn3(0L);
        log.setColumn2(0);
        log.setColumn1("");
        supplierWalletLogMapper.insert(log);
    }
    //用户出入账记录
    private void insertCustomerWalletLog(Long userId,String tradeNo) {
        CustomerWalletLog log = new CustomerWalletLog();
        log.setColumn3(0L);
        log.setColumn2(0);
        log.setColumn1(" ");
        log.setRemark("购买商品消费");
        log.setUuid("");
        log.setOperationId(0L);
        log.setStatus(0);
        log.setCreateDate(new Date());
        log.setCustomerId(userId);
        log.setType(4);
        log.setPayMode(4);
        log.setTradeNo(tradeNo);
        customerWalletLogMapper.insert(log);
    }



}
