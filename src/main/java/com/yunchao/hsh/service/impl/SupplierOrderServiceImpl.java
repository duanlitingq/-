package com.yunchao.hsh.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.dao.*;
import com.yunchao.hsh.dto.resp.SupplierGoodsResp;
import com.yunchao.hsh.dto.resp.SupplierOrderResp;
import com.yunchao.hsh.dto.resp.SupplierResp;
import com.yunchao.hsh.model.*;
import com.yunchao.hsh.service.ISupplierOrderService;
import com.yunchao.hsh.utils.*;
import com.yunchao.hsh.utils.superdir.sub.Result;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
@SuppressWarnings("all")
public class SupplierOrderServiceImpl implements ISupplierOrderService {

    @Autowired
    private SupplierOrderMapper supplierOrderMapper;
    @Autowired
    private SupplierOrderItemMapper supplierOrderItemMapper;
    @Autowired
    private SupplierMapper supplierMapper;
    @Autowired
    private SupplierGoodsMapper supplierGoodsMapper;
    @Autowired
    private WalletMapper walletMapper;
    @Autowired
    private CustomerWalletLogMapper customerWalletLogMapper;
    @Autowired
    private IntegralRulesMapper integralRulesMapper;
    @Autowired
    private SupplierWalletLogMapper supplierWalletLogMapper;
    @Autowired
    private OrderPayInfoMapper orderPayInfoMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private HshLogisticsMapper hshLogisticsMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private HshStationMapper stationMapper;



    @Override
    public SupplierOrderResp findById(Long id) {
        SupplierOrderResp order = supplierOrderMapper.findById(id);
        if (ObjectUtils.isNotEmpty(order)) {
            List<SupplierOrderItem> byOrderId = supplierOrderItemMapper.findByOrderId(id);
            for (int i = 0; i < byOrderId.size(); i++) {
                SupplierOrderItem item = byOrderId.get(i);
                SupplierGoodsResp byId = supplierGoodsMapper.findById(item.getGoodsId());
                item.setSupplierGoodsResp(byId);
            }
            order.setItems(byOrderId);
        }
        return order;
    }

    @Override
    public PageInfo<SupplierOrderResp> getPage(HashMap<String, Object> map, int pageNum, int pageSize) throws Exception {
        PageHelper.orderBy("  o.create_time desc");
        PageHelper.startPage(pageNum, pageSize);
        List<SupplierOrderResp> list = supplierOrderMapper.findPage(map);
        if (list != null) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                SupplierOrderResp supplierOrderResp = list.get(i);
                Long id = supplierOrderResp.getId();
                SupplierResp supplier = supplierMapper.findById(id);
                supplierOrderResp.setSupplierResp(supplier);
                List<SupplierOrderItem> items = supplierOrderItemMapper.findByOrderId(id);
                for (SupplierOrderItem item : items) {
                    Long goodsId = item.getGoodsId();
                    SupplierGoodsResp byId = supplierGoodsMapper.findById(goodsId);
                    item.setSupplierGoodsResp(byId);
                }
                supplierOrderResp.setItems(items);
            }
        }
        PageInfo<SupplierOrderResp> pageInfo = new PageInfo<SupplierOrderResp>(list);
        return pageInfo;
    }

    @Override
    public void insert(SupplierOrder supplierOrder) throws Exception {
        supplierOrderMapper.insert(supplierOrder);
    }

    @Override
    public void updatePayStatus(SupplierOrder su) throws Exception {
        supplierOrderMapper.updatePayStatus(su);
    }

    @Override
    public void confirmOrder(SupplierOrder su) throws Exception {
        su.setConfirmTime(new Date());
        supplierOrderMapper.updateOrderStatus(su);
        Message message = new Message();
        String str = "";
        Integer orderStatus = su.getOrderStatus();
        if(orderStatus == 2){
            message.setContent("商家已接单，正在配货中...");
            message.setTitle("商家已接单");
        }else {
            message.setContent("商家已发货");
            message.setTitle("商家已发货");
        }
        message.setType(0L);
        message.setSendId(0L);
        message.setReceiveId(su.getUserId());
        message.setState(0L);
        message.setImgPath("");
        message.setColumn1("");
        message.setColumn2(0L);
        message.setColumn3(new Date());
        message.setSendTime(new Date());
        messageMapper.insert(message);
    }

    /****
     * 取消订单，暂时未用
     * @param result
     * @param su
     * @return
     * @throws Exception
     */
    @Override
    public Result cancelOrder(Result result, SupplierOrderResp su) throws Exception {
        su.setFinishTime(new Date());
        su.setOrderStatus(6);
        //判断如果已支付需退还金额和积分
        if (su.getPayStatus() == 2) {
            //用户积分返还，钱包返还钱
            Long userId = su.getUserId();
            Wallet wallet = walletMapper.findByCustomerId(userId);
            Double burse = wallet.getBurse();
            Double score = wallet.getScore();
            //驳回订单
            //用户实际支付
            Double actPayment = su.getActPayment();
            //平台收入
            Double platformRevenue = su.getPlatformRevenue();
            //积分
            Double integral = su.getIntegral();
            Long supplierId = su.getSupplierId();

            //驳回之后用户得到的积分要退回
            Double userIntegral = (actPayment / 100) * 3;
            //用户应退还积分
            Double dou = numberFormat(userIntegral);
            if (dou > 0) {
                //个人记录添加
                insertCustomerWalletLog(1, dou, 3, "订单取消，积分扣除", su.getOrderNo(),
                        userId, 1, wallet.getBurse(), supplierId, su.getTradeNo());
                wallet.setScore(score - dou);
            }
            //店铺余额变化
            SupplierResp supplierResp = supplierMapper.findById(supplierId);
            //店铺应退还金额
            Double doubleValue = BigDecimalUtils.subtract(actPayment, platformRevenue).doubleValue();
            //店铺总余额
            Double totalMoney = supplierResp.getTotalMoney();
            //店铺剩余余额
            Double doubleValue1 = BigDecimalUtils.subtract(totalMoney, doubleValue).doubleValue();
            supplierResp.setTotalMoney(doubleValue);
            supplierMapper.updateTotalMoney(supplierResp);
            //店铺记录
            SupplierWalletLog log = new SupplierWalletLog();
            log.setColumn1(" ");
            log.setColumn2(0);
            log.setColumn3(0L);
            log.setChangeMoney(doubleValue);
            log.setCreateTime(new Date());
            log.setFrontUserId(userId);
            log.setOrderId(su.getId());
            log.setInOrOut(2);
            log.setRemark("取消订单，退款");
            log.setSupplierId(supplierId);
            supplierWalletLogMapper.insert(log);
            //用户钱包添加

            double userBurse = BigDecimalUtils.add(burse, actPayment).doubleValue();
            double userScore = BigDecimalUtils.add(integral, score).doubleValue();
            wallet.setScore(userScore);
            wallet.setBurse(userBurse);
            walletMapper.update(wallet);
            CustomerWalletLog walletLog = new CustomerWalletLog();
            walletLog.setAmount(actPayment);
            walletLog.setPayMode(1);
            walletLog.setType(2);
            walletLog.setCustomerId(userId);
            walletLog.setCreateDate(new Date());
            walletLog.setStatus(1);
            walletLog.setCurrentBalance(wallet.getBurse());
            walletLog.setUuid(su.getOrderNo());
            walletLog.setRemark("订单退款，支付金额已退还到余额");
            walletLog.setOperationId(supplierResp.getId());
            walletLog.setTradeNo(su.getTradeNo());
            walletLog.setColumn1(" ");
            walletLog.setColumn2(0);
            walletLog.setColumn3(0L);
            customerWalletLogMapper.insert(walletLog);
            Customer customer = customerMapper.getIdCustomer(userId);
            if (integral > 0) {//添加记录
                CustomerWalletLog record = new CustomerWalletLog();
                record.setAmount(integral);
                record.setPayMode(3);
                record.setType(2);
                record.setCustomerId(customer.getId());
                record.setCreateDate(new Date());
                record.setStatus(1);
                record.setCurrentBalance(wallet.getBurse());
                record.setUuid(su.getOrderNo());
                record.setRemark("取消订单，积分返还");
                record.setOperationId(supplierResp.getId());
                record.setTradeNo(su.getTradeNo());
                record.setColumn1(" ");
                record.setColumn2(0);
                record.setColumn3(0L);
                customerWalletLogMapper.insert(record);
            }
            Message message = new Message();
            message.setContent("商家确认取消了订单，支付金额将返还到余额，请注意查收");
            message.setTitle("订单取消");
            message.setType(0L);
            message.setSendId(0L);
            message.setReceiveId(userId);
            message.setState(0L);
            message.setImgPath("");
            message.setColumn1("");
            message.setColumn2(0L);
            message.setColumn3(new Date());
            message.setSendTime(new Date());
            messageMapper.insert(message);
        }
        supplierOrderMapper.updateOrderStatus(su);
        return result;
    }

    //店铺出入账记录
    private void insertSupplierWalletLog(Double money, Long supplierId, Long orderId, Long userId) {
        SupplierWalletLog log = new SupplierWalletLog();
        log.setSupplierId(supplierId);
        log.setRemark("收入");
        log.setOrderId(orderId);
        log.setInOrOut(1);
        log.setFrontUserId(userId);
        log.setCreateTime(new Date());
        log.setChangeMoney(+money);
        log.setColumn3(0L);
        log.setColumn2(0);
        log.setColumn1("");
        supplierWalletLogMapper.insert(log);
    }

    @Override
    public SupplierOrderResp findByUserOrderId(Long orderId, Long userId) throws Exception {
        //用于查询使用
        SupplierOrder order = new SupplierOrder();
        order.setId(orderId);
        order.setUserId(userId);
        //得到订单
        SupplierOrderResp orderResp = supplierOrderMapper.getByUserOrderId(order);
        //获取订单项
        List<SupplierOrderItem> orders = supplierOrderItemMapper.findByOrderId(orderId);
        int len = orders.size();
        //获取订单项商品
        for (int j = 0; j < len; j++) {
            SupplierOrderItem item = orders.get(j);
            Long goodsId = item.getGoodsId();
            SupplierGoodsResp goods = supplierGoodsMapper.findById(goodsId);
            item.setSupplierGoodsResp(goods);
        }
        orderResp.setItems(orders);
        return orderResp;
    }

    @Override
    public void removeOrder(Long userId, Long orderId) {
        SupplierOrder order = new SupplierOrder();
        order.setId(orderId);
        order.setUserId(userId);
        supplierOrderMapper.removeOrder(order);
        supplierOrderItemMapper.removeOrderItemByOrderId(orderId);
    }

    @Override
    public Result receivingGoods(Result result, Long orderId, Long userId) throws Exception {
        SupplierOrder order = new SupplierOrder();
        order.setUserId(userId);
        order.setId(orderId);
        order.setFinishTime(new Date());
        order.setOrderStatus(4);
        supplierOrderMapper.receivingGoods(order);
        result.setS("", "操作成功");
        return result;
    }

    /**
     * 订单结算生成订单
     *
     * @param
     * @param datas {"supplierId":2,"addressId":2 ,"haggle":0,"integral:":0, "datas":[
     *              "goodsId":3,"goodsNum":2,
     *              "goodsId":3,"goodsNum":2,
     *              "goodsId":3,"goodsNum":2,
     *              "goodsId":3,"goodsNum":2,
     *              "goodsId":3,"goodsNum":2,
     *              ]
     *              }
     */
    @Override
    public Result createOrder(Result result, Map<Long, List<ShoppingCar>> map, String datas, Customer customer) {
        //接收到的数据
        SupplierOrder order = new SupplierOrder();
        JSONObject obj = JSONObject.fromObject(datas);
//        店铺编号用于修改购物车数据
        Long supplierId = obj.getLong("supplierId");
//        地址
        Long addressId = obj.getLong("addressId");
        if (addressId == null || addressId == 0) {
            result.setF("创建订单失败，地址数据异常");
            return result;
        }
//        议价
        double haggle = obj.getDouble("haggle");
//        积分
        double integral = obj.getDouble("integral");
        order.setParentId(0L);
        order.setIntegral(integral);
        order.setSupplierId(supplierId);
        order.setHagglePrice(haggle);
        order.setAddressId(addressId);
//        购买人编号
        Long userId = customer.getId();
        Object data = obj.get("datas");
        Double totalMoney = 0.0;
//        购物车中的商品数据
        List<ShoppingCar> list = map.get(supplierId);
//        要删除的数据
        List<ShoppingCar> removelist = new ArrayList<>();
        if (ObjectUtils.isNotEmpty(data)) {
            JSONArray jsonArray = JSONArray.fromObject(data);
            int size = jsonArray.size();
            if (size > 0) {
                //存放订单项
                List<SupplierOrderItem> items = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    Object o = jsonArray.get(i);
                    JSONObject jsonObject = JSONObject.fromObject(o);
                    long goodsId = jsonObject.getLong("goodsId");
                    int goodsNum = jsonObject.getInt("goodsNum");
                    SupplierGoodsResp goodsResp = supplierGoodsMapper.findById(goodsId);
                    if (goodsResp.getStatus() == 1) {// && goodsResp.getLibraryNum() >= goodsNum库存暂不校验
                        for (ShoppingCar car : list) {
                            long cid = car.getGoodsId();
                            if (cid == goodsId) {
                                SupplierOrderItem item = new SupplierOrderItem();
                                item.setSalesNum(goodsNum);
                                item.setColumn1("");
                                item.setGoodsId(goodsId);
                                item.setColumn3(0L);
                                item.setColumn2(0);
                                item.setPlanPrice(goodsResp.getPlanPrice());
                                item.setActPrice(goodsResp.getPlanPrice());
                                items.add(item);
                                totalMoney += BigDecimalUtils.multiply(goodsResp.getPlanPrice(), goodsNum).doubleValue();
                                removelist.add(car);
                            }
                        }
                    } else {
//                        if (goodsResp.getStatus() == 0){
                        result.setF(goodsResp.getName() + goodsResp.getSpecifications() + "商品已下架");
//                        }
//                        if( goodsResp.getLibraryNum() < goodsNum){
//                            result.setF(goodsResp.getName()+goodsResp.getSpecifications()+"商品库存不足");
//                        }
                        return result;
                    }
                }
                if (items.size() > 0) {
                    int size1 = removelist.size();
                    for (int j = 0; j < size1; j++) {
                        list.remove(removelist.get(j));
                    }
                    order.setItems(items);
                    //当前购物车中是否有数据
                    if (list.size() == 0) {
                        map.remove(supplierId);
                    } else {
                        map.put(supplierId, list);
                    }
                    Double actMoney = 0.0;
                    if (haggle > 0) {
                        if (integral > 0) {
                            //用户需要支付的金额，议价-积分是用户需要支付的金额
                            actMoney = BigDecimalUtils.subtract(haggle, integral).doubleValue();
                        } else {
                            actMoney = haggle;
                        }
                    } else {
                        if (integral > 0) {
                            //用户需要支付的金额，议价-积分是用户需要支付的金额，由于微信金额单位为分所以输入金额*100转换单位
                            actMoney = BigDecimalUtils.subtract(totalMoney, integral).doubleValue();
                        } else {
                            actMoney = totalMoney;
                        }
                    }
                    order.setActPayment(actMoney);
                    initOrder(order, totalMoney, supplierId, userId,addressId);
                    insertOrder(order);
                    result.setS("", order, customer.getOpenId());
                } else {
                    result.setF("商品数据异常");
                }
            } else {
                result.setF("商品数据异常");
            }
        } else {
            result.setF("商品数据异常");
        }
        return result;
    }

    //初始化订单数据
    private void initOrder(SupplierOrder order, Double totalMoney, Long supplierId, Long userId,Long addressId) {
        // 设置地址
        HshLogistics hshLogistics1 = hshLogisticsMapper.selectByPrimaryKey(addressId);
        Long stationId = hshLogistics1.getStationId();
        HshStation hshStation = stationMapper.selectByPrimaryKey(stationId);
        order.setColumn1(hshStation.getName());
        order.setAddress(hshLogistics1.getAddress());
        order.setBuyUserName(hshLogistics1.getRealName());
        order.setBuyUserPhone(hshLogistics1.getPhone());

        order.setPayStatus(1);
        order.setColumn2(0);
        order.setColumn3(0L);
        order.setCreateTime(new Date());
        if (order.getHagglePrice() == null) {
            order.setHagglePrice(0.0);
        }
        if (order.getIntegral() == null) {
            order.setIntegral(0.0);
        }
        order.setOrderMoney(totalMoney);
        order.setOrderStatus(1);
        if (order.getActPayment() == null) {
            order.setActPayment(0.0);
        }
        if (order.getPlatformRevenue() == null) {
            order.setPlatformRevenue(0.0);
        }
        String after = Constant.ORDER_SUFFIX + MnUtil.getCurTime() + CommonUtil.$getRandom(4, 4);
        order.setOrderNo(after);
        order.setPayType(0);
        order.setRemark("");
        order.setSupplierId(supplierId);
        order.setParentId(0L);
        order.setUserId(userId);
    }

    //保存订单数据
    private void insertOrder(SupplierOrder supplierOrder) {
        supplierOrder.setParentId(0L);
        supplierOrderMapper.insert(supplierOrder);
        Long id = supplierOrder.getId();
        List<SupplierOrderItem> items = supplierOrder.getItems();
        for (int j = 0; j < items.size(); j++) {
            SupplierOrderItem item = items.get(j);
            item.setOrderId(id);
            supplierOrderItemMapper.insert(item);
        }
    }


    //点击结算后更新订单（暂时弃用）
    @Override
    public Result updateOrderData(Result result, Customer customer, Long orderId, Double haggle, Double integral, Long addressId) {
        double actMoney = 0.0;
        SupplierOrder order = supplierOrderMapper.findById(orderId);
        order.setAddressId(addressId);
        order.setHagglePrice(haggle);
        order.setIntegral(integral);
        String after = Constant.ORDER_SUFFIX + MnUtil.getCurTime() + CommonUtil.$getRandom(4, 4);
        order.setOrderNo(after);

        if (haggle > 0) {
            if (integral > 0) {
                //用户需要支付的金额，议价-积分是用户需要支付的金额
                actMoney = BigDecimalUtils.subtract(haggle, integral).doubleValue() * 100;
            } else {
                actMoney = haggle * 100;
            }
        } else {
            if (integral > 0) {
                //用户需要支付的金额，议价-积分是用户需要支付的金额，由于微信金额单位为分所以输入金额*100转换单位
                actMoney = BigDecimalUtils.subtract(order.getOrderMoney(), integral).doubleValue() * 100;
            } else {
                actMoney = order.getOrderMoney() * 100;
            }
        }
        order.setActPayment(actMoney);
        supplierOrderMapper.updateOrderAddrAndHaggle(order);
        result.setS("", order, customer.getOpenId());
        return result;
    }

    @Override
    public SupplierOrderResp findByOrderNo(String orderNo) {
        return supplierOrderMapper.findByOrderNo(orderNo);
    }

    @Override
    public void updateOrderStatusToFinish(HashMap hashMap) {
        Date today = new Date();
        List<SupplierOrderResp> page = supplierOrderMapper.findPage(hashMap);
        if (page != null && page.size() > 0) {
            for (int i = 0; i < page.size(); i++) {
                SupplierOrderResp orderResp = page.get(i);
                Date confirmTime = orderResp.getConfirmTime();
                if ((today.getTime() - confirmTime.getTime()) / (1000 * 60 * 60) > 72) {
                    orderResp.setOrderStatus(4);
                    orderResp.setFinishTime(today);
                    supplierOrderMapper.receivingGoods(orderResp);
                }
            }
        }
    }

    @Override
    public List<SupplierOrderResp> findEndTimeNoConfirmOrder() {
        return supplierOrderMapper.findEndTimeNoConfirmOrder();
    }

    @Override
    public Result singleBuy(Result result, Customer customer, String datas) {
        //接收到的数据
        SupplierOrder order = new SupplierOrder();
        JSONObject obj = JSONObject.fromObject(datas);
//        店铺编号用于修改购物车数据
        Long supplierId = obj.getLong("supplierId");
//        地址
        Long addressId = obj.getLong("addressId");
        if (addressId == null || addressId == 0) {
            result.setF("创建订单失败，地址数据异常");
            return result;
        }
//        议价
        double haggle = obj.getDouble("haggle");
//        积分
        double integral = obj.getDouble("integral");
//        商品编号
        long goodsId = obj.getLong("goodsId");
        SupplierGoodsResp goods = supplierGoodsMapper.findById(goodsId);
        double actMoney = 0.0;
        if (haggle > 0) {
            if (integral > 0) {
                //用户需要支付的金额，议价-积分是用户需要支付的金额
                actMoney = BigDecimalUtils.subtract(haggle, integral).doubleValue();
            } else {
                actMoney = haggle;
            }
        } else {
            if (integral > 0) {
                //用户需要支付的金额，议价-积分是用户需要支付的金额，由于微信金额单位为分所以输入金额*100转换单位
                actMoney = BigDecimalUtils.subtract(goods.getPlanPrice(), integral).doubleValue();
            } else {
                actMoney = goods.getPlanPrice();
            }
        }
        order.setParentId(0L);
        order.setIntegral(integral);
        order.setSupplierId(supplierId);
        order.setHagglePrice(haggle);
        order.setAddressId(addressId);
//        购买人编号
        Long userId = customer.getId();
        order.setUserId(userId);
        order.setActPayment(actMoney);
        initOrder(order, goods.getPlanPrice(), supplierId, userId,addressId);
        supplierOrderMapper.insert(order);
        Long orderId = order.getId();
        SupplierOrderItem item = new SupplierOrderItem();
        item.setActPrice(actMoney);
        item.setColumn2(0);
        item.setColumn3(0L);
        item.setSalesNum(1);
        item.setGoodsId(goodsId);
        item.setColumn1(" ");
        item.setOrderId(orderId);
        supplierOrderItemMapper.insert(item);
        return result.setS("", order);
    }

    @Override
    public double sumOrderPayTransactionFlow() {
        Double v = supplierOrderMapper.sumOrderPayTransactionFlow();
        if (v != null) {
            return v;
        }
        return 0.0;
    }

    @Override
    public Long countPayOrderNum() {
        return this.supplierOrderMapper.countPayOrderNum();
    }

    /**
     * 驳回订单
     * @param map
     * @param supplierOrderResp
     */
    @Override
    public void backOrder(HashMap<String, Object> map, SupplierOrderResp supplierOrderResp) {
        if (supplierOrderResp.getPayStatus() == 2) {
            //用户积分返还，钱包返还钱
            Long userId = supplierOrderResp.getUserId();
            Long supplierId = supplierOrderResp.getSupplierId();
            //用户钱包添加
            Wallet wallet = walletMapper.findByCustomerId(userId);
            Double burse = wallet.getBurse();
            Double score = wallet.getScore();
            //驳回订单
            //用户实际支付
            Double actPayment = supplierOrderResp.getActPayment();
            //驳回之后用户得到的积分要退回
            Double userIntegral = (actPayment / 100) * 3;
            //用户应退还积分
            Double dou = numberFormat(userIntegral);
            if (dou > 0) {
                //个人记录添加
                insertCustomerWalletLog(1, dou, 3, "订单驳回，积分扣除", supplierOrderResp.getOrderNo(),
                        userId, 1, wallet.getBurse(), supplierId, supplierOrderResp.getTradeNo());
                wallet.setScore(score - dou);
                score = numberFormat(score - dou);
            }
            //平台收入
            Double platformRevenue = supplierOrderResp.getPlatformRevenue();
            //积分
            Double integral = supplierOrderResp.getIntegral();

            //店铺余额变化
            SupplierResp supplierResp = supplierMapper.findById(supplierId);
            //店铺应退还金额 actPayment
            //Double doubleValue = BigDecimalUtils.subtract(actPayment, platformRevenue).doubleValue();
            //店铺总余额
            Double totalMoney = supplierResp.getTotalMoney();
            //店铺剩余余额
            Integer payType = supplierOrderResp.getPayType();
            /**支付方式 1:微信 2：余额3：积分4：支付宝*/
            if(payType == 3){
                actPayment = BigDecimalUtils.subtract(supplierOrderResp.getIntegral(), supplierOrderResp.getPlatformRevenue()).doubleValue();
            }
            Double doubleValue1 = BigDecimalUtils.subtract(totalMoney, actPayment).doubleValue();
            System.out.println(totalMoney+"zongjine-----"+actPayment);
            supplierResp.setTotalMoney(doubleValue1);
            supplierMapper.updateTotalMoney(supplierResp);
            //店铺记录
            SupplierWalletLog log = new SupplierWalletLog();
            log.setColumn1(" ");
            log.setColumn2(0);
            log.setColumn3(0L);
            log.setChangeMoney(actPayment);
            log.setCreateTime(new Date());
            log.setFrontUserId(userId);
            log.setOrderId(supplierOrderResp.getId());
            log.setInOrOut(2);
            log.setRemark("驳回订单，退款");
            log.setSupplierId(supplierId);
            supplierWalletLogMapper.insert(log);
            double userBurse = BigDecimalUtils.add(burse, actPayment).doubleValue();
            double userScore = BigDecimalUtils.add(integral, score).doubleValue();
            wallet.setScore(userScore);
            wallet.setBurse(userBurse);
            walletMapper.update(wallet);
            //个人记录添加
            insertCustomerWalletLog(2, actPayment, 1, "订单驳回，支付金额退还", supplierOrderResp.getOrderNo(),
                    userId, 1, wallet.getBurse(), supplierId, supplierOrderResp.getTradeNo());
            System.out.println("订单驳回，支付金额退还:" + actPayment);
            if (integral > 0) {//添加记录
                //积分退回
                System.out.println("订单驳回，积分退还:" + integral);
                insertCustomerWalletLog(2, integral, 3, "订单驳回，积分退还", supplierOrderResp.getOrderNo(),
                        userId, 1, wallet.getBurse(), supplierId, supplierOrderResp.getTradeNo());
            }
            Message message = new Message();
            message.setContent("订单被驳回，支付金额将返还到余额，请注意查收");
            message.setTitle("订单驳回");
            message.setType(0L);
            message.setSendId(0L);
            message.setReceiveId(userId);
            message.setState(0L);
            message.setImgPath("");
            message.setColumn1("");
            message.setColumn2(0L);
            message.setColumn3(new Date());
            message.setSendTime(new Date());
            messageMapper.insert(message);
        }
        supplierOrderResp.setOrderStatus(7);
        supplierOrderMapper.updateOrderStatus(supplierOrderResp);
    }

    //导出
    @Override
    public HSSFWorkbook exportExcel(Long supplierId, String filePath, String sheetName, String orderNo, Integer payStatus, Integer orderStatus) {
        SimpleDateFormat ymdh = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        HashMap<String, Object> map = new HashMap<>();
        map.put("supplierId", supplierId);
        if (orderNo != null) {
            map.put("orderNo", orderNo.trim());
        }
        if (payStatus != null && payStatus > 0) {
            map.put("payStatus", payStatus);
        }
        if (orderStatus != null && orderStatus > 0) {
            map.put("orderStatus", orderStatus);
        }
        List<SupplierOrderResp> list = supplierOrderMapper.findPage(map);
        int len = 0;
        if (list != null && list.size() > 0) {
            len = list.size();
        }
        String[] title = new String[15];
        title[0] = "序号";
        title[1] = "订单号";
        title[2] = "商品信息";
        title[3] = "收货人";
        title[4] = "收货人电话";
        title[5] = "收货地址";
        title[6] = "订单金额";
        title[7] = "议价";
        title[8] = "积分";
        title[9] = "实付金额";
        title[10] = "下单时间";
        title[11] = "接单时间";
        title[12] = "完成时间";
        title[13] = "订单状态";
        title[14] = "支付状态";
        String[][] values = new String[len][15];
        for (int i = 0; i < len; i++) {
            String[] val = values[i];
            SupplierOrderResp orderResp = list.get(i);
            val[0] = (i + 1) + "";
            val[1] = orderResp.getOrderNo();
            Long orderId = orderResp.getId();
            String goods = "";
            List<SupplierOrderItem> items = supplierOrderItemMapper.findByOrderId(orderId);
            if (items != null && items.size() > 0) {
                for (int j = 0; j < items.size(); j++) {
                    SupplierOrderItem item = items.get(j);
                    SupplierGoodsResp goodsResp = supplierGoodsMapper.findById(item.getGoodsId());
                    if (goodsResp != null) {
                        goods += goodsResp.getName() + "  " + item.getSalesNum() + goodsResp.getUnitName() + "\n";
                    }
                }
            }
            val[2] = goods;
            val[3] = orderResp.getBuyUserName();
            val[4] = orderResp.getBuyUserPhone();
            val[5] = orderResp.getAddress();
            val[6] = orderResp.getOrderMoney().toString();
            val[7] = orderResp.getHagglePrice().toString();
            val[8] = orderResp.getIntegral().toString();
            val[9] = orderResp.getActPayment().toString();
            String ctime = "";
            if (orderResp.getCreateTime() != null) {
                ctime = ymdh.format(orderResp.getCreateTime());
            }
            val[10] = ctime;
            String cftime = "";
            if (orderResp.getConfirmTime() != null) {
                cftime = ymdh.format(orderResp.getConfirmTime());
            }
            val[11] = cftime;
            String ftime = "";
            if (orderResp.getFinishTime() != null) {
                ftime = ymdh.format(orderResp.getFinishTime());
            }
            val[12] = ftime;
            String orderStatusName = "";
            /**订单状态：1：未接单2：已接单(已发货)3：已完成4：取消订单，5：已取消 6：商家驳回*/
            switch (orderResp.getOrderStatus()) {
                case 1:
                    orderStatusName = "未接单";
                    break;
                case 2:
                    orderStatusName = "已接单";
                    break;
                case 3:
                    orderStatusName = "已完成";
                    break;
                case 4:
                    orderStatusName = "取消订单";
                    break;
                case 5:
                    orderStatusName = "已取消";
                    break;
                case 6:
                    orderStatusName = "已驳回";
                    break;
            }
            val[13] = orderStatusName;
            String payStatusName = "";
            switch (orderResp.getPayStatus()) {
                case 1:
                    payStatusName = "未支付";
                    break;
                case 2:
                    payStatusName = "已支付";
                    break;
            }
            val[14] = payStatusName;
        }
        HSSFWorkbook hssfWorkbook = ExcelUtils.getHSSFWorkbook(sheetName, title, values, null);
        return hssfWorkbook;
    }

    @Override
    public List<SupplierOrderItem> findItemsByOrderId(Long orderId) {
        return supplierOrderItemMapper.findByOrderId(orderId);
    }

    @Override
    public Integer countOrderBuyOrderStatus(Long supplierId, Integer orderStatus) {
        Map<String, Object> map = new HashMap<>();
        map.put("supplierId", supplierId);
        map.put("orderStatus", orderStatus);
        return supplierOrderMapper.countOrderBuyOrderStatus(map);
    }


    /**
     * @param type        类型;1：支出、2：收入;3：提现、4:系统
     * @param changeMoney 改变的积分/金额
     * @param payMode     消费方式; 1：余额;2:微信;3:积分;4:系统
     * @param remark      备注
     * @param orderNo     系统好物订单编号orderNo
     * @param userId      用户编号
     * @param status      状态（0失败，1成功）
     * @param burse       当前钱包金额
     * @param supplierId  供应商编号
     * @param tradeNo     第三方微信流水号
     */
    private void insertCustomerWalletLog(Integer type, Double changeMoney, Integer payMode, String remark, String orderNo, Long userId, Integer status, Double burse, Long supplierId, String tradeNo) {
        CustomerWalletLog record = new CustomerWalletLog();
        record.setAmount(changeMoney);
        record.setType(type);
        record.setCustomerId(userId);
        record.setCreateDate(new Date());
        record.setStatus(status);
        record.setCurrentBalance(burse);
        record.setUuid(orderNo);
        record.setRemark(remark);
        record.setOperationId(supplierId);
        record.setTradeNo(tradeNo);
        record.setPayMode(payMode);
        record.setColumn1(" ");
        record.setColumn2(0);
        record.setColumn3(0L);
        customerWalletLogMapper.insert(record);
    }

    private Double numberFormat(double d) {
        NumberFormat nf = NumberFormat.getNumberInstance();
        // 保留两位小数
        nf.setMaximumFractionDigits(2);
        // 如果不需要四舍五入，可以使用RoundingMode.DOWN
        nf.setRoundingMode(RoundingMode.UP);
        String format = nf.format(d);
        return Double.valueOf(format);
    }

}
