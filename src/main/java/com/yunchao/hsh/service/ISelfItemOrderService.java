package com.yunchao.hsh.service;

import com.yunchao.hsh.utils.superdir.Result;

public interface ISelfItemOrderService {

    Result getPage(int pageNum, int pageSize, String orderId);

    Result updateOrderStatus(String orderId, String status);

    Result createOrder(Long itemId, Long customerId, Long logisticsId) throws Exception;

    Result selectByCustomerIdOrderList(Long customerId, int pageNum, int pageSize);

    Result selectByOrderIdOrderDetails(String orderId);
}
