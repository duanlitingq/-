package com.yunchao.hsh.controller.admin;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.SupplierCashWithdrawalApplication;
import com.yunchao.hsh.model.SysUser;
import com.yunchao.hsh.service.ISupplierCashWithdrawalApplicationService;
import com.yunchao.hsh.service.ISupplierService;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
import com.yunchao.hsh.utils.Constant;
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
 * 供应商提现申请控制器
 */
@Controller
@RequestMapping(value = "/page/admin/SupplierCashWithdrawalApplication")
public class SupplierCashWithdrawalApplicationController {


    @Autowired
    private ISupplierCashWithdrawalApplicationService supplierCashWithdrawalApplicationService;
    @Autowired
    private ISupplierService supplierService;


    @ApiOperation(value = "店铺提现申请", tags = "申请列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "pageSize", value = "每页展示数量", dataType = "int"),
            @ApiImplicitParam(name = "pageNum", value = "页码", dataType = "int"),
    })
    @RequestMapping(value = "findPage", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result findPage(Long supplierId, Integer pageNum, Integer pageSize, Integer status) {
        Result result = new Result();
        try {
            if (pageSize == null) {
                pageSize = 20;
            }
            if (pageNum == null) {
                pageNum = 1;
            }
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (status != null && status > 0) {
                map.put("status", status);
            }
            if (supplierId != null && supplierId > 0) {
                map.put("supplierId", supplierId);
            }
            PageHelper.orderBy("  create_time desc ");
            PageInfo<SupplierCashWithdrawalApplication> page = supplierCashWithdrawalApplicationService.findPage(map, pageNum, pageSize);
            result.setS("", page);
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("");
        }
        return result;
    }

    @ApiOperation(value = "店铺提现申请", tags = "添加申请")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "supplierId", value = "店铺编号", dataType = "Long"),
            @ApiImplicitParam(name = "receiveName", value = "收款人姓名", dataType = "String"),
            @ApiImplicitParam(name = "bankName", value = "银行名称", dataType = "String"),
            @ApiImplicitParam(name = "bankCard", value = "银行卡", dataType = "String"),
            @ApiImplicitParam(name = "phone", value = "提现电话", dataType = "String"),
            @ApiImplicitParam(name = "amountOfMoney", value = "提现金额", dataType = "double")
    })
    @RequestMapping("doAdd")
    @ResponseBody
    public Result doAdd(SupplierCashWithdrawalApplication apply) {
        Result result = new Result();
       try{
           SupplierResp supplierResp = supplierService.findById(apply.getSupplierId());
           if(supplierResp != null){
               if(apply.getAmountOfMoney() > supplierResp.getTotalMoney()){
                   result.setData(2);
                   return result.setF("余额不足");
               }else{
                   supplierCashWithdrawalApplicationService.insert(apply);
                   result.setS("",apply);
                   ALiYunSendMsgUtils.sendMsg(apply.getPhone(), com.yunchao.hsh.constant.Constant.WITHDRAWAL_MSG,apply.getAmountOfMoney()+"");
               }
           }else{
               return result.setF("店铺数据异常，请重新登录重试");
           }
       }catch (Exception e){
           e.printStackTrace();
           result.setF("系统异常，请刷新重试");
       }
        return result;
    }

    /**
     * 审批提现申请
     *
     * @param id
     * @param status
     * @return
     */
    @ApiOperation(value = "店铺提现申请", tags = "申请审批")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "申请编号", dataType = "Long"),
            @ApiImplicitParam(name = "status", value = "状态 1：待审 2通过 3：驳回", dataType = "integer"),
    })
    @RequestMapping("approvalApply")
    @ResponseBody
    public Result approvalApply(HttpServletRequest request,Long id, Integer status) {
        Result result = new Result();
        try {
            SupplierCashWithdrawalApplication scwa =  supplierCashWithdrawalApplicationService.getById(id);
            scwa.setStatus(status);
            SysUser attribute = (SysUser) request.getSession().getAttribute(Constant.LOGIN_USER);
            scwa.setOperationUserId(attribute.getId());
            supplierCashWithdrawalApplicationService.updateStatus(scwa);
            result.setS("","操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }


}
