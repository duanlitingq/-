package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.resp.*;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.*;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.superdir.Result;
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

import java.util.List;

/**
 * 首页controller
 */
@Api(value = "wxapp首页接口")
@Controller
@RequestMapping(value = "/page/wxapp/index")
public class IndexController extends BaseController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private IAdvertisementService advertisementService;

    @Autowired
    private ISelfItemService selfItemService;

    @Autowired
    private ICustomerWalletLogService walletLogService;

    /**
     * 获取首页数据
     *
     * @return
     */
    @ApiOperation(value = "wxapp获取首页数据", notes = "wxapp获取首页数据", tags = "wxapp获取首页数据")
    @RequestMapping(value = {"getIndexPage"}, method = RequestMethod.GET)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "token值", name = "token", required = false, dataType = "String", paramType = "query")
    })
    @ResponseBody
    public Result<IndexResp> getIndex(String token) {
        Result result = new Result();
        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        log.info("login=token=>" + token + ";redisOpenId=>" + redisOpenId);
        //用户信息
        Customer customer = this.customerService.getByOpenID(redisOpenId);
        if (customer == null) {

            result.setMessage("查询用户为空,");
            log.info("查询用户为空");
            //如果用户为空，不查积分，只查所有推荐的自营商品，和自营商品
            IndexResp resp = getUsed(0L, false);
            result.setData(resp);
            result.setSuccess(true);
            return result;
        } else {
            IndexResp resp = this.getUsed(customer.getId(), true);
            result.setData(resp);
            result.setSuccess(true);
            return result;
        }
    }

    //抽取方法
    private IndexResp getUsed(Long customerId, boolean flag) {
        IndexResp resp = new IndexResp();

        if (flag) {
            //查询积分
            Wallet wallet = this.walletService.getByCustomerId(customerId);
            //查询自营商品集合
            List<ScoreItemResp> itemList = this.selfItemService.selectByCusNum1(wallet.getScore());
            resp.setScore(wallet.getScore());
            resp.setItemList(itemList);
        } else {
            resp.setScore(0.00);
            List<ScoreItemResp> notScoreItemList = this.selfItemService.selectByCusNum1(0.00);
            resp.setItemList(notScoreItemList);
        }

        //查询广告
        List<AdvertisementResp> adList = this.advertisementService.getList(1);
        resp.setAdList(adList);
        return resp;
    }


    @ApiOperation(value = "wxapp获取积分商品详情", notes = "wxapp获取积分商品详情", tags = "wxapp获取积分商品详情")
    @RequestMapping(value = {"getItem"}, method = {RequestMethod.GET})
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(value = "商品ID", name = "itemId", required = true, dataType = "Long", paramType = "query")
    })
    public Result getByItemId(Long itemId) {
        ScoreItemResp item = this.selfItemService.selectByItemId(itemId);
        return Result.ok(item);
    }


    @ApiOperation(value = "wxapp获取积分详细", notes = "wxapp获取积分详细", tags = "wxapp获取积分详细")
    @RequestMapping(value = {"getScoreDetails"}, method = {RequestMethod.GET})
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(value = "token值", name = "token", required = true, dataType = "Long", paramType = "query")
    })
    public Result getScoreDetails(String token) {
        //根据openID获取用户
        String redisOpenId = ToolsUtil.getRedisOpenId(token);
        log.info("login=token=>" + token + ";redisOpenId=>" + redisOpenId);
        //用户信息
        Customer customer = this.customerService.getByOpenID(redisOpenId);
        List<WalletLogResp> walletLog = this.walletLogService.getByCustomerIdScoreDetailList(customer.getId());
        return Result.ok(walletLog);
    }

    /**
     * 积分可换
     *
     * @return
     */
    @ApiOperation(value = "wxapp当前可换商品", tags = "wxapp当前可换商品")
    @RequestMapping(value = {"getScoreItems"}, method = {RequestMethod.GET})
    @ResponseBody
    @ApiImplicitParam(value = "token值", name = "token", required = true, dataType = "Long", paramType = "query")
    public Result getScoreItems(String token, int page, @RequestParam(defaultValue = "10") int pageSize, @RequestParam(defaultValue = "1") int type) {
        if (StringUtils.isNotBlank(token)) {
            //根据openID获取用户
            String redisOpenId = ToolsUtil.getRedisOpenId(token);
            log.info("login=token=>" + token + ";redisOpenId=>" + redisOpenId);
            //用户信息
            Customer customer = this.customerService.getByOpenID(redisOpenId);
            Result result = this.selfItemService.getScoreItemsList(customer.getId(), page, pageSize, type);
            return result;
        } else {
            return Result.build("token为空");
        }
    }
}