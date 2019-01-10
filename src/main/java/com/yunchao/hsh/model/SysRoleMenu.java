package com.yunchao.hsh.model;

import org.apache.commons.lang.StringUtils;

/**
 * Created by wdz on 2018/11/6
 * Remarks: 角色和页面关系表
 */
public class SysRoleMenu {

    /**
     *角色编号
     */
    private Long roleId;
    /**
     * 菜单编号
     */
    private Long menuId;

    private String childs;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }


    public String getChilds() {
        return childs;
    }

    public void setChilds(String childs) {
        this.childs = childs;
    }

}
