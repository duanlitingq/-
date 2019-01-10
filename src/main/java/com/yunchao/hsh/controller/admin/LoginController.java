package com.yunchao.hsh.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wangqi on 2018/5/16
 */
@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        System.err.println("================================= 进入登录页面！！========================================");
        return "login";
    }
}
