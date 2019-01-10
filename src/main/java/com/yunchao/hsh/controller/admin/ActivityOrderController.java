package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.IActivityOrderService;
import com.yunchao.hsh.utils.Constant;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 活动订单管理
 *
 * @ClassName: ActivityOrderController
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/9 11:25
 * @Version: 1.0
 */
@Api
@Controller
@RequestMapping(value = {"/page/admin/activity/order/"})
public class ActivityOrderController extends BaseController {

    /**
     * 修改领取次数
     *
     * @param orderId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = {"update"})
    public Result updateGetNum(@RequestParam String orderId, HttpServletRequest request) {
        SysUser adminUser = (SysUser) request.getSession().getAttribute(Constant.LOGIN_USER);
        if (StringUtils.isEmpty(orderId)) {
            return Result.build("orderId 参数为空");
        }
        if (adminUser == null) {
            return Result.build("当前用户未登陆");
        }
        try {
            Result result = this.activityOrderService.updateGetNum(orderId, adminUser);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("修改订单异常");
            return Result.build("修改订单异常");
        }
    }

    @Autowired
    private IActivityOrderService activityOrderService;

    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderId", value = "订单ID", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "activityId", value = "活动ID", dataType = "String", paramType = "query"),
    })
    @RequestMapping(value = {"getPage"}, method = RequestMethod.POST)
    @ResponseBody
    public Result getPage(HttpServletRequest request) {
        try {
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", 10);
            String orderId = ParamUtils.getParameter(request, "orderId", "");
            String activityId = ParamUtils.getParameter(request, "activityId", "");
            Result result = this.activityOrderService.getOrderPageList(pageNum, pageSize, orderId, activityId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询活动订单分页列表失败");
            return Result.build("查询异常");
        }
    }
}
