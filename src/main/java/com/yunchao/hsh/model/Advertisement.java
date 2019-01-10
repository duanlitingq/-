package com.yunchao.hsh.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wdz on 2018/11/6
 * Remarks:广告实体
 */
public class Advertisement implements Serializable{

    /**广告编号*/
    private Long id;
    /**标题*/
    private String title;
    /**信息*/
    private String info;
    /**图片*/
    private String imgs;
    /**排序*/
    private Integer sort;
    /**是否展示1：展示0：不展示*/
    private Integer status;
    /**创建时间*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    /**链接*/
    private String link;

    private String column1;
    private Integer column2;
    private Long column3;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
}
