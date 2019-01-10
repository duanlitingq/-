package com.yunchao.hsh.service;

import com.yunchao.hsh.dto.resp.OrderGetLogResp;
import com.yunchao.hsh.model.HshActivityOrderGetLog;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 18:27
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public interface IHshActivityOrderGetLogService {

    List<HshActivityOrderGetLog> findByEndTimeEqules();

    void updateByOrderIdStatus(HshActivityOrderGetLog log);

    //批量插入记录
    void inserBatchOrderGetLog(String orderNo);

    List<OrderGetLogResp> selectByOrderId(String orderId) throws Exception;

    void updateByLogIdStatus(Long logId) throws Exception;

    List<String> selectByOrderIdGroupBy();

    Byte sumByOrderIdStatus(String orderId);
}
