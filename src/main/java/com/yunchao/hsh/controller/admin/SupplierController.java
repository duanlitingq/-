package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.AdvertisementResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.HshCollection;
import com.yunchao.hsh.model.Supplier;
import com.yunchao.hsh.service.ICollectionService;
import com.yunchao.hsh.service.ISupplierService;
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
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.IntSupplier;

/**
 * 供应商控制器
 */
@Controller
@RequestMapping("/page/admin/supplier/")
public class SupplierController {
    org.apache.log4j.Logger logger = Logger.getLogger("SupplierController.class") ;

    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ICollectionService collectionService;
    /**
     * 供应商分页
     * @param request
     * @param name
     * @param status
     * @return
     */
    @ApiOperation(value = "后台供应商", tags = "供应商列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int")
    })
    @RequestMapping("getPage")
    @ResponseBody
    public PageInfo<SupplierResp> getPage(HttpServletRequest request, String name, Integer status,Long userId){
        Result result = new Result();
        try {
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("status", status);
            map.put("userId", userId);
            PageHelper.orderBy("  priority desc");
            PageInfo<SupplierResp> page = supplierService.getPage(map, pageNum, pageSize);
            result.setData(page);
            result.setSuccess(true);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }
    @ApiOperation(value = "后台供应商", tags = "用户店铺列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "是否展示1：展示0：不展示", dataType = "int")
    })
    @RequestMapping("findUserSupplier")
    @ResponseBody
    public PageInfo<SupplierResp> findUserSupplier(HttpServletRequest request, Long userId){
        Result result = new Result();
        try {
            if(userId == null || userId == 0){
                return null;
            }
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("userId", userId);
            PageHelper.orderBy("  priority desc");
            PageInfo<SupplierResp> page = supplierService.getPage(map, pageNum, pageSize);
            result.setData(page);
            result.setSuccess(true);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }

    @ApiOperation(value = "后台供应商", tags = "查询店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺编号", dataType = "Long")
    })
    @RequestMapping("findById")
    @ResponseBody
    public SupplierResp findById(Long id){
        Result result = new Result();
        SupplierResp su = null;
        try {
            su = supplierService.findById(id);
            result.setSuccess(true);
            result.setData(su);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setData(su);
        }
        return su;
    }
    @ApiOperation(value = "后台供应商", tags = "修改店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "店铺名称", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "店铺地址", dataType = "String"),
            @ApiImplicitParam(name = "linkman", value = "联系人", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "联系电话", dataType = "String"),
            @ApiImplicitParam(name = "info", value = "店铺简介", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "file", dataType = "file"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "类别编号", dataType = "Long"),
            @ApiImplicitParam(name = "priority", value = "排序", dataType = "Long"),
    })
    @RequestMapping("doUpdate")
    //@ResponseBody
    public String doUpdate(HttpServletRequest request, Supplier su){
        Result result = new Result();
        try {
            su.setUpdateTime(new Date());
            supplierService.update(su);
            //店铺隐藏 用户个人中心收藏店铺删除
            if (su.getStatus()==0){
                HshCollection collection=new HshCollection();
                collection.setStoreId(su.getId());
                collectionService.delCollction(collection);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return "supplier/list";
    }
    @ApiOperation(value = "后台供应商", tags = "添加店铺")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "店铺名称", dataType = "String"),
            @ApiImplicitParam(name = "address", value = "店铺地址", dataType = "String"),
            @ApiImplicitParam(name = "linkman", value = "联系人", dataType = "String"),
            @ApiImplicitParam(name = "mobile", value = "联系电话", dataType = "String"),
            @ApiImplicitParam(name = "info", value = "店铺简介", dataType = "String"),
            @ApiImplicitParam(name = "file", value = "file", dataType = "file"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int"),
            @ApiImplicitParam(name = "typeId", value = "类别编号", dataType = "Long"),
            @ApiImplicitParam(name = "priority", value = "排序", dataType = "Long")
    })
    @RequestMapping("doAdd")
    //@ResponseBody
    public String doAdd(HttpServletRequest request, Supplier su){
        Result result = new Result();
        try {
            supplierService.insert(su);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("请求失败");
            result.setSuccess(false);
        }
        return "supplier/list";
    }

    @ApiOperation(value = "后台供应商", tags = "单个店铺状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "店铺编号", dataType = "long"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int")
    })
    @RequestMapping("updateStatus")
    @ResponseBody
    public Result updateStatus(Long id,Integer status){
        Result result = new Result();
        try {
            Supplier su = supplierService.findById(id);
            if(!ObjectUtils.isEmpty(su)){
                su.setStatus(status);
                supplierService.updateStatus(su);
                result.setMessage("操作成功");
                result.setSuccess(true);
            }else{
                result.setMessage("数据不存在，刷新重试");
                result.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("访问失败");
            result.setSuccess(true);
        }
        return result;
    }
    @ApiOperation(value = "后台供应商", tags = "批量店铺状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "店铺编号串逗号分隔", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int")
    })
    @RequestMapping("batchUpdateStatus")
    @ResponseBody
    public Result batchUpdateStatus(String ids,Integer status){
        Result result = new Result();
        try {
            if(!StringUtils.isBlank(ids)){
                String[] split = ids.split(",");
                List<Supplier> list = new ArrayList<>(split.length);
                for (int i = 0; i < split.length; i++) {
                    String id = split[i];
                    Supplier su = supplierService.findById(Long.valueOf(id));
                    if(!ObjectUtils.isEmpty(su)){
                        su.setStatus(status);
                       list.add(su);
                    }
                }
                supplierService.batchUpdateStatus(list,status);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("访问失败");
            result.setSuccess(false);
        }
        return result;
    }

}
