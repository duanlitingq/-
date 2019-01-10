package com.yunchao.hsh.service.impl;

import com.yunchao.hsh.dao.HshActivityOrderGetLogMapper;
import com.yunchao.hsh.dao.HshActivityOrderMapper;
import com.yunchao.hsh.dao.HshOrderShippingMapper;
import com.yunchao.hsh.dto.resp.OrderGetLogResp;
import com.yunchao.hsh.model.HshActivityOrder;
import com.yunchao.hsh.model.HshActivityOrderGetLog;
import com.yunchao.hsh.model.HshOrderShipping;
import com.yunchao.hsh.model.HshOrderShippingExample;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.IHshActivityOrderGetLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 活动领取日志
 *
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 18:27
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Service
@Transactional
public class HshActivityOrderGetLogServiceImpl extends BaseService implements IHshActivityOrderGetLogService {

    @Autowired
    private HshActivityOrderGetLogMapper activityOrderGetLogMapper;

    @Autowired
    private HshActivityOrderMapper activityOrderMapper;

    @Autowired
    private HshOrderShippingMapper orderShippingMapper;


    @Override
    public List<HshActivityOrderGetLog> findByEndTimeEqules() {
        List<HshActivityOrderGetLog> list = this.activityOrderGetLogMapper.findByEndTimeEqules();
        return list;
    }

    @Override
    public void updateByOrderIdStatus(HshActivityOrderGetLog orderGetLog) {
        this.activityOrderGetLogMapper.updateByOrderIdStatus(orderGetLog);
    }

    //同步方法生成12 条领取记录
    @Override
    public synchronized void inserBatchOrderGetLog(String orderNo) {
        //根据订单生成12条记录
        HshActivityOrder activityOrder = this.activityOrderMapper.selectByPrimaryKey(orderNo);
        if (activityOrder != null) {
            //支付成功的时间 //  领取时间 = 支付成功时间 + 1个月
            Date payTime = activityOrder.getPayTime();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(payTime);
            calendar.add(Calendar.MONTH, 1);
            //领取截止时间
            Date getItemDate = calendar.getTime();

            List<HshActivityOrderGetLog> listLog = new ArrayList<>();
            for (int i = 0; i < 12; i++) {
                HshActivityOrderGetLog getLog = new HshActivityOrderGetLog();
                if (i == 0) {
                    //如果是第一条记录是确定领取状态 0 确定领取、1、已领取 、2待领取
                    getLog.setStatus((byte) 0);
                } else {
                    getLog.setStatus((byte) 2);
                }
                getLog.setOrderId(orderNo);
                calendar.setTime(getItemDate);
                calendar.add(Calendar.MONTH, i);
                getLog.setEndTime(calendar.getTime());
                listLog.add(getLog);
            }
            this.activityOrderGetLogMapper.inserBatchOrderGetLog(listLog);
        }
    }

    @Override
    public List<OrderGetLogResp> selectByOrderId(String orderId) throws Exception {
        HshOrderShippingExample example = new HshOrderShippingExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        HshOrderShipping hshOrderShipping = orderShippingMapper.selectByExample(example).get(0);
        List<OrderGetLogResp> list = this.activityOrderGetLogMapper.selectByOrderId(orderId);
        for (OrderGetLogResp orderGetLogResp : list) {
            orderGetLogResp.setShoppingAddress(hshOrderShipping.getReceiverAddress());
        }
        return list;
    }

    @Override
    public void updateByLogIdStatus(Long logId) throws Exception {
        HshActivityOrderGetLog getLog = new HshActivityOrderGetLog();
        //已领取
        getLog.setLogId(logId);
        getLog.setStatus((byte) 1);
        this.activityOrderGetLogMapper.updateByLogIdStatus(getLog);

    }

    @Override
    public List<String> selectByOrderIdGroupBy() {
        List<String> orderStr = this.activityOrderGetLogMapper.findByOrderIdGroupBy();
        log.info("返回的活动订单ID集合为=====》{}", orderStr);
        return orderStr;
    }

    @Override
    public Byte sumByOrderIdStatus(String orderId) {
        Byte sumStatus = this.activityOrderGetLogMapper.sumByOrderIdStatus(orderId);
        return sumStatus;

    }

}
