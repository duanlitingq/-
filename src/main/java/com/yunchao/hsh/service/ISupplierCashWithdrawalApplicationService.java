package com.yunchao.hsh.service;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.model.SupplierCashWithdrawalApplication;
import com.yunchao.hsh.utils.superdir.sub.Result;

import java.util.HashMap;

/**\
 * 供应商提现申请业务接口
 */
public interface ISupplierCashWithdrawalApplicationService {


    /**提现记录列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<SupplierCashWithdrawalApplication> findPage(HashMap<String, Object> map, Integer pageNum, Integer pageSize);

    /**保存提现申请
     * @param supplierCashWithdrawalApplication
     * @return
     */
    Result insert(SupplierCashWithdrawalApplication supplierCashWithdrawalApplication);

    /**状态修改
     * @param supplierCashWithdrawalApplication
     * @return
     */
    Result updateStatus(SupplierCashWithdrawalApplication supplierCashWithdrawalApplication);

    /**
     * 获取提现申请
     * @param id
     * @return
     */
    SupplierCashWithdrawalApplication getById(Long id);

    double sumCashSupplierPirce();

}


