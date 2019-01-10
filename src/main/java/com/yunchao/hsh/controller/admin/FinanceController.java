package com.yunchao.hsh.controller.admin;

import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.service.*;
import com.yunchao.hsh.utils.ArithUtil;
import com.yunchao.hsh.utils.superdir.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 财务 、、  平台流水
 *
 * @Authher:Zhai Qing
 * @Date: 2018/12/8 17:24
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Api
@RequestMapping(value = {"/page/admin/finance/"})
@Controller
public class FinanceController extends BaseController {

    @Autowired
    private ISupplierOrderService supplierOrderService;

    @Autowired
    private IWalletService walletService;

    @Autowired
    private ICustomerWithdrawalsService customerWithdrawalsService;

    @Autowired
    private ISupplierCashWithdrawalApplicationService supplierCashWithdrawalApplicationService;

    /**
     * 财务平台总流水统计
     *
     * @return
     */
    @RequestMapping(value = {"platformCount"})
    @ResponseBody
    public Result countPlatformTransactionFlow() {


        //平台总收入  =  好物成功交易的流水 + 用户总余额（其中用户总余额不随用户提现余额而变化)
        //好物成功交易的流水
        double supplierPayOrderFlow = this.supplierOrderService.sumOrderPayTransactionFlow();
        //用户总余额（其中用户总余额不随用户提现余额而变化)
        //用户现在有余额
        double currentCustomerPrice = this.walletService.sumCustomerPrice();
        //获取用户已经提现的余额
        double cashPrice = this.customerWithdrawalsService.sumCashCustomerPirce();
        //用户总余额
        double customerCountPrice = ArithUtil.add(currentCustomerPrice, cashPrice);
        //平台总收入
        double platformCountPrice = ArithUtil.add(customerCountPrice, supplierPayOrderFlow);


        /**=============================================================**/
        //已确认提现 = 厂商提现数 + 小程序提现数

        //厂商提现数
        double cashSupplierPirce = this.supplierCashWithdrawalApplicationService.sumCashSupplierPirce();
        double confirmCountPrice = ArithUtil.add(cashSupplierPirce, cashPrice);

        /**=============================================================**/

        //总订单
        Long supplierOrderNum = this.supplierOrderService.countPayOrderNum();

        /**=============================================================**/

        Map<String, Object> map = new HashMap<>();
        //平台总收入
        map.put("platformCountPrice", platformCountPrice);
        //已提现数
        map.put("confirmCountPrice", confirmCountPrice);
        //订单总数
        map.put("orderCount", supplierOrderNum);
        return Result.ok(map);
    }

}
