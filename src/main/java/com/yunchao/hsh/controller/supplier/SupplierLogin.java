package com.yunchao.hsh.controller.supplier;

import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.MnUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/page/supplier/userLogin")
public class SupplierLogin {

    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "APP供应商登录", tags = {"APP供应商登录"}, notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = false, dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "password", value = "密码", required = false, dataType = "String", paramType = "body"),
    })

    @RequestMapping(value = "/login")
    @ResponseBody
    public Result login(HttpServletRequest request,String userName, String password) {
        Result result = new Result();
        try {
            RedisUtil redis = RedisUtil.getInstance();
            if (StringUtils.isBlank(userName)) {
                return result.setF("用户名不能为空");
            }
            if (StringUtils.isBlank(password)) {
                return result.setF("密码不能为空");
            }
            SysUserResp sysUserResp = sysUserService.getByUserName(userName);
            if (ObjectUtils.isNotEmpty(sysUserResp)) {
                String md5Password = MnUtil.md5(password);
                if (sysUserResp.getPassword().equals(md5Password)) {
                    sysUserResp.setLastLoginDate(new Date());
                    sysUserService.update(sysUserResp);
                    String random = ToolsUtil.getRandom(32, 4);
                    Long userId = sysUserResp.getId();
                    redis.putInRedis(Constant.SUPPLIER_LOGIN + random, userId, 7 * 24 * 60 * 60);
                    result.setS("", sysUserResp, Constant.SUPPLIER_LOGIN + random);
                } else {
                    result.setF("用户名或密码不正确!");
                }
            } else {
                result.setF("用户名或密码不正确!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
