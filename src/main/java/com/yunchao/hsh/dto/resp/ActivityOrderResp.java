package com.yunchao.hsh.dto.resp;

import java.io.Serializable;

/**
 * @ClassName: ActivityOrderResp
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/9 11:34
 * @Version: 1.0
 */
public class ActivityOrderResp implements Serializable {

    private String activityId;
    private String orderId;
    private String activityName;
    //下单人ID
    private Long userId;
    //收货人
    private String receiverName;
    //收货人手机号
    private String receiverMobile;
    //订单创建时间
    private String createTime;
    //结束领取时间
    private String getEndTime;
    //领取次数
    private Long getNum;
    //最后修改客服
    private String adminUserNmae;
    //客服ID
    private String adminId;
    //最近一次领取时间
    private String updateTime;
    //领取状态 1、领取中， 
    private String cusNum1;
    //驿站名称
    private String stationName;
    //驿站地址、收货地址
    private String stationAddress;

    private String status;

    private String orderPrice;
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getGetEndTime() {
        return getEndTime;
    }

    public void setGetEndTime(String getEndTime) {
        this.getEndTime = getEndTime;
    }

    public Long getGetNum() {
        return getNum;
    }

    public void setGetNum(Long getNum) {
        this.getNum = getNum;
    }

    public String getAdminUserNmae() {
        return adminUserNmae;
    }

    public void setAdminUserNmae(String adminUserNmae) {
        this.adminUserNmae = adminUserNmae;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCusNum1() {
        return cusNum1;
    }

    public void setCusNum1(String cusNum1) {
        this.cusNum1 = cusNum1;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ActivityOrderResp{" +
                "activityId='" + activityId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", activityName='" + activityName + '\'' +
                ", userId=" + userId +
                ", receiverName='" + receiverName + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", createTime='" + createTime + '\'' +
                ", getEndTime='" + getEndTime + '\'' +
                ", getNum=" + getNum +
                ", adminUserNmae='" + adminUserNmae + '\'' +
                ", adminId='" + adminId + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", cusNum1='" + cusNum1 + '\'' +
                ", stationName='" + stationName + '\'' +
                ", stationAddress='" + stationAddress + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
