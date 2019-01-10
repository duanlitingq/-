package com.yunchao.hsh.dto.req;

import java.io.Serializable;

public class ActivityReq implements Serializable {

    private String activityName;
    private String activityImg;
    private String activityPrice;
    private String itemNum;
    private String itemRebate;
    private String itemImgs;
    private String sellPoint;
    private String status;
    private String desc;

    //商品描述图片
    private String itemDescImgs;

    //玩法图片
    private String gameImgs;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityPrice() {
        return activityPrice;
    }

    public void setActivityPrice(String activityPrice) {
        this.activityPrice = activityPrice;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemRebate() {
        return itemRebate;
    }

    public void setItemRebate(String itemRebate) {
        this.itemRebate = itemRebate;
    }

    public String getItemImgs() {
        return itemImgs;
    }

    public void setItemImgs(String itemImgs) {
        this.itemImgs = itemImgs;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getItemDescImgs() {
        return itemDescImgs;
    }

    public void setItemDescImgs(String itemDescImgs) {
        this.itemDescImgs = itemDescImgs;
    }

    public String getGameImgs() {
        return gameImgs;
    }

    public void setGameImgs(String gameImgs) {
        this.gameImgs = gameImgs;
    }

    @Override
    public String toString() {
        return "ActivityReq{" +
                "activityName='" + activityName + '\'' +
                ", activityImg='" + activityImg + '\'' +
                ", activityPrice='" + activityPrice + '\'' +
                ", itemNum='" + itemNum + '\'' +
                ", itemRebate='" + itemRebate + '\'' +
                ", itemImgs='" + itemImgs + '\'' +
                ", sellPoint='" + sellPoint + '\'' +
                ", status='" + status + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
