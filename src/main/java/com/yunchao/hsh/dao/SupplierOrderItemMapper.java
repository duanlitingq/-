package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.SupplierOrder;
import com.yunchao.hsh.model.SupplierOrderItem;

import java.util.HashMap;
import java.util.List;

public interface SupplierOrderItemMapper {


    void updateStatus(SupplierOrderItem so);

    void update(SupplierOrderItem supplierOrder);

    void insert(SupplierOrderItem supplierOrder);

    SupplierOrderItem findById(Long id);

    List<SupplierOrderItem> findPage(HashMap<String, Object> map);

    List<SupplierOrderItem> findByOrderId(Long id);

    //通过订单编号删除订单项
    void removeOrderItemByOrderId(Long orderId);
}
