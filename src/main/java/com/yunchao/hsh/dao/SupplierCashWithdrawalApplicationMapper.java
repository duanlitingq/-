package com.yunchao.hsh.dao;

import com.yunchao.hsh.model.SupplierCashWithdrawalApplication;

import java.util.HashMap;
import java.util.List;

public interface SupplierCashWithdrawalApplicationMapper {
    /***
     *
     * @param supplierCashWithdrawalApplication
     */
    void updateStatus(SupplierCashWithdrawalApplication supplierCashWithdrawalApplication);

    /**
     * 保存
     * @param supplierCashWithdrawalApplication
     */
    void insert(SupplierCashWithdrawalApplication supplierCashWithdrawalApplication);

    /**
     * 列表
     * @param map
     * @return
     */
    List<SupplierCashWithdrawalApplication> findPage(HashMap<String, Object> map);

    /**
     * 统计提现总金额
     * @param map
     * @return
     */
    Double sumAllApplication(HashMap<String, Object> map);

    /**
     * 获取提现申请对象
     * @param id
     * @return
     */
    SupplierCashWithdrawalApplication findById(Long id);


    Double sumCashSupplierPirce();
}
