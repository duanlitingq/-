package com.yunchao.hsh.dto.resp;

import com.yunchao.hsh.model.SysUser;

import java.text.SimpleDateFormat;

/**
 * Created by admin on 2018/4/25.
 */
public class SysUserResp extends SysUser {
    //roleName
    private String roleName;
    private String createDateStr ;
    private String lastLoginDateStr ;
    //date类型转换为String类型在前端展示
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public String getCreateDateStr(){
        return sdf.format(this.getLastLoginDate()) ;
    }
    public String getLastLoginDateStr(){
        return sdf.format(this.getLastLoginDate()) ;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
