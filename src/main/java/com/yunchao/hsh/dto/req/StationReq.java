package com.yunchao.hsh.dto.req;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import springfox.documentation.annotations.ApiIgnore;

import java.io.Serializable;

/**
 * 驿站
 */
@ApiModel(value = "驿站")
public class StationReq implements Serializable {

    @ApiModelProperty(value = "驿站名称")
    private String name;
    @ApiModelProperty(value = "是否删除 1上线 、2下线")
    private String isDel;
    //驿站ID
    private Long stationId;

    //当前页
    @ApiModelProperty(value = "当前页")
    private int num;
    //每条显示的条数
    @ApiModelProperty(value = "条数")
    private int pageSize = 10;

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

    //商家手机号
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getFeaturs() {
        return featurs;
    }

    public void setFeaturs(String featurs) {
        this.featurs = featurs;
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
        this.address = address;
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

    public String getStationImg() {
        return stationImg;
    }

    public void setStationImg(String stationImg) {
        this.stationImg = stationImg;
    }

    public String getStationLicense() {
        return stationLicense;
    }

    public void setStationLicense(String stationLicense) {
        this.stationLicense = stationLicense;
    }

    public String getStationQuality() {
        return stationQuality;
    }

    public void setStationQuality(String stationQuality) {
        this.stationQuality = stationQuality;
    }

    public Long getStationId() {
        return stationId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
