package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel
public class CustomerWalletLog {
    private Long id;

    @ApiModelProperty(value = "消费金额/积分", dataType = "double")
    private Double amount;

    @ApiModelProperty(value = "消费方式; 1：余额;2:微信;3:积分;4:系统", dataType = "Integer")
    private Integer payMode;

    @ApiModelProperty(value = "类型;1：支出、2：收入;3：提现、4:系统", dataType = "Integer")
    private Integer type;

    @ApiModelProperty(value = "用户ID", dataType = "Long")
    private Long customerId;

    @ApiModelProperty(value = "消费时间", dataType = "String")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;

    private Integer status;

    private Double currentBalance;

    private String uuid;

    @ApiModelProperty(value = "记录说明", dataType = "String")
    private String remark;

    private Long operationId;

    private String tradeNo;
    @JsonIgnore
    private String column1;
    @JsonIgnore
    private Integer column2;
    @JsonIgnore
    private Long column3;
    private String customerName;  //用户名称
    private String customerPhome;  //用户手机
    private String sysUserName;  //后台管理员名称

    private String payModeStr;//消费状态
    private String typeStr;//类型
    private String statusStr;//状态

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayMode() {
        return payMode;
    }

    public void setPayMode(Integer payMode) {
        this.payMode = payMode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhome() {
        return customerPhome;
    }

    public void setCustomerPhome(String customerPhome) {
        this.customerPhome = customerPhome;
    }

    public String getSysUserName() {
        return sysUserName;
    }

    public void setSysUserName(String sysUserName) {
        this.sysUserName = sysUserName;
    }

    public String getPayModeStr() {
        return payModeStr;
    }

    public void setPayModeStr(String payModeStr) {
        this.payModeStr = payModeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    @Override
    public String toString() {
        return "CustomerWalletLog{" +
                "id=" + id +
                ", amount=" + amount +
                ", payMode=" + payMode +
                ", type=" + type +
                ", customerId=" + customerId +
                ", createDate=" + createDate +
                ", status=" + status +
                ", currentBalance=" + currentBalance +
                ", uuid='" + uuid + '\'' +
                ", remark='" + remark + '\'' +
                ", operationId=" + operationId +
                ", tradeNo='" + tradeNo + '\'' +
                ", column1='" + column1 + '\'' +
                ", column2=" + column2 +
                ", column3=" + column3 +
                '}';
    }
}