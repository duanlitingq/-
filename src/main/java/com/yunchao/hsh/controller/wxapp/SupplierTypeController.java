package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierTypeResp;
import com.yunchao.hsh.model.SupplierType;
import com.yunchao.hsh.service.ISupplierTypeService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @Title: SupplierTypeController
 * @Description: 供应商类别,弃用，使用SupplierGoodsTypeController
 * @author wdz
 * @date 2019/1/7 14:16
 */
@Controller(value = "appSupplierTypeController")
@RequestMapping("/page/wxapp/suppliertype/")
public class SupplierTypeController {

    @Autowired
    private ISupplierTypeService supplierTypeService;

    /**
     * 供应商类别
     * @param pageSize
     * @param pageNum
     * @param name
     * @param status
     * @return
     */
    @ApiOperation(value = "前台店铺类别", tags = "类别列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "类别状态1展示0不展示", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "类别名称", dataType = "Long"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int"),
    })
    @RequestMapping("getPage")
    @ResponseBody
    public Result getPage(Integer pageSize,Integer pageNum,String name, Integer status){
        Result result = new Result();
        try {
            if(pageNum == null){
                pageNum = 1;
            }
            if(pageSize == null){
                pageSize = 20;
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("status", status);
            PageHelper.orderBy(" sort desc ");
            PageInfo<SupplierTypeResp> page = supplierTypeService.getPage(map, pageNum, pageSize);
            result.setS("操作成功",page);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }
}
