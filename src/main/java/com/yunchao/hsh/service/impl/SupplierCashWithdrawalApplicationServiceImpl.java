package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dao.SupplierCashWithdrawalApplicationMapper;
import com.yunchao.hsh.dao.SupplierMapper;
import com.yunchao.hsh.dao.SupplierWalletLogMapper;
import com.yunchao.hsh.dao.SysUserMapper;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.dto.resp.SysUserResp;
import com.yunchao.hsh.model.SupplierCashWithdrawalApplication;
import com.yunchao.hsh.model.SupplierWalletLog;
import com.yunchao.hsh.service.ISupplierCashWithdrawalApplicationService;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.DateUtils;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.AbstractList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
@Transactional
/***
 * 供应商体现申请业务层实现类
 */
public class SupplierCashWithdrawalApplicationServiceImpl implements ISupplierCashWithdrawalApplicationService {

    @Autowired
    private SupplierCashWithdrawalApplicationMapper supplierCashWithdrawalApplicationMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierWalletLogMapper supplierWalletLogMapper;
    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public PageInfo<SupplierCashWithdrawalApplication> findPage(HashMap<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize) ;
        List<SupplierCashWithdrawalApplication> list = supplierCashWithdrawalApplicationMapper.findPage(map) ;
        if(list != null){
            int size = list.size();
            for (int i = 0; i < size; i++) {
                SupplierCashWithdrawalApplication sc = list.get(i);
                Long supplierId = sc.getSupplierId();
                SupplierResp supplierResp = supplierMapper.findById(supplierId);
                sc.setSupplierResp(supplierResp);
                Long operationUserId = sc.getOperationUserId();
                if(operationUserId != null && operationUserId > 0){
                    SysUserResp sysUserResp = sysUserMapper.findById(operationUserId);
                    sc.setOperationUserName(sysUserResp.getRealName());
                }
            }
        }
        PageInfo<SupplierCashWithdrawalApplication> pageInfo = new PageInfo<SupplierCashWithdrawalApplication>(list) ;
        return pageInfo;
    }

    @Override
    public Result insert(SupplierCashWithdrawalApplication supplierCashWithdrawalApplication) {
        Result result = new Result<>();
       try{
           supplierCashWithdrawalApplication.setCreateTime(new Date());
           supplierCashWithdrawalApplication.setRemark("发起提现申请时间："+ DateUtils.getDateFormat().format(new Date()));
           supplierCashWithdrawalApplication.setStatus(1);
           supplierCashWithdrawalApplicationMapper.insert(supplierCashWithdrawalApplication);
           Long id = supplierCashWithdrawalApplication.getId();
           //提现申请保存之后将店铺余额减去
           SupplierResp supplierResp = supplierMapper.findById(supplierCashWithdrawalApplication.getSupplierId());
           Double totalMoney = supplierResp.getTotalMoney();
           Double amountOfMoney = supplierCashWithdrawalApplication.getAmountOfMoney();
           supplierResp.setTotalMoney(BigDecimalUtils.subtract(totalMoney,amountOfMoney).doubleValue());
           supplierMapper.updateTotalMoney(supplierResp);
           SupplierWalletLog log = new SupplierWalletLog();
           log.setChangeMoney(-amountOfMoney);
           log.setColumn1(" ");
           log.setColumn2(0);
           log.setColumn3(0L);
           log.setCreateTime(new Date());
           log.setFrontUserId(0L);
           log.setInOrOut(2);
           log.setOrderId(id);
           log.setRemark("申请提现");
           log.setSupplierId(supplierResp.getId());
           supplierWalletLogMapper.insert(log);
           result.setS("",supplierCashWithdrawalApplication);
       }catch (Exception e){
           e.printStackTrace();
           result.setF("访问失败");
       }
        return result;
    }

    @Override
    public Result updateStatus(SupplierCashWithdrawalApplication supplierCashWithdrawalApplication) {
        Result result = new Result<>();
        try{
            supplierCashWithdrawalApplication.setCreateTime(new Date());
            supplierCashWithdrawalApplication.setRemark("修改状态时间："+ DateUtils.getDateFormat().format(new Date()));
            supplierCashWithdrawalApplication.setFinishTime(new Date());
            supplierCashWithdrawalApplicationMapper.updateStatus(supplierCashWithdrawalApplication);
            //审批提现申请
            Integer status = supplierCashWithdrawalApplication.getStatus();
            if(status == 3){
                SupplierResp supplierResp = supplierMapper.findById(supplierCashWithdrawalApplication.getSupplierId());
                Double amountOfMoney = supplierCashWithdrawalApplication.getAmountOfMoney();
                Double totalMoney = supplierResp.getTotalMoney();
                double doubleValue = BigDecimalUtils.add(amountOfMoney, totalMoney).doubleValue();
                supplierResp.setTotalMoney(doubleValue);
                supplierMapper.updateTotalMoney(supplierResp);
                SupplierWalletLog log = new SupplierWalletLog();
                log.setChangeMoney(+amountOfMoney);
                log.setColumn1(" ");
                log.setColumn2(0);
                log.setColumn3(0L);
                log.setCreateTime(new Date());
                log.setFrontUserId(0L);
                log.setInOrOut(1);
                log.setRemark("申请驳回");
                supplierWalletLogMapper.insert(log);
            }else{
                result.setF("访问成功");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setF("访问失败");
        }
        return result;
    }

    @Override
    public SupplierCashWithdrawalApplication getById(Long id) {
        return supplierCashWithdrawalApplicationMapper.findById(id);
    }

    @Override
    public double sumCashSupplierPirce() {
        Double supplierPrice = this.supplierCashWithdrawalApplicationMapper.sumCashSupplierPirce();
        if (supplierPrice == null ){
            return 0;
        }
        return supplierPrice;
    }
}
