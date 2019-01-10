package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.up72.framework.util.ObjectUtils;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.utils.*;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Description: 用户controller
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7 0007 13:28
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Controller
@RequestMapping("/page/admin/customer/")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取用户列表", tags = {"用户管理"}, notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "nickname", value = "用户昵称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, dataType = "String", paramType = "query")
    })
    public Result getPage(String phone, String nickname, HttpServletRequest request) {
        Result result = new Result();
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("nickname", nickname);
        PageInfo<Customer> page = customerService.getPage(map, pageNum, pageSize);
        result.setS("操作成功");
        result.setData(page);
        return result;
    }
}
