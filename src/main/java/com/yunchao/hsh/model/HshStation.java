package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


public class HshStation implements Serializable {
    /**
     * 店铺ID
     */
    private Long stationId;

    /**
     * 店铺名称
     */
    private String name;

    /**
     * 主打特色
     */
    private String featurs;

    /**
     * 人气
     */
    private Integer popularity;

    /**
     * 人均
     */
    private Integer preAvg;

    /**
     * 地址
     */
    private String address;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 0 下线，1 上线
     */
    private Byte isDel;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;

    /**
     * 下架时间
     */
    private Date offlineTime;

    /**
     * 上架时间
     */
    private Date onlineTime;

    /**
     * 店铺头像
     */
    private String stationImg;

    /**
     * 店铺营业执照
     */
    private String stationLicense;

    /**
     * 食品资质证书
     */
    private String stationQuality;

    /**
     * 保留字段
     */
    private String cusNumOne;

    /**
     * 保留字段
     */
    private String cusNumTwo;

    private static final long serialVersionUID = 1L;

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getFeaturs() {
        return featurs;
    }

    public void setFeaturs(String featurs) {
        this.featurs = featurs == null ? null : featurs.trim();
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Integer getPreAvg() {
        return preAvg;
    }

    public void setPreAvg(Integer preAvg) {
        this.preAvg = preAvg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
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

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public String getStationImg() {
        return stationImg;
    }

    public void setStationImg(String stationImg) {
        this.stationImg = stationImg == null ? null : stationImg.trim();
    }

    public String getStationLicense() {
        return stationLicense;
    }

    public void setStationLicense(String stationLicense) {
        this.stationLicense = stationLicense == null ? null : stationLicense.trim();
    }

    public String getStationQuality() {
        return stationQuality;
    }

    public void setStationQuality(String stationQuality) {
        this.stationQuality = stationQuality == null ? null : stationQuality.trim();
    }

    public String getCusNumOne() {
        return cusNumOne;
    }

    public void setCusNumOne(String cusNumOne) {
        this.cusNumOne = cusNumOne == null ? null : cusNumOne.trim();
    }

    public String getCusNumTwo() {
        return cusNumTwo;
    }

    public void setCusNumTwo(String cusNumTwo) {
        this.cusNumTwo = cusNumTwo == null ? null : cusNumTwo.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", stationId=").append(stationId);
        sb.append(", name=").append(name);
        sb.append(", featurs=").append(featurs);
        sb.append(", popularity=").append(popularity);
        sb.append(", preAvg=").append(preAvg);
        sb.append(", address=").append(address);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", isDel=").append(isDel);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", offlineTime=").append(offlineTime);
        sb.append(", onlineTime=").append(onlineTime);
        sb.append(", stationImg=").append(stationImg);
        sb.append(", stationLicense=").append(stationLicense);
        sb.append(", stationQuality=").append(stationQuality);
        sb.append(", cusNumOne=").append(cusNumOne);
        sb.append(", cusNumTwo=").append(cusNumTwo);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}