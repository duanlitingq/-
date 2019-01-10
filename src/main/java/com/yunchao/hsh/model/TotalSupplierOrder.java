package com.yunchao.hsh.model;

import java.util.Date;

/**
 * @Title: TotalSupplierOrder
 * @Description: 一级订单
 * @author wdz
 * @date 2019/1/9 16:49
 */
public class TotalSupplierOrder {

    /**主键*/
    private Long id;
    /**订单金额*/
    private Double orderMoney;
    /**订单使用积分*/
    private Double integral;
    /**议价*/
    private Double haggle;
    /**实际支付*/
    private Double actPayment;
    /**余额*/
    private Double balance;
    /**购买人编号*/
    private Long buyUserId;
    /**第三方流水*/
    private String tradeNo;
    /**订单编号*/
    private String orderNo;
    /**订单状态1：未接单 2：已接单 3：已发货 4：已完成 5：已取消 6 已驳回*/
    private Integer orderStatus;
    /**支付状态 1：未支付 2：已支付*/
    private Integer payStatus;
    /**配送方式 1：配送 2：自提（不存在驿站和地址）*/
    private Integer delivery;
    /**购买人姓名*/
    private String buyUserName;
    /**选择的驿站名称*/
    private String selectStation;
    /**购买人地址*/
    private String buyUserAddress;
    /**购买人电话*/
    private String buyUserPhone;
    /**支付时间*/
    private Date payTime;
    /**创建时间*/
    private Date createTime;
    /**完成时间*/
    private Date finishTime;
    /**备用*/
    private String column1;
    /**备用*/
    private String column2;
    /**备用*/
    private Integer column3;
    /**备用*/
    private Integer column4;
    /**备用*/
    private Long column5;
    /**备用*/
    private Long column6;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getHaggle() {
        return haggle;
    }

    public void setHaggle(Double haggle) {
        this.haggle = haggle;
    }

    public Double getActPayment() {
        return actPayment;
    }

    public void setActPayment(Double actPayment) {
        this.actPayment = actPayment;
    }

    public Long getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(Long buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Integer getDelivery() {
        return delivery;
    }

    public void setDelivery(Integer delivery) {
        this.delivery = delivery;
    }

    public String getBuyUserName() {
        return buyUserName;
    }

    public void setBuyUserName(String buyUserName) {
        this.buyUserName = buyUserName;
    }

    public String getSelectStation() {
        return selectStation;
    }

    public void setSelectStation(String selectStation) {
        this.selectStation = selectStation;
    }

    public String getBuyUserAddress() {
        return buyUserAddress;
    }

    public void setBuyUserAddress(String buyUserAddress) {
        this.buyUserAddress = buyUserAddress;
    }

    public String getBuyUserPhone() {
        return buyUserPhone;
    }

    public void setBuyUserPhone(String buyUserPhone) {
        this.buyUserPhone = buyUserPhone;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public Integer getColumn3() {
        return column3;
    }

    public void setColumn3(Integer column3) {
        this.column3 = column3;
    }

    public Integer getColumn4() {
        return column4;
    }

    public void setColumn4(Integer column4) {
        this.column4 = column4;
    }

    public Long getColumn5() {
        return column5;
    }

    public void setColumn5(Long column5) {
        this.column5 = column5;
    }

    public Long getColumn6() {
        return column6;
    }

    public void setColumn6(Long column6) {
        this.column6 = column6;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "TotalSupplierOrder{" +
                "id=" + id +
                ", orderMoney=" + orderMoney +
                ", integral=" + integral +
                ", haggle=" + haggle +
                ", actPayment=" + actPayment +
                ", buyUserId=" + buyUserId +
                ", balance=" + balance +
                ", tradeNo='" + tradeNo + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderStatus=" + orderStatus +
                ", payStatus=" + payStatus +
                ", delivery=" + delivery +
                ", buyUserName='" + buyUserName + '\'' +
                ", selectStation='" + selectStation + '\'' +
                ", buyUserAddress='" + buyUserAddress + '\'' +
                ", buyUserPhone='" + buyUserPhone + '\'' +
                ", payTime=" + payTime +
                ", createTime=" + createTime +
                ", finishTime=" + finishTime +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3=" + column3 +
                ", column4=" + column4 +
                ", column5=" + column5 +
                ", column6=" + column6 +
                '}';
    }
}
