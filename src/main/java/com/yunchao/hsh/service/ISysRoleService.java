package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SysRoleResp;
import com.yunchao.hsh.model.SysRole;
import com.yunchao.hsh.model.SysRoleMenu;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.utils.superdir.sub.Result;

import java.util.List;
import java.util.Map;

public interface ISysRoleService {

    //增加角色
    void save(SysRole sysRole, String ids) ;

    //更新角色
    int update(SysRole sysRole, String ids) ;

    //通过ID获取角色对象
    SysRoleResp getById(Long id);

    //通过角色名查询角色信息
    SysRoleResp getByName(String name, Long id);

    //角色列表
    List<SysRoleResp> getList();

    /**
     * 分页模板
     * 角色列表分页
     * @param params 参数
     * @param pageNum 当前x页
     * @param pageSize 每页显示x条数据
     * @return
     */
    PageInfo<SysRoleResp> getPage(Map<String,Object> params,int pageNum,int pageSize);

    //对用户的密码进行初始化
    Result updatePassword(SysUser sysUser);


    List<SysRoleMenu> getRoleMenu(Long roleId);
}
