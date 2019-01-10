package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dao.HshStationOrderMapper;
import com.yunchao.hsh.dto.req.StationOrderReq;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.HshStationOrder;
import com.yunchao.hsh.model.HshStationOrderExample;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.IStationOrderService;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.MnUtil;
import com.yunchao.hsh.utils.superdir.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StationOrderServiceImpl extends BaseService implements IStationOrderService {

    @Autowired
    private HshStationOrderMapper stationOrderMapper;

    @Override
    public PageInfo<HshStationOrder> findStationOrderList() {
        HshStationOrderExample example = new HshStationOrderExample();
        List<HshStationOrder> list = this.stationOrderMapper.selectByExample(example);
        PageInfo<HshStationOrder> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 生成驿站订单
     *
     * @param request
     * @param stationOrderReq
     * @param customer
     * @return
     * @throws Exception
     */
    @Override
    public Result insertStationOrder(HttpServletRequest request, StationOrderReq stationOrderReq, Customer customer) throws Exception {

        HshStationOrder record = new HshStationOrder();
        //生成订单
        String orderId = Constant.STATION_ORDER + MnUtil.getCurTime() + CommonUtil.$getRandom(4, 1);
        record.setOrderPrice(BigDecimalUtils.toBigDecimal(String.valueOf(stationOrderReq.getPrice()), 2));
        record.setOrderId(orderId);
        record.setCreateTime(new Date());
        //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭''
        record.setOrderStatus((byte) 1);
        //付款人
        record.setUserId(customer.getId());
        record.setPaymentType((byte) 2);
        //收款人
        record.setStationId(stationOrderReq.getId());

        this.stationOrderMapper.insertSelective(record);
        //返回订单号
        Map<String, Object> map = new HashMap<>();
        map.put("orderId", orderId);
        map.put("openId", customer.getOpenId());
        map.put("price", stationOrderReq.getPrice() * 100);
        return Result.ok(map);
    }

    @Override
    public HshStationOrder selectByOrderId(String orderId) {
        HshStationOrder stationOrder = null;
        HshStationOrderExample example = new HshStationOrderExample();
        example.createCriteria().andOrderIdEqualTo(orderId);
        List<HshStationOrder> hshStationOrders = this.stationOrderMapper.selectByExample(example);
        if(hshStationOrders != null && hshStationOrders.size() > 0){
            stationOrder = hshStationOrders.get(0);
        }
        return stationOrder;
    }

    @Override
    public void updateByOrderId(HshStationOrder stationOrder) {
        this.stationOrderMapper.updateByPrimaryKeySelective(stationOrder);
    }


}
