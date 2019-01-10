package com.yunchao.hsh.controller.wxapp;

import com.up72.component.wxpay.protocol.UnifiedOrderDto;
import com.up72.component.wxpay.service.UnifiedOrderService;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.resp.OrderDetailsResp;
import com.yunchao.hsh.dto.resp.OrderGetLogResp;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.*;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
import com.yunchao.hsh.utils.ArithUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/20 10:53
 * @Description:
 * @Email: hkwind959@gmail.com
 * @update:隗鹏
 * @updateDate: 2018/11/20 13:00
 */
@Controller
@RequestMapping(value = "/page/wxapp/active/order/")
public class ActiviOrderController extends BaseController {
    @Autowired
    private IActivityOrderService activityOrderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IActivityService activityService;

    @Autowired
    private IHshActivityOrderGetLogService orderGetLogService;

    @Autowired
    private ICustomerWalletLogService customerWalletLogService;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private IIntegralRulesService rulesService;

    @Autowired
    private IActivityWelfareService activityWelfareService;

    @Autowired
    private IHshActivityOrderGetLogService activityOrderGetLogService;
    Logger logger = Logger.getLogger("ActiviOrderController.class");

    @ApiOperation(value = "生成活动订单", tags = "生成活动订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", required = true, value = "用户token", dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "activeId", required = true, value = "活动ID", dataType = "String", paramType = "body"),
            @ApiImplicitParam(name = "logisticsId", required = true, value = "收货人地址ID", dataType = "String", paramType = "body")
    })
    @RequestMapping(value = "createActivityOrder", method = RequestMethod.POST)
    @ResponseBody
    public Result createActivityOrder(String token, Long activeId, Long logisticsId, Long welfareId) {
        Result result = new Result();
        try {
            Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
            //     Customer customer = customerService.getIdCustomer(1l)
            //welfareId 选中的活动福利ID
            if (ObjectUtils.isEmpty(customer)) {
                logger.info("创建活动订单==>用户为空");
                return result.setF("用户为空");
            }
            HshActivity activity = (HshActivity) activityService.findByActivityId(activeId).getData();
            if (ObjectUtils.isEmpty(activity)) {
                logger.info("创建活动订单==>活动为空");
                return result.setF("活动为空");
            }
            com.yunchao.hsh.utils.superdir.Result orderResult = activityOrderService.createOrder(activeId, customer.getId(), logisticsId, welfareId);//创建订单
            //返回前台订单号,金额
            if (orderResult.isSuccess()) {
                Object orderSn = orderResult.getData();//订单号
                Double price = activity.getActivityPrice().doubleValue();

                result.setS("操作成功", orderSn, price * 100, customer.getOpenId());
                logger.info("创建活动订单==>成功");
            } else {
                result.setF("操作失败", orderResult.getMessage());
                logger.info("创建活动订单==>失败");
            }
            return result;
        } catch (Exception e) {
            log.error("创建订单失败-----{}", e.getMessage());
            return result.setF("系统异常");
        }
    }

    //余额支付
    @ApiOperation(value = "余额支付活动订单", tags = "余额支付活动订单")
    @RequestMapping(value = {"payActivityOrderByYue"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderSn", value = "订单sn号", required = true, dataType = "String", paramType = "body")
    })
    public Result payActivityOrderByYue(String orderSn) {
        //TODO  待测试
        Result result = new Result();
        try {
            HshActivityOrder activityOrder = activityOrderService.selectByOrderId(orderSn);
            if (ObjectUtils.isEmpty(activityOrder)) {
                logger.info("余额支付活动订单,订单信息为空");
                return result.setF("订单信息为空");
            }
            HshActivity activity = (HshActivity) activityService.findByActivityId(activityOrder.getActivityId()).getData();//活动
            Customer customer = customerService.getIdCustomer(activityOrder.getUserId());//用户
            Wallet wallet = walletService.getByCustomerId(customer.getId());//用户钱包
            double activityPrice = activity.getActivityPrice().doubleValue();
            if (ObjectUtils.isEmpty(wallet) || (wallet.getBurse() < activityPrice)) {
                //钱包信息错误,或者余额不足
                logger.info("余额支付活动订单==>余额不足");
                return result.setF("余额不足");
            }
            //1 更新订单信息
            activityOrder.setPayTime(new Date());
            activityOrder.setOrderStatus(Byte.valueOf(Constant.YES_PAY_2));//支付成功
            activityOrder.setPayMode(Constant.YUE_PAY_MODE);//余额支付方式
            activityOrder.setUpdateTime(new Date());
            activityOrderService.updateById(activityOrder);
            logger.info("余额支付活动订单==>订单更新成功");
            //2 修改用户钱包信息
            wallet.setBurse(ArithUtil.sub(wallet.getBurse(), activityPrice));
            wallet.setAmountOutBurse(ArithUtil.add(wallet.getAmountOutBurse(), activityPrice));
            walletService.update(wallet);
            logger.info("余额支付活动订单==>用户钱包修改成功");
            //3 生成用户钱包出入账记录
            CustomerWalletLog customerWalletLog = new CustomerWalletLog();
            customerWalletLog.setAmount(activityPrice);
            customerWalletLog.setPayMode(Constant.YUE_PAY_MODE);
            customerWalletLog.setType(Constant.PAY_OUT);
            customerWalletLog.setCustomerId(customer.getId());
            customerWalletLog.setCreateDate(new Date());
            customerWalletLog.setStatus(1);
            customerWalletLog.setCurrentBalance(wallet.getBurse());
            customerWalletLog.setRemark("余额支付活动订单,支出" + activityPrice + "元");
            customerWalletLogService.save(customerWalletLog);
            logger.info("余额支付活动订单==>用户钱包记录添加成功");
            //4添加用户领取记录
            activityOrderGetLogService.inserBatchOrderGetLog(orderSn);
            logger.info("余额支付活动订单==>添加领取记录成功");
            result.setS("操作成功");
        } catch (Exception e) {
            logger.info("余额支付活动订单==>系统异常");
            e.printStackTrace();
            result.setF("操作失败");
        }
        return result;
    }

    /**
     * 活动订单支付回调
     *
     * @param request
     */
    @RequestMapping(value = {"payActiveOrderNotify"}, method = RequestMethod.POST)
    public void payActiveOrderNotify(HttpServletRequest request) {
        try {
            logger.info("***********************微信支付预付回调进入了**********************");
            UnifiedOrderService unifiedOrderService = new UnifiedOrderService();
            UnifiedOrderDto unifiedOrderDto = unifiedOrderService.getUnifiedOrderDto(request);
            if (ObjectUtils.isNotEmpty(unifiedOrderDto)) {
                String txnAmt = unifiedOrderDto.getTotal_fee();///交易金额
                String orderId = unifiedOrderDto.getOut_trade_no();///商户订单号
                String transactionId = unifiedOrderDto.getTransaction_id();///微信支付订单号
                System.out.println("交易金额:" + txnAmt + "------------------商户订单号:-" + orderId);
                double amount = Double.valueOf(txnAmt).doubleValue() / 100;////支付的金额

                if (unifiedOrderDto.getReturn_code() != null && unifiedOrderDto.getResult_code() != null && "SUCCESS".equals(unifiedOrderDto.getReturn_code()) && "SUCCESS".equals(unifiedOrderDto.getResult_code())) {
                    //交易成功业务处理
                    log.info("----success 预付订单号:" + unifiedOrderDto.getOut_trade_no() + "  预付订单金额" + amount);
                    HshActivityOrder activityOrder = activityOrderService.selectByOrderId(orderId);
                    HshActivity activity = (HshActivity) activityService.findByActivityId(activityOrder.getActivityId()).getData();//活动
                    Customer customer = customerService.getIdCustomer(activityOrder.getUserId());//用户
                    Wallet wallet = walletService.getByCustomerId(customer.getId());//用户钱包
                    double activityPrice = activity.getActivityPrice().doubleValue();
                    //TODO 判断订单状态
                    if (ObjectUtils.isNotEmpty(activityOrder) && ObjectUtils.isNotEmpty(activityOrder.getOrderId()) && activityOrder.getOrderStatus() == Byte.valueOf(Constant.UN_PAY_1)) {
                        //TODO 保存用户钱包出入账记录
                        CustomerWalletLog customerWalletLog = new CustomerWalletLog();
                        customerWalletLog.setAmount(activityPrice);
                        customerWalletLog.setPayMode(Constant.WX_PAY_MODE);
                        customerWalletLog.setType(Constant.PAY_OUT);
                        customerWalletLog.setCustomerId(customer.getId());
                        customerWalletLog.setCreateDate(new Date());
                        customerWalletLog.setStatus(1);
                        customerWalletLog.setCurrentBalance(wallet.getBurse());
                        customerWalletLog.setRemark("微信支付活动订单,支出" + activityPrice + "元");
                        customerWalletLog.setUuid(transactionId);
                        customerWalletLogService.save(customerWalletLog);
                        logger.info("微信支付活动订单==>用户钱包记录添加成功");
                        //TODO 更新订单信息
                        activityOrder.setPayTime(new Date());
                        activityOrder.setOrderStatus(Byte.valueOf(Constant.YES_PAY_2));//支付成功
                        activityOrder.setPayMode(Constant.WX_PAY_MODE);//余额支付方式
                        activityOrder.setUpdateTime(new Date());
                        activityOrder.setTradeNo(transactionId);
                        activityOrderService.updateById(activityOrder);
                        logger.info("微信支付活动订单==>订单更新成功");
                        //添加领取记录
                        activityOrderGetLogService.inserBatchOrderGetLog(orderId);
                        //================================绑定规则 START========================================//
                        log.info("================================绑定规则 START========================================");
                        // 不影响修改订单状态逻辑
                        try {
                            //查找规则
                            IntegralRules integralRules = this.rulesService.selOneIntegralRules(Constant.ACTIVITY_RULES_MONEY);
                            if (customer.getSuperiorId() == null) {
                                logger.info("没有上级");
                            } else {
                                logger.info("查找到上级ID");
                                //上级用户、钱包
                                Customer supperCustomer = customerService.getIdCustomer(customer.getSuperiorId());
                                Wallet supperWallet = walletService.getByCustomerId(customer.getSuperiorId());
                                supperWallet.setBurse(ArithUtil.add(supperWallet.getBurse(), integralRules.getIntegral()));
                                this.walletService.update(supperWallet);
                                try {

                                    CustomerWalletLog walletLog = new CustomerWalletLog();
                                    walletLog.setAmount(integralRules.getIntegral());
                                    walletLog.setPayMode(Constant.YUE_PAY_MODE);
                                    walletLog.setType(Constant.PAY_IN);
                                    walletLog.setCustomerId(supperCustomer.getId());
                                    walletLog.setCreateDate(new Date());
                                    walletLog.setStatus(1);
                                    walletLog.setCurrentBalance(supperWallet.getBurse());
                                    walletLog.setRemark("微信支付活动订单,支出" + integralRules.getIntegral() + "元");
                                    walletLog.setUuid(transactionId);
                                    customerWalletLogService.save(walletLog);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                                logger.info("微信支付活动订单==>上级用户钱包修改成功");
                            }
                            log.info("添加规则成功");
                        } catch (Exception e) {
                            log.error("添加积分规则失败");
                        }
                        log.info("================================绑定规则 END========================================");
                        //================================绑定规则 END ========================================//

                        try {
                            Customer supperCustomer = customerService.getIdCustomer(customer.getSuperiorId());
                            ALiYunSendMsgUtils.sendMsg(supperCustomer.getPhone(), Constant.INVITATION_USE_MSG, null);
                            log.info("发送成功");
                        } catch (Exception e) {
                            log.error("短信发送失败", e.getMessage());
                        }
                        logger.info("余额支付活动订单==>添加领取记录成功");
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

    /**
     * 查看活动订单列表
     *
     * @param token
     * @return
     */
    @ApiOperation(value = "wxapp查看活动订单列表", notes = "wxapp查看活动订单列表", tags = "wxapp查看活动订单列表")
    @ApiImplicitParam(name = "token", required = true, value = "用户token", dataType = "String", paramType = "query")
    @RequestMapping(value = {"getPageOrder"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getActivityOrderList(String token, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int pageSize) {
        Result result = new Result();
        Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
        if (ObjectUtils.isEmpty(customer)) {
            logger.info("用户为空");
            return result.setF("用户为空");
        }
        try {
            result = this.activityOrderService.selectOrderList(customer.getId(), page, pageSize);
            return result;
        } catch (Exception e) {
            log.error("出现异常:{}", e.getMessage());
        }
        return result;
    }

    /**
     * 活动订单领取记录
     *
     * @return
     */
    @ApiOperation(value = "wxapp活动订单领取记录", notes = "wxapp活动订单领取记录", tags = "wxapp活动订单领取记录")
    @ApiImplicitParam(name = "orderId", required = true, value = "订单ID", dataType = "String", paramType = "query")
    @RequestMapping(value = {"getOrderLogList"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getOrderIdLogList(String orderId) {
        Result result = new Result();
        try {
            List<OrderGetLogResp> list = this.orderGetLogService.selectByOrderId(orderId);
            result.setSuccess(true);
            result.setData(list);
            return result;
        } catch (Exception e) {
            log.error("活动订单查询异常");
            result.setSuccess(false);
            result.setMessage("查询异常");
            return result;
        }
    }

    /**
     * 活动订单详情
     *
     * @param orderId
     * @return
     */
    @ApiOperation(value = "wxapp活动订单详情", notes = "wxapp活动订单详情", tags = "wxapp活动订单详情")
    @ApiImplicitParam(name = "orderId", required = true, value = "订单ID", dataType = "String", paramType = "query")
    @RequestMapping(value = {"getOrderByid"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getOrderById(String orderId) {
        Result result = new Result();
        try {
            OrderDetailsResp orderDetailsResp = this.activityOrderService.getOrderDetail(orderId);
            result.setS(orderDetailsResp);
            return result;
        } catch (Exception e) {
            log.error("查询异常：{}", e.getMessage());
            result.setF("查询异常", "");
            return result;
        }
    }


    /**
     * 领取活动订单
     *
     * @param logId
     * @return
     */
    @ApiOperation(value = "wxapp确定活动订单领取", notes = "wxapp确定活动订单领取", tags = "wxapp确定活动订单领取")
    @ApiImplicitParam(name = "logId", required = true, value = "订单ID", dataType = "Long", paramType = "query")
    @RequestMapping(value = {"confirmOrder"}, method = RequestMethod.GET)
    @ResponseBody
    public Result confirmOrderLog(Long logId) {
        Result result = new Result();
        try {
            this.orderGetLogService.updateByLogIdStatus(logId);
            return result.setS("修改成功");
        } catch (Exception e) {
            log.error("修改异常状态异常=============={}", e.getMessage());
            return result.setF("修改失败");
        }
    }

    /**
     * 获取活动福利卡
     */
    @RequestMapping(value = {"getWelfare"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getActivityOrderWelfare(String orderId) {
        Result result = new Result();
        try {
            HshActivityOrder activityOrder = this.activityOrderService.selectByOrderId(orderId);
            HshActivityWelfare welfare = this.activityWelfareService.selectById(activityOrder.getCusNum2());
            result.setS(welfare);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("失败", null);
            return result;
        }
    }
}
