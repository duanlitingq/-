package com.yunchao.hsh.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class HshSelfOrderDetail implements Serializable {
    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品购买数量
     */
    private Long num;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品单价
     */
    private BigDecimal itemPrice;

    /**
     * 商品总金额
     */
    private BigDecimal totalCount;

    /**
     * 积分
     */
    private Long itemRebate;

    /**
     * 总积分
     */
    private Long totalRebate;

    /**
     * 商品图片
     */
    private String itemImg;

    private static final long serialVersionUID = 1L;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public BigDecimal getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(BigDecimal totalCount) {
        this.totalCount = totalCount;
    }

    public Long getItemRebate() {
        return itemRebate;
    }

    public void setItemRebate(Long itemRebate) {
        this.itemRebate = itemRebate;
    }

    public Long getTotalRebate() {
        return totalRebate;
    }

    public void setTotalRebate(Long totalRebate) {
        this.totalRebate = totalRebate;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg == null ? null : itemImg.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", itemId=").append(itemId);
        sb.append(", orderId=").append(orderId);
        sb.append(", num=").append(num);
        sb.append(", itemName=").append(itemName);
        sb.append(", itemPrice=").append(itemPrice);
        sb.append(", totalCount=").append(totalCount);
        sb.append(", itemRebate=").append(itemRebate);
        sb.append(", totalRebate=").append(totalRebate);
        sb.append(", itemImg=").append(itemImg);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}