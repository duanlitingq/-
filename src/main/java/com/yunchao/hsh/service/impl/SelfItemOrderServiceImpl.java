package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dao.*;
import com.yunchao.hsh.dto.resp.*;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.BaseService;
import com.yunchao.hsh.service.ISelfItemOrderService;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.CommonUtil;
import com.yunchao.hsh.utils.DateUtils;
import com.yunchao.hsh.utils.MnUtil;
import com.yunchao.hsh.utils.superdir.Result;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 自营订单信息
 */
@Service
@Transactional
public class SelfItemOrderServiceImpl extends BaseService implements ISelfItemOrderService {

    @Autowired
    private HshSelfOrderMapper hshSelfOrderMapper;

    @Autowired
    private HshSelfOrderDetailMapper hshSelfOrderDetailMapper;

    @Autowired
    private HshOrderShippingMapper hshOrderShippingMapper;

    @Autowired
    private HshSelfItemMapper itemMapper;

    @Autowired
    private HshLogisticsMapper logisticsMapper;

    @Autowired
    private CustomerMapper customerMapper;

    @Autowired
    private CustomerWalletLogMapper walletLogMapper;

    @Autowired
    private HshAnnounMapper announMapper;

    @Autowired
    private WalletMapper walletMapper;

    @Override
    public Result getPage(int pageNum, int pageSize, String orderId) {
        HshSelfOrderExample example = new HshSelfOrderExample();
        if (StringUtils.isNotBlank(orderId)) {
            example.createCriteria().andOrderIdEqualTo(orderId);
        }
        //订单列表 + 商品列表
        PageHelper.startPage(pageNum, pageSize);
        List<OrderItemListResp> orderList = this.hshSelfOrderMapper.findPage(example);
        PageInfo<OrderItemListResp> pageInfo = new PageInfo<>(orderList);
        return Result.ok(pageInfo);
    }

    @Override
    public Result updateOrderStatus(String orderId, String status) {
        HshSelfOrder record = new HshSelfOrder();
        record.setOrderId(orderId);
        //1、未付款，2、已付款(待发货)，3、未发货，4、已发货，5、交易成功，6、交易关闭'',',
        record.setStatus(Byte.valueOf(status));
        //发货时间
        record.setConsignTime(new Date());
        this.hshSelfOrderMapper.updateByPrimaryKeySelective(record);
        return Result.ok(null);
    }

    /**
     * 自营商品订单生成
     *
     * @param itemId      　商品ID
     * @param customerId  用户ID
     * @param logisticsId 　提货驿站ID
     * @return
     */
    @Override
    public Result createOrder(Long itemId, Long customerId, Long logisticsId) {
        //生成自营订单号ZY_20181117140056865
        //结算订单TODO  查看用户是积分足够商品
        Wallet wallet = this.walletMapper.findByCustomerId(customerId);
        HshSelfItem item = this.itemMapper.selectByPrimaryKey(itemId);
        HshLogistics logistics = this.logisticsMapper.selectByPrimaryKey(logisticsId);
        if (wallet.getScore() >= item.getItemIntegral()) {
            log.info("积分充足");
            String orderNo = Constant.ZY_ORDER + +MnUtil.getCurTime() + CommonUtil.$getRandom(4, 1);

            //订单
            HshSelfOrder order = new HshSelfOrder();
            order.setOrderId(orderNo);
            order.setUserId(String.valueOf(customerId));
            //根据商品ID查询价格

            order.setPayment(BigDecimalUtils.obj2big(item.getItemIntegral()));
            order.setPaymentType((byte) 1);
            order.setPostFee("0");
            order.setCreateTime(new Date());
            order.setPaymentTime(new Date());
            //待发货
            order.setStatus((byte) 3);
            this.hshSelfOrderMapper.insertSelective(order);
            log.info("积分商品生成订单=========={}", orderNo);

            //订单详情
            HshSelfOrderDetail orderDetail = new HshSelfOrderDetail();
            orderDetail.setOrderId(orderNo);
            orderDetail.setItemId(itemId);
            orderDetail.setItemName(item.getItemName());
            orderDetail.setNum(1L);
            orderDetail.setItemRebate(item.getItemIntegral());
            orderDetail.setItemPrice(BigDecimalUtils.obj2big(item.getItemIntegral()));
            //商品图片
            String img = item.getItemImg().split(";")[0];
            orderDetail.setItemImg(img);
            //总积分
            orderDetail.setItemRebate(item.getItemIntegral());
            orderDetail.setTotalRebate(item.getItemIntegral());
            //总金额
            orderDetail.setTotalCount(BigDecimalUtils.obj2big(item.getItemIntegral()));
            this.hshSelfOrderDetailMapper.insertSelective(orderDetail);


            //持久化订单收货人
            HshOrderShipping shipping = new HshOrderShipping();
            shipping.setOrderId(orderNo);
            shipping.setReceiverName(logistics.getRealName());
            shipping.setReceiverMobile(logistics.getPhone());
            shipping.setReceiverAddress(logistics.getAddress());
            shipping.setCreated(new Date());
            this.hshOrderShippingMapper.insertSelective(shipping);

            //结算 当前积分 - 商品积分
            Double score = wallet.getScore();
            //商品积分
            Double itemIntegral = item.getItemIntegral().doubleValue();
            Double overScore = score - itemIntegral;
            //结算完成后的积分
            wallet.setScore(overScore);
            this.walletMapper.updateByCustomerIdWallet(overScore, customerId);
            //插入消费日志
            CustomerWalletLog record = new CustomerWalletLog();
            record.setAmount(itemIntegral);
            //消费方式（1余额，2微信，3积分   4系统 ）',
            record.setPayMode(3);
            //(1支出;2收入;3提现;4充值)',
            record.setType(1);
            record.setCustomerId(customerId);
            record.setCreateDate(new Date());
            record.setStatus(1);
            record.setCurrentBalance(overScore);
            record.setUuid(orderNo);
            record.setRemark(Constant.JI_FEN_DUI_HUAN);
            this.walletLogMapper.insert(record);

            ///插入兑换消息通知
            HshAnnoun announ = new HshAnnoun();
            announ.setUserName(logistics.getRealName());
            announ.setItemName(item.getItemName());
            announ.setCreateTime(new Date());
            this.announMapper.insertSelective(announ);

            log.info("积分商品订单成功");
            return Result.ok(null);
        } else {
            log.info("积分商品订单生成失败=====================");
            return Result.build("商品积分生成失败");
        }
    }

    @Override
    public Result selectByCustomerIdOrderList(Long customerId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        HshSelfOrderExample example = new HshSelfOrderExample();
        HshSelfOrderExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(String.valueOf(customerId));
        List<SelfOrderDetailResp> detailResps = this.hshSelfOrderMapper.selectByPage(example);
        PageInfo<SelfOrderDetailResp> info = new PageInfo<>(detailResps);
        PageResultResp resp = new PageResultResp();
        resp.setPage(pageNum);
        resp.setTotal(info.getPages());
        resp.setRows(detailResps);
        resp.setRecords(info.getTotal());
        return Result.ok(resp);
    }

    @Override
    public Result selectByOrderIdOrderDetails(String orderId) {
        SelfOrderResp resp = new SelfOrderResp();
        //查看订单
        HshSelfOrder order = this.hshSelfOrderMapper.selectByPrimaryKey(orderId);
        HshOrderShippingExample shppingExa = new HshOrderShippingExample();
        shppingExa.createCriteria().andOrderIdEqualTo(orderId);

        //查询物流详情
        HshOrderShipping hshOrderShippings = this.hshOrderShippingMapper.selectByExample(shppingExa).get(0);
        HshSelfOrderDetailExample detalExa = new HshSelfOrderDetailExample();
        detalExa.createCriteria().andOrderIdEqualTo(orderId);
        //订单商品详情
        HshSelfOrderDetail orderDetail = this.hshSelfOrderDetailMapper.selectByExample(detalExa).get(0);

        //收货人
        resp.setReceiverName(hshOrderShippings.getReceiverName());
        //收货人手机号
        resp.setReceiverMobile(hshOrderShippings.getReceiverMobile());
        //提货地址
        resp.setReceiverAddress(hshOrderShippings.getReceiverAddress());

        SelfItemResp itemResp = new SelfItemResp();
        itemResp.setItemName(orderDetail.getItemName());
        itemResp.setItemRebate(String.valueOf(orderDetail.getItemRebate()));
        itemResp.setImages(orderDetail.getItemImg());

        resp.setOrderId(orderId);
        resp.setPaymentTime(DateUtils.dateToStr(order.getPaymentTime()));
        resp.setTotalPayment(String.valueOf(order.getPayment()));
        resp.setTotalRebate(String.valueOf(order.getPayment()));
        resp.setEndTime(order.getConsignTime() == null ? "" : DateUtils.dateToStr(order.getConsignTime()));
        List<SelfItemResp> itemList = new ArrayList<>();
        itemList.add(itemResp);
        resp.setDetails(itemList);
        return Result.ok(resp);
    }
}
