package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
public class IntegralRules implements Serializable {
    private Long id;

    private String code;

    private String integralName;

    private Double integral;

    private String Introduction;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String cusNum1;

    private String cusNum2;

    private String cusNum3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getIntegralName() {
        return integralName;
    }

    public void setIntegralName(String integralName) {
        this.integralName = integralName == null ? null : integralName.trim();
    }

    public Double getIntegral() {
        return integral;
    }

    public void setIntegral(Double integral) {
        this.integral = integral;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String desc) {
        this.Introduction = desc == null ? null : desc.trim();
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
}