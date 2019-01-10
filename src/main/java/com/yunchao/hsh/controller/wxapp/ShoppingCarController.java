package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.ShoppingCar;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.ISupplierGoodsService;
import com.yunchao.hsh.service.ISupplierService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Serializable;
import java.util.*;


/**
 * @Title: ShoppingCarController
 * @Description: TODO
 * @author wdz
 * @date 2019/1/8 15:55
 */
@Controller
@RequestMapping("/page/wxapp/shoppingcar/")
public class ShoppingCarController {

    @Autowired
    private ISupplierGoodsService supplierGoodsService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ICustomerService customerService;
    /**添加购物车
     * @param tokenId
     * @return
     */
    @ApiOperation(value = "购物车", tags = "添加购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户微信号", dataType = "String"),
            @ApiImplicitParam(name = "goodsId", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(name = "num", value = "+1/-1", dataType = "String"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "type", value = "-1减号0重新设置数量1加一", dataType = "Long")
    })
    @RequestMapping("addCar")
    @ResponseBody
    public Result addCar(String tokenId,ShoppingCar car,Integer type){
        Result result = new Result();
        RedisUtil redisUtil = RedisUtil.getInstance();
        try{
            if(ObjectUtils.isNotEmpty(tokenId)){
                String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
                Customer customer = customerService.getByOpenID(redisOpenId);
                String userKey = Constant.SHOP_KEY+customer.getOpenId();
                Map<Long,List<ShoppingCar>> map = (Map<Long,List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
                if(map != null && !map.isEmpty()){//判断购物车是否有数据
                    List<ShoppingCar> list = map.get(car.getSupplierId());
                    if(list != null && list.size() > 0){
                        boolean flag = true; //true代表新增商品
                        Iterator<ShoppingCar> iterator = list.iterator();
                        while (iterator.hasNext()){
                            ShoppingCar next = iterator.next();
                            long goodsId = next.getGoodsId();
                            long goodsId1 = car.getGoodsId();
                            if(goodsId==goodsId1){
                                if(type == -1){
                                    if(next.getNum()-1 > 0){
                                        next.setNum(next.getNum()-1);
                                    }else{
                                        return result.setF("购物车商品数量不能为0");
                                    }
                                }else if(type == 0){
                                    if (car.getNum() > 0){
                                        next.setNum(car.getNum());
                                    }
                                }else if(type == 1 && next.getNum()+1 <= 99){
                                    next.setNum(next.getNum()+1);
                                }else{
                                    return result.setF("最多可购买99件商品");
                                }
                                flag = false ;
                                break;
                            }
                        }
                        if (flag){
                            list.add(car);
                        }
                    }else{
                        if (list == null){
                            list = new ArrayList<>();
                        }
                        list.add(car);
                        map.put(car.getSupplierId(),list);
                    }
                }else{
                    if (map == null){
                        map = new HashMap<>();
                    }
                    List<ShoppingCar> list = new ArrayList<>();
                    list.add(car);
                    map.put(car.getSupplierId(),list);
                }
                redisUtil.putInRedis(userKey, (Serializable) map,7*(24*60*60));
                result.setS("");
            }else{
                result.setF("用户数据异常，请重新登陆");
            }
       }catch (Exception e){
           e.getMessage();
            result.setF("访问失败");
       }
        return result;
    }

    /**
     * 获取购物车
     * @param tokenId
     * @return
     */
    @ApiOperation(value = "购物车", tags = "查询购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户微信号", dataType = "String")
    })
    @RequestMapping("findCar")
    @ResponseBody
    public Result findCar(String tokenId){
        Result result = new Result();
        List<SupplierResp> list = new ArrayList<>();
       try{
           if(ObjectUtils.isNotEmpty(tokenId)){
               String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
               Customer customer = customerService.getByOpenID(redisOpenId);
               RedisUtil redisUtil = RedisUtil.getInstance();//创建redis工具
               String userKey = Constant.SHOP_KEY+customer.getOpenId();//redisKey
               //获取redis中购物车数据
               Map<Long,List<ShoppingCar>> map = (Map<Long,List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
               if(map != null && !map.isEmpty()){
                   //获取出所有key，店铺编号
                   Set<Long> longs = map.keySet();
                   Iterator<Long> iterator = longs.iterator();
                   while (iterator.hasNext()){
                       Long next = iterator.next();
                       //获取店铺数据
                       SupplierResp supplier = supplierService.findById(next);
                       //设置店铺购物车商品
                       List<ShoppingCar> list1 = map.get(next);
                       if(list1 != null){
                           int size = list1.size();
                           for (int i = 0; i < size; i++) {
                               ShoppingCar car = list1.get(i);
                               Long goodsId = car.getGoodsId();
                               //设置商品属性
                               SupplierGoodsResp goods = supplierGoodsService.findById(goodsId);
                               String name = goods.getName();
                               String specifications = goods.getSpecifications();
                               Double price = goods.getPlanPrice();
                               car.setGoodsImg(goods.getImgsArr());
                               car.setGoodsName(name);
                               car.setSpecifications(specifications);
                               car.setPrice(price);
                           }
                           supplier.setCars(list1);
                           list.add(supplier);
                       }
                   }
               }
               result.setS("",list);
           }else{
               result.setF("您还没有登陆");
           }
       }catch (Exception e){
           e.printStackTrace();
           result.setF("访问失败");
       }
        return result;
    }

    /**
     * 统计购物车商品数量
     * @param tokenId
     * @return
     */

    @ApiOperation(value = "购物车", tags = "购物车商品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户微信号", dataType = "String"),
    })
    @RequestMapping("countCar")
    @ResponseBody
    public Result countCar(String tokenId) {
        Result result = new Result();
        Integer count = 0;
        try {
            if(ObjectUtils.isNotEmpty(tokenId)){
                String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
                //Customer customer = customerService.getByOpenID(redisOpenId);
                Customer customer =  customerService.getCustomerByOpenId(redisOpenId);
                if(ObjectUtils.isNotEmpty(customer)){
                    String userKey = Constant.SHOP_KEY + customer.getOpenId();
                    RedisUtil instance = RedisUtil.getInstance();
                    Map<Long, List<ShoppingCar>> map = (Map<Long, List<ShoppingCar>>) instance.getFromRedis(userKey);
                    if (map != null && !map.isEmpty()) {
                        Set<Long> set = map.keySet();
                        Iterator<Long> iterator = set.iterator();
                        while (iterator.hasNext()) {
                            Long next = iterator.next();
                            List<ShoppingCar> list = map.get(next);
                            if (list != null) {
                                for (int i = 0; i < list.size(); i++) {
                                    ShoppingCar car = list.get(i);
                                    count += car.getNum();
                                }
                            }
                        }
                    }
                }
            }
            result.setS("",count);
        }catch (Exception e){
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }

    @ApiOperation(value = "购物车", tags = "删除商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户微信号", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long",paramType = "query"),
            @ApiImplicitParam(name = "goodsId", value = "商品编号", dataType = "Long",paramType = "query"),
    })
    @RequestMapping("removeGoods")
    @ResponseBody
    public Result removeGoods(String tokenId,Long supplierId,Long goodsId){
        Result result = new Result();
        RedisUtil redisUtil = RedisUtil.getInstance();
        try{
            if(ObjectUtils.isNotEmpty(tokenId)){
                String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
                Customer customer = customerService.getByOpenID(redisOpenId);
                String userKey = Constant.SHOP_KEY+customer.getOpenId();
                //获取出购物车数据
                Map<Long,List<ShoppingCar>> map = (Map<Long,List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
                if(map != null && !map.isEmpty()){
                    List<ShoppingCar> shoppingCars = map.get(supplierId);
                    List<ShoppingCar> rm = new ArrayList<>();
                    for (int i = 0; i < shoppingCars.size(); i++) {
                        ShoppingCar car = shoppingCars.get(i);
                        Long goodsId1 = car.getGoodsId();
                        if(goodsId1.equals(goodsId)){
                            rm.add(car);
                            break;
                        }
                    }
                    for (int i = 0; i < rm.size(); i++) {
                        shoppingCars.remove(rm.get(i));
                    }
                    if(shoppingCars.size() == 0){
                        map.remove(supplierId);
                    }else{
                        map.put(supplierId,shoppingCars);
                    }
                    redisUtil.putInRedis(userKey, (Serializable) map,7*(24*60*60));
                }
                result.setS("","删除成功");
            }else{
                result.setF("用户数据异常，请重新登陆");
            }
        }catch (Exception e){
            e.getMessage();
            result.setSuccess(false);
            result.setF("访问失败");
        }
        return result;
    }


    @ApiOperation(value = "店铺购物车", tags = "查询店铺购物车商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tokenId", value = "用户微信号", dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long",paramType = "query"),
    })
    @RequestMapping("findSupplierCar")
    @ResponseBody
    public Result findSupplierCar(String tokenId,Long supplierId){
        Result result = new Result();
        if(ObjectUtils.isNotEmpty(tokenId)){
            return result.setF("您还没有登陆");
        }
        if(supplierId == null || supplierId == 0){
            return result.setF("数据异常请刷新重试");
        }
        try{
            String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
            Customer customer = customerService.getByOpenID(redisOpenId);
            if (ObjectUtils.isNotEmpty(customer)){
                // 创建redis工具
                RedisUtil redisUtil = RedisUtil.getInstance();
                // 用户 redisKey
                String userKey = Constant.SHOP_KEY+customer.getOpenId();
                // 获取redis中购物车数据
                Map<Long,List<ShoppingCar>> map = (Map<Long,List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
                if(map != null && !map.isEmpty()){
                    List<ShoppingCar> cars = map.get(supplierId);
                    return result.setS("",cars);
                }
            }else {
                return result.setF("用户数据异常，请刷新重试");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }






}
