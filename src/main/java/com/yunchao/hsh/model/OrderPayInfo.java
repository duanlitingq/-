package com.yunchao.hsh.model;

import java.util.Date;

/**
 * 多订单直接支付  弃用
 */
public class OrderPayInfo {

    private Long id;
    //微信订单号
    private String wxOrderNo;
    //本地订单号
    private String orderNo;
    //订单总金额
    private Double totalMoney;
    //支付状态 1：未支付 2：支付成功
    private Integer status;
    //支付账号（微信opendId）
    private String payAccount;
    //收入账号
    private String revenueAccount;
    //备注
    private String remark;
    //创建时间
    private Date createTime;

    /**备用*/
    private String column1;
    private String column2;
    private Integer column3;
    private Integer column4;
    private Long column5;
    private Long column6;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWxOrderNo() {
        return wxOrderNo;
    }

    public void setWxOrderNo(String wxOrderNo) {
        this.wxOrderNo = wxOrderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public String getRevenueAccount() {
        return revenueAccount;
    }

    public void setRevenueAccount(String revenueAccount) {
        this.revenueAccount = revenueAccount;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
