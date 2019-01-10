package com.yunchao.hsh.model;

import java.io.Serializable;

public class HshActivityWelfare implements Serializable {
    private Long id;

    /**
     * 名称
     */
    private String welfareTitle;

    /**
     * 图片
     */
    private String welfareImg;

    /**
     * 是否展示 1 展示 2不展示
     */
    private Byte welfareStatus;

    /**
     * 标签名
     */
    private String welfareTag;

    /**
     * 保留字段
     */
    private String cusNum1;

    /**
     * 保留字段
     */
    private Long cusNum2;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWelfareTitle() {
        return welfareTitle;
    }

    public void setWelfareTitle(String welfareTitle) {
        this.welfareTitle = welfareTitle == null ? null : welfareTitle.trim();
    }

    public String getWelfareImg() {
        return welfareImg;
    }

    public void setWelfareImg(String welfareImg) {
        this.welfareImg = welfareImg == null ? null : welfareImg.trim();
    }

    public Byte getWelfareStatus() {
        return welfareStatus;
    }

    public void setWelfareStatus(Byte welfareStatus) {
        this.welfareStatus = welfareStatus;
    }

    public String getWelfareTag() {
        return welfareTag;
    }

    public void setWelfareTag(String welfareTag) {
        this.welfareTag = welfareTag == null ? null : welfareTag.trim();
    }

    public String getCusNum1() {
        return cusNum1;
    }

    public void setCusNum1(String cusNum1) {
        this.cusNum1 = cusNum1 == null ? null : cusNum1.trim();
    }

    public Long getCusNum2() {
        return cusNum2;
    }

    public void setCusNum2(Long cusNum2) {
        this.cusNum2 = cusNum2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", welfareTitle=").append(welfareTitle);
        sb.append(", welfareImg=").append(welfareImg);
        sb.append(", welfareStatus=").append(welfareStatus);
        sb.append(", welfareTag=").append(welfareTag);
        sb.append(", cusNum1=").append(cusNum1);
        sb.append(", cusNum2=").append(cusNum2);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}