package com.yunchao.hsh.model;
/**
 *
 * 店铺类别暂时弃用，调用suppliergoodstype
 *
 *
 * */
public class SupplierType {

    private Long id;

    private String name;

    private Integer sort;

    private Integer status;

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


}
