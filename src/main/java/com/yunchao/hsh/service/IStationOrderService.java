package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.req.StationOrderReq;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.HshStationOrder;
import com.yunchao.hsh.utils.superdir.Result;

import javax.servlet.http.HttpServletRequest;

public interface IStationOrderService {

    PageInfo<HshStationOrder> findStationOrderList();

    Result insertStationOrder(HttpServletRequest request, StationOrderReq stationOrderReq, Customer customer) throws Exception;

    HshStationOrder selectByOrderId(String orderId);

    void updateByOrderId(HshStationOrder stationOrder);
}
