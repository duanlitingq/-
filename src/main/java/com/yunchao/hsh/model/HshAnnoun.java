package com.yunchao.hsh.model;

import java.io.Serializable;
import java.util.Date;

public class HshAnnoun implements Serializable {
    private Long id;

    /**
     * 兑换人姓名
     */
    private String userName;

    /**
     * 商品名称
     */
    private String itemName;

    private Date createTime;

    private String cusNum1;

    private Long cusNum2;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userName=").append(userName);
        sb.append(", itemName=").append(itemName);
        sb.append(", createTime=").append(createTime);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}