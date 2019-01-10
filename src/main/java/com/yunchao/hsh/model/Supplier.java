package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wdz on 2018/11/6
 * Remarks:供应商
 */
public class Supplier implements Serializable{

    /**
     * 供应商编号
     */
    private Long id;

    /**
     * 供应商名称
     */
    private String name;
    /**
     * 供应商地址
     */
    private String address;
    /**
     * 联系人
     */
    private String linkman;
    /**
     * 联系电话
     */
    private String mobile;
    /**
     * 供应商信息
     */
    private String info;
    /**
     * 供应商图片
     */
    private String imgs;
    /**
     * 供应商状态是否展示
     */
    private Integer status;

    /**
     * 浏览次数
     */
    private Integer browsNum;
    /**创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**更新时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**类别*/
    private Long typeId;

    /**店铺金额*/
    private Double totalMoney;
    /*推荐级别(优先级，越大权重越大，0没有权重)*/
    private Integer priority;
    /**备用*/
    private String column1;
    private Integer column2;
    private Long column3;

    /**管理人编号*/
    private Long userId;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getImgs() {
        return imgs;
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBrowsNum() {
        return browsNum;
    }

    public void setBrowsNum(Integer browsNum) {
        this.browsNum = browsNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", linkman='" + linkman + '\'' +
                ", mobile='" + mobile + '\'' +
                ", info='" + info + '\'' +
                ", imgs='" + imgs + '\'' +
                ", status=" + status +
                ", browsNum=" + browsNum +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", typeId=" + typeId +
                ", totalMoney=" + totalMoney +
                ", priority=" + priority +
                ", column1='" + column1 + '\'' +
                ", column2=" + column2 +
                ", column3=" + column3 +
                ", userId=" + userId +
                '}';
    }
}
