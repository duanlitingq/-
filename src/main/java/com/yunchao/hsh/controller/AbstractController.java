package com.yunchao.hsh.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;

/**
 * Created by wangqi on 2017/11/13
 */
public abstract class AbstractController {
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        webDataBinder.registerCustomEditor(java.util.Date.class,new CustomDateEditor(simpleDateFormat,true));
    }
}
