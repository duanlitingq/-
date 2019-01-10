package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunchao.hsh.dto.resp.SupplierResp;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wdz on 2018/11/6
 * Remarks:供应商订单  作为二级订单
 */
public class SupplierOrder implements Serializable{

    /**订单编号 */
    private Long id;
    /**订单号*/
    private String orderNo;
    /**下单人编号*/
    private Long userId;
    /**供应商编号*/
    private Long supplierId;
    /**创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**支付状态:1未支付,2已支付*/
    private Integer payStatus;
    /**订单状态：1：未接单2：已接单 3 已发货 4 已完成 5：取消订单，6：已取消 7：商家驳回*/
    private Integer orderStatus;
    /**支付方式 1:微信 2：余额3：积分4：支付宝*/
    private Integer payType;
    /** 支付时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;
    /**订单金额*/
    private Double orderMoney;
    /***实付金额*/
    private Double actPayment;
    /**平台收入*/
    private Double platformRevenue;
    /**积分*/
    private Double integral;
    /**完成时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
    /**备注*/
    private String remark;

    private Long addressId;
    //第三方交易流水号
    private String tradeNo;
    /**站点名称*/
    private String column1;
    private Integer column2;
    private Long column3;
    /**议价金额*/
    private Double hagglePrice;
    /**接单时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;
    //总订单编号
    private Long parentId;
    private List<SupplierOrderItem> items;

    //购买人姓名
    private String buyUserName;
    //订单收货地址
    private String address;
    //购买人电话
    private String buyUserPhone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Integer getColumn2() {
        return column2;
    }

    public void setColumn2(Integer column2) {
        this.column2 = column2;
    }

    public Long getColumn3() {
        return column3;
    }

    public void setColumn3(Long column3) {
        this.column3 = column3;
    }

    public List<SupplierOrderItem> getItems() {
        return items;
    }

    public void setItems(List<SupplierOrderItem> items) {
        this.items = items;
    }

    public Double getHagglePrice() {
        return hagglePrice;
    }

    public void setHagglePrice(Double hagglePrice) {
        this.hagglePrice = hagglePrice;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    //临时数据分界线。。。。。。。。。。。。。。。
    private SupplierResp supplierResp;

    public SupplierResp getSupplierResp() {
        return supplierResp;
    }

    public void setSupplierResp(SupplierResp supplierResp) {
        this.supplierResp = supplierResp;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Double getActPayment() {
        return actPayment;
    }

    public void setActPayment(Double actPayment) {
        this.actPayment = actPayment;
    }

    public Double getPlatformRevenue() {
        return platformRevenue;
    }

    public void setPlatformRevenue(Double platformRevenue) {
        this.platformRevenue = platformRevenue;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuyUserPhone() {
        return buyUserPhone;
    }

    public void setBuyUserPhone(String buyUserPhone) {
        this.buyUserPhone = buyUserPhone;
    }

    @Override
    public String toString() {
        return "SupplierOrder{" +
                "id=" + id +
                ", orderNo='" + orderNo + '\'' +
                ", userId=" + userId +
                ", supplierId=" + supplierId +
                ", createTime=" + createTime +
                ", payStatus=" + payStatus +
                ", orderStatus=" + orderStatus +
                ", payType=" + payType +
                ", payTime=" + payTime +
                ", orderMoney=" + orderMoney +
                ", integral=" + integral +
                ", finishTime=" + finishTime +
                ", remark='" + remark + '\'' +
                ", addressId=" + addressId +
                ", tradeNo='" + tradeNo + '\'' +
                ", column1='" + column1 + '\'' +
                ", column2=" + column2 +
                ", column3=" + column3 +
                ", hagglePrice=" + hagglePrice +
                ", confirmTime=" + confirmTime +
                ", items=" + items +
                ", supplierResp=" + supplierResp +
                '}';
    }
}
