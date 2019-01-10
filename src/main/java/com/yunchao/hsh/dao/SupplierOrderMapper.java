package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.model.SupplierOrder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SupplierOrderMapper {


    //修改订单状态
    //void updateStatus(SupplierOrder so);
    //保存订单
    void insert(SupplierOrder supplierOrder);
    //获取订单
    SupplierOrderResp findById(Long id);
    //订单列表
    List<SupplierOrderResp> findPage(HashMap<String, Object> map);

    //修改订单状态
    void updateOrderStatus(SupplierOrder su);
    //修改支付状态
    void updatePayStatus(SupplierOrder su);

    //根据订单编号和用户编号查询订单
    SupplierOrderResp getByUserOrderId(SupplierOrder su);

    //删除订单
    void removeOrder(SupplierOrder order);
    //通过总订单号查询二级订单
    //List<SupplierOrderResp> findOrderByParentId(SupplierOrder order);

    /**
     * 根据订单号查询订单
     * @param orderNo
     * @return
     */
    SupplierOrderResp findByOrderNo(String orderNo);

    /**
     * 完成订单收货
     * @param order
     */
    void receivingGoods(SupplierOrder order);

    //点击立即结算修改订单数据议价、积分、地址
    void updateOrderAddrAndHaggle(SupplierOrder order);

    //获取已发货未确认收货数据
    List<SupplierOrderResp> findEndTimeNoConfirmOrder();
    //统计多少已支付订单
    Long countAllOrderNum(HashMap<String, Object> mapOrder);

    Double sumOrderPayTransactionFlow();

    Long countPayOrderNum();
    //统计待发货数据
    Integer countOrderBuyOrderStatus(Map<String,Object> map);

}
