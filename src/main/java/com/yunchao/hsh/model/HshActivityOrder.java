package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class HshActivityOrder implements Serializable {

    //
    private Long id;
    /**
     * 活动订单ID
     */
    private String orderId;

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 下单人ID
     */
    private Long userId;

    /**
     * 收货人手机号
     */
    private String receiverMobile;

    /**
     * 收货人姓名
     */
    private String receiverName;

    /**
     * 订单创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 订单支付时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date payTime;

    /**
     * 订单支付方式
     */
    private Integer payMode;

    /**
     * 订单更新时间
     */
    private Date updateTime;

    /**
     * 开始领取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getStartTime;

    /**
     * 结束领取时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date getEndTime;

    /**
     * 订单过期时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 领取次数 最多12次
     */
    private Long getNum;

    /**
     * 订单状态1、成功2失败
     */
    private Byte orderStatus;

    /**
     * 客服修改次数的人
     */
    private Long customerId;

    /**
     * 最后修改客服
     */
    private String lastName;

    /**
     * 预留字段
     */
    private String cusNum1;

    /**
     * 预留字段
     */
    private Long cusNum2;

    /**
     * 预留字段
     */
    private String cusNum3;

    //交易流水号
    private String tradeNo;

    private static final long serialVersionUID = 1L;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getGetStartTime() {
        return getStartTime;
    }

    public void setGetStartTime(Date getStartTime) {
        this.getStartTime = getStartTime;
    }

    public Date getGetEndTime() {
        return getEndTime;
    }

    public void setGetEndTime(Date getEndTime) {
        this.getEndTime = getEndTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getGetNum() {
        return getNum;
    }

    public void setGetNum(Long getNum) {
        this.getNum = getNum;
    }

    public Byte getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Byte orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName == null ? null : lastName.trim();
    }

    public String getCusNum1() {
        return cusNum1;
    }

    public void setCusNum1(String cusNum1) {
        this.cusNum1 = cusNum1 == null ? null : cusNum1.trim();
    }

    public Long getCusNum2() {
        return cusNum2;
    }

    public void setCusNum2(Long cusNum2) {
        this.cusNum2 = cusNum2;
    }

    public String getCusNum3() {
        return cusNum3;
    }

    public void setCusNum3(String cusNum3) {
        this.cusNum3 = cusNum3 == null ? null : cusNum3.trim();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(",id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(",tradeNo=").append(tradeNo);
        sb.append(", activityId=").append(activityId);
        sb.append(", userId=").append(userId);
        sb.append(", receiverMobile=").append(receiverMobile);
        sb.append(", receiverName=").append(receiverName);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", getStartTime=").append(getStartTime);
        sb.append(", getEndTime=").append(getEndTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", getNum=").append(getNum);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", customerId=").append(customerId);
        sb.append(", lastName=").append(lastName);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", cusNum3=").append(cusNum3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}