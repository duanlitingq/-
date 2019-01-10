package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.utils.superdir.sub.Result;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2018/4/25.
 */
public interface ISysUserService {

    //增加用户
    void save (SysUser sysUser) ;

    //更新用户信息
    int update (SysUserResp  sysUserResp) ;

    //通过ID获取用户对象
    SysUserResp getById(Long id);

    //用户列表
    List<SysUserResp> getList();

    //根据用户名获取用户
    SysUserResp getByUserName(String userName);

    //验证账号和密码正确性
    Result cheakName(String userName, String password);

    //判断用户名唯一不可重复
    Result getNameUnique(String userName);

    //判断手机号唯一不可重
    Result getPhoneUnique(String phone);

    //更改用户的权限
    int updateAdmin(SysUserResp sysUserResp);

    //密码的修改
    int updatePassword(SysUser sysUser);

    //更改用户的锁定
    Result updateUserLock(SysUserResp sysUserResp);
    /**
     * 管理员列表分页
     * @param params 参数
     * @param pageNum 当前x页
     * @param pageSize 每页显示x条数据
     * @return
     */
    PageInfo<SysUserResp> getPage(Map<String,Object> params, int pageNum, int pageSize);

    //更具权限的ID查询用户列表
    List<SysUserResp> getRoleIDUser(Long roleID);

    //初始化用户的密码
    int updatePassword(SysUserResp sysUserResp);



}
