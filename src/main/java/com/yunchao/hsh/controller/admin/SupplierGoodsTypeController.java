package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.SupplierGoodsType;
import com.yunchao.hsh.service.ISupplierGoodsTypeServer;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/page/admin/supplierGoodsType")
public class SupplierGoodsTypeController {

    @Autowired
    private ISupplierGoodsTypeServer supplierGoodsTypeServer;
    @Autowired
    private ISupplierService supplierService;

    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "添加店铺商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parentId", value = "父级编号", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态0隐藏1展示", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "hierarchy", value = "层级", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/doAdd")
    @ResponseBody
    public Result doAdd(SupplierGoodsType supplierGoodsType) {
        Result result = new Result();
        try {
            // 重复校验可用
//            SupplierGoodsType goodsType = supplierGoodsTypeServer.getBuyName(supplierGoodsType.getName(),supplierGoodsType.getSupplierId(),null);
//            if(goodsType == null){
//
//            }else{
//                return result.setF("", "添加重复");
//            }
            supplierGoodsTypeServer.insert(supplierGoodsType);
            return result.setS("", "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("修改失败");
        }
        return result;
    }

    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "修改店铺商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "parentId", value = "父级编号", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态0隐藏1展示", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "hierarchy", value = "层级", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "priority", value = "优先级", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/doUpdate")
    @ResponseBody
    public Result doUpdate(SupplierGoodsType supplierGoodsType) {
        Result result = new Result();
        try {
            supplierGoodsTypeServer.update(supplierGoodsType);
            return result.setS("", "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("修改失败");
        }
        return result;
    }

    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "店铺商品类别列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态0隐藏1展示", required = false, dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "hierarchy", value = "层级", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/listPage")
    @ResponseBody
    public PageInfo<SupplierGoodsType> listPage(Integer pageNum, Integer pageSize, Integer status, Integer hierarchy, String name,Long parentId) {
        Map<String, Object> params = new HashMap<String, Object>();
        if (status != null) {
            params.put("status", status);
        }
        if (hierarchy != null) {
            params.put("hierarchy", hierarchy);
        }
        params.put("name", name);
        if (pageNum == null) {
            pageNum = 1;
        }
        if (pageSize == null) {
            pageSize = 20;
        }
        if (parentId != null) {
            params.put("parentId", parentId);
        }
        try {
            PageHelper.orderBy(" hierarchy asc,priority desc ");
            PageInfo<SupplierGoodsType> pageInfo = supplierGoodsTypeServer.listPage(params, pageNum, pageSize);
            return pageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "店铺商品类别单个状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别编号", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态0隐藏1展示", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/updateStatus")
    @ResponseBody
    public Result updateStatus(Long id, Integer status) {
        Result result = new Result();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);
            map.put("status", status);
            supplierGoodsTypeServer.updateStatus(map);
            return result.setS("", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("操作失败");
        }
        return result;
    }
    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "店铺商品类别批量状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "类别编号", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态0隐藏1展示", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/batchUpdateStatus")
    @ResponseBody
    public Result batchUpdateStatus(String ids, Integer status) {
        Result result = new Result();
        try {
            if(StringUtils.isEmpty(ids)){
                return result.setF("请选择要操作的类别");
            }
            Map<String, Object> map = null;
            String[] split = ids.split(",");
            int len = split.length;
            for (int i = 0; i < len; i++) {
                if (StringUtils.isNotEmpty(split[i])){
                    if(map == null){
                        map = new HashMap<>(20);
                    }
                    map.put("id", split[i]);
                    map.put("status", status);
                    supplierGoodsTypeServer.updateStatus(map);
                }
            }
            return result.setS("", "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("操作失败");
        }
        return result;
    }

    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "获取店铺商品类别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别编号", required = false, dataType = "Long", paramType = "query"),
    })
    @RequestMapping(value = "/getSupplierGoodsType")
    @ResponseBody
    public Result getSupplierGoodsType(Long id) {
        Result result = new Result();
        try {
            SupplierGoodsType supplierGoodsType = supplierGoodsTypeServer.getSupplierGoodsType(id);
            return result.setS("", supplierGoodsType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    @ApiOperation(value = "店铺商品类别", tags = {"店铺商品类别"}, notes = "获取店铺商品类别list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态0隐藏1展示", required = false, dataType = "Integer", paramType = "query"),
    })
    @RequestMapping(value = "/listGoodsType")
    @ResponseBody
    public Result listGoodsType(Long supplierId,Integer status,Long parentId){
        Result result = new Result();
        try {
            if(supplierId != null){
                SupplierResp supplier = supplierService.findById(supplierId);
                parentId = supplier.getTypeId();
            }
            List<SupplierGoodsType> lists = supplierGoodsTypeServer.listGoodsType(parentId,status);
            return result.setS("", lists);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


}
