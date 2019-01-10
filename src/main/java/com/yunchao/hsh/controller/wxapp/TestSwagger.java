package com.yunchao.hsh.controller.wxapp;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Api     //标记是一个被swwager生成文档的类
@Controller
@RequestMapping(value = "/v1/api/")
public class TestSwagger {

    //生成的方法名，value 简述 ， tags 方法名， notes：描述
    @ApiOperation(value = "一个测试API", tags = {"测试API"}, notes = "第一个测试API")
    @ResponseBody
    @RequestMapping(value = {"test"}, method = {RequestMethod.GET})
    //需要接收的参数,可以包含多个
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "密码", required = true, dataType = "String",paramType = "query"),
            /**
             * name :参数名
             * value：备注
             * dataType:数据类型 ，如果类型名称相同，请指定全路径，例如 dataType = “java.util.Date”，springfox会自动根据类型生成模型
             * required:true/false 是否必传
             * paramType: 参数类型  body 、 path 、query 、header 、form
             * body:使用@RequestBody接收数据POST有效
             * path:在url中配置{}的参数 ，rest风格
             * query:普通查询参数eg: ?query=q,{query:”q”},springMVC中不需要添加注解接收
             * header: 使用@RequestHeader接收数据
             * form:表单
             */
    })
    public String test(String userName,String pwd) {
        System.out.println(userName + pwd);
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("pwd", pwd);
        return map.toString();
    }
}
