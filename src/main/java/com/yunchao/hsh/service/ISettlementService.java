package com.yunchao.hsh.service;

import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.ShoppingCar;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;

import java.util.List;
import java.util.Map;

/**
 * wdz
 * 下订单业务接口
 */

public interface ISettlementService {

    //未付款订单单独结算
    Result singlePayment(Result result,Customer customer, Long orderId, Double haggle, Double integral, Long addressId)throws  Exception;

}
