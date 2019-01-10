package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yunchao.hsh.constant.Constant;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 出入账记录
 */
public class SupplierWalletLog {

    private Long id;

    private Long supplierId;
    /**记录时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**变更金额*/
    private Double changeMoney;
    /**购买用户编号*/
    private Long frontUserId;
    /**备注*/
    private String remark;
    /**订单编号/提现记录编号*/
    private Long orderId;
    /**收入/支出 1收入2：支出*/
    private Integer inOrOut;

    private String column1;
    private Integer column2;
    private Long column3;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Double getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(Double changeMoney) {
        this.changeMoney = changeMoney;
    }

    public Long getFrontUserId() {
        return frontUserId;
    }

    public void setFrontUserId(Long frontUserId) {
        this.frontUserId = frontUserId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Integer inOrOut) {
        this.inOrOut = inOrOut;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public Integer getColumn2() {
        return column2;
    }

    public void setColumn2(Integer column2) {
        this.column2 = column2;
    }

    public Long getColumn3() {
        return column3;
    }

    public void setColumn3(Long column3) {
        this.column3 = column3;
    }

}
