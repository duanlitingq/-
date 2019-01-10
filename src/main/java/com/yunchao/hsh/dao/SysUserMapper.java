package com.yunchao.hsh.dao;

import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysUser;

import java.util.List;
import java.util.Map;
public interface SysUserMapper {

    int deleteById(Long id);

    void insert(SysUser sysUser);

    SysUserResp findById(Long id);

    int update(SysUserResp sysUserResp);

    List<SysUserResp> findList();

    //�ж��û����Ƿ���ȷ
    SysUserResp findByName(String userName);

    //�ж��û���Ψһ�����ظ�
    SysUserResp checkNameUnique(String userName);

    //�ж��ֻ���Ψһ������
    SysUserResp checkPhoneUnique(String phone);

    //更改用户的权限
    int updateAdmin(SysUserResp sysUserResp);

    //密码的修改
    int updatePassword(SysUser sysUser);

    List<SysUserResp> findPage(Map<String,Object> params);

    //根据角色的ID查询出用户
    List<SysUserResp> findByRoleIDUser(Long roleID);

    //更改用户的锁定状态
    int updateUserLock(SysUserResp sysUserResp);

    //初始化用户的密码
    int initPassword(SysUserResp sysUserResp);

}