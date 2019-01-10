package com.yunchao.hsh.model;

import java.io.Serializable;

public class ShoppingCar implements Serializable {

    /**商品编号*/
    private Long goodsId;
    /**商品数量*/
    private Integer num;
    /**总金额*/
    private Double  totalPrice;
    /**单价*/
    private Double price;
    /**商品图片*/
    private String[] goodsImg;
    /**商品规格*/
    private String specifications;
    /**商品名称*/
    private String goodsName;

    private Long supplierId;
    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String[] getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String[] goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}
