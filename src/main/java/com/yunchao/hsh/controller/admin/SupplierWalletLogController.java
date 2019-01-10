package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierWalletLogResp;
import com.yunchao.hsh.model.SupplierWalletLog;
import com.yunchao.hsh.service.ISupplierWalletLogService;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/***
 * 供应商出入账控制器
 *
 */
@Controller
@RequestMapping("/page/admin/supplierwallet/")
public class SupplierWalletLogController {

    @Autowired
    private ISupplierWalletLogService supplierWalletLogService;

    @ApiOperation(value = "店铺出入账", tags = "类别批量状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int")
    })
    @RequestMapping("findPage")
    @ResponseBody
    public PageInfo<SupplierWalletLogResp> findPage(Long supplierId,Integer pageSize,Integer pageNum){
        Result result = new Result();
        if(pageSize == null){
            pageSize = 20;
        }
        if(pageNum == null){
            pageNum = 1;
        }
       try {
           Map<String,Object> map = new HashMap<>();
           map.put("supplierId",supplierId);
           PageHelper.orderBy(" id desc ");
           PageInfo<SupplierWalletLogResp> page = supplierWalletLogService.findPage(map,pageNum,pageSize);
           result.setSuccess(true);
           return page;
       }catch (Exception e){
           e.printStackTrace();
       }
        return  null;
    }
}
