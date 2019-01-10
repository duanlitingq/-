package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.OrderGetLogResp;
import com.yunchao.hsh.model.HshActivityOrderGetLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 18:38
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public interface HshActivityOrderGetLogMapper {

    List<HshActivityOrderGetLog> findByEndTimeEqules();

    void updateByOrderIdStatus(HshActivityOrderGetLog orderGetLog);

    void inserBatchOrderGetLog(List<HshActivityOrderGetLog> listLog);

    List<OrderGetLogResp> selectByOrderId(String orderId);

    void updateByLogIdStatus(HshActivityOrderGetLog logId);

    List<String> findByOrderIdGroupBy();

    Byte sumByOrderIdStatus(@Param("orderId") String orderId);
}
