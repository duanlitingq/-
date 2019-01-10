package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.HshActivityOrder;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/22 13:42
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class OrderItemResp extends HshActivityOrder {

    //活动名称、商品名称
    private String activityName;

    //活动图片、商品图片
    private String activityImg;

    private String activityPrice;

    private String receiverMobile;
    private String receiverName;
    private String receiverAddress;
    private String orderNo;
    //支付时间
    private String payMentTime;
    //交易流水号
    private String wxTradeNo;

    private String status;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityImg() {
        return activityImg.split(";")[0].replace("\\","/");
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(String activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String getReceiverMobile() {
        return receiverMobile;
    }

    @Override
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    @Override
    public String getReceiverName() {
        return receiverName;
    }

    @Override
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    public String getWxTradeNo() {
        return wxTradeNo;
    }

    public void setWxTradeNo(String wxTradeNo) {
        this.wxTradeNo = wxTradeNo;
    }

    public String getPayMentTime() {
        return payMentTime;
    }

    public void setPayMentTime(String payMentTime) {
        this.payMentTime = payMentTime;
    }

    @Override
    public String toString() {
        return "OrderItemResp{" +
                "activityName='" + activityName + '\'' +
                ", activityImg='" + activityImg + '\'' +
                ", activityPrice='" + activityPrice + '\'' +
                ", receiverMobile='" + receiverMobile + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", payMentTime='" + payMentTime + '\'' +
                ", wxTradeNo='" + wxTradeNo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
