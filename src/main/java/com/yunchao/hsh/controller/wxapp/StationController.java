package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.resp.StationResp;
import com.yunchao.hsh.model.HshLogistics;
import com.yunchao.hsh.model.HshStation;
import com.yunchao.hsh.service.IHshLogisticsService;
import com.yunchao.hsh.service.StationService;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Api
@Controller(value = "wxStationController")
@RequestMapping(value = {"/page/wxapp/station/"})
public class StationController extends BaseController {

    @Autowired
    private StationService stationService;

    @Autowired
    private IHshLogisticsService logisticsService;

    /**
     * 获取驿站信息列表
     *
     * @return
     */
    @ApiOperation(value = "wxapp获取驿站列表", tags = "wxapp获取驿站列表")
    @RequestMapping(value = {"getStationList"})
    @ResponseBody
    public Result getStationList() {
        List<StationResp> list = this.stationService.getStationList();
        return Result.ok(list);
    }

    /**
     * 获取驿站信息列表
     *
     * @return
     */
    @ApiOperation(value = "wxapp获取驿站列表", tags = "wxapp获取驿站列表")
    @RequestMapping(value = {"getStationPage"})
    @ResponseBody
    public Result getStationPage(int page, @RequestParam(defaultValue = "10") int pageSize,String name) {
        //PageInfo<StationResp> pageInfo=this.stationService.getStationPage(page,pageSize);
        PageInfo<StationResp> conditionPage = this.stationService.getConditionPage(page, pageSize, name);
        return Result.ok(conditionPage);
    }


    /**
     * 根据驿站ID获取详情
     *
     * @return
     */
    @ApiOperation(value = "wxapp获取驿站详情", tags = "wxapp获取驿站详情")
    @ApiImplicitParam(value = "驿站ID", name = "stationId", paramType = "query", dataType = "Long", required = true)
    @RequestMapping(value = {"getStation"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getStationId(Long stationId) {
        HshStation station = this.stationService.findByStationId(stationId);
        return Result.ok(station);
    }

    /**
     * 添加收货地址
     *
     * @param realName
     * @param realMobile
     * @param stationId
     * @return
     */
    @ApiOperation(value = "wxapp添加收货地址", tags = "wxapp添加收货地址")
    @RequestMapping(value = {"saveAddress"}, method = {RequestMethod.POST})
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(value = "收货人", name = "realName", dataType = "String", paramType = "body"),
            @ApiImplicitParam(value = "收货人手机号", name = "realMobile", dataType = "String", paramType = "body"),
            @ApiImplicitParam(value = "驿站ID", name = "stationId", dataType = "Long", paramType = "body"),
    })
    public Result saveShoppingAddress(Long customerId, String realName, String realMobile, Long stationId) {
        try {
            this.logisticsService.saveShoppingAddress(customerId, realName, realMobile, stationId);
        } catch (Exception e) {
            log.error("保存收货地址失败");
            return Result.error();
        }
        return Result.ok(null);
    }

    /**
     * 收货地址列表
     *
     * @param customerId
     * @return
     */
    @ApiOperation(value = "wxapp收货地址列表", tags = "wxapp收货地址列表")
    @ApiImplicitParam(value = "用户ID", name = "customerId", paramType = "query", dataType = "Long")
    @RequestMapping(value = {"selectAllLogistics"}, method = {RequestMethod.GET})
    @ResponseBody
    public Result shoppingAddressList(Long customerId) {
        List<HshLogistics> logistics = this.logisticsService.selectAllLogistics(customerId);
        return Result.ok(logistics);
    }

    /**
     * 条件查询  分页
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @RequestMapping(value = {"condition"}, method = {RequestMethod.POST})
    @ResponseBody
    public Result condition(@RequestParam(value = "page", required = false)int page, @RequestParam(defaultValue = "10") int pageSize,String name){
        PageInfo<StationResp> conditionPage = this.stationService.getConditionPage(page, pageSize, name);
        return  Result.ok(conditionPage);
    }

}
