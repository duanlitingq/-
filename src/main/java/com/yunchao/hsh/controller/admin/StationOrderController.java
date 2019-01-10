package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.HshStationOrder;
import com.yunchao.hsh.service.IStationOrderService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Api
@Controller
@RequestMapping("/page/admin/station/order/")
public class StationOrderController extends BaseController {

    @Autowired
    private IStationOrderService stationOrderService;


    @ApiOperation(value = "获取驿站订单列表", tags = "获取驿站订单列表", notes = "获取驿站订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态 0：未支付、1：已支付", dataType = "int", paramType = "query"),
    })
    @RequestMapping(value = {"getPage"}, method = RequestMethod.POST)
    @ResponseBody
    public Result getPageList(HttpServletRequest request) {
        int num = ParamUtils.getIntParameter(request, "pageNum", 1);
        int size = ParamUtils.getIntParameter(request, "pageSize", 10);
        PageHelper.startPage(num, size);
        PageInfo<HshStationOrder> pageInfo = this.stationOrderService.findStationOrderList();
        return Result.ok(pageInfo);
    }
}
