package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.up72.framework.util.page.Page;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.model.Advertisement;
import com.yunchao.hsh.model.Menu;
import com.yunchao.hsh.service.IAdvertisementService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@Controller
@RequestMapping(value="/page/admin/ad/")
public class AdvertisementController {

    Logger logger = Logger.getLogger("AdvertisementController.class") ;
    @Autowired
    private IAdvertisementService advertisementService;


    @ApiOperation(value = "后台广告", tags = "后台广告列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int"),
            @ApiImplicitParam(name = "pageSize", value = "条数", dataType = "int"),
    })
    @ResponseBody
    @RequestMapping("getPage")
    public PageInfo<AdvertisementResp> getPage(HttpServletRequest request){
        try {
            int pageNum = ParamUtils.getIntParameter(request,"pageNum",1);
            int pageSize = ParamUtils.getIntParameter(request,"pageSize",20) ;
            Map<String,Object> params = new HashMap<String,Object>();
            String title = ParamUtils.getParameter(request,"title",""); //菜单名
            Integer status = ParamUtils.getIntParameter(request,"status",-1); //状态
            if(status != -1){
                params.put("status",status);
            }
            params.put("title",title);
            PageHelper.orderBy("  sort desc");
            PageInfo<AdvertisementResp> pageInfo = advertisementService.getPage(params,pageNum,pageSize) ;
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("后台广告分页列表异常！" + e.getMessage());
        }
        return null;
    }

    @ApiOperation(value = "后台广告", tags = "广告详细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告编号", dataType = "Long"),
    })
    @RequestMapping("getById")
    @ResponseBody
    public AdvertisementResp getById(Long id){
        AdvertisementResp ad = advertisementService.getById(id);
        return ad;
    }
    @ApiOperation(value = "后台广告", tags = "添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "广告标题", dataType = "String"),
            @ApiImplicitParam(name = "info", value = "广告简介", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "file"),
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int"),
            @ApiImplicitParam(name = "link", value = "链接", dataType = "String"),
    })
    @RequestMapping("doAdd")
    //@ResponseBody
    public String doAdd(HttpServletRequest request, Advertisement ad){
        Result result = new Result();
        String imgs = "";
        try {
            if(ObjectUtils.isEmpty(ad.getLink())){
                ad.setLink("#");
            }
           /* for (int i = 0; i < file.length; i++) {
                if (file[i].getSize() > 0) {
                    result = CommonUtil.uploadPic(file[i], request, logger);
                    if (!result.isSuccess()) {
                        logger.error("返回上传图片路径错误！");
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "ad/list";
                    }else{
                        Object[] obj = (Object[]) result.getData();
                        imgs = imgs + obj[1].toString() + ";";
                    }
                }
            }*/
           /* ad.setImgs(imgs);*/
            advertisementService.save(ad);
            result.setSuccess(true);
            return "ad/list";
        } catch (Exception e) {
            logger.error("添加异常 = " + e.getMessage());
            result.setSuccess(false);
            result.setMessage("添加异常");
        }
        return "ad/list";
    }
    @ApiOperation(value = "后台广告", tags = "修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "title", value = "广告标题", dataType = "String"),
            @ApiImplicitParam(name = "info", value = "广告简介", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "file"),
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int"),
            @ApiImplicitParam(name = "link", value = "链接", dataType = "String"),
    })
    @RequestMapping("doUpdate")
   // @ResponseBody  , @RequestParam MultipartFile[] file,String imgsStr
    public String doUpdate(HttpServletRequest request, Advertisement ad){//imgs 图片
        Result result = new Result();
        try {
            if(ObjectUtils.isEmpty(ad.getLink())){
                ad.setLink("#");
            }
          /*  for (int i = 0; i < file.length; i++) {
                if (file[i].getSize() > 0) {
                    result = CommonUtil.uploadPic(file[i], request, logger);
                    if (!result.isSuccess()) {
                        result.setMessage("返回上传图片路径错误");
                        result.setSuccess(false);
                        return "ad/list";
                    }else{
                        Object[] obj = (Object[]) result.getData();
                        imgsStr = imgsStr + obj[1].toString() + ";";
                    }
                }
            }
            ad.setImgs(imgsStr);*/
            advertisementService.update(ad);
            result.setSuccess(true);
            return "ad/list";
        } catch (Exception e) {
            logger.error("更新异常 = " + e.getMessage());
            result.setF("");
        }
        return "ad/list";
    }

    @ApiOperation(value = "后台广告", tags = "根据状态查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int"),
    })
    @RequestMapping("getList")
    @ResponseBody
    public List getList(Integer status){
        List<AdvertisementResp> list = advertisementService.getList(status);
        return list;
    }
    @ApiOperation(value = "后台广告", tags = "状态单个修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "广告编号", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int"),
    })
    @RequestMapping("updateStatus")
    @ResponseBody
    public Result doUpdateStatus(Long id,Integer status){
        Result result = new Result();
        try {
            if(id != null && id > 0){
                advertisementService.doUpdateAdStatus(id,status);
                result.setMessage("更新状态成功！");
                result.setSuccess(true);
            }else{
                result.setMessage("数据编号不能为空！");
                result.setSuccess(false);
            }
        } catch (Exception e){
            result.setMessage("更新状态失败！");
            result.setSuccess(false);
        }
        return result;
    }

    @ApiOperation(value = "后台广告", tags = "状态皮；批量修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "adIds", value = "广告编号串逗号分隔", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int"),
    })
    @RequestMapping("batchUpdateState")
    @ResponseBody
    public Result batchUpdateAdStatus(String adIds,Integer status){
        Result result = new Result();
        try {
            if(!StringUtils.isBlank(adIds)){
                advertisementService.batchUpdateAdStatus(adIds,status);
                result.setMessage("更新状态成功！");
                result.setSuccess(true);
            }else{
                result.setMessage("数据编号不能为空！");
                result.setSuccess(false);
            }
        } catch (Exception e){
            result.setMessage("更新状态失败！");
            result.setSuccess(false);
        }
        return result;
    }
}
