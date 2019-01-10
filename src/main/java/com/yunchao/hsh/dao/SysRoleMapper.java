package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SysRoleResp;
import com.yunchao.hsh.model.SysRole;

import java.util.List;
import java.util.Map;
//@Resource(name="sysRoleMapper")
public interface SysRoleMapper {

    int deleteById(Long id);

    void insert(SysRole record);

    SysRoleResp findById(Long id);

    int update(SysRole record);

    //根据角色名查询到角色信息
    SysRoleResp findByName(String name, Long id);

    //查询角色的集合
    List<SysRoleResp> findList() ;

    //对角色用户进行分页模板
    List<SysRoleResp> findPage(Map<String,Object> params);

    //查询所有角色的并对应一个角色对应的用户
    List<SysRoleResp> findByRoleIdSysuser(Long id);

    SysRoleResp findByName(Map<String,Object> map);
}
