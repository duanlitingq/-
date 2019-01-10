package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.WalletLogResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.HshCollection;
import com.yunchao.hsh.service.ICollectionService;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.Api;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.interfaces.RSAKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

@Api
@Controller(value = "appCollectionController")
@RequestMapping("/page/wxapp/collection/")
public class CollectionController {
    @Autowired
    private ICollectionService collectionService;
    @Autowired
    private ICustomerService customerService;

    /**
     * 页面初始化,获取商品是否被收藏
     *
     * @param token
     * @param itemId
     * @return
     */
    @RequestMapping("findCollection")
    @ResponseBody
    public Result findCollection(String token, Long itemId, Long storeId) {
        Result result = new Result();
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        Customer customer = this.customerService.getCustomerByOpenId(redisOpenId);
        if (customer != null) {
            Long customerId = customer.getId();//获取用户id
            if (itemId != null) {
                HshCollection collection = new HshCollection();
                collection.setUserId(customerId);
                collection.setItemId(itemId);
                HshCollection collection1 = collectionService.selectUserCollection(collection);
                if (collection1 != null) {
                    return result.setS("", 1, "已收藏!");
                } else {
                    return result.setS("", 2, "未收藏!");
                }
            }
            if (storeId != null) {
                HshCollection collection = new HshCollection();
                collection.setUserId(customerId);
                collection.setStoreId(storeId);
                HshCollection collection1 = collectionService.selectUserCollection(collection);
                if (collection1 != null) {
                    return result.setS("", 1, "已收藏!");
                } else {
                    return result.setS("", 2, "未收藏!");
                }
            }
        }

        return null;
    }

    /**
     * 添加收藏商品/店铺
     *
     * @param token  token
     * @param itemId 商品id
     * @return
     */
    @RequestMapping("addGood")
    @ResponseBody
    public Result addGood(String token, Long itemId, Long storeId) {
        Result result = new Result();
        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        //用户信息
        Customer customer = this.customerService.getByOpenID(redisOpenId);
        if (customer != null) {
            Long customerId = customer.getId();
            //商品id
            if (itemId != null) {
                HshCollection collection = new HshCollection();
                collection.setUserId(customerId);
                collection.setItemId(itemId);
                collection.setCreateTime(new Date());
                byte b = 1;
                collection.setCollType(b);
                HshCollection collection1 = collectionService.selectUserCollection(collection);
                if (collection1 != null) {
                    int i = collectionService.delCollction(collection);
                    if (i>0){
                        return result.setS("", 2,"取消收藏商品成功!");
                    }
                } else {
                    int i = collectionService.addCollection(collection);
                    if (i>0){
                        return result.setS("", 1,"收藏商品成功!");
                    }
                }
            }
            //店铺id
            if (storeId != null) {
                HshCollection collection = new HshCollection();
                collection.setUserId(customerId);
                collection.setStoreId(storeId);
                collection.setCreateTime(new Date());
                byte b = 2;
                collection.setCollType(b);
                HshCollection collection1 = collectionService.selectUserCollection(collection);
                if (collection1 != null) {
                    int i = collectionService.delCollction(collection);
                    if (i>0){
                        return result.setS("", 2,"取消收藏店铺成功!");
                    }
                } else {
                    int i = collectionService.addCollection(collection);
                    if (i > 0) {
                        return result.setS("",1, "收藏店铺成功!");
                    }
                }
            }

        }
        return null;
    }

    /**
     * 个人中心获取我的收藏
     * @param pageNum
     * @param pageSize
     * @param token
     * @param collType
     * @return
     */
    @RequestMapping("userCollection")
    @ResponseBody
    public Result userCollection(int pageNum, @RequestParam(defaultValue = "10") int pageSize, String token, Long collType) {
        Result result = new Result();
        try {
            if (StringUtils.isNotBlank(token)) {
                //根据openID获取用户
                String redisOpenId = ToolsUtil.getRedisOpenId(token);
                //用户信息
                Customer customer = this.customerService.getByOpenID(redisOpenId);
                if (collType != null) {
                    if (customer != null) {
                        Long customerId = customer.getId();
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("userId", customerId);
                        map.put("collType", collType);
                        PageInfo<HshCollection> pageIntegral = this.collectionService.getPage(map, pageNum, pageSize);
                        return  result.setS("",pageIntegral);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}
