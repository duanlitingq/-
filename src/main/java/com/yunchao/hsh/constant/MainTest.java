package com.yunchao.hsh.constant;

import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wangqi on 2018/4/26
 */
public class MainTest {
    @Autowired
    private static ISysUserService sysUserService ;
    public static List<SysUserResp> getList(){
        return sysUserService.getList();

    }
    public static void main(String[] args) {
        System.out.println(getList());
    }

}
