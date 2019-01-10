package com.yunchao.hsh.controller.supplier;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.ISupplierOrderService;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.DateUtils;
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

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 供应商订单
 */

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller(value = "supplierSupplierOrderController")
@RequestMapping(value = "/page/supplier/order")
public class SupplierOrderController {

    @Autowired
    private ISupplierOrderService supplierOrderService;
    @Autowired
    private ISysUserService sysUserService;

    @ApiOperation(value = "APP供应商订单", tags = {"APP供应商端订单"}, notes = "订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "用户名", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "orderNo", value = "订单号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数据", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "payStatus", value = "支付状态", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/findPage")
    @ResponseBody
    public Result findPage(Integer pageNum, Integer pageSize, String orderNo, Integer payStatus, Integer orderStatus, String token, Long supplierId) {

        Result result = new Result();
        if (StringUtil.isEmpty(token)) {
            result.setData(2);
            return result.setF("用户信息异常请重新登录登录");
        }
        if (supplierId == null || supplierId == 0) {
            result.setData(2);
            return result.setF("店铺信息异常");
        }
        RedisUtil redis = RedisUtil.getInstance();
        Long userId = (Long) redis.getFromRedis(token);
        SysUserResp sysUserResp = sysUserService.getById(userId);
        if (ObjectUtils.isEmpty(sysUserResp)) {
            result.setData(2);
            return result.setF("用户信息异常，请重新登录");
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("supplierId", supplierId);
        if (StringUtil.isNotEmpty(orderNo)) {
            map.put("orderNo", orderNo.trim());
        }
        map.put("payStatus", 2);
        map.put("orderStatus", orderStatus);
        try {
            //H5订单列表都按时间排序（时间越晚在前面）
            PageHelper.orderBy(" CREATE_TIME desc");
            PageInfo<SupplierOrderResp> page = supplierOrderService.getPage(map, pageNum, pageSize);
            System.out.println("返回订单列表");
            return result.setS("", page);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "APP供应商订单", tags = {"APP供应商订单详细"}, notes = "APP供应商订单详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单号", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = "/getOrderDetail")
    @ResponseBody
    public Result getOrderDetail(Long orderId) {
        Result result = new Result();
        try {
            SupplierOrderResp orderResp = supplierOrderService.findById(orderId);
            if (ObjectUtils.isNotEmpty(orderResp)) {
                result.setS("", orderResp);
            } else {
                return result.setF("订单数据异常，请刷新重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "APP订单驳回", tags = "APP商家驳回接单数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(name = "token", value = "随机数", dataType = "String"),
    })
    @RequestMapping("backOrder")
    @ResponseBody
    public Result backOrder(Long orderId, String token) {
        Result result = new Result();
        try {
            if (StringUtils.isNotEmpty(token)) {
                RedisUtil redisUtil = RedisUtil.getInstance();
                Long userId = (Long) redisUtil.getFromRedis(token);
                SysUserResp sysUserResp = sysUserService.getById(userId);
                if (ObjectUtils.isNotEmpty(sysUserResp)) {
                    SupplierOrderResp supplierOrderResp = supplierOrderService.findById(orderId);
                    if (supplierOrderResp != null) {
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("id", orderId);
                        map.put("orderStatus", 6);
                        supplierOrderService.backOrder(map, supplierOrderResp);
                        result.setS("", supplierOrderResp);
                    } else {
                        result.setF("订单数据异常请刷新重试");
                    }
                } else {
                    result.setData(2);
                    return result.setF("用户数据异常，请登录重试");
                }
            } else {
                result.setData(2);
                return result.setF("用户数据异常，请登录重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "APP后台店铺订单", tags = "APP商家确认接单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(name = "token", value = "随机数", dataType = "String"),
            @ApiImplicitParam(name = "orderStatus", value = "1：未接单2：已接单 3 已发货 4 已完成 5：取消订单，6：已取消 7：商家驳回", dataType = "Integer"),
    })
    @RequestMapping("confirmOrder")
    @ResponseBody
    public Result confirmOrder(Long orderId, String token,Integer orderStatus) {
        Result result = new Result();
        try {
            if (StringUtils.isNotEmpty(token)) {
                RedisUtil redisUtil = RedisUtil.getInstance();
                Long userId = (Long) redisUtil.getFromRedis(token);
                SysUserResp sysUserResp = sysUserService.getById(userId);
                if (ObjectUtils.isNotEmpty(sysUserResp)) {
                    SupplierOrderResp su = supplierOrderService.findById(orderId);
                    if (ObjectUtils.isNotEmpty(su)) {
                        su.setConfirmTime(new Date());
                        su.setOrderStatus(orderStatus);
                        supplierOrderService.confirmOrder(su);
                        result.setS("", su);
                    } else {
                        result.setSuccess(false);
                        result.setMessage("订单数据异常，请刷新重试");
                    }
                } else {
                    result.setData(2);
                    return result.setF("用户数据异常，请登录重试");
                }
            } else {
                result.setData(2);
                return result.setF("用户数据异常，请登录重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("用户数据异常，请登录重试");
        }
        return result;
    }



}
