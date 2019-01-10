package com.yunchao.hsh.model;

import com.yunchao.hsh.dto.resp.MenuResp;

import java.util.List;

public class Menu {
    private Long id;

    private String name;

    private Long parentId;

    private Integer status;

    private String pageUrl;

    private Integer level ;

    private Integer sort;
    private List<MenuResp> childs;
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

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
        this.name = name == null ? null : name.trim();
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl == null ? null : pageUrl.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public List<MenuResp> getChilds() {
        return childs;
    }

    public void setChilds(List<MenuResp> childs) {
        this.childs = childs;
    }
}