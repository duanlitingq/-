package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wdz on 2018/11/6
 * Remarks:供应商商品
 */
public class SupplierGoods implements Serializable{

    /**主键*/
    private Long id;
    /**供应商编号*/
    private Long supplierId;
    /**商品名称*/
    private String name;
    /**商品简介*/
    private String info;
    /**商品状态0下架 1上架*/
    private Integer status;
    /**商品图片*/
    private String imgs;
    /**商品计划价格*/
    private Double planPrice;
    /**商品实际价格*/
    private Double actPrice;
    /**商品单位*/
    private Integer unit;
    /**商品类别编号*/
    private Long typeId;

    /**库存*/
    private Integer libraryNum;
    /**产地*/
    private String productArea;
    /**生产日期*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date productTime;
    /**保质期*/
    private String shelfLife;
    /**规格*/
    private String specifications;
    /**权重:数值越大排序越靠前默认0没有权重*/
    private Integer priority;
    /**备用字段*/
    private String column1;
    private Integer column2;
    private Long column3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
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

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getLibraryNum() {
        return libraryNum;
    }

    public void setLibraryNum(Integer libraryNum) {
        this.libraryNum = libraryNum;
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea;
    }

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    public String getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(String shelfLife) {
        this.shelfLife = shelfLife;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    @Override
    public String toString() {
        return "SupplierGoods{" +
                "id=" + id +
                ", supplierId=" + supplierId +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", status=" + status +
                ", imgs='" + imgs + '\'' +
                ", planPrice=" + planPrice +
                ", actPrice=" + actPrice +
                ", unit=" + unit +
                ", typeId=" + typeId +
                ", libraryNum=" + libraryNum +
                ", productArea='" + productArea + '\'' +
                ", productTime=" + productTime +
                ", shelfLife='" + shelfLife + '\'' +
                ", specifications='" + specifications + '\'' +
                ", priority=" + priority +
                ", column1='" + column1 + '\'' +
                ", column2=" + column2 +
                ", column3=" + column3 +
                '}';
    }
}
