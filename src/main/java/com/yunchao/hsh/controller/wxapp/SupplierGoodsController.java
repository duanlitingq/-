package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller(value = "appSupplierGoodsController")
@RequestMapping("/page/wxapp/suppliergoods/")
public class SupplierGoodsController {

    @Autowired
    private ISupplierGoodsService supplierGoodsService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ICustomerService customerService;


    @ApiOperation(value = "前台商品", tags = "商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "商品名称", dataType = "Long"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int"),
    })
    @RequestMapping(value = "findPage",produces ="application/json;charset=UTF-8")
    @ResponseBody
    public Result findPage(String tokenId,Long supplierId,String name,Integer pageSize,Integer pageNum,Long typeId){
        System.out.println("进入商品搜索，搜索内容是："+name+"，店铺编号是："+supplierId);
        Result result = new Result();
        if(StringUtils.isEmpty(tokenId)){
            return result.setF("用户数据异常，请重新登录或刷新页面");
        }
        if(pageSize == null){
            pageSize = 12;
        }
        if(pageNum == null ){
            pageNum = 1;
        }
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("name", name);
            map.put("status", 1);
            if(typeId != null && typeId >0){
                map.put("typeId",typeId);
            }
            if(supplierId > 0){
                map.put("supplierId",supplierId);
            }
            PageHelper.orderBy("  PRIORITY  desc");
            PageInfo<SupplierGoodsResp> page = supplierGoodsService.findPage(map, pageNum, pageSize);
            SupplierResp supplier = supplierService.findById(supplierId);

            // 判断是否有商品数据
            List<SupplierGoodsResp> list = page.getList();
            if(list != null && list.size() > 0){
                // 创建redis工具
                RedisUtil redisUtil = RedisUtil.getInstance();
                // 获取到redisOpenId用于获取用户信息
                String redisOpenId = ToolsUtil.getRedisOpenId(tokenId);
                Customer customer = customerService.getByOpenID(redisOpenId);
                if(ObjectUtils.isEmpty(customer)){
                    return result.setF("用户数据异常，请重新登录或刷新页面");
                }
                // 获取到用户的openId,前缀用设定好的字符串拼接，得到用户购物车在redis中的key
                String userKey = Constant.SHOP_KEY + customer.getOpenId();
                // 通过userKey获取用户在购物车中的数据
                Map<Long, List<ShoppingCar>> mapShoppingCar = (Map<Long, List<ShoppingCar>>) redisUtil.getFromRedis(userKey);
                // 判断购物车中是否有数据
                if(mapShoppingCar == null || mapShoppingCar.isEmpty()){
                    // 购物车为空，直接返回商品数据
                    return result.setS("",page,supplier);
                }
                // 购物车不为空，判断店铺编号supplierId是否存在
                if(supplierId != null && supplierId > 0){
                    // 存在，获取到当前店铺下商品在购物中存在的商品
                    List<ShoppingCar> cars = mapShoppingCar.get(supplierId);
                    if(cars == null || cars.size() == 0){
                        // 当前店铺商品不存在购物车，直接返回
                        return result.setS("",page,supplier);
                    }
                    // 数据匹配
                    setGoodsRespNum(list,cars);
                }else{
                    // 如果店铺编号不存在，匹配购物车所有商品
                    for (Map.Entry<Long, List<ShoppingCar>> entry : mapShoppingCar.entrySet()){
                        List<ShoppingCar> value = entry.getValue();
                        if(value != null && value.size() > 0){
                            // 数据匹配
                            setGoodsRespNum(list,value);
                        }
                    }
                }
            }
            return result.setS("",page,supplier);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }

    // 匹配并添加数量
    private void setGoodsRespNum(List<SupplierGoodsResp> list, List<ShoppingCar> cars){
        int goodsNum = list.size();
        int size = cars.size();
        for (int i = 0; i < size; i++) {
            ShoppingCar car = cars.get(i);
            // 循环获取到的数据
            for (int j = 0; j < goodsNum; j++) {
                SupplierGoodsResp goodsResp = list.get(j);
                // 匹配到，获取出购物车中数量
                if(car.getGoodsId().equals(goodsResp.getId())){
                    Integer num = car.getNum();
                    goodsResp.setCarNum(num);
                    break;
                }
            }
        }
    }

    /**
     * 获取商品
     * @param id
     * @return
     */
    @ApiOperation(value = "前台商品", tags = "获取商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品编号", dataType = "Long"),
    })
    @RequestMapping("findById")
    @ResponseBody
    public Result findById(Long id){
        Result result = new Result();
        try {
            SupplierGoodsResp byId = supplierGoodsService.findById(id);
            result.setS("",byId);
        }catch (Exception e){
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }
}
