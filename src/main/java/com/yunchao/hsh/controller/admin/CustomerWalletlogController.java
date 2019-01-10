package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.service.ISysUserService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @Description: 用户钱包出入账记录controller
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7 0007 13:28
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Controller
@RequestMapping("/page/admin/customerWalletlog/")
public class CustomerWalletlogController {
    @Autowired
    private ICustomerWalletLogService customerWalletLogService;
    @Autowired
    private ISysUserService sysUserService;

    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取用户钱包出入账记录列表", tags = {"用户钱包出入账记录管理"}, notes = "获取用户钱包出入账记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "payMode", value = "消费方式", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, dataType = "String", paramType = "query")
    })
    public Result getPage(Integer status, String phone, Integer payMode, Integer type, Long customerId, HttpServletRequest request) {
        Result result = new Result();
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("phone", phone);
        map.put("payMode", payMode);
        map.put("type", type);
        map.put("status", status);
        map.put("customerId", customerId);
        PageInfo<CustomerWalletLog> page = customerWalletLogService.getPage(map, pageNum, pageSize);
        /*List<CustomerWalletLog>list= page.getDate();*/
        List<CustomerWalletLog> list = page.getList();
        for (CustomerWalletLog customerWalletLog : list) {
            Long operationId = customerWalletLog.getOperationId();
            if (operationId != null) {
                SysUserResp sysUser = sysUserService.getById(operationId);
                if (sysUser != null) {
                    String userName = sysUser.getUserName();
                    customerWalletLog.setSysUserName(userName);
                }
            }
            //获取支付方式
            Integer payMode1 = customerWalletLog.getPayMode();
            if (payMode1 != null) {
                if (payMode1 == 1) {
                    customerWalletLog.setPayModeStr("余额");
                } else if (payMode1 == 2) {
                    customerWalletLog.setPayModeStr("微信");
                } else if (payMode1 == 3) {
                    customerWalletLog.setPayModeStr("积分");
                } else if (payMode1 == 4) {
                    customerWalletLog.setPayModeStr("系统");
                }
            }
            Integer type1 = customerWalletLog.getType();
            if (type1 != null) {
                if (type1 == 1) {
                    customerWalletLog.setTypeStr("支出");
                } else if (type1 == 2) {
                    customerWalletLog.setTypeStr("收入");
                } else if (type1 == 3) {
                    customerWalletLog.setTypeStr("提现");
                } else if (type1 == 4) {
                    customerWalletLog.setTypeStr("充值");
                }
            }
        }
        result.setS("操作成功");
        result.setData(page);
        return result;
    }
}
