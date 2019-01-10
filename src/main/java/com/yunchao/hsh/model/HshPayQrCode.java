package com.yunchao.hsh.model;

import java.io.Serializable;
import java.util.Date;

public class HshPayQrCode implements Serializable {
    /**
     * ID
     */
    private Long codeId;

    /**
     * 二维码存储地址
     */
    private String codeAddress;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 手机号
     */
    private String userMobile;

    /**
     * 创建时间
     */
    private Date createTime;

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

    private static final long serialVersionUID = 1L;

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getCodeAddress() {
        return codeAddress;
    }

    public void setCodeAddress(String codeAddress) {
        this.codeAddress = codeAddress == null ? null : codeAddress.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", codeId=").append(codeId);
        sb.append(", codeAddress=").append(codeAddress);
        sb.append(", userId=").append(userId);
        sb.append(", userMobile=").append(userMobile);
        sb.append(", createTime=").append(createTime);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", cusNum3=").append(cusNum3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}