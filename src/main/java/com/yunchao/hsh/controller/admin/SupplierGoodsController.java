package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.model.HshCollection;
import com.yunchao.hsh.model.SupplierGoods;
import com.yunchao.hsh.service.ICollectionService;
import com.yunchao.hsh.service.ISupplierGoodsService;
import com.yunchao.hsh.utils.*;
import com.yunchao.hsh.utils.superdir.sub.Result;
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
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 店铺商品
 */
@Controller
@RequestMapping(value = "/page/admin/suppliergoods/")
public class SupplierGoodsController {
    org.apache.log4j.Logger logger = Logger.getLogger("SupplierController.class") ;
    @Autowired
    private ISupplierGoodsService supplierGoodsService;
    @Autowired
    private ICollectionService collectionService;

    @ApiOperation(value = "后台商品", tags = "商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态1展示0不展示", dataType = "int")
    })
    @RequestMapping(value = "getPage",method = RequestMethod.POST)
    @ResponseBody
    public PageInfo<SupplierGoodsResp> getPage(HttpServletRequest request, Long supplierId,String name, Integer status,Integer pageSize,Integer pageNum,Integer typeId){
        Result result = new Result();
        if(pageNum == null){
            pageNum = 1;
        }
        if(pageSize == null){
            pageSize = 20;
        }
        try {
            PageInfo<SupplierGoodsResp> page = new PageInfo<>();
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("name", name);
            map.put("status", status);
            if (typeId != null && typeId > 0){
                map.put("typeId",typeId);
            }
            PageHelper.orderBy("  priority desc,type_Id desc");
            if(supplierId != null && supplierId > 0){
                map.put("supplierId", supplierId);
                page = supplierGoodsService.findPage(map, pageNum, pageSize);
            }
            result.setData(page);
            result.setSuccess(true);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }

    @ApiOperation(value = "后台商品", tags = "商品查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品编号", dataType = "Long")
    })
    @RequestMapping("findById")
    @ResponseBody
    public SupplierGoodsResp findById(Long id){
        Result result = new Result();
        try {
            SupplierGoodsResp su = supplierGoodsService.findById(id);
            result.setSuccess(true);
            return  su;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }
    @ApiOperation(value = "后台商品", tags = "商品修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "info", value = "商品简介", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "1展示0不展示", dataType = "int"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "Long"),
            @ApiImplicitParam(name = "planPrice", value = "计划价格", dataType = "Long"),
            @ApiImplicitParam(name = "unit", value = "单位", dataType = "Long"),
            @ApiImplicitParam(name = "libraryNum", value = "库存", dataType = "Long"),
            @ApiImplicitParam(name = "productArea", value = "产地", dataType = "String"),
            @ApiImplicitParam(name = "productTime", value = "生产日期", dataType = "Date"),
            @ApiImplicitParam(name = "shelfLife", value = "保质期", dataType = "String"),
            @ApiImplicitParam(name = "specifications", value = "规格", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "排序", dataType = "int"),
    })
    @RequestMapping("doUpdate")
    //@ResponseBody
    public String  doUpdate(HttpServletRequest request, SupplierGoods su){
        Result result = new Result();
        try {
            request.setAttribute("id",su.getSupplierId());
            supplierGoodsService.update(su);
            //商品下架,删除用户商品收藏
            if (su.getStatus()==0){
                HshCollection collection=new HshCollection();
                collection.setItemId(su.getId());
                collectionService.delCollction(collection);
            }
            result.setData(su.getSupplierId());
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return "redirect:/page/admin/suppliergoods/list.html?id="+su.getSupplierId();
    }

    @ApiOperation(value = "后台商品", tags = "商品添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "name", value = "商品名称", dataType = "String"),
            @ApiImplicitParam(name = "info", value = "商品简介", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "1展示0不展示", dataType = "int"),
            @ApiImplicitParam(name = "file", value = "附件", dataType = "Long"),
            @ApiImplicitParam(name = "planPrice", value = "计划价格", dataType = "Long"),
            @ApiImplicitParam(name = "unit", value = "单位", dataType = "Long"),
            @ApiImplicitParam(name = "libraryNum", value = "库存", dataType = "Long"),
            @ApiImplicitParam(name = "productArea", value = "产地", dataType = "String"),
            @ApiImplicitParam(name = "productTime", value = "生产日期", dataType = "Date"),
            @ApiImplicitParam(name = "shelfLife", value = "保质期", dataType = "String"),
            @ApiImplicitParam(name = "specifications", value = "规格", dataType = "String"),
            @ApiImplicitParam(name = "priority", value = "排序", dataType = "int"),
    })
    @RequestMapping(value = "doAdd",method = RequestMethod.POST)
    //@ResponseBody
    public String doAdd(HttpServletRequest request, SupplierGoods su){
        Result result = new Result();
        try {
            supplierGoodsService.insert(su);
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("请求失败");
            result.setSuccess(false);
        }
        return "redirect:/page/admin/suppliergoods/list.html?id="+su.getSupplierId();
    }

    @ApiOperation(value = "后台商品", tags = "单个商品状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "商品编号", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "商品状态1展示0不展示", dataType = "int"),
    })
    @RequestMapping("updateStatus")
    @ResponseBody
    public Result updateStatus(Long id,Integer status){
        Result result = new Result();
        try {
            SupplierGoods su = supplierGoodsService.findById(id);
            if(!ObjectUtils.isEmpty(su)){
                su.setStatus(status);
                supplierGoodsService.updateStatus(su);
                result.setMessage("操作成功");
                result.setSuccess(true);
            }else{
                result.setMessage("数据不存在，刷新重试");
                result.setSuccess(false);
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("访问失败");
            result.setSuccess(false);
        }
        return result;
    }
    @ApiOperation(value = "后台商品", tags = "批量商品状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "商品编号", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "商品状态1展示0不展示", dataType = "int"),
    })
    @RequestMapping("batchUpdateStatus")
    @ResponseBody
    public Result batchUpdateStatus(String ids,Integer status){
        Result result = new Result();
        try {
            if(!StringUtils.isBlank(ids)){
                String[] split = ids.split(",");
                List<SupplierGoods> list = new ArrayList<>(split.length);
                for (int i = 0; i < split.length; i++) {
                    String id = split[i];
                    SupplierGoods su = supplierGoodsService.findById(Long.valueOf(id));
                    if(!ObjectUtils.isEmpty(su)){
                        su.setStatus(status);
                        list.add(su);
                    }
                }
                supplierGoodsService.batchUpdateStatus(list);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("访问失败");
            result.setSuccess(false);
        }
        return result;
    }


    /**
     * 上传excel
     * @param excelFile
     * @param supplierId
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping("ajaxUpload")
    @ResponseBody
    public Map<String,Object> readExcel(@RequestParam(value = "file") MultipartFile excelFile, @RequestParam(value="supplierId") Long supplierId, HttpServletRequest req, HttpServletResponse resp){
        Map<String, Object> param = new HashMap<String, Object>();
        StringBuffer sb=new StringBuffer();
        List<SupplierGoods> list=new ArrayList<>();
        int isWr=0;
        try {
            List<String[]> rowList = POIUtils.readExcel(excelFile);
            for(int i=0;i<rowList.size();i++){
                String[] row = rowList.get(i);
                if(null==row[0]||"".equals(row[0])||
                        null==row[1]||"".equals(row[1])||
                        null==row[2]||"".equals(row[2])||
                        null==row[3]||"".equals(row[3])||
                        null==row[4]||"".equals(row[4])||
                        null==row[5]||"".equals(row[5])||
                        null==row[6]||"".equals(row[6])||
                        null==row[7]||"".equals(row[7])||
                        null==row[8]||"".equals(row[8])||
                        null==row[9]||"".equals(row[9])||
                        null==row[10]||"".equals(row[10])||
                        null==row[11]||"".equals(row[11])||
                        null==row[12]||"".equals(row[12])
                ){
                    sb.append("第"+(i+2)+"行有错误\n");
                    isWr=1;
                    break;
                }
                SupplierGoods sg = new SupplierGoods();
                sg.setSupplierId(supplierId);
                sg.setName(row[0]);//商品名
                sg.setPlanPrice(Double.valueOf(row[1]));//价格
                sg.setSpecifications(row[2]);//规格
                sg.setUnit(Integer.parseInt(row[3]));//单位
                sg.setLibraryNum(Integer.parseInt(row[4]));//库存
                sg.setProductArea(row[5]);//产地
                sg.setProductTime(DateUtils.StrToDate(row[6]));//生产日期
                sg.setShelfLife(row[7]);//保质期
                sg.setPriority(Integer.parseInt(row[8]));//排序
                sg.setStatus(Integer.parseInt(row[9]));//状态
                sg.setInfo(row[10]);//简介
                sg.setInfo(row[11]);//类别
                sg.setImgs(row[12]);//图片
                list.add(sg);
                sb.append("第"+i+2+"行上传成功\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            param.put("dataStatus",2);
            param.put("sb","上传中断！\n"+sb);
        }
        if(isWr==0){
            for(SupplierGoods sg:list){
                supplierGoodsService.insert(sg);//
            }
            sb.append("上传成功");
        }
        param.put("dataStatus", 1);
        param.put("sb",sb);
        return param;
    }







    /**   下载模板
     *  @param request
     *  @param response
     * */
    @RequestMapping("downloadTmpl")
    public void downloadTmpl(HttpServletRequest request,HttpServletResponse response){


        // 下载本地文件
        String fileName = "1.xlsx".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = null;// 文件的存放路径
        String path = request.getSession().getServletContext().getRealPath("/page/style")+"/"+fileName;
        path = path.replace("\\", "/");
        try {
            inStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
            inStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
