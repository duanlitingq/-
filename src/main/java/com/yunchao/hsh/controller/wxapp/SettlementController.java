package com.yunchao.hsh.controller.wxapp;

import com.alibaba.fastjson.JSON;
import com.up72.component.wxpay.protocol.UnifiedOrderDto;
import com.up72.component.wxpay.service.UnifiedOrderService;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.*;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结算
 */
@Controller
@RequestMapping("/page/wxapp/settlement/")
public class SettlementController {

    org.apache.log4j.Logger logger = Logger.getLogger("SupplierController.class");
    @Autowired
    private ISupplierOrderService supplierOrderService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IIntegralRulesService integralRulesService;
    @Autowired
    private ISupplierWalletLogService supplierWalletLogService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private ICustomerWalletLogService customerWalletLogService;
    @Autowired
    private IWalletService walletService;


    //支付回调
    @RequestMapping(value = "orderPayCallback", method = RequestMethod.POST)
    public void orderPayCallback(HttpServletRequest request) {
        logger.info("***********************商铺微信支付预付回调进入了**********************");
        try {
            UnifiedOrderService unifiedOrderService = new UnifiedOrderService();
            UnifiedOrderDto unifiedOrderDto = unifiedOrderService.getUnifiedOrderDto(request);
            String orderMoney = unifiedOrderDto.getTotal_fee();///交易金额，由于微信金额单位为分，所以调用该参数时将该参数转换为系统金额单位
            String orderNo = unifiedOrderDto.getOut_trade_no();///商户订单号
            String transactionId = unifiedOrderDto.getTransaction_id();///微信支付订单号
            String openid = unifiedOrderDto.getOpenid();
            SupplierOrderResp orderResp = supplierOrderService.findByOrderNo(orderNo);
            if (orderResp != null && orderNo.equals(orderResp.getOrderNo())) {
                //获取用户
                Customer customer = customerService.getCustomerByOpenId(openid);
                Long userId = customer.getId();
                Long orderId = orderResp.getId();
                Integer payStatus = orderResp.getPayStatus();
                if (payStatus == 1) {
                    List<SupplierOrderItem> items = supplierOrderService.findItemsByOrderId(orderId);
                    //修改订单支付状态
                    orderResp.setPayStatus(2);
                    orderResp.setTradeNo(transactionId);
                    orderResp.setPayTime(new Date());
                    //获取店铺修改店铺金额
                    Long supplierId = orderResp.getSupplierId();
                    SupplierResp supplierResp = supplierService.findById(supplierId);
                    //获取进账规则
                    IntegralRules integralRules = integralRulesService.selOneIntegralRules(2L);
                    Double rules = integralRules.getIntegral();
                    //规则之后的金额存入商铺总金额中
                    //计算议价和积分
                    Double integral = orderResp.getIntegral();
                    //消费总金额
                    Integer integer = Integer.valueOf(orderMoney);
                    //将金额转换为元（系统单位元）
                    Double totalMoney = BigDecimalUtils.divideRoundHalfUp(integer, 100).doubleValue();
                    //商铺应得金额
                    Double supplierMoney = BigDecimalUtils.multiply(rules, totalMoney).doubleValue();
                    //平台费用
                    Double platformRevenue = BigDecimalUtils.subtract(totalMoney, supplierMoney).doubleValue();
                    //店铺金额修改
                    Double totalMoney1 = supplierResp.getTotalMoney();
                    orderResp.setActPayment(totalMoney);//实际支付
                    orderResp.setPlatformRevenue(platformRevenue);//平台收入
                    orderResp.setPayType(1);
                    supplierOrderService.updatePayStatus(orderResp);
                    Double supplierAllMoney = BigDecimalUtils.add(totalMoney1, supplierMoney).doubleValue();
                    supplierResp.setTotalMoney(supplierAllMoney);
                    supplierService.updateTotalMoney(supplierResp);
                    //添加店铺金额记录
                    SupplierWalletLog log = new SupplierWalletLog();
                    log.setSupplierId(supplierId);
                    log.setRemark("收入");
                    log.setOrderId(orderId);
                    log.setInOrOut(1);
                    log.setFrontUserId(customer.getId());
                    log.setCreateTime(new Date());
                    log.setChangeMoney(+supplierMoney);
                    log.setColumn3(0L);
                    log.setColumn2(0);
                    log.setColumn1("");
                    supplierWalletLogService.insert(log);
                    //个人添加纪录,由于用户涉及到金额支付所以涉及到积分添加，故回调需要记录消费记录和积分记录
                    //payMode 1：余额;2:微信;3:积分;4:系统
                    //type  ;1：支出、2：收入;3：提现、4:系统
                    Wallet wallet = walletService.getByCustomerId(customer.getId());
                    //积分抵扣
                    System.out.println(customer.getNickname() + ":微信购买商品花费：" + totalMoney);
                    insertCustomerWalletLog(1, totalMoney, 2, "购买商品消费", orderResp.getOrderNo(),
                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                    //积分减少
                    if (integral > 0) {
                        Double score = wallet.getScore();
                        double value = BigDecimalUtils.subtract(score, integral).doubleValue();
                        wallet.setScore(value);
                        System.out.println("用户消费使用积分：" + integral);
                        //积分抵扣
                        System.out.println(customer.getNickname() + ":积分抵扣：" + integral);
                        insertCustomerWalletLog(1, integral, 3, "积分抵扣", orderResp.getOrderNo(),
                                userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());

                    }
                    //积分兑换100元兑换3积分
                    //用户应得积分
                    Double userIntegral = (totalMoney / 100) * 3;
                    Double dou = numberFormat(userIntegral);
                    System.out.println("********************" + customer.getNickname() + "********************用户可兑换积分为：" + userIntegral);
                    //用户积分
                    System.out.println(customer.getNickname() + "添加之前的积分为：" + wallet.getScore());
                    Double score = wallet.getScore() + dou;
                    System.out.println(customer.getNickname() + "添加之后的积分为：" + score);
                    if (dou > 0) {
                        wallet.setScore(score);
                        walletService.update(wallet);
                        //消费金额兑换积分
                        System.out.println(customer.getNickname() + ":花费" + totalMoney + ",返还积分" + userIntegral);
                        insertCustomerWalletLog(2, dou, 3, "消费返积分", orderResp.getOrderNo(),
                                userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                    }
                    sendMessage(orderResp, integral + "", supplierMoney + "", supplierResp.getMobile());
                    logger.info(customer.getNickname() + "----支付回调----------------------订单支付成功--------------------------------------------");
                } else {
                    logger.info("----支付回调----------------------订单已支付--------------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(request.toString() + "支付回调成功");
    }

    private void sendMessage(SupplierOrderResp orderResp, String integral, String actInMoney, String mobile) {
        Map<String, String> msgMap = new HashMap<>();
        msgMap.put("buyUserName", orderResp.getBuyUserName());
        msgMap.put("buyUserPhone", orderResp.getBuyUserPhone());
        msgMap.put("address", orderResp.getAddress());
        msgMap.put("orderMoney", orderResp.getOrderMoney() + "");
        msgMap.put("hagglePrice", orderResp.getHagglePrice() + "");
        msgMap.put("integral", integral);
        msgMap.put("actPayment", orderResp.getActPayment() + "");
        msgMap.put("actInMoney", actInMoney);
        ALiYunSendMsgUtils.sendMsg(mobile, Constant.NEW_ORDER_MSG, JSON.toJSONString(msgMap));
    }

    //用户积分支付
    @RequestMapping("integralPay")
    @ResponseBody
    public Result integralPay(String tokenId, Long orderId, Double haggle, Double integral, Long addressId) {
        Result result = new Result();
        try {
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            if (ObjectUtils.isEmpty(customer)) {
                return result.setF("用户数据异常");
            }
            Long userId = customer.getId();
            //获取订单
            SupplierOrderResp orderResp = supplierOrderService.findById(orderId);
            if (orderResp.getPayStatus() == 1) {
                List<SupplierOrderItem> items = supplierOrderService.findItemsByOrderId(orderId);
                // 获取店铺
                Long supplierId = orderResp.getSupplierId();
                SupplierResp supplier = supplierService.findById(supplierId);
                // 获取用户钱包、
                Wallet wallet = walletService.getByCustomerId(userId);
                // 由于积分支付用户积分减少
                Double score = wallet.getScore();
                // 获取进账规则
                IntegralRules integralRules = integralRulesService.selOneIntegralRules(2L);
                Double rules = integralRules.getIntegral();
                if (score >= integral) {
                    // 用户剩余积分
                    double userIntegral = BigDecimalUtils.subtract(score, integral).doubleValue();
                    wallet.setScore(userIntegral);
                    // 修改用户积分
                    walletService.update(wallet);
                    // 添加积分消费记录
                    // 个人添加纪录
                    insertCustomerWalletLog(1, integral, 3, "积分购买商品", orderResp.getOrderNo(),
                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                    // 店铺余额增加
                    Double totalMoney = supplier.getTotalMoney();
                    // 计算店铺应收进账
                    Double multiply = BigDecimalUtils.multiply(integral, rules).doubleValue();
                    // 店铺总金额
                    BigDecimal add = BigDecimalUtils.add(multiply, totalMoney);
                    // 平台应收
                    Double platformRevenue = BigDecimalUtils.subtract(integral, multiply).doubleValue();
                    supplier.setTotalMoney(add.doubleValue());
                    supplierService.updateTotalMoney(supplier);
                    // 添加店铺积分进账记录
                    String remark = "收入";
                    insertSupplierWalletLog(supplierId,orderId,customer.getId(),multiply,remark);
                    // 修改订单支付状态
                    orderResp.setPayStatus(2);
                    orderResp.setTradeNo("");
                    orderResp.setPayTime(new Date());
                    orderResp.setActPayment(0.0);//实际支付
                    orderResp.setPlatformRevenue(platformRevenue);//平台收入
                    orderResp.setPayType(3);
                    supplierOrderService.updatePayStatus(orderResp);
                    result.setS("", "积分支付成功");
                    sendMessage(orderResp, integral + "", multiply + "", supplier.getMobile());
                } else {
                    result.setF("积分不足");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    /**
     * 使用余额支付
     *
     * @param tokenId
     * @param orderId   订单id
     * @param haggle    议价金额
     * @param integral  使用积分
     * @param addressId 收货地址id
     * @return
     */
    @RequestMapping("balancePay")
    @ResponseBody
    public Result balancePay(String tokenId, Long orderId, Double haggle, Double integral, Long addressId, Double money) {
        Result result = new Result();
        try {
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            if (ObjectUtils.isEmpty(customer)) {
                return result.setF("用户数据异常");
            }
            Long userId = customer.getId();
            //获取订单
            SupplierOrderResp orderResp = supplierOrderService.findById(orderId);
            Integer payStatus = orderResp.getPayStatus();
            if (payStatus == 1) {
                //获取店铺修改店铺金额
                Long supplierId = orderResp.getSupplierId();
                SupplierResp supplierResp = supplierService.findById(supplierId);
                //获取进账规则
                IntegralRules integralRules = integralRulesService.selOneIntegralRules(2L);
                Double rules = integralRules.getIntegral();
                //个人添加纪录,由于用户涉及到金额支付所以涉及到积分添加，故回调需要记录消费记录和积分记录
                //payMode 1：余额;2:微信;3:积分;4:系统
                //type  ;1：支出、2：收入;3：提现、4:系统
                Wallet wallet = walletService.getByCustomerId(customer.getId());
                //用户当前余额
                Double burse = wallet.getBurse();
                //用户当前积分
                Double score = wallet.getScore();
                //添加店铺金额记录

                double allMoney = 0.0;//初始化金额
                /**
                 * 议价支付
                 */
                if (haggle > 0.0) {
                    /**
                     * 议价+积分 支付
                     */
                    if (integral > 0) {
                        if (integral <= score) {
                            if (integral <= haggle) {
                                if (integral == haggle) {
                                    double surplusScore = BigDecimalUtils.subtract(score, integral).doubleValue();
                                    wallet.setScore(surplusScore);
                                    System.out.println("用户消费使用积分：" + integral);
                                    //积分抵扣
                                    System.out.println(customer.getNickname() + ":积分抵扣：" + integral);
                                    insertCustomerWalletLog(1, integral, 3, "积分抵扣", orderResp.getOrderNo(),
                                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                                } else {
                                    //抵用完剩余积分
                                    double surplusScore = BigDecimalUtils.subtract(score, integral).doubleValue();
                                    //抵用完剩余余额
                                    double surplusBurse = BigDecimalUtils.subtract(burse, money).doubleValue();
                                    wallet.setScore(surplusScore);
                                    wallet.setBurse(surplusBurse);
                                    System.out.println("用户消费使用积分：" + integral);
                                    //积分抵扣
                                    System.out.println(customer.getNickname() + ":积分抵扣：" + integral);
                                    insertCustomerWalletLog(1, integral, 3, "积分抵扣", orderResp.getOrderNo(),
                                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                                    insertCustomerWalletLog(1, haggle, 1, "余额支付", orderResp.getOrderNo(),
                                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                                }

                            } else {
                                return result.setF("使用积分不可大于议价价格!");
                            }
                        } else {
                            return result.setF("当前使用积分大于钱包积分!");
                        }
                    } else {
                        /**
                         * 纯议价支付
                         */
                        if (burse >= haggle) {
                            //抵用完剩余余额
                            double surplusBurse = BigDecimalUtils.subtract(burse, haggle).doubleValue();
                            wallet.setBurse(surplusBurse);
                            System.out.println("用户消费使用积分：" + integral);
                            //积分抵扣
                            System.out.println(customer.getNickname() + ":余额抵扣：" + integral);
                            insertCustomerWalletLog(1, haggle, 1, "余额抵扣", orderResp.getOrderNo(),
                                    userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                        } else {
                            return result.setF("余额支付金额不足!");
                        }

                    }
                } else {
                    //余额支付
                    List<SupplierOrderItem> items = supplierOrderService.findItemsByOrderId(orderId);
                    for (SupplierOrderItem item : items) {
                        //商品数量
                        Integer salesNum = item.getSalesNum();
                        //商品单价
                        Double planPrice = item.getPlanPrice();
                        //单间商品总和
                        double oneMoney = BigDecimalUtils.multiply(salesNum, planPrice).doubleValue();
                        //订单总金额
                        allMoney = allMoney + oneMoney;
                    }
                    /**
                     * 余额积分支付
                     */
                    if (integral > 0.0) {
                        //获取用户当前订单商品详情
                        if (integral <= score) {
                            //积分纯抵换余额
                            if (integral == allMoney) {
                                //抵扣完剩余积分
                                double subtract = BigDecimalUtils.subtract(score, integral).doubleValue();
                                wallet.setScore(subtract);
                                insertCustomerWalletLog(1, integral, 3, "积分抵扣", orderResp.getOrderNo(),
                                        userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                            } else {
                                //计算 积分  +  余额
                                double add = BigDecimalUtils.add(integral, money).doubleValue();
                                if (add == allMoney) {
                                    //抵用完剩余积分
                                    double surplusScore = BigDecimalUtils.subtract(score, integral).doubleValue();
                                    //抵用完剩余余额
                                    double surplusBurse = BigDecimalUtils.subtract(burse, money).doubleValue();
                                    wallet.setScore(surplusScore);
                                    wallet.setBurse(surplusBurse);
                                    System.out.println("用户消费使用积分：" + integral);
                                    //积分抵扣
                                    System.out.println(customer.getNickname() + ":积分抵扣：" + integral);
                                    insertCustomerWalletLog(1, integral, 3, "积分抵扣", orderResp.getOrderNo(),
                                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                                    insertCustomerWalletLog(1, haggle, 1, "余额支付", orderResp.getOrderNo(),
                                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                                } else {
                                    return result.setF("订单失败!");
                                }
                            }
                        } else {
                            return result.setF("积分不足!");
                        }
                    } else {
                        /**
                         * 纯余额支付
                         */
                        if (allMoney == money) {
                            //抵用完剩余余额
                            double surplusBurse = BigDecimalUtils.subtract(burse, money).doubleValue();
                            wallet.setBurse(surplusBurse);
                            insertCustomerWalletLog(1, money, 1, "余额支付", orderResp.getOrderNo(),
                                    userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                        } else {
                            return result.setF("订单失败!");
                        }
                    }
                }
                //修改订单支付状态
                orderResp.setPayStatus(2);
                orderResp.setPayTime(new Date());
                //规则之后的金额存入商铺总金额中
                //计算议价和积分
                Double integr = orderResp.getIntegral();
                //商铺应得金额
                Double supplierMoney = BigDecimalUtils.multiply(rules, allMoney).doubleValue();
                //平台费用
                Double platformRevenue = BigDecimalUtils.subtract(allMoney, supplierMoney).doubleValue();
                //店铺金额修改
                Double totalMoney1 = supplierResp.getTotalMoney();
                orderResp.setActPayment(allMoney);//实际支付
                orderResp.setPlatformRevenue(platformRevenue);//平台收入
                orderResp.setPayType(1);
                supplierOrderService.updatePayStatus(orderResp);
                Double supplierAllMoney = BigDecimalUtils.add(totalMoney1, supplierMoney).doubleValue();
                supplierResp.setTotalMoney(supplierAllMoney);
                supplierService.updateTotalMoney(supplierResp);
                String remark = "收入";
                // 保存店铺收入记录
                insertSupplierWalletLog(supplierId,orderId,customer.getId(),+supplierMoney,remark);
                //积分抵扣
                System.out.println(customer.getNickname() + ":微信购买商品花费：" + allMoney);
                insertCustomerWalletLog(1, allMoney, 2, "购买商品消费", orderResp.getOrderNo(),
                        userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                //积分减少
                if (integral > 0) {
                    double value = BigDecimalUtils.subtract(score, integral).doubleValue();
                    wallet.setScore(value);
                    System.out.println("用户消费使用积分：" + integral);
                    //积分抵扣
                    System.out.println(customer.getNickname() + ":积分抵扣：" + integral);
                    insertCustomerWalletLog(1, integral, 3, "积分抵扣", orderResp.getOrderNo(),
                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                }
                //积分兑换100元兑换3积分
                //用户应得积分
                Double userIntegral = (allMoney / 100) * 3;
                Double dou = numberFormat(userIntegral);
                System.out.println("********************" + customer.getNickname() + "********************用户可兑换积分为：" + userIntegral);
                //用户积分
                System.out.println(customer.getNickname() + "添加之前的积分为：" + wallet.getScore());
                Double score1 = wallet.getScore() + dou;
                System.out.println(customer.getNickname() + "添加之后的积分为：" + score1);
                if (dou > 0) {
                    wallet.setScore(score1);
                    walletService.update(wallet);
                    //消费金额兑换积分
                    System.out.println(customer.getNickname() + ":花费" + allMoney + ",返还积分" + userIntegral);
                    insertCustomerWalletLog(2, dou, 3, "消费返积分", orderResp.getOrderNo(),
                            userId, 1, wallet.getBurse(), supplierId, orderResp.getTradeNo());
                }
                //sendMessage(orderResp,integral+"",supplierMoney+"",supplierResp.getMobile());
                logger.info(customer.getNickname() + "----支付回调----------------------订单支付成功--------------------------------------------");
            } else {
                logger.info("----支付回调----------------------订单已支付--------------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    private void insertSupplierWalletLog(Long supplierId,Long orderId,Long fronUserId,Double money,String remark){
        SupplierWalletLog log = new SupplierWalletLog();
        log.setSupplierId(supplierId);
        log.setRemark(remark);
        log.setOrderId(orderId);
        log.setInOrOut(1);
        log.setFrontUserId(fronUserId);
        log.setCreateTime(new Date());
        log.setChangeMoney(money);
        log.setColumn3(0L);
        log.setColumn2(0);
        log.setColumn1("");
        supplierWalletLogService.insert(log);
    }

    /**
     * @param type        类型;1：支出、2：收入;3：提现、4:系统
     * @param changeMoney 改变的积分/金额
     * @param payMode     消费方式; 1：余额;2:微信;3:积分;4:系统
     * @param remark      备注
     * @param orderNo     系统好物订单编号orderNo
     * @param userId      用户编号
     * @param status      状态（0失败，1成功）
     * @param burse       当前钱包金额
     * @param supplierId  供应商编号
     * @param tradeNo     第三方微信流水号
     */
    private void insertCustomerWalletLog(Integer type, Double changeMoney, Integer payMode, String remark, String orderNo, Long userId, Integer status, Double burse, Long supplierId, String tradeNo) {
        CustomerWalletLog record = new CustomerWalletLog();
        record.setAmount(changeMoney);
        record.setType(type);
        record.setCustomerId(userId);
        record.setCreateDate(new Date());
        record.setStatus(status);
        record.setCurrentBalance(burse);
        record.setUuid(orderNo);
        record.setRemark(remark);
        record.setOperationId(supplierId);
        record.setTradeNo(tradeNo);
        record.setPayMode(payMode);
        record.setColumn1(" ");
        record.setColumn2(0);
        record.setColumn3(0L);
        customerWalletLogService.save(record);
    }

    /***
     * @Description:  格式化数据
     * @param d
     * @return java.lang.Double
     * @throws
     * @author wdz
     * @date 2019/1/8 16:56
     */
    private Double numberFormat(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        String format = nf.format(d);
        return Double.valueOf(format);
    }
}
