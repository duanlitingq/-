package com.yunchao.hsh.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.service.ISupplierCashWithdrawalApplicationService;
import com.yunchao.hsh.service.ISupplierOrderService;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.ParamUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/page/admin/supplierorder/")
public class SupplierOrderController {

    @Autowired
    private ISupplierOrderService supplierOrderService;
    @Autowired
    private ISupplierService supplierService;
    @Autowired
    private ISupplierCashWithdrawalApplicationService supplierCashWithdrawalApplicationService;



    @ApiOperation(value = "后台店铺订单", tags = "订单列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "订单状态：1：未接单2：已接单 3 已发货 4 已完成 5：取消订单，6：已取消 7：商家驳回", dataType = "int"),
    })
    @RequestMapping("getPage")
    @ResponseBody
    public  PageInfo<SupplierOrderResp> getPage(HttpServletRequest request,Long supplierId, Integer orderStatus,String orderNo,Integer payStatus){
        Result result = new Result();
        try {
            int pageNum = ParamUtils.getIntParameter(request, "pageNum", 1);
            int pageSize = ParamUtils.getIntParameter(request, "pageSize", 20);
            HashMap<String, Object> map = new HashMap<String, Object>();
            if(supplierId > 0){
                map.put("supplierId", supplierId);
            }
            if(orderStatus != null && orderStatus > 0){
                map.put("orderStatus", orderStatus);
            }
            if(payStatus != null && payStatus > 0){
                map.put("payStatus", payStatus);
            }
            if(orderNo != null){
                map.put("orderNo", orderNo.trim());
            }
            PageInfo<SupplierOrderResp> page = supplierOrderService.getPage(map, pageNum, pageSize);
            result.setData(page);
            result.setSuccess(true);
            return page;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }


    @ApiOperation(value = "后台店铺订单", tags = "订单查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单编号", dataType = "Long"),
    })
    @RequestMapping("findById")
    @ResponseBody
    public SupplierOrderResp findById(Long id){
        Result result = new Result();
        try {
            SupplierOrderResp su = supplierOrderService.findById(id);
            result.setSuccess(true);
            result.setData(su);
            return su;
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
        }
        return null;
    }

    @ApiOperation(value = "后台店铺订单", tags = "商家确认接单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单编号", dataType = "Long"),
    })
    @RequestMapping("confirmOrder")
    @ResponseBody
    public Result  confirmOrder(Long id,Integer orderStatus){
        Result result = new Result();
        try {
            SupplierOrderResp su = supplierOrderService.findById(id);
            if(ObjectUtils.isNotEmpty(su)){
                su.setOrderStatus(orderStatus);
                su.setConfirmTime(new Date());
                supplierOrderService.confirmOrder(su);
                result.setSuccess(true);
                result.setData(su);
                return result;
            }else{
                result.setSuccess(false);
                result.setMessage("订单数据异常，请刷新重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "后台店铺订单", tags = "商家确认取消订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "订单编号", dataType = "Long"),
    })
    @RequestMapping("cancelOrder")
    @ResponseBody
    public Result  cancelOrder(Long id){
        Result result = new Result();
        try {
            SupplierOrderResp su = supplierOrderService.findById(id);
            if(ObjectUtils.isNotEmpty(su)){
                supplierOrderService.cancelOrder(result,su);
                result.setS("",su);
                //PostMsgHttpContent.sendMessage(customer.getPhone(),"您的订单："+su.getOrderNo()+",商家已接单，正在发货");
            }else{
                result.setSuccess(false);
                result.setMessage("订单数据异常，请刷新重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("访问失败");
        }
        return result;
    }

    @ApiOperation(value = "提现统计", tags = "商家提现数据")
    @ApiImplicitParams({
    })
    @RequestMapping("sumAllOrder")
    @ResponseBody
    public Result sumAllOrder() {
        Result result = new Result();
        try {
            //统计所有已接单数据支付成功并且交易中或交易完成
            Double allOrderMoney = supplierOrderService.sumOrderPayTransactionFlow();
            //好物提现统计
            Double sumCashSupplierPirce = supplierCashWithdrawalApplicationService.sumCashSupplierPirce();
            result.setS("", allOrderMoney, sumCashSupplierPirce);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }
    @ApiOperation(value = "订单驳回", tags = "商家驳回接单数据")
    @ApiImplicitParams({
    })
    @RequestMapping("backOrder")
    @ResponseBody
    public Result backOrder(Long id){
        Result result = new Result();
        try {
            SupplierOrderResp supplierOrderResp = supplierOrderService.findById(id);
            if(supplierOrderResp != null){
                HashMap<String,Object> map = new HashMap<>();
                map.put("id",id);
                map.put("orderStatus",7);
                supplierOrderService.backOrder(map,supplierOrderResp);
            }else{
                result.setF("订单数据异常请刷新重试");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }


    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, Long supplierId, String orderNo, Integer payStatus, Integer orderStatus){
        SupplierResp supplier = supplierService.findById(supplierId);
        String path = request.getServletContext().getRealPath("/temp");
        File file = new File(path);
        if(file.exists()){
            file.mkdir();
        }
        String filePath = path + "/" + supplierId;
        String fileName = supplier.getName() + "订单数据.xls";
        String sheetName = supplier.getName() + "订单数据";
        try {
            //生成文件
            HSSFWorkbook wb = supplierOrderService.exportExcel(supplierId, filePath, sheetName, orderNo, payStatus, orderStatus);
            //响应到客户端
            setResponseHeader(response, fileName);
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
