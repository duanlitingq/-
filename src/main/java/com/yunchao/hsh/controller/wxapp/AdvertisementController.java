package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.model.Advertisement;
import com.yunchao.hsh.service.IAdvertisementService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wdz on 2018/11/7
 * Remarks:
 */
@Controller(value = "appAdvertisementController")
@RequestMapping(value="/page/wxapp/ad/")
public class AdvertisementController {

    Logger logger = Logger.getLogger("AdvertisementController.class") ;
    @Autowired
    private IAdvertisementService advertisementService;


    @ApiOperation(value = "前台广告", tags = "广告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int")
    })
    @ResponseBody
    @RequestMapping(value = "getPage",produces = "application/json;charset=UTF-8")
    public Result getPage(Integer pageNum, Integer pageSize){
        Result result = new Result();
        try {
            if(pageNum == null){
                pageNum = 1;
            }
            if(pageSize == null){
                pageSize = 20;
            }
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("status",1);
            PageHelper.orderBy("  id desc");
            PageInfo<AdvertisementResp> pageInfo = advertisementService.getPage(params,pageNum,pageSize) ;
            result.setS("",pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("前台广告分页列表异常！" + e.getMessage());
            result.setF("");
        }
        return result;
    }
}
