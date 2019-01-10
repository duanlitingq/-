package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.req.ActivityReq;
import com.yunchao.hsh.dto.req.ActivityReqs;
import com.yunchao.hsh.model.HshActivity;
import com.yunchao.hsh.service.IActivityService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ParamUtils;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 2352活动管理
 *
 * @ClassName: ActivityController
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/8 18:19
 * @Version: 1.0
 */
@Api
@Controller
@RequestMapping("/page/admin/activity/")
public class ActivityController extends BaseController {

    @Autowired
    private IActivityService activityService;

    /**
     * 商品列表
     *
     * @return
     */
    @ApiOperation(value = "自营商品列表", tags = "自营商品列表")
    @RequestMapping(value = {"getPage"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "int"),
    })
    @ResponseBody
    public Result getItemList(HttpServletRequest request) {
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 10);
        Result result = this.activityService.getItemList(pageNum, pageSize);
        return result;
    }

    @ApiOperation(value = "添加活动商品", tags = "添加活动商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityName", value = "活动名称", dataType = "String"),
            @ApiImplicitParam(name = "activityPrice", value = "活动商品价格", dataType = "Long"),
            @ApiImplicitParam(name = "activityImg", value = "活动图像", dataType = "String"),
            @ApiImplicitParam(name = "activityItemImg", value = "商品图片", dataType = "String"),
            @ApiImplicitParam(name = "sellPoint", value = "商品简介", dataType = "String"),
            @ApiImplicitParam(name = "itemNum", value = "商品数量", dataType = "Integer"),
            @ApiImplicitParam(name = "itemRebate", value = "购买返还的积分", dataType = "Integer"),
    })
    @RequestMapping(value = "doAdd", method = RequestMethod.POST)
    public String addActivity(HttpServletRequest request, ActivityReq activity, @RequestParam MultipartFile[] file, @RequestParam MultipartFile[] fileDesc, @RequestParam MultipartFile[] gameDesc) {
        String itemImgs = "";
        //商品描述图片
        String itemDescImgs = "";
        //0元吃好油，玩法图片
        String gameIms = "";
        for (int i = 0; i < file.length; i++) {
            if (file[i].getSize() > 0) {
                if (file[i].getSize() > 0) {
                    com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                    if (!result.isSuccess()) {
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "/activity/list";
                    }
                    Object[] obj = (Object[]) result.getData();
                    itemImgs = itemImgs + obj[1].toString() + ";";
                }
            }
        }
        for (int i = 0; i < fileDesc.length; i++) {
            if (file[i].getSize() > 0) {
                if (file[i].getSize() > 0) {
                    com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                    if (!result.isSuccess()) {
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "/activity/list";
                    }
                    Object[] obj = (Object[]) result.getData();
                    itemDescImgs = itemDescImgs + obj[1].toString() + ";";
                }
            }
        }
        for (int i = 0; i < gameDesc.length; i++) {
            if (file[i].getSize() > 0) {
                if (file[i].getSize() > 0) {
                    com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                    if (!result.isSuccess()) {
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "/activity/list";
                    }
                    Object[] obj = (Object[]) result.getData();
                    gameIms = gameIms + obj[1].toString() + ";";
                }
            }
        }
        //活动商品图片
        activity.setItemImgs(itemImgs);
        activity.setGameImgs(gameIms);
        activity.setItemDescImgs(itemDescImgs);
        this.activityService.addItem(activity, null);
        return "/activity/list";
    }

    @ApiOperation(value = "修改活动商品", tags = "修改活动商品")
    @RequestMapping(value = {"doUpdate"}, method = RequestMethod.POST)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityId", value = "活动ID", dataType = "String"),
            @ApiImplicitParam(name = "activityName", value = "活动名称", dataType = "String"),
            @ApiImplicitParam(name = "activityPrice", value = "活动商品价格", dataType = "Long"),
            @ApiImplicitParam(name = "activityImg", value = "活动图像", dataType = "String"),
            @ApiImplicitParam(name = "activityItemImg", value = "商品图片", dataType = "String"),
            @ApiImplicitParam(name = "sellPoint", value = "商品简介", dataType = "String"),
            @ApiImplicitParam(name = "itemNum", value = "商品数量", dataType = "Integer"),
            @ApiImplicitParam(name = "itemRebate", value = "购买返还的积分", dataType = "Integer"),
    })
    public String updateActivity(ActivityReqs reqs) {
        this.activityService.updateItem(reqs);
        return "/activity/list";
    }

    @ApiOperation(value = "删除/下架活动商品", tags = "删除/下架活动商品")
    @ApiImplicitParam(value = "活动商品ID", name = "activityId", dataType = "int", paramType = "query")
    @RequestMapping(value = {"del"}, method = RequestMethod.GET)
    @ResponseBody
    public Result delActivity(@RequestParam Long activityId) {
        Result result = this.activityService.delItem(activityId);
        return result;
    }

    @RequestMapping(value = {"findById"})
    @ResponseBody
    public Result findByActivityId(Long activityId) {
        Result result = this.activityService.findByActivityId(activityId);
        return result;
    }


}

