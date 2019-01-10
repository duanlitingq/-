package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/12/11 10:38
 * @Description:
 * @Email: hkwind959@gmail.com
 */
public class HshCustomerWithdrawals {

    private Long id;
    //用户id
    private Long customerId;
    //提现金额
    private double money;
    //0失败,1成功
    private int state;

    //创建时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //交易流水号
    private String tradeNo;
    //唯一单号
    private String uuid;

    //退款成功时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date successTime;
    //银行卡号
    private String bankNumber;
    //银行开户行
    private String bankName;
    //收款人姓名
    private String name;
    //电话
    private String phone;
    //当前总金额
    private Double totalMoney;
    //当前总提现
    private Double totalWithdrawals;

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Double getTotalWithdrawals() {
        return totalWithdrawals;
    }

    public void setTotalWithdrawals(Double totalWithdrawals) {
        this.totalWithdrawals = totalWithdrawals;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "HshCustomerWithdrawals{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", money=" + money +
                ", state=" + state +
                ", createTime=" + createTime +
                ", tradeNo='" + tradeNo + '\'' +
                ", uuid='" + uuid + '\'' +
                ", successTime=" + successTime +
                '}';
    }
}
