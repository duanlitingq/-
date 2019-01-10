package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper {

    void save(SysRoleMenu srm);

    void delete(Long id);

    List<SysRoleMenu> getRoleMenu(Long roleId);
}
