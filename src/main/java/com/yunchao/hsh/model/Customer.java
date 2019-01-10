package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Customer {
    private Long id;

    private String openId;

    private String nickname;

    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date addTime;

    private String img;

    private String inviteCode;
    //收款码
    private String receivablesCode;
    @JsonIgnore
    private Integer column2;
    private Long superiorId;//上级id(用于邀请)
    private String gender;//性别
    private String area;//地址
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date  invitationTime;//被邀请时间
    ///////字段结束
    private Double burse;//当前余额

    private  Double score;//当前积分

    private Double amountInBurse;

    private Double amountInScore;

    private Double amountOutBurse;

    private Integer amountOutScore;

    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }

    public String getReceivablesCode() {
        return receivablesCode;
    }

    public void setReceivablesCode(String receivablesCode) {
        this.receivablesCode = receivablesCode;
    }

    public Integer getColumn2() {
        return column2;
    }

    public void setColumn2(Integer column2) {
        this.column2 = column2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Long getSuperiorId() {
        return superiorId;
    }

    public void setSuperiorId(Long superiorId) {
        this.superiorId = superiorId;
    }

    public Date getInvitationTime() {
        return invitationTime;
    }

    public void setInvitationTime(Date invitationTime) {
        this.invitationTime = invitationTime;
    }
}