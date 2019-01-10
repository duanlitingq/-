package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.resp.ActivityItemResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.HshActivityWelfare;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.service.IActivityService;
import com.yunchao.hsh.service.IActivityWelfareService;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.IHshLogisticsService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/20 10:53
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Api(tags = "wxapp活动商品查询")
@Controller
@RequestMapping(value = {"/page/wxapp/activity/"})
public class ActivityItemController extends BaseController {

    @Autowired
    private IActivityService activityService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IHshLogisticsService hshLogisticsService;

    @Autowired
    private IActivityWelfareService activityWelfareService;


    @ApiOperation(value = "wxapp活动商品查询", notes = "wxapp活动商品查询", tags = "wxapp活动商品查询")
    @ApiImplicitParam(value = "活动ID", name = "activityId", dataType = "Long", paramType = "query", required = true)
    @RequestMapping(value = {"getByActivityItemId"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getByActivityItemId(@RequestParam(defaultValue = "1") Long activityId, String token) {
        try {
            Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));

            ActivityItemResp activityResp = this.activityService.getByActivityId(activityId);
            if (ObjectUtils.isEmpty(customer)) {
                log.info("用户为空");
            } else {
                HshLogistics logistics = this.hshLogisticsService.selectBycustomer(customer.getId());
                if (logistics != null) {
                    activityResp.setLogId(logistics.getId());
                    activityResp.setReceiverName(logistics.getRealName());
                    activityResp.setReceiverMobile(logistics.getPhone());
                    activityResp.setReceiverAddress(logistics.getAddress());
                }
            }
            List<HshActivityWelfare> welfareList = this.activityWelfareService.selectAll();
            activityResp.setWelfareList(welfareList);
            log.info("返回数据:{}", activityResp);
            return Result.ok(activityResp);
        } catch (Exception e) {
            log.error("查询异常:{}", e.getMessage());
            return Result.build("查询异常");
        }
    }

}
