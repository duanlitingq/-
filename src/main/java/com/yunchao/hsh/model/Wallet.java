package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.models.auth.In;

import java.awt.geom.RoundRectangle2D;

public class Wallet {
    private Long id;

    private Long customerId;

    private Double burse;

    private Double score;

    private Double amountInBurse;

    private Double amountInScore;

    private Double amountOutBurse;

    private Integer amountOutScore;
    @JsonIgnore
    private String column1;
    @JsonIgnore
    private Integer column2;
    @JsonIgnore
    private Long column3;

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

    public Double getBurse() {
        return burse;
    }

    public void setBurse(Double burse) {
        this.burse = burse;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getAmountInBurse() {
        return amountInBurse;
    }

    public void setAmountInBurse(Double amountInBurse) {
        this.amountInBurse = amountInBurse;
    }

    public Double getAmountInScore() {
        return amountInScore;
    }

    public void setAmountInScore(Double amountInScore) {
        this.amountInScore = amountInScore;
    }

    public Double getAmountOutBurse() {
        return amountOutBurse;
    }

    public void setAmountOutBurse(Double amountOutBurse) {
        this.amountOutBurse = amountOutBurse;
    }

    public Integer getAmountOutScore() {
        return amountOutScore;
    }

    public void setAmountOutScore(Integer amountOutScore) {
        this.amountOutScore = amountOutScore;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1 == null ? null : column1.trim();
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