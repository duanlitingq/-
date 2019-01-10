package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dto.resp.WalletLogResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.HshCustomerWithdrawals;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.service.ICustomerWithdrawalsService;
import com.yunchao.hsh.service.IWalletService;
import com.yunchao.hsh.utils.*;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Controller(value = "appCustomerWithdrawalsController")
@RequestMapping("/page/wxapp/customerWithdrawals/")
public class CustomerWithdrawalsController {
    @Autowired
    private  ICustomerWithdrawalsService customerWithdrawalsService;
    @Autowired
    private  ICustomerWalletLogService customerWalletLogService;
    @Autowired
    private IWalletService walletService;
    @Autowired
    private ICustomerService customerService;

    @RequestMapping(value = "saveCustomerWithdrawals",method = RequestMethod.POST)
    @ResponseBody
    public Result saveCustomerWithdrawals(String token,HshCustomerWithdrawals customerWithdrawals) {
        Result result = new Result();
        if (ObjectUtils.isNotEmpty(token)) {
            //获取提现金额
            double money = customerWithdrawals.getMoney();
            if(money>0.0){
                String redisOpenId = ToolsUtil.getRedisOpenId(token);
                Customer customer = customerService.getByOpenID(redisOpenId);
                //获取用户id
                Long customerId = customer.getId();
                //获取用户钱包
                Wallet customerWallet = walletService.getByCustomerId(customerId);
                if (customerWallet != null) {
                    //获取当前余额
                    Double burse = customerWallet.getBurse();
                    if (money > burse) {
                        return result.setF("提现金额不能大于余额");
                    }
                    double v = BigDecimalUtils.subtract(burse, money).doubleValue();
                    customerWallet.setBurse(v);
                    walletService.update(customerWallet);
                    CustomerWalletLog customerWalletLog=new CustomerWalletLog();
                    customerWalletLog.setAmount(money);
                    customerWalletLog.setPayMode(1);
                    customerWalletLog.setType(3);
                    customerWalletLog.setCustomerId(customerId);
                    customerWalletLog.setCreateDate(new Date());
                    customerWalletLog.setStatus(0);
                    customerWalletLog.setCurrentBalance(v);//用的上面钱包的钱
                    customerWalletLog.setRemark("用户提现");
                    customerWalletLogService.save(customerWalletLog);
                }
                customerWithdrawals.setCustomerId(customerId);
                Date date = new Date();
                customerWithdrawals.setCreateTime(date);
                customerWithdrawalsService.saveWithdrawals(customerWithdrawals);
                String phone = customer.getPhone();
                if (StringUtils.isNotEmpty(phone)){
                    String random = ALiYunSendMsgUtils.sendMsg(phone, Constant.WITHDRAWAL_MSG, Double.toString(money));
                }
                return result.setS(0, "提交成功!");
            }
            return result.setF("提现失败！",1);
        } else {
            return result.setF("用户数据异常，请重新登陆");
        }
    }
}