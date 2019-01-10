package com.yunchao.hsh.model;

import com.yunchao.hsh.dto.resp.SupplierGoodsResp;

import java.io.Serializable;

/**
 * Created by wdz on 2018/11/6
 * Remarks:后台供应商订单项
 */
public class SupplierOrderItem implements Serializable{

    /**订单项编号*/
    private Long id;
    /**订单编号*/
    private Long orderId;
    /**商品编号*/
    private Long goodsId;
    /**销售数量*/
    private Integer salesNum;
    /**销售价格*/
    private Double planPrice;
    /**实际价格*/
    private Double actPrice;
    /**备用*/
    private String column1;
    private Integer column2;
    private Long column3;

    private SupplierGoodsResp supplierGoodsResp;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
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

    public Double getPlanPrice() {
        return planPrice;
    }

    public void setPlanPrice(Double planPrice) {
        this.planPrice = planPrice;
    }

    public Double getActPrice() {
        return actPrice;
    }

    public void setActPrice(Double actPrice) {
        this.actPrice = actPrice;
    }

    public SupplierGoodsResp getSupplierGoodsResp() {
        return supplierGoodsResp;
    }

    public void setSupplierGoodsResp(SupplierGoodsResp supplierGoodsResp) {
        this.supplierGoodsResp = supplierGoodsResp;
    }
}
