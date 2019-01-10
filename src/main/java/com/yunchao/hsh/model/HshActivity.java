package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HshActivity implements Serializable {
    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 活动名称
     */
    private String activityName;

    /**
     * 活动商品价格
     */
    private BigDecimal activityPrice;

    /**
     * 活动图像
     */
    private String activityImg;

    /**
     * 商品图片
     */
    private String activityItemImg;

    /**
     * 商品简介
     */
    private String sellPoint;

    /**
     * 商品数量
     */
    private Long itemNum;

    /**
     * 活动状态 1 上线  2 下架
     */
    private Byte status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /**
     * 下架时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /**
     * 分享次数
     */
    private Long shareNum;

    /**
     * 预留字段
     */
    private String cusNum5;

    /**
     * 预留字段
     */
    private String cusNum4;

    /**
     * 商品评价
     */
    private String itemEval;

    /**
     * 购买返还的积分
     */
    private Long itemRebate;

    /**
     * 预留字段
     */
    private String cusNum1;

    /**
     * 预留字段
     */
    private Long cusNum3;

    private static final long serialVersionUID = 1L;

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public BigDecimal getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(BigDecimal activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg == null ? null : activityImg.trim();
    }

    public String getActivityItemImg() {
        return activityItemImg;
    }

    public void setActivityItemImg(String activityItemImg) {
        this.activityItemImg = activityItemImg == null ? null : activityItemImg.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Long getItemNum() {
        return itemNum;
    }

    public void setItemNum(Long itemNum) {
        this.itemNum = itemNum;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getShareNum() {
        return shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
    }

    public String getCusNum5() {
        return cusNum5;
    }

    public void setCusNum5(String cusNum5) {
        this.cusNum5 = cusNum5 == null ? null : cusNum5.trim();
    }

    public String getCusNum4() {
        return cusNum4.split(";")[0].replace("\\","/");
    }

    public void setCusNum4(String cusNum4) {
        this.cusNum4 = cusNum4 == null ? null : cusNum4.trim();
    }

    public String getItemEval() {
        return itemEval;
    }

    public void setItemEval(String itemEval) {
        this.itemEval = itemEval == null ? null : itemEval.trim();
    }

    public Long getItemRebate() {
        return itemRebate;
    }

    public void setItemRebate(Long itemRebate) {
        this.itemRebate = itemRebate;
    }

    public String getCusNum1() {
        return cusNum1.split(";")[0].replace("\\","/");
    }

    public void setCusNum1(String cusNum1) {
        this.cusNum1 = cusNum1 == null ? null : cusNum1.trim();
    }

    public Long getCusNum3() {
        return cusNum3;
    }

    public void setCusNum3(Long cusNum3) {
        this.cusNum3 = cusNum3;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", activityId=").append(activityId);
        sb.append(", activityName=").append(activityName);
        sb.append(", activityPrice=").append(activityPrice);
        sb.append(", activityImg=").append(activityImg);
        sb.append(", activityItemImg=").append(activityItemImg);
        sb.append(", sellPoint=").append(sellPoint);
        sb.append(", itemNum=").append(itemNum);
        sb.append(", status=").append(status);
        sb.append(", createUser=").append(createUser);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", shareNum=").append(shareNum);
        sb.append(", cusNum5=").append(cusNum5);
        sb.append(", cusNum4=").append(cusNum4);
        sb.append(", itemEval=").append(itemEval);
        sb.append(", itemRebate=").append(itemRebate);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum3=").append(cusNum3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}