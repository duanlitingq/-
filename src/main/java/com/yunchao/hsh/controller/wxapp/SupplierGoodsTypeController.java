package com.yunchao.hsh.controller.wxapp;


import com.github.pagehelper.PageHelper;
import com.yunchao.hsh.model.SupplierGoodsType;
import com.yunchao.hsh.service.ISupplierGoodsTypeServer;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;


@Controller(value = "wxappSupplierGoodsTypeController")
@RequestMapping("/page/wxapp/supplierGoodsType")
public class SupplierGoodsTypeController {

    @Autowired
    private ISupplierGoodsTypeServer supplierGoodsTypeServer;

    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "获取店铺商品类别list")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/listGoodsType")
    @ResponseBody
    public Result listGoodsType(){
        Result result = new Result();
        try {
            PageHelper.orderBy(" priority desc");
            List<SupplierGoodsType> lists = supplierGoodsTypeServer.listGoodsType(0L,1);
            return result.setS("", lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "获取店铺商品类别list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentId", value = "类别编号", required = false, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value = "/listSecondGoodsType")
    @ResponseBody
    public Result listSecondGoodsType(Long parentId){
        Result result = new Result();
        try {
            PageHelper.orderBy(" PRIORITY desc");
            List<SupplierGoodsType> lists = supplierGoodsTypeServer.listGoodsType(parentId,1);
            return result.setS("", lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
