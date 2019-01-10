package com.yunchao.hsh.controller.supplier;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.service.ISupplierOrderService;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

/**
 * 获取用户店铺数据
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller(value = "supplierSupplierManageController")
@RequestMapping("/page/supplier/manage")
public class SupplierManageController {

    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISupplierOrderService supplierOrderService;

    @ApiOperation(value = "APP供应商店铺", tags = {"APP供应商店铺"}, notes = "店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/findPage")
    @ResponseBody
    public Result findPage(String token, Integer pageSize, Integer pageNum) {
        Result result = new Result();
        try {
            if (StringUtils.isNotEmpty(token)) {
                RedisUtil redisUtil = RedisUtil.getInstance();
                Long userId = (Long) redisUtil.getFromRedis(token);
                SysUserResp sysUserResp = sysUserService.getById(userId);
                if (ObjectUtils.isNotEmpty(sysUserResp)) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("userId", userId);
                    PageInfo<SupplierResp> page = supplierService.getPage(map, pageNum, pageSize);
                    return result.setS("", page);
                } else {
                    result.setData(2);
                    result.setF("用户数据异常，请重新登录");
                }
            } else {
                result.setData(2);
                result.setF("用户数据异常，请重新登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "APP供应商店铺", tags = {"APP店铺详细"}, notes = "店铺详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/getSupplier")
    @ResponseBody
    public Result getSupplier(String token, Long supplierId) {
        Result result = new Result();
        try {
            if (StringUtils.isEmpty(token)) {
                result.setData(2);
                return result.setF("用户数据异常,请重新登录");
            } else {
                if(supplierId == null){
                    return result.setF("店铺数据异常");
                }
                RedisUtil redisUtil = RedisUtil.getInstance();
                Long userId = (Long) redisUtil.getFromRedis(token);
                SysUserResp sysUserResp = sysUserService.getById(userId);
                if (ObjectUtils.isNotEmpty(sysUserResp)) {
                    SupplierResp supplierResp = supplierService.findById(supplierId);
                    //新订单
                    Integer newOrder = supplierOrderService.countOrderBuyOrderStatus(supplierId, 1);
                    //待发货
                    Integer waitOrder = supplierOrderService.countOrderBuyOrderStatus(supplierId, 2);
                    supplierResp.setNewOrderNum(newOrder);
                    supplierResp.setWaitOrderNum(waitOrder);
                    result.setS("", supplierResp);
                } else {
                    result.setData(2);
                    return result.setF("用户数据异常,请重新登录");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

}
