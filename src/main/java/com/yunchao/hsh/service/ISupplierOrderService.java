package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.ShoppingCar;
import com.yunchao.hsh.model.SupplierOrder;
import com.yunchao.hsh.model.SupplierOrderItem;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ISupplierOrderService {

    public SupplierOrderResp findById(Long id)throws Exception;

    public PageInfo<SupplierOrderResp> getPage(HashMap<String, Object> map, int pageNum, int pageSize)throws Exception;

    public void insert(SupplierOrder supplierOrder)throws Exception;

    public void updatePayStatus(SupplierOrder supplierOrder)throws Exception;
    /**确认订单*/
    void confirmOrder(SupplierOrder su)throws Exception;

    Result cancelOrder(Result result,SupplierOrderResp su)throws Exception;

//    List<SupplierOrderResp> findOrderByParentId(Long parentId, Long userId) throws Exception;

    SupplierOrderResp findByUserOrderId(Long orderId, Long userId) throws Exception;

    //删除订单
    void removeOrder(Long userId, Long orderId)throws Exception;

    //确认收货
    Result receivingGoods(Result result,Long orderId, Long userId)throws Exception;

    //生成订单
    Result createOrder(Result result, Map<Long, List<ShoppingCar>> map, String datas, Customer customer);
    /**
     * 点击结算更新订单数据
     * @param result
     * @param customer
     * @param orderId
     * @param haggle
     * @param integral
     * @param addressId
     * @return
     */
    Result updateOrderData(Result result, Customer customer, Long orderId, Double haggle, Double integral, Long addressId);

    //根据订单号查询订单
    SupplierOrderResp findByOrderNo(String orderNo);

    //修改已支付并发货超过72小时未点击确定收货的订单修改为已完成
    void updateOrderStatusToFinish(HashMap hashMap);

    //获取未完成的商家确认的数据
    List<SupplierOrderResp> findEndTimeNoConfirmOrder();

    /**
     * 商品详细立即购买
     * @param result
     * @param customer
     * @param datas
     * @return
     */
    Result singleBuy(Result result, Customer customer, String datas);

    double sumOrderPayTransactionFlow();

    Long countPayOrderNum();

    /**驳回*/
    void backOrder(HashMap<String, Object> map,SupplierOrderResp supplierOrderResp);

    //导出表格
    HSSFWorkbook exportExcel(Long supplierId, String filePath,String sheetName, String orderNo, Integer payStatus, Integer orderStatus);

    /**
     * 获取订单项数据
     * @param orderId
     * @return
     */
    List<SupplierOrderItem> findItemsByOrderId(Long orderId);

   Integer countOrderBuyOrderStatus(Long supplierId,Integer orderStatus);
}
