package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.Supplier;
import com.yunchao.hsh.service.ISupplierGoodsService;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller(value = "appSupplierController")
@RequestMapping("/page/wxapp/supplier/")
public class SupplierController {

    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ISupplierGoodsService supplierGoodsService;

    /**
     *
     * @param typeId   店铺类别
     * @param name     店铺名称
     * @param pageSize 每页展示数量，默认20
     * @param pageNum  页码
     * @return
     */
    @ApiOperation(value = "前台店铺", tags = "店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "店铺类别", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "店铺名称", dataType = "Long"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int"),
    })
    @RequestMapping(value = "findPage",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result findSupplier(Long typeId,String name,Integer pageSize,Integer pageNum){
        Result result = new Result();
        if(pageSize == null){
            pageSize = 20;
        }
        if(pageNum == null ){
            pageNum = 1;
        }
        try {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("status", 1);
            map.put("typeId",typeId);
            PageInfo<SupplierResp> page = supplierService.getPage(map, pageNum, pageSize);
            List<SupplierResp> list = page.getList();
            for (int i = 0; i < list.size(); i++) {
                SupplierResp supplierResp = list.get(i);
                Long id = supplierResp.getId();
                HashMap<String,Object> maps = new HashMap<>();
                maps.put("supplierId",id);
                maps.put("status",1);
                PageInfo<SupplierGoodsResp> page1 = supplierGoodsService.findPage(maps, 1, 3);
                List<SupplierGoodsResp> list1 = page1.getList();
                supplierResp.setGoodsRespList(list1);
            }
            result.setS("",page);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }
    @ApiOperation(value = "前台店铺", tags = "店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
    })
    @RequestMapping(value = "getSupplier")
    @ResponseBody
    public Result findSupplier(Long supplierId){
        Result result = new Result();
        SupplierResp supplierResp = supplierService.findById(supplierId);
        result.setS("",supplierResp);
        return result;
    }

}
