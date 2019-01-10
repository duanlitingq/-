package com.yunchao.hsh.model;

import java.io.Serializable;
import java.util.Date;

public class HshSelfItemDesc implements Serializable {
    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 预留字段
     */
    private String cusNum1;

    /**
     * 预留字段
     */
    private String cusNum2;

    /**
     * 预留字段
     */
    private String cusNum3;

    /**
     * 商品描述
     */
    private String itemDesc;

    private static final long serialVersionUID = 1L;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
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

    public String getCusNum1() {
        return cusNum1;
    }

    public void setCusNum1(String cusNum1) {
        this.cusNum1 = cusNum1 == null ? null : cusNum1.trim();
    }

    public String getCusNum2() {
        return cusNum2;
    }

    public void setCusNum2(String cusNum2) {
        this.cusNum2 = cusNum2 == null ? null : cusNum2.trim();
    }

    public String getCusNum3() {
        return cusNum3;
    }

    public void setCusNum3(String cusNum3) {
        this.cusNum3 = cusNum3 == null ? null : cusNum3.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", cusNum3=").append(cusNum3);
        sb.append(", itemDesc=").append(itemDesc);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}