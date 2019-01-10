package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.resp.ScoreItemResp;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.service.IHshLogisticsService;
import com.yunchao.hsh.service.ISelfItemOrderService;
import com.yunchao.hsh.service.ISelfItemService;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.Result;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.service.ICustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Api(value = "积分兑换商品订单")
@Controller
@RequestMapping(value = {"/page/wxapp/self/order/"})
public class SelfOrderController extends BaseController {

    @Autowired
    private ISelfItemOrderService itemOrderService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ISelfItemService itemService;

    @Autowired
    private IHshLogisticsService logisticsService;

    @ApiOperation(value = "wxapp立即结算生成订单", tags = "wxapp立即结算生成订单")
    @RequestMapping(value = {"createOrder"})
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(value = "itemId", name = "商品ID", dataType = "String", paramType = "body"),
            @ApiImplicitParam(value = "token", name = "token", dataType = "String", paramType = "body"),
            @ApiImplicitParam(value = "logisticsId", name = "收货人地址ID", dataType = "Long", paramType = "body")
    })
    public Result createOrder(Long itemId, String token, Long logisticsId) {

        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        log.info("积分立即结算订单生成的订单的token==============" + token + ";积分立即结算订单生成的订单=>" + redisOpenId);
        //用户信息
        try {
            Customer customer = this.customerService.getByOpenID(redisOpenId);
            Result result = this.itemOrderService.createOrder(itemId, customer.getId(), logisticsId);
            return result;
        } catch (Exception e) {
            log.error("创建订单失败-----{}", e.getMessage());
            return Result.error();
        }
    }


    /**
     * 立即兑换跳转页面
     *
     * @param itemId
     * @param token
     * @return
     */
    @ApiOperation(value = "wxapp立即兑换", tags = "wxapp立即兑换")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品ID", name = "itemId", dataType = "Long", paramType = "body"),
            @ApiImplicitParam(value = "用户ID", name = "customerId", dataType = "Long", paramType = "body")
    })
    @RequestMapping(value = "jumpOrderDetail", method = {RequestMethod.POST})
    @ResponseBody
    public Result jumpOrderDetail(@RequestParam() Long itemId, @RequestParam() String token) {

        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        log.info("login=token=>" + token + ";redisOpenId=>" + redisOpenId);
        //用户信息
        Customer customer = this.customerService.getByOpenID(redisOpenId);
        if (customer != null) {
            //收货地址
            HshLogistics logistics = this.logisticsService.selectBycustomer(customer.getId());
            ScoreItemResp item = this.itemService.selectByItemId(itemId);
            Map<String, Object> map = new HashMap<>();
            map.put("logistics", logistics);
            map.put("item", item);
            return Result.ok(map);
        } else {
            return Result.build("用户未登陆");
        }

    }


    @ApiOperation(value = "wxapp自营商品积分订单列表", tags = "wxapp自营商品积分订单列表")
    @RequestMapping(value = {"getSelfOrderList"}, method = {RequestMethod.GET})
    @ResponseBody
    public Result selfOrderList(String token, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "15") int pageSize) {
        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        log.info("login=token=>" + token + ";redisOpenId=>" + redisOpenId);
        //用户信息
        Customer customer = this.customerService.getByOpenID(redisOpenId);
        Result result = this.itemOrderService.selectByCustomerIdOrderList(customer.getId(), page, pageSize);
        log.info("积分订单列表================================{}", result);
        return result;
    }


    @ApiOperation(value = "wxapp根据积分订单ID获取订单详情", tags = "wxapp根据积分订单ID获取订单详情")
    @ApiImplicitParam(value = "订单ID", name = "orderId", dataType = "String", paramType = "body")
    @RequestMapping(value = {"getByOrderIdDetail"}, method = {RequestMethod.GET})
    @ResponseBody
    public Result getByOrderIdDetail(String orderId) {
        if (StringUtils.isNotBlank(orderId)) {
            Result result = this.itemOrderService.selectByOrderIdOrderDetails(orderId);
            log.info("订单查询列表为：{}", result);
            return result;
        }
        return Result.build("订单ID为空");
    }


}
