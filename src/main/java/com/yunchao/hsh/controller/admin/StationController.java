package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.dto.req.StationReq;
import com.yunchao.hsh.model.HshStation;
import com.yunchao.hsh.service.StationService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@Api
@Controller
@RequestMapping(value = "/page/admin/station/")
public class StationController extends BaseController {

    @Autowired
    private StationService stationService;

    /**
     * 查询驿站列表
     *
     * @param stationReq
     * @return
     */
    @RequestMapping(value = "getPage", method = {RequestMethod.POST, RequestMethod.GET})
    @ApiOperation(value = "获取驿站列表", tags = "获取驿站列表", notes = "获取驿站列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "驿站名称", dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "isDel", value = "是否上下线，1 上线， 0下线", dataType = "String", paramType = "header"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", dataType = "int", paramType = "header"),
            @ApiImplicitParam(name = "pageSize", value = "每条显示的条数", dataType = "int", paramType = "header"),
    })
    @ResponseBody
    public Result findStationList(StationReq stationReq, HttpServletRequest request) {
        int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
        int pageSize = ParamUtils.getIntParameter(request, "pageSize", 10);
        String name = ParamUtils.getParameter(request, "name", "");
        String status = ParamUtils.getParameter(request, "isDel", "1");
        PageHelper.startPage(pageNum, pageSize);
        stationReq.setNum(pageNum);
        stationReq.setPageSize(pageSize);
        stationReq.setName(name);
        stationReq.setIsDel(status);
        PageInfo<HshStation> pageInfo = this.stationService.findStationList(stationReq);
        return Result.ok(pageInfo);
    }

    /**
     * 新增驿站
     *
     * @return
     */
    @ApiOperation(value = "新增驿站", tags = "新增驿站", notes = "新增驿站")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "驿站名称", name = "name", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "主打特色", name = "featurs", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "人均", name = "preAvg", dataType = "Integer", paramType = "form"),
            @ApiImplicitParam(value = "地址", name = "address", dataType = "String", paramType = "form", required = true),
            @ApiImplicitParam(value = "店铺头像", name = "stationImg", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "地址", name = "address", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "店铺营业执照", name = "stationLicense", dataType = "String", paramType = "form"),
            @ApiImplicitParam(value = "食品资质证书", name = "stationQuality", dataType = "String", paramType = "form"),
    })
    @RequestMapping(value = "insert", method = {RequestMethod.POST})
    public String insertStation(HttpServletRequest request, StationReq dto) {
       try {
         /*    String imgsStr = "";
            for (int i = 0; i < file.length; i++) {
                if (file[i].getSize() > 0) {
                    if (file[i].getSize() > 0) {
                        com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                        if (!result.isSuccess()) {
                            result.setMessage("返回上传图片路径错误");
                            result.setSuccess(false);
                            return "/station/list";
                        }
                        Object[] obj = (Object[]) result.getData();
                        imgsStr = imgsStr + obj[1].toString() + ";";
                    }
                }
            }
            dto.setStationImg(imgsStr);*/
            //查找经度/纬度
            this.stationService.addStation(dto);
            return "/station/list";
        } catch (Exception e) {
            log.error("保存失败", e);
            return "";
        }
    }

    /**
     * @return
     * @author Wind
     * @email hkwind959@google.com
     * @Description 删除驿站
     * @Date 21:50 2018/11/7
     * @Param
     **/
    @ApiOperation(value = "删除驿站", tags = "删除驿站", notes = "删除驿站")
    @ApiImplicitParam(name = "stationId", value = "驿站ID", paramType = "query", dataType = "String")
    @RequestMapping(value = "del", method = {RequestMethod.GET})
    @ResponseBody
    public Result delStation(String stationId) {
        if (StringUtils.isNotBlank(stationId)) {
            try {
                this.stationService.delStation(stationId);
                log.info("删除成功");
                return Result.ok(null);
            } catch (Exception e) {
                log.error("删除失败");
                return Result.error();
            }
        } else {
            log.info("{}参数为空", stationId);
            return Result.build("传递参数为空");
        }
    }

    /**
     * @return
     * @author Wind
     * @email hkwind959@google.com
     * @Description 删除驿站
     * @Date 21:50 2018/11/7
     * @Param
     **/
    @ApiOperation(value = "上架驿站", tags = "上架驿站", notes = "上架驿站")
    @ApiImplicitParam(name = "stationId", value = "驿站ID", paramType = "query", dataType = "String")
    @RequestMapping(value = "upStation", method = {RequestMethod.GET})
    @ResponseBody
    public Result upStation(String stationId) {
        if (StringUtils.isNotBlank(stationId)) {
            try {
                this.stationService.upStation(stationId);
                log.info("上架成功");
                return Result.ok(null);
            } catch (Exception e) {
                log.error("上架成功");
                return Result.error();
            }
        } else {
            log.info("{}参数为空", stationId);
            return Result.build("传递参数为空");
        }
    }

    @ApiOperation(value = "修改驿站", tags = "修改驿站", notes = "修改驿站")
    @RequestMapping(value = "doUpdate", method = {RequestMethod.POST})
    public String updateStation(HttpServletRequest request, StationReq stationReq,String stationsImg) {
        if (stationReq != null) {
            try {
               /* for (int i = 0; i < file.length; i++) {
                    if (file[i].getSize() > 0) {
                        if (file[i].getSize() > 0) {
                            com.yunchao.hsh.utils.superdir.sub.Result result = CommonUtil.uploadPic(file[i], request, null);
                            if (!result.isSuccess()) {
                                result.setMessage("返回上传图片路径错误");
                                result.setSuccess(false);
                                return "/station/list";
                            }
                            Object[] obj = (Object[]) result.getData();
                            imgsStr = imgsStr + obj[1].toString() + ";";
                        }
                    }
                }
                stationReq.setStationImg(imgsStr);*/
               stationsImg= stationsImg.replaceAll("http://other.yueyongyueyou.com:8888/","").replaceAll(",",";");
                stationReq.setStationImg(stationsImg);
                this.stationService.updateStation(stationReq);
                return "/station/list";
            } catch (Exception e) {
                return "上传失败";
            }
        } else {
            log.info("{}参数为空", stationReq);
            return "";
        }
    }

    @ApiOperation(value = "根据ID查找驿站", tags = "根据ID查找驿站", notes = "根据ID查找驿站")
    @RequestMapping(value = {"findById"}, method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Result findByStationId(@RequestParam String stationId) {
        if (StringUtils.isNotBlank(stationId)) {
            HshStation station = this.stationService.findByStationId(Long.valueOf(stationId));
            return Result.ok(station);
        } else {
            return Result.build("stationId 为空");
        }
    }

}
