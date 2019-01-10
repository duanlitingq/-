package com.yunchao.hsh.controller.wxapp;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.HshAnnoun;
import com.yunchao.hsh.service.IAnnounService;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/23 09:55
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Api(value = "积分兑换公告列表")
@Controller
@RequestMapping(value = "/page/wxapp/anno/")
public class AnnounController extends BaseController {


    @Autowired
    private IAnnounService announService;

    @ApiOperation(value = "wxapp查询积分兑换消息列表", tags = "wxapp查询积分兑换消息列表", notes = "wxapp查询积分兑换消息列表")
    @RequestMapping(value = {"getAnnounListMsg"}, method = RequestMethod.GET)
    @ResponseBody
    public Result getAnnounListMsg() {
        try {
            List<HshAnnoun> announListMsg = this.announService.getAnnounListMsg();
            return Result.ok(announListMsg);
        } catch (Exception e) {
            log.error("查询失败:{}" + e.getMessage());
        }
        return Result.build("查询失败");
    }
}
