package com.yunchao.hsh.service;

import com.yunchao.hsh.dto.resp.OrderDetailsResp;
import com.yunchao.hsh.model.HshActivityOrder;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.utils.superdir.Result;

public interface IActivityOrderService {

    Result getOrderPageList(int pageNum, int pageSize, String orderId, String activityId) throws Exception;

    Result updateGetNum(String orderId, SysUser adminUser) throws Exception;

    HshActivityOrder selectByOrderId(String orderId) throws Exception;

    Result createOrder(Long activeId, Long customerId, Long logisticsId,Long welfareId) throws Exception;

    Result updateById(HshActivityOrder record) throws Exception;

    com.yunchao.hsh.utils.superdir.sub.Result selectOrderList(Long userId, int page, int pageSize) throws Exception;

    OrderDetailsResp getOrderDetail(String orderId) throws Exception;

    void updateOrderStatus(String orderId);
}
