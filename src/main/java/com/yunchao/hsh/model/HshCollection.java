package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class HshCollection implements Serializable {
    /**
     * 收藏ID
     */
    private Long collId;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 店铺ID
     */
    private Long storeId;

    /**
     * 收藏时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 收藏类型  1 商品 2 店铺
     */
    private Byte collType;

    /**
     * 是否有效  0无效， 1 有效
     */
    private Byte isDel;

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

    private static final long serialVersionUID = 1L;

    public Long getCollId() {
        return collId;
    }

    public void setCollId(Long collId) {
        this.collId = collId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getCollType() {
        return collType;
    }

    public void setCollType(Byte collType) {
        this.collType = collType;
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", collId=").append(collId);
        sb.append(", itemId=").append(itemId);
        sb.append(", userId=").append(userId);
        sb.append(", storeId=").append(storeId);
        sb.append(", createTime=").append(createTime);
        sb.append(", collType=").append(collType);
        sb.append(", isDel=").append(isDel);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", cusNum3=").append(cusNum3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}