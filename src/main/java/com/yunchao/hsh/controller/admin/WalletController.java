package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.service.IWalletService;
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

/**
 * @Description: 用户钱包controller
 * @Author: 隗鹏
 * @CreateDate: 2018/11/8 0007 10:30
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Controller
@RequestMapping("/page/admin/wallet/")
public class WalletController {
    @Autowired
    private IWalletService walletService;

    /**
     * 获取用户钱包明细
     * @param customerId
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取用户钱包列表", tags = {"用户钱包管理"}, notes = "获取用户钱包列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "customerId", value = "用户ID", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = false, dataType = "String", paramType = "query")
    })
    public Result getPage(Long customerId, HttpServletRequest request) {
        Result result = new Result();
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("customerId", customerId);
        PageInfo<Wallet> page = walletService.getPage(map, pageNum, pageSize);
        result.setS("操作成功");
        result.setData(page);
        return result;
    }
}
