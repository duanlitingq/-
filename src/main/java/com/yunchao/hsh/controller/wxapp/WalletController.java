package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.WalletLogResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.service.IWalletService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

@Controller(value = "appWalletController")
@RequestMapping("/page/wxapp/wallet/")
public class WalletController {
    Logger logger = Logger.getLogger("WalletController.class");
    @Autowired
    private IWalletService walletService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerWalletLogService customerWalletLogService;

    @RequestMapping(value = "getWallet", method = RequestMethod.POST)
    @ResponseBody
    public Result getWallet(String tokenId) {
        Result result = new Result();
        try {
            String openID = ToolsUtil.getRedisOpenId(tokenId);
            if (openID != null && openID != "") {
                Customer customer = customerService.getCustomerByOpenId(openID);
                Wallet byWallet = walletService.getByCustomerId(customer.getId());
                result.setS("", byWallet);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("系统异常");
        }
        return result;
    }

    /**
     * 获取余额明细
     */
    @RequestMapping(value = "getIntegralDetail")
    @ResponseBody
    public Result getIntegralDetail(String token, int page, @RequestParam(defaultValue = "10") int pageSize) {
       Result result=new Result();
        try {
            if (StringUtils.isNotBlank(token)) {
                String redisOpenId = ToolsUtil.getRedisOpenId(token);
                Customer customer = this.customerService.getByOpenID(redisOpenId);
                if (customer != null) {
                    Long customerId = customer.getId();
                    HashMap<String,Object>map=new HashMap<String,Object>();
                    map.put("customerId",customerId);
                    PageInfo<WalletLogResp> pageIntegral = this.customerWalletLogService.getPageIntegral(customerId, page, pageSize);
                    result.setS("",pageIntegral);
                    return  result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
