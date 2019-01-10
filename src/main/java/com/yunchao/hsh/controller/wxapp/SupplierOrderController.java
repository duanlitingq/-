package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.up72.component.wxpay.protocol.UnifiedOrderDto;
import com.up72.component.wxpay.service.UnifiedOrderService;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.*;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.redis.RedisUtil;
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
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * 供应商订单
 */
@Controller(value = "appSupplierOrderController")
@RequestMapping("/page/wxapp/supplierorder/")
public class SupplierOrderController {

    @Autowired
    private ISupplierOrderService supplierOrderService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IHshLogisticsService hshLogisticsService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private IIntegralRulesService integralRulesService;
    @Autowired
    private ISupplierWalletLogService supplierWalletLogService;
    @Autowired
    private ICustomerWalletLogService customerWalletLogService;
    @Autowired
    private IWalletService walletService;

    /**
     * @param datas
     * {"supplierId":2,"addressId":2 ,"haggle":0,"integral:":0, "datas":[
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     * ]
     * }
     * @return
     */
    @ApiOperation(value = "订单", tags = "生成订单")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "openId", value = "用户微信号", dataType = "String"),
        @ApiImplicitParam(name = "datas", value = "商品JSON格式数据", dataType = "String")
    })
    @RequestMapping(value = "createOrder",method = RequestMethod.POST)
    @ResponseBody
    public Result createOrder(String tokenId,String datas){
        Result result = new Result();
        try{
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            if(ObjectUtils.isEmpty(customer)){
                result.setF("用户数据异常,请重新登陆");
                return result;
            }
            if(ObjectUtils.isNotEmpty(datas)){
                RedisUtil redisUtil = RedisUtil.getInstance();
                String userKey = Constant.SHOP_KEY+customer.getOpenId();
                Map<Long,List<ShoppingCar>> map = (Map<Long,List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
                if(map == null || map.isEmpty()){
                    result.setF("数据异常，请刷新重试");
                    return result;
                }
                result =  supplierOrderService.createOrder(result,map,datas,customer);
                if(result.isSuccess()){
                    //购物车数据
                    if(map == null || map.isEmpty()){
                        redisUtil.deleteByKey(userKey);
                    }else{
                        redisUtil.putInRedis(userKey, (Serializable) map,7*(24*60*60));
                    }
                }
            }else{
                result.setF("订单数据异常请刷新重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "订单", tags = "订单详细立即购买")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "用户微信号", dataType = "String"),
            @ApiImplicitParam(name = "datas", value = "商品JSON格式数据", dataType = "String")
    })
    @RequestMapping(value = "singleBuy",method = RequestMethod.POST)
    @ResponseBody
    public Result singleBuy(String tokenId, String datas) {
        Result result = new Result();
        try {
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            if (ObjectUtils.isEmpty(customer)) {
                result.setF("用户数据异常,请重新登陆");
                return result;
            }
            if (ObjectUtils.isNotEmpty(datas)) {
                result = supplierOrderService.singleBuy(result,customer, datas);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @ApiOperation(value = "订单", tags = "结算前更新订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户微信号", dataType = "String"),
            @ApiImplicitParam(name = "orderId", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(name = "haggle", value = "议价", dataType = "Double"),
            @ApiImplicitParam(name = "integral", value = "积分", dataType = "Double"),
            @ApiImplicitParam(name = "addressId", value = "地址编号", dataType = "long")
    })
    @RequestMapping(value = "updateOrderData",method = RequestMethod.POST)
    @ResponseBody
    public Result updateOrderData(String tokenId,Long orderId,Double haggle,Double integral,Long addressId){
        Result result = new Result();
        String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
        try{
            Customer customer = customerService.getByOpenID(redisOpenId);
            if(ObjectUtils.isEmpty(customer)){
                result.setF("用户信息异常，请重新登陆");
                return result;
            }
            if(orderId == null || orderId == 0){
                result.setF("订单号数据异常请刷新重试");
                return result;
            }
            if(haggle == null){
                haggle=0.0;
            }
            if(integral == null){
                integral=0.0;
            }
            if(addressId == null || addressId == 0){
                result.setF("用户地址数据异常，请重新选择");
                return result;
            }
            //result = settlementService.singlePayment(result,customer,orderId,haggle,integral,addressId);
            result = supplierOrderService.updateOrderData(result,customer,orderId,haggle,integral,addressId);
        }catch (Exception e){
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }


    @ApiOperation(value = "用户订单", tags = "订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户随机码", dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示条数", dataType = "Integer"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "Integer"),
            @ApiImplicitParam(name = "payStatus", value = "支付状态1未支付,2已支付", dataType = "Integer"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态1：未接单2：已接单(已发货)3：已完成4：取消订单，5：已取消", dataType = "Integer"),
    })
    @RequestMapping("findPage")
    @ResponseBody
    public  Result findPage(String tokenId,Integer pageSize,Integer pageNum,Integer payStatus,Integer orderStatus){
        Result result = new Result();
        if (pageSize == null) {
            pageSize = 20;
        }
        if (pageNum == null) {
            pageNum = 1;
        }
        if (ObjectUtils.isEmpty(tokenId)) {
            return result.setF("用户数据异常，请登录重试");
        }
        try {
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            Long userId = customer.getId();
            // 查询订单时先校验已发货订单是否超过72小时，包含72小时的直接将订单修改为已收货
            // 定时数据是固定时间跑数据，这个是预防已超过未确认的数据

            if (orderStatus == 3) {
                HashMap hashMap = new HashMap();
                hashMap.put("payStatus", 2);
                hashMap.put("orderStatus", 4);
                hashMap.put("userId", userId);
                supplierOrderService.updateOrderStatusToFinish(hashMap);
            }

            // 查询逻辑优化 由于小程序未提交故 暂时先用这个查询 1：未接单 2：已接单(已发货)3：已完成4：取消订单，5：已取消
            // orderStatus等于0 待付款 ,1 待发货 ,2 待收货,3 已完成

            HashMap map = new HashMap();
            map.put("payStatus", payStatus);
            if (orderStatus == 1){
                map.put("orderStatus", 2);
            }else if (orderStatus == 2){
                map.put("orderStatus", 3);
            }else if (orderStatus == 3){
                map.put("orderStatus", 4);
            }
            map.put("userId", userId);
            PageHelper.orderBy("  o.create_time desc");
            PageInfo<SupplierOrderResp> page = supplierOrderService.getPage(map, pageNum, pageSize);
            result.setS("", page);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }

    @ApiOperation(value = "用户订单", tags = "订单详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(name = "tokenId", value = "用户随机码", dataType = "String"),
    })
    @RequestMapping("getOrderByOrderId")
    @ResponseBody
    public Result getOrderById(Long orderId,String tokenId){
        Result result = new Result();
        try{
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            SupplierOrderResp order = supplierOrderService.findByUserOrderId(orderId,customer.getId());
            result.setS("",order);
        }catch (Exception e){
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }

    @ApiOperation(value = "用户订单", tags = "删除订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(name = "tokenId", value = "用户随机码", dataType = "String"),
    })
    @RequestMapping("removeOrder")
    @ResponseBody
    public Result removeOrder(String tokenId,Long orderId){
        Result result = new Result();
        try {
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            SupplierOrderResp order = supplierOrderService.findByUserOrderId(orderId, customer.getId());
            if(ObjectUtils.isNotEmpty(order)){
                supplierOrderService.removeOrder(customer.getId(),orderId);
                result.setS("","删除成功");
            }else{
                result.setF("订单已删除");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setF("");
        }
        return  result;
    }

    @ApiOperation(value = "用户订单", tags = "确认收货")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单编号", dataType = "Long"),
            @ApiImplicitParam(name = "tokenId", value = "用户随机码", dataType = "String"),
    })
    @RequestMapping("receivingGoods")
    @ResponseBody
    public Result receivingGoods(String tokenId, Long orderId) {
        Result result = new Result();
        try {
            if (ObjectUtils.isNotEmpty(tokenId)) {
                String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
                Customer customer = customerService.getByOpenID(redisOpenId);
                result = supplierOrderService.receivingGoods(result,orderId, customer.getId());
            } else {
                result.setF("您还没有登陆");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @RequestMapping("updatePayStatus")
    public Result updatePayStatus(HttpServletRequest request){
        Result result = new Result();
        System.out.println("***********************商铺微信支付预付回调进入了**********************");
        try{
            UnifiedOrderService unifiedOrderService = new UnifiedOrderService();
            UnifiedOrderDto unifiedOrderDto = unifiedOrderService.getUnifiedOrderDto(request);
            String actPayMoney = unifiedOrderDto.getTotal_fee();///交易金额
            String orderNo = unifiedOrderDto.getOut_trade_no();///商户订单号
            String transactionId = unifiedOrderDto.getTransaction_id();///微信支付订单号
            String openid = unifiedOrderDto.getOpenid();
            SupplierOrderResp orderResp = supplierOrderService.findByOrderNo(orderNo);
            if(orderResp != null && orderNo.equals(orderResp.getOrderNo())){
                //获取用户
                Customer customer = customerService.getCustomerByOpenId(openid);
                Long orderId = orderResp.getId();
                Integer payStatus = orderResp.getPayStatus();
                if(payStatus == 1){
                    //修改订单支付状态
                    orderResp.setPayStatus(2);
                    orderResp.setTradeNo(transactionId);
                    orderResp.setPayTime(new Date());
                    supplierOrderService.updatePayStatus(orderResp);
                    //获取店铺修改店铺金额
                    Long supplierId = orderResp.getSupplierId();
                    SupplierResp supplierResp = supplierService.findById(supplierId);
                    //获取进账规则
                    IntegralRules integralRules = integralRulesService.selOneIntegralRules(2L);
                    Double rules = integralRules.getIntegral();
                    //规则之后的金额存入商铺总金额中
                    //计算议价和积分
                    Double hagglePrice = orderResp.getHagglePrice();
                    Double integral = orderResp.getIntegral();
                    //总金额
                    Double totalMoney = BigDecimalUtils.divide(actPayMoney,100,2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    //商铺应得金额
                    Double supplierMoney = BigDecimalUtils.multiply(rules,totalMoney).doubleValue();
                    //店铺金额修改
                    Double totalMoney1 = supplierResp.getTotalMoney();
                    //判断是否有议价，提交结算时更新订单返回的数据中orderMoney是计算之后的，临时存储用于计算使用
                    if(hagglePrice > 0){
                        if(integral > 0){
                            //议价-积分=用户实付
                            totalMoney = BigDecimalUtils.subtract(hagglePrice,integral).doubleValue();
                        }else{
                            totalMoney = hagglePrice;
                        }
                    }else{
                        if(integral > 0){
                            //订单金额-积分=用户实付
                            Double orderPrice = orderResp.getOrderMoney();
                            totalMoney = BigDecimalUtils.subtract(orderPrice,integral).doubleValue();
                        }
                    }
                    double supplierAllMoney = BigDecimalUtils.add(totalMoney1, supplierMoney).doubleValue();
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
                    //个人添加纪录
                    CustomerWalletLog record = new CustomerWalletLog();
                    Wallet wallet = walletService.getByCustomerId(customer.getId());
                    record.setAmount(totalMoney);
                    record.setType(2);
                    record.setCustomerId(customer.getId());
                    record.setCreateDate(new Date());
                    record.setStatus(1);
                    record.setCurrentBalance(wallet.getBurse());
                    record.setUuid(orderResp.getOrderNo());
                    record.setRemark("消费");
                    record.setOperationId(supplierResp.getId());
                    record.setTradeNo(orderResp.getTradeNo());
                    record.setColumn1(" ");
                    record.setColumn2(0);
                    record.setColumn3(0L);
                    customerWalletLogService.save(record);
                    if(integral > 0){
                        Double score = wallet.getScore();
                        double value = BigDecimalUtils.subtract(score, integral).doubleValue();
                        wallet.setScore(value);
                        walletService.update(wallet);
                    }
                    System.out.println("----支付回调----------------------订单支付成功--------------------------------------------");
                }else{
                    System.out.println("----支付回调----------------------订单已支付--------------------------------------------");
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param datas
     * {"supplierId":2,"addressId":2 ,"haggle":0,"integral:":0, "datas":[
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     *      "goodsId":3,"goodsNum":2,
     * ]
     * }
     * @return
     */
    private Result createOrder1(String datas,String tokenId){
        Result result = new Result();
        try{
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            if(ObjectUtils.isEmpty(customer)){
                result.setF("用户数据异常,请重新登陆");
                return result;
            }
            if(ObjectUtils.isNotEmpty(datas)){
                RedisUtil redisUtil = RedisUtil.getInstance();
                String userKey = Constant.SHOP_KEY+customer.getOpenId();
                Map<Long,List<ShoppingCar>> map = (Map<Long,List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
                if(map == null || map.isEmpty()){
                    result.setF("数据异常，请刷新重试");
                    return result;
                }
                result =  supplierOrderService.createOrder(result,map,datas,customer);
                if(result.isSuccess()){
                    //购物车数据
                    if(map == null || map.isEmpty()){
                        redisUtil.deleteByKey(userKey);
                    }else{
                        redisUtil.putInRedis(userKey, (Serializable) map,7*(24*60*60));
                    }
                }
            }else{
                result.setF("订单数据异常请刷新重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }


}
