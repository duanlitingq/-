package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 16:57
 * @Description: 活动订单领取日志表
 * @Email: hkwind959@gmail.com
 */
public class HshActivityOrderGetLog implements Serializable {


    private Long logId;

    //订单状态
    private String orderId;

    //领取状态 （1、确定领取，2：待领取）
    private byte status;

    //截止领取时间
    @DateTimeFormat(pattern = "yyyy-MM-dd") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private Date endTime;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "HshActivityOrderGetLog{" +
                "logId=" + logId +
                ", orderId='" + orderId + '\'' +
                ", status=" + status +
                ", endTime=" + endTime +
                '}';
    }
}
