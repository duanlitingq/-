package com.yunchao.hsh.controller.supplier;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.dto.resp.SupplierWalletLogResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SupplierCashWithdrawalApplication;
import com.yunchao.hsh.model.SupplierWalletLog;
import com.yunchao.hsh.service.*;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
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
import java.util.Map;

/**
 * 供应商
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping(value = "/page/supplier/wallet")
public class SupplierWalletController {

    @Autowired
    private ISupplierWalletLogService supplierWalletLogService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ISupplierCashWithdrawalApplicationService supplierCashWithdrawalApplicationService;
    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private ISupplierOrderService supplierOrderService;

    @ApiOperation(value = "APP供应商钱包", tags = {"APP供应商钱包"}, notes = "供应商钱包")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value = "/getSupplierWallet")
    @ResponseBody
    public Result getSupplierWallet(String token, Long supplierId) {
        Result result = new Result();
        try {
            if (StringUtils.isNotEmpty(token)) {
                RedisUtil redisUtil = RedisUtil.getInstance();
                Long userId = (Long) redisUtil.getFromRedis(token);
                SysUserResp sysUserResp = sysUserService.getById(userId);
                if (ObjectUtils.isNotEmpty(sysUserResp)) {
                    SupplierResp supplierResp = supplierService.findById(supplierId);
                    if (ObjectUtils.isNotEmpty(supplierResp)) {
                        result.setS("", supplierResp);
                    } else {
                        result.setF("店铺数据异常，请刷新重试");
                    }
                } else {
                    result.setData(2);
                    result.setF("用户信息异常，请刷新重试");
                }
            } else {
                result.setData(2);
                result.setF("用户信息异常，请刷新重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "APP供应商出入账记录", tags = {"APP供应出入账记录"}, notes = "供应商出入账记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize", required = true, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "pageNum", required = true, dataType = "integer", paramType = "query"),
            @ApiImplicitParam(name = "supplierId", value = "supplierId", required = true, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value = "/findSupplierWalletLog")
    @ResponseBody
    public Result findSupplierWalletLog(String token, Integer pageSize, Integer pageNum, Long supplierId) {
        Result result = new Result();
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        if (StringUtils.isBlank(token)) {
            result.setData(2);
            return result.setF("用户数据异常，请重新登录");
        }
        RedisUtil redisUtil = RedisUtil.getInstance();
        try {
            Long userId = (Long) redisUtil.getFromRedis(token);
            SysUserResp sysUserResp = sysUserService.getById(userId);
            if (ObjectUtils.isNotEmpty(sysUserResp)) {
                Map<String, Object> map = new HashMap<>();
                map.put("supplierId", supplierId);
                PageHelper.orderBy(" id desc ");
                PageInfo<SupplierWalletLogResp> page = supplierWalletLogService.findPage(map, pageNum, pageSize);
                result.setS("", page);
            } else {
                result.setData(2);
                return result.setF("用户数据异常，请重新登录");
            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "APP店铺提现申请", tags = "APP店铺提现列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "Integer"),
    })
    @RequestMapping(value = "findPage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result findPage(String token, Long supplierId, Integer pageNum, Integer pageSize, Integer status) {
        Result result = new Result();
        if (pageSize == null) {
            pageSize = 20;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        try {
            if (StringUtils.isEmpty(token)) {
                result.setData(2);
                return result.setF("用户数据异常，请重新登录");
            } else {
                RedisUtil redisUtil = RedisUtil.getInstance();
                Long userId = (Long) redisUtil.getFromRedis(token);
                SysUserResp userResp = sysUserService.getById(userId);
                if (ObjectUtils.isNotEmpty(userResp)) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    if (status != null && status > 0) {
                        map.put("status", status);
                    }
                    if (supplierId != null && supplierId > 0) {
                        map.put("supplierId", supplierId);
                    }
                    PageHelper.orderBy("  create_time desc ");
                    PageInfo<SupplierCashWithdrawalApplication> page = supplierCashWithdrawalApplicationService.findPage(map, pageNum, pageSize);
                    result.setS("", page);
                } else {
                    result.setData(2);
                    return result.setF("用户数据异常，请重新登录");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }

    @ApiOperation(value = "APP店铺提现申请", tags = "APP店铺提现申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "receiveName", value = "收款人姓名", dataType = "String"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", dataType = "String"),
            @ApiImplicitParam(name = "bankCard", value = "银行卡", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "提现电话", dataType = "String"),
            @ApiImplicitParam(name = "amountOfMoney", value = "提现金额", dataType = "double")
    })
    @RequestMapping("doAdd")
    @ResponseBody
    public Result doAdd(String token, SupplierCashWithdrawalApplication apply) {
        Result result = new Result();
        System.out.println("保存请求");
        if (apply.getAmountOfMoney()==null||apply.getAmountOfMoney()<=0) {
            result.setData(2);
            return result.setF("提现数据异常，请使用合法操作");
        }
        if (StringUtils.isEmpty(token)) {
            result.setData(2);
            return result.setF("用户数据异常，请重新登录");
        }
        try {
            RedisUtil redisUtil = RedisUtil.getInstance();
            Long userId = (Long) redisUtil.getFromRedis(token);
            SysUserResp userResp = sysUserService.getById(userId);
            if (ObjectUtils.isNotEmpty(userResp)) {
                SupplierResp supplierResp = supplierService.findById(apply.getSupplierId());
                if (ObjectUtils.isNotEmpty(supplierResp)) {
                    if (apply.getAmountOfMoney() > supplierResp.getTotalMoney()) {
                        return result.setF("余额不足");
                    } else {
                        supplierCashWithdrawalApplicationService.insert(apply);
                        result.setS("", apply);
                        ALiYunSendMsgUtils.sendMsg(apply.getPhone(), com.yunchao.hsh.constant.Constant.WITHDRAWAL_MSG, apply.getAmountOfMoney().toString());
                    }
                } else {
                    result.setData(2);
                    return result.setF("店铺数据异常，请重新登录重试");
                }
            } else {
                result.setData(2);
                result.setF("用户信息异常，请重新登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("系统异常，请刷新重试");
        }
        return result;
    }

    @ApiOperation(value = "APP店铺提现申请", tags = "APP店铺提现申请详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", dataType = "String"),
            @ApiImplicitParam(name = "id", value = "申请编号", dataType = "Long"),
    })
    @RequestMapping("getApplyDetail")
    @ResponseBody
    public Result getApplyDetail(String token,Long id){
        Result result = new Result();
        System.out.println("保存请求");
        if (StringUtils.isEmpty(token)) {
            result.setData(2);
            return result.setF("用户数据异常，请重新登录");
        }
        try {
            RedisUtil redisUtil = RedisUtil.getInstance();
            Long userId = (Long) redisUtil.getFromRedis(token);
            SysUserResp userResp = sysUserService.getById(userId);
            if (ObjectUtils.isNotEmpty(userResp)) {
                SupplierWalletLogResp walletLogResp = supplierWalletLogService.getById(id);
                Integer inOrOut = walletLogResp.getInOrOut();
                if(inOrOut == 1){
                    // 收入，调用订单数据
                    SupplierOrderResp byId = supplierOrderService.findById(walletLogResp.getOrderId());
                    result.setS("",byId);
                }else if(inOrOut == 2){
                    // 支出（提现），调用提现记录
                    SupplierCashWithdrawalApplication byId = supplierCashWithdrawalApplicationService.getById(walletLogResp.getOrderId());
                    result.setS("",byId);
                }
            } else {
                result.setData(2);
                result.setF("用户信息异常，请重新登录");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("系统异常，请刷新重试");
        }
        return result;
    }
}
