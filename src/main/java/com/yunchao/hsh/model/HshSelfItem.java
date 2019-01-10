package com.yunchao.hsh.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class HshSelfItem implements Serializable {
    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品图片
     */
    private String itemImg;

    /**
     * 商品简介
     */
    private String sellPoint;

    /**
     * 商品价格
     */
    private BigDecimal itemPrice;

    /**
     * 商品积分
     */
    private Long itemIntegral;

    /**
     * 商品单位
     */
    private String itemUnit;

    /**
     * 邮费 0 就包邮， 大于0 不包邮
     */
    private Long postFee;

    /**
     * 商品数量
     */
    private Long itemNum;

    /**
     * 商品状态  1-正常，2-下架，3-删除'
     */
    private Byte status;

    /**
     * 分享次数
     */
    private Long shareNum;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 商品评价
     */
    private String itemEval;

    /**
     * 购买返的积分
     */
    private Long itemRebate;

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

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg == null ? null : itemImg.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Long getItemIntegral() {
        return itemIntegral;
    }

    public void setItemIntegral(Long itemIntegral) {
        this.itemIntegral = itemIntegral;
    }

    public String getItemUnit() {
        return itemUnit;
    }

    public void setItemUnit(String itemUnit) {
        this.itemUnit = itemUnit == null ? null : itemUnit.trim();
    }

    public Long getPostFee() {
        return postFee;
    }

    public void setPostFee(Long postFee) {
        this.postFee = postFee;
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

    public Long getShareNum() {
        return shareNum;
    }

    public void setShareNum(Long shareNum) {
        this.shareNum = shareNum;
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
        sb.append(", itemId=").append(itemId);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemImg=").append(itemImg);
        sb.append(", sellPoint=").append(sellPoint);
        sb.append(", itemPrice=").append(itemPrice);
        sb.append(", itemIntegral=").append(itemIntegral);
        sb.append(", itemUnit=").append(itemUnit);
        sb.append(", postFee=").append(postFee);
        sb.append(", itemNum=").append(itemNum);
        sb.append(", status=").append(status);
        sb.append(", shareNum=").append(shareNum);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", itemEval=").append(itemEval);
        sb.append(", itemRebate=").append(itemRebate);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", cusNum3=").append(cusNum3);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}