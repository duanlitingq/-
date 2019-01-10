package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.service.ISelfItemOrderService;
import com.yunchao.hsh.utils.ParamUtils;
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

import javax.servlet.http.HttpServletRequest;

@Api
@Controller
@RequestMapping("/page/admin/self/order/")
public class SelfItemOrderController extends BaseController {

    @Autowired
    private ISelfItemOrderService selfItemOrderService;


    @ApiOperation(value = "自营订单列表查询", tags = "自营订单列表查询")
    @RequestMapping(value = {"list"}, method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(value = "当前页", name = "pageNum", dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "条数", name = "pageNum", dataType = "int", paramType = "query"),
            @ApiImplicitParam(value = "订单号", name = "orderId", dataType = "String", paramType = "query")
    })
    public Result getPage(HttpServletRequest request) {
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 10);
        String orderId = ParamUtils.getParameter(request, "orderId", "");
        Result result = this.selfItemOrderService.getPage(pageNum, pageSize, orderId);
        return result;
    }

    @ApiOperation(value = "修改自营商品订单状态", tags = "修改自营商品订单状态")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "订单号", name = "orderId", dataType = "String", paramType = "body"),
            @ApiImplicitParam(value = "订单状态", name = "status", dataType = "String", paramType = "body"),
    })
    @RequestMapping(value = {"updateStatus"}, method = RequestMethod.GET)
    @ResponseBody
    public Result updateOrderStatus(@RequestParam String orderId, @RequestParam String status) {
        if (StringUtils.isNotBlank(orderId) && StringUtils.isNotBlank(status)) {
            Result result = this.selfItemOrderService.updateOrderStatus(orderId, status);
            return result;
        } else {
            return Result.build("参数为空");
        }
    }
}
