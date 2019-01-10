package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.dao.SupplierCashWithdrawalApplicationMapper;
import com.yunchao.hsh.dao.SupplierMapper;
import com.yunchao.hsh.dao.SupplierOrderMapper;
import com.yunchao.hsh.dto.resp.MenuResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.Supplier;
import com.yunchao.hsh.model.SupplierCashWithdrawalApplication;
import com.yunchao.hsh.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SupplierServiceImpl implements ISupplierService {

    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierCashWithdrawalApplicationMapper supplierCashWithdrawalApplicationMapper;
    @Autowired
    private SupplierOrderMapper supplierOrderMapper;

    @Override
    public PageInfo<SupplierResp> getPage(HashMap<String, Object> map, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SupplierResp> list = supplierMapper.findPage(map) ;
        PageInfo<SupplierResp> pageInfo = new PageInfo<SupplierResp>(list) ;
        return pageInfo;
    }

    public SupplierResp findById(Long id) {
        SupplierResp supplierResp = null;
        if (id != null && id > 0) {
            supplierResp = supplierMapper.findById(id);
            //获取店铺提现成功总金额
            HashMap<String, Object> map = new HashMap<>();
            map.put("supplierId", supplierResp.getId());
            map.put("status", 2);
            Double aDouble = supplierCashWithdrawalApplicationMapper.sumAllApplication(map);
            supplierResp.setOutMoney(aDouble == null ? 0 : aDouble);
            //统计一共多少已支付订单
            HashMap<String, Object> mapOrder = new HashMap<>();
            mapOrder.put("supplierId", supplierResp.getId());
            mapOrder.put("orderStatus", 3);
            Long allOrderNum = supplierOrderMapper.countAllOrderNum(mapOrder);
            supplierResp.setOrderNum(allOrderNum == null ? 0 : allOrderNum);
        }
        return supplierResp;
    }


    public void insert(Supplier su){
        su.setBrowsNum(0);
        su.setColumn1("");
        su.setColumn2(0);
        su.setColumn3(0l);
        su.setCreateTime(new Date());
        su.setTotalMoney(0.0);
        su.setUpdateTime(new Date());
        supplierMapper.insert(su);
    }


    public void update(Supplier su){
        supplierMapper.update(su);
    }

    public void updateStatus(Supplier su){
        su.setUpdateTime(new Date());
        supplierMapper.updateStatus(su);
    }

    public void batchUpdateStatus(List<Supplier> list,Integer status){
        int len = list.size();
        for (int i = 0; i < len; i++) {
            Supplier su = list.get(i);
            su.setStatus(status);
            su.setUpdateTime(new Date());
            supplierMapper.updateStatus(su);
        }
    }

    @Override
    public void updateTotalMoney(SupplierResp supplierResp) {
        supplierMapper.updateTotalMoney(supplierResp);
    }
}
