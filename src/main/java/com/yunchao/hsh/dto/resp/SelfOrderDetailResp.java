package com.yunchao.hsh.dto.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/19 09:14
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class SelfOrderDetailResp implements Serializable {

    private String orderId;
    private Long customerId;
    //交易时间 1:未付款，2:已付款，3:未发货，4:已发货，5交易成功，6交易关闭
    private String status;
    private String itemId;
    private String itemImg;
    private String itemName;
    private String itemPrice;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemImg() {
        return itemImg.replace("\\", "/");
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

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

    @Override
    public String toString() {
        return "SelfOrderDetailResp{" +
                "orderId='" + orderId + '\'' +
                ", customerId=" + customerId +
                ", status='" + status + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemImg='" + itemImg + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
