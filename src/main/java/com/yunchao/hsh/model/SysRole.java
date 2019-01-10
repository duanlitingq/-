package com.yunchao.hsh.model;

/**
 * Created by wangqi on 2018/4/25
 */
//角色表
public class SysRole implements java.io.Serializable {

    private Long id;  //角色id

    private String name; //角色的名字


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
