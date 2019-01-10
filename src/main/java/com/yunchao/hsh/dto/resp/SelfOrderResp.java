package com.yunchao.hsh.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.List;

/**
 * 订单返回列表
 */
public class SelfOrderResp implements Serializable {
    private String orderId;
    //用户ID
    private String userId;
    //购买人
    private String userName;
    //订单总金额
    private String totalPayment;
    //订单创建时间
    private String createTime;
    //支付时间
    private String paymentTime;
    //支付类型  支付类型 1、积分、2、余额、3、微信 、4积分余额混合支付
    private String paymentType;
    //总积分
    private String totalRebate;
    //交易关闭时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String closeTime;
    //交易完成时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
    //交易时间 1:未付款，2:已付款，3:未发货，4:已发货，5交易成功，6交易关闭
    private String status;
    //收货人
    private String receiverName;
    //收货人手机号
    private String receiverMobile;
    //驿站地址(收货人地址)
    private String receiverAddress;
    //商品信息
    private List<SelfItemResp> details;

    private String getStatusStr() {
        String str = null;
        String status = this.getStatus();
        switch (status) {
            case "1":
                str = "未付款";
                break;
            case "2":
                str = "待发货";
                break;
            case "3":
                str = "未发货";
                break;
            case "4":
                str = "已收货";
                break;
            case "5":
                str = "交易成功";
                break;
            case "6":
                str = "交易关闭";
                break;
        }
        return str;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getTotalRebate() {
        return totalRebate;
    }

    public void setTotalRebate(String totalRebate) {
        this.totalRebate = totalRebate;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<SelfItemResp> getDetails() {
        return details;
    }

    public void setDetails(List<SelfItemResp> details) {
        this.details = details;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
