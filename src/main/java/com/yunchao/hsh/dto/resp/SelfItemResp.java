package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.HshSelfItem;

import java.io.Serializable;

/**
 * 自营商品详情
 */
public class SelfItemResp implements Serializable {
    private HshSelfItem item;
    private String itemDesc;
    private String itemId;
    private String itemName;
    private Long num;
    //商品单价
    private String itemPrice;
    //商品积分
    private String itemRebate;
    //图片集合
    private String images;
    // 1 是可兑换，0 不可兑换
    private int flag;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemRebate() {
        return itemRebate;
    }

    public void setItemRebate(String itemRebate) {
        this.itemRebate = itemRebate;
    }

    public String getImages() {
        return images.replace("\\","/");
    }

    public void setImages(String images) {
        this.images = images;
    }

    public HshSelfItem getItem() {
        return item;
    }

    public void setItem(HshSelfItem item) {
        this.item = item;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SelfItemResp{" +
                "item=" + item +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", num=" + num +
                ", itemPrice='" + itemPrice + '\'' +
                ", itemRebate='" + itemRebate + '\'' +
                ", images='" + images + '\'' +
                ", flag=" + flag +
                '}';
    }
}
