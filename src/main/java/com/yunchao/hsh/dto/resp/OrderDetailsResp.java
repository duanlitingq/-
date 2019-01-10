package com.yunchao.hsh.dto.resp;/**
 * @author ZhaiQing
 * @email hkwind959@google.com
 * @Description
 * @Date $time$ $date$
 * @Param $param$
 * @return $return$
 **/

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *  @ClassName: OrderDetailsResp
 *  @Description: TODO
 *  @author: Wind
 *  @email: hkwind959@google.com
 *  @date: 2018/11/25 19:57
 *  @version: V1.0
 **/
public class OrderDetailsResp{

//    order_id,activity_id,trade_no,user_id,receiver_mobile,receiver_name,pay_time
    private Long activityId;

    private String receiverMobile;
    private String receiverName;
    private String receiverAddress;
    private String activityPrice;
    //驿站名称
    private String stationName;
    //活动名称、商品名称
    private String activityName;

    //活动图片、商品图片
    private String activityImg;
    private String tradeNo;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String payTime;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(String activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
