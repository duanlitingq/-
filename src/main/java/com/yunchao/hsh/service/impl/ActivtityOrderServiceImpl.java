package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dao.*;
import com.yunchao.hsh.dto.resp.OrderDetailsResp;
import com.yunchao.hsh.dto.resp.OrderItemResp;
import com.yunchao.hsh.dto.resp.PageResultResp;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.IActivityOrderService;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.DateUtils;
import com.yunchao.hsh.utils.MnUtil;
import com.yunchao.hsh.utils.ObjectUtils;
import com.yunchao.hsh.utils.superdir.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: ActivtityOrderServiceImpl
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/9 11:26
 * @Version: 1.0
 */
@Service
@Transactional
public class ActivtityOrderServiceImpl extends BaseService implements IActivityOrderService {

    @Autowired
    private HshActivityOrderMapper activityOrderMapper;

    @Autowired
    private HshActivityMapper activityMapper;

    @Autowired
    private HshOrderShippingMapper orderShippingMapper;
    @Autowired
    private HshLogisticsMapper logisticsMapper;

    @Autowired
    private HshStationMapper stationMapper;

    @Override
    public Result getOrderPageList(int pageNum, int pageSize, String orderId, String activityId) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        HshActivityOrderExample example = new HshActivityOrderExample();
        HshActivityOrderExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotEmpty(orderId)) {
            criteria.andOrderIdEqualTo(orderId);
        } else if (StringUtils.isNotEmpty(activityId)) {
            criteria.andActivityIdEqualTo(Long.valueOf(activityId));
        }
        example.setOrderByClause("create_time desc");
        //订单成功的
//        criteria.andOrderStatusEqualTo((byte) 1);
        List<HshActivityOrder> orderList = this.activityOrderMapper.selectByExample(example);

        for (HshActivityOrder activityOrder : orderList) {
            HshOrderShippingExample orderShipping = new HshOrderShippingExample();
            orderShipping.createCriteria().andOrderIdEqualTo(activityOrder.getOrderId());
            HshOrderShipping shipping = this.orderShippingMapper.selectByExample(orderShipping).get(0);
            activityOrder.setCusNum3(shipping.getReceiverAddress());
        }
        PageInfo<HshActivityOrder> pageInfo = new PageInfo<HshActivityOrder>(orderList);
        return Result.ok(pageInfo);
    }

    @Override
    public Result updateGetNum(String orderId, SysUser adminUser) throws Exception {
        HshActivityOrder order = new HshActivityOrder();
        HshActivityOrder activityOrder = this.activityOrderMapper.selectByPrimaryKey(orderId);
        order.setOrderId(orderId);
        if (activityOrder != null) {
            if (activityOrder.getGetNum() >= 12) {
                return Result.build("领取次数已经达到上限!!!");
            }
            //增加领取次数
            order.setGetNum(activityOrder.getGetNum() + 1);
        } else {
            return Result.build("修改订单异常");
        }
        order.setLastName(adminUser.getRealName());
        //客服修改次数的人
        order.setCustomerId(adminUser.getId());
        //最后修改更新领取时间
        order.setUpdateTime(new Date());
        this.activityOrderMapper.updateByPrimaryKeySelective(order);
        return Result.ok(null);
    }


    @Override
    public HshActivityOrder selectByOrderId(String orderId) throws Exception {
        if (StringUtils.isNotEmpty(orderId)) {
            HshActivityOrder order = this.activityOrderMapper.selectByPrimaryKey(orderId);
            return order;
        }
        return null;
    }

    @Override
    public Result createOrder(Long activeId, Long customerId, Long logisticsId,Long welfareId ) throws Exception {

        // 添加获取活动价格
        HshActivity activity = this.activityMapper.selectByPrimaryKey(activeId);
        //取货地址
        HshLogistics logistics = logisticsMapper.selectByPrimaryKey(logisticsId);
        HshStation station = this.stationMapper.selectByPrimaryKey(logistics.getStationId());
        if (ObjectUtils.isEmpty(logistics)) {
            return Result.build("提货地址为空");
        }
        HshActivityOrder activityOrder = new HshActivityOrder();
        String orderNo = Constant.ACTIVE_ORDER + MnUtil.getCurTime() + CommonUtil.$getRandom(4, 1);
        activityOrder.setOrderId(orderNo);
        activityOrder.setActivityId(activeId);
        activityOrder.setUserId(customerId);
        //设置订单价格
        activityOrder.setCusNum1(String.valueOf(activity.getActivityPrice()));
        activityOrder.setReceiverMobile(logistics.getPhone());
        activityOrder.setReceiverName(logistics.getRealName());
        activityOrder.setCreateTime(new Date());
        activityOrder.setOrderStatus((byte) Integer.parseInt(Constant.UN_PAY_1));
        //cusNum2 添加成活动福利卡ID
        activityOrder.setCusNum2(welfareId);
        activityOrderMapper.insert(activityOrder);
        //创建提货地址记录
        HshOrderShipping hshOrderShipping = new HshOrderShipping();
        hshOrderShipping.setCreated(new Date());
        hshOrderShipping.setOrderId(orderNo);
        hshOrderShipping.setReceiverMobile(logistics.getPhone());
        hshOrderShipping.setReceiverName(logistics.getRealName());
        hshOrderShipping.setReceiverAddress(logistics.getAddress());
        hshOrderShipping.setReceiverZip(logistics.getPostCode());
        //驿站名称
        hshOrderShipping.setCusNum1(station.getName());
        orderShippingMapper.insert(hshOrderShipping);
        return Result.ok(orderNo);
    }

    @Override
    public Result updateById(HshActivityOrder record) throws Exception {
        activityOrderMapper.updateById(record);
        return Result.ok(null);
    }

    @Override
    public com.yunchao.hsh.utils.superdir.sub.Result selectOrderList(Long userId, int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        HshActivityOrderExample example = new HshActivityOrderExample();
        HshActivityOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        //已支付，待领取  1.待支付，2，已支付，3，已完成

        List<OrderItemResp> orderItemResps = this.activityOrderMapper.selectByPage(example);

        PageInfo<OrderItemResp> info = new PageInfo<>(orderItemResps);
        PageResultResp resp = new PageResultResp();
        resp.setPage(page);
        resp.setTotal(info.getPages());
        resp.setRows(orderItemResps);
        resp.setRecords(info.getTotal());
        Result result = new com.yunchao.hsh.utils.superdir.sub.Result();
        return ((com.yunchao.hsh.utils.superdir.sub.Result) result).setS(resp);
    }

    @Override
    public OrderDetailsResp getOrderDetail(String orderId) throws Exception {

        OrderDetailsResp resp = new OrderDetailsResp();
        HshActivityOrder activityOrder = this.activityOrderMapper.selectByPrimaryKey(orderId);

        HshOrderShippingExample example = new HshOrderShippingExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        //物流
        HshOrderShipping orderShipping = this.orderShippingMapper.selectByExample(example).get(0);

        resp.setReceiverMobile(orderShipping.getReceiverMobile());
        resp.setReceiverName(orderShipping.getReceiverName());
        resp.setReceiverAddress(orderShipping.getReceiverAddress());
        resp.setTradeNo(activityOrder.getTradeNo());
        resp.setPayTime(DateUtils.dateToStr(activityOrder.getPayTime()));

        HshActivity activity = this.activityMapper.selectByPrimaryKey(activityOrder.getActivityId());

        resp.setActivityName(activity.getActivityName());
        resp.setActivityPrice(String.valueOf(activity.getActivityPrice()));
        String activityImg = activity.getActivityItemImg();
        String[] split = activityImg.split(";");
        resp.setActivityImg(split[0].replace("\\","/"));
        //驿站名称
        if (StringUtils.isNotBlank(orderShipping.getCusNum1())){
            resp.setStationName(orderShipping.getCusNum1());
        }else{
            resp.setStationName("");
        }
        return resp;
    }

    @Override
    public void updateOrderStatus(String orderId) {
        HshActivityOrder record = new HshActivityOrder();
        record.setOrderId(orderId);
        record.setOrderStatus((byte) 3);
        this.activityOrderMapper.updateByPrimaryKeySelective(record);
    }
}
