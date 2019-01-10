package com.yunchao.hsh.controller.wxapp;

import com.up72.component.wxpay.protocol.UnifiedOrderDto;
import com.up72.component.wxpay.service.UnifiedOrderService;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.req.StationOrderReq;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.HshStationOrder;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.service.IStationOrderService;
import com.yunchao.hsh.service.IWalletService;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
import com.yunchao.hsh.utils.ArithUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/26 11:05
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Api
@Controller("stationOrder")
@RequestMapping(value = {"/page/wxapp/station/order/"})
public class StationOrderController extends BaseController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IStationOrderService stationOrderService;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private ICustomerWalletLogService customerWalletLogService;

    /**
     * 生成驿站订单
     *
     * @param request
     * @param stationOrderReq id    收款人ID
     * @return
     */
    @ApiOperation(value = "wxapp生成驿站订单", tags = "wxapp生成驿站订单")
    @RequestMapping(value = {"createStationOrder"}, method = {RequestMethod.POST})
    @ResponseBody
    public Result createStationOrder(HttpServletRequest request, StationOrderReq stationOrderReq) {

        //获取付款人信息
        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(stationOrderReq.getToken());
        log.info("login=token=>" + stationOrderReq.getToken() + ";redisOpenId=>" + redisOpenId);
        if (ObjectUtils.isEmpty(redisOpenId)) {
            return Result.build("用户不存在");
        }
        Customer customer = customerService.getByOpenID(redisOpenId);
        if (customer == null) {
            log.info("此用户不存在========================{}", customer);
            return Result.build("用户不存在");
        }
        try {

            Result result = this.stationOrderService.insertStationOrder(request, stationOrderReq, customer);
            log.info("生成订单成功===============");
            return result;
        } catch (Exception e) {
            log.error("生成订单失败====={}", e.getMessage());
        }
        return null;
    }

    /**
     * 驿站支付回调
     *
     * @return
     */
    @ApiImplicitParam(name = "orderId", value = "订单sn号", required = true, dataType = "String", paramType = "body")
    @RequestMapping(value = {"payStationOrderNotify"}, method = RequestMethod.POST)
    public void payStationOrderNotify(HttpServletRequest request) {

        log.info("****************************微信支付驿站预付回调进入了***************************");
        try {
            log.info("***********************微信支付预付回调进入了**********************");
            UnifiedOrderService unifiedOrderService = new UnifiedOrderService();
            UnifiedOrderDto unifiedOrderDto = unifiedOrderService.getUnifiedOrderDto(request);
            if (ObjectUtils.isNotEmpty(unifiedOrderDto)) {
                ///交易金额
                String txnAmt = unifiedOrderDto.getTotal_fee();
                ///商户订单号
                String orderId = unifiedOrderDto.getOut_trade_no();
                ///微信支付订单号
                String transactionId = unifiedOrderDto.getTransaction_id();
                System.out.println("交易金额:" + txnAmt + "------------------商户订单号:-" + orderId);
                ////支付的金额
                double amount = Double.valueOf(txnAmt).doubleValue() / 100;

                if (unifiedOrderDto.getReturn_code() != null && unifiedOrderDto.getResult_code() != null && "SUCCESS".equals(unifiedOrderDto.getReturn_code()) && "SUCCESS".equals(unifiedOrderDto.getResult_code())) {
                    //交易成功业务处理
                    log.info("----success 预付订单号:" + unifiedOrderDto.getOut_trade_no() + "  预付订单金额" + amount);
                    HshStationOrder stationOrder = this.stationOrderService.selectByOrderId(orderId);
                    //用户 获取驿站用户信息，给用户加入余额
                    Customer customer = customerService.getIdCustomer(stationOrder.getStationId());
                    //用户钱包
                    Wallet wallet = walletService.getByCustomerId(customer.getId());
                    //订单金额
                    double orderPrice = stationOrder.getOrderPrice().doubleValue();
                    //TODO 判断订单状态
                    if (ObjectUtils.isNotEmpty(stationOrder) && ObjectUtils.isNotEmpty(stationOrder.getOrderId()) && stationOrder.getOrderStatus() == Byte.valueOf(Constant.UN_PAY_1)) {
                        //TODO 保存用户钱包出入账记录
                        CustomerWalletLog customerWalletLog = new CustomerWalletLog();
                        customerWalletLog.setAmount(orderPrice);
                        customerWalletLog.setPayMode(Constant.YUE_PAY_MODE);
                        customerWalletLog.setType(Constant.PAY_IN);
                        customerWalletLog.setCustomerId(customer.getId());
                        customerWalletLog.setCreateDate(new Date());
                        customerWalletLog.setStatus(1);
                        customerWalletLog.setCurrentBalance(wallet.getBurse());
                        customerWalletLog.setRemark("微信支付活动订单,支出" + orderPrice + "元");
                        customerWalletLog.setUuid(transactionId);
                        customerWalletLogService.save(customerWalletLog);
                        log.info("微信支付活动订单==>用户钱包记录添加成功");
                        //TODO 更新订单信息
                        stationOrder.setPaymentTime(new Date());
                        ////支付成功
                        stationOrder.setOrderStatus((byte) 2);
                        //微信支付
                        stationOrder.setPaymentType((byte) Constant.WX_PAY_MODE);
                        stationOrder.setTradeNo(transactionId);
                        this.stationOrderService.updateByOrderId(stationOrder);
                        log.info("微信支付活动订单==>订单更新成功");
                        //修改用户钱包信息
                        //wallet.setAmountOutBurse(ArithUtil.add(wallet.getAmountOutBurse(), orderPrice));
                        wallet.setBurse(ArithUtil.add(wallet.getBurse(), orderPrice));
                        walletService.update(wallet);

                        /*发送短信通知收款人*/
                        try {
                            ALiYunSendMsgUtils.sendMsg(customer.getPhone(), Constant.IN_MONEY_MSG, String.valueOf(orderPrice));
                            log.info("短信发送成功");
                        } catch (Exception e) {
                            log.info("转账发送短信失败", e);
                        }

                        log.info("微信支付活动订单==>用户钱包修改成功");
                    } else {
                        log.info("error---订单信息错误 或 已支付处理成功   无需再次处理：");
                    }
                } else if (unifiedOrderDto.getReturn_msg() != null && !"".equals(unifiedOrderDto.getReturn_msg())) {
                    //数据异常
                    log.info("error----数据异常:" + unifiedOrderDto.getReturn_msg());
                } else {
                    //失败或者异常
                    log.info("error----失败或者异常:" + unifiedOrderDto.getReturn_msg());
                }
            } else {
                //--------请求异常，或者返回数据加密异常
                log.info("error----请求异常，或者返回数据加密异常:");
            }
            log.info("------end-----------后台调用成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
