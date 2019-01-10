package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierTypeResp;
import com.yunchao.hsh.model.SupplierType;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.service.ISupplierTypeService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 供应商控制器
 */
@Controller
@RequestMapping("/page/admin/suppliertype/")
public class SupplierTypeController {
    Logger logger = Logger.getLogger("SupplierTypeController.class") ;

    @Autowired
    private ISupplierTypeService supplierTypeService;


    @ApiOperation(value = "后台店铺类别", tags = "类别列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "类别名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "1展示0不展示", dataType = "int")
    })
    @RequestMapping("getPage")
    @ResponseBody
    public PageInfo<SupplierTypeResp> getPage(HttpServletRequest request,String name,Integer status){
        Result result = new Result();
        try {
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("status", status);
            PageHelper.orderBy(" sort desc ");
            PageInfo<SupplierTypeResp> page = supplierTypeService.getPage(map, pageNum, pageSize);
            result.setData(page);
            result.setSuccess(true);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }

    @ApiOperation(value = "后台店铺类别", tags = "根据状态检索列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "1展示0不展示", dataType = "int")
    })
    @RequestMapping("findList")
    @ResponseBody
    public List<SupplierTypeResp> findList(Integer status){
        Result result = new Result();
        try {
            List<SupplierTypeResp> su = supplierTypeService.findList(status);
            return su;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }
    @ApiOperation(value = "后台店铺类别", tags = "类别查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别编号", dataType = "Long")
    })
    @RequestMapping("findById")
    @ResponseBody
    public SupplierTypeResp findById(Long id){
        Result result = new Result();
        try {
            SupplierTypeResp su = supplierTypeService.findById(id);
            result.setSuccess(true);
            result.setData(su);
            return su;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }

    @ApiOperation(value = "后台店铺类别", tags = "类别修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别编号", dataType = "Long"),
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "类别名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "int")
    })
    @RequestMapping("doUpdate")
    @ResponseBody
    public Result doUpdate(HttpServletRequest request, SupplierType su){
        Result result = new Result();
        try {
            SupplierTypeResp st = supplierTypeService.findByName(su.getName(),su.getId());
            if(ObjectUtils.isEmpty(st)){
                supplierTypeService.update(su);
                result.setSuccess(true);
            }else{
                result.setSuccess(false);
                result.setMessage("数据重复");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return result;
    }


    @ApiOperation(value = "后台店铺类别", tags = "类别添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "sort", value = "排序", dataType = "int"),
            @ApiImplicitParam(name = "name", value = "类别名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态", dataType = "int")
    })
    @RequestMapping("doAdd")
    @ResponseBody
    public Result doAdd(SupplierType su){
        Result result = new Result();
        try {
            SupplierTypeResp st = supplierTypeService.findByName(su.getName(),null);
            if(ObjectUtils.isEmpty(st)){
                supplierTypeService.insert(su);
                result.setSuccess(true);
            }else{
                result.setSuccess(false);
                result.setMessage("数据重复");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("请求失败");
            result.setSuccess(false);
        }
        return result;
    }

    @ApiOperation(value = "后台店铺类别", tags = "类别单个状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "类别编号", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int")
    })
    @RequestMapping("updateStatus")
    @ResponseBody
    public Result updateStatus(Long id,Integer status){
        Result result = new Result();
        try {
            SupplierTypeResp su = supplierTypeService.findById(id);
            if(!ObjectUtils.isEmpty(su)){
                su.setStatus(status);
                supplierTypeService.updateStatus(su);
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
    @ApiOperation(value = "后台店铺类别", tags = "类别批量状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "类别编号", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int")
    })
    @RequestMapping("batchUpdateStatus")
    @ResponseBody
    public Result batchUpdateStatus(String ids,Integer status){
        Result result = new Result();
        try {
            if(!StringUtils.isBlank(ids)){
                String[] split = ids.split(",");
                List<SupplierTypeResp> list = new ArrayList<SupplierTypeResp>(split.length);
                for (int i = 0; i < split.length; i++) {
                    String id = split[i];
                    SupplierTypeResp su = supplierTypeService.findById(Long.valueOf(id));
                    if(!ObjectUtils.isEmpty(su)){
                       list.add(su);
                    }
                }
                supplierTypeService.batchUpdateStatus(list,status);
                result.setS("","操作成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("访问失败");
            result.setSuccess(true);
        }
        return result;
    }

}
