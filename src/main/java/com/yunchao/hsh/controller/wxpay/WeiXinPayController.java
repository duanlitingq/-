package com.yunchao.hsh.controller.wxpay;

import com.up72.component.pay.common.PayInfoUtil;
import com.up72.component.wxpay.JsapiDto;
import com.up72.component.wxpay.Signature;
import com.up72.component.wxpay.Util;
import com.up72.component.wxpay.WxResult;
import com.up72.component.wxpay.protocol.*;
import com.up72.component.wxpay.service.RefundService;
import com.up72.component.wxpay.service.TransfersService;
import com.up72.component.wxpay.service.UnifiedOrderService;
import com.up72.framework.util.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 付鹏 on 2017/5/19.
 * insert by 隗鹏 on 2017/09/28
 */
@Controller
@RequestMapping("/weixinPay/")
public class WeiXinPayController {

    public Logger logger = Logger.getLogger("WeiXinPayController.class");

    /**
     * 微信企业转账 （暂时用于提现）
     *
     * @param partner_trade_no 商户订单号
     * @param openid           用户openid
     * @param amount           提现金额
     * @param desc             企业付款描述信息
     * @return
     */
    @RequestMapping("transfer")
    @ResponseBody
    public WxResult transfer(String partner_trade_no, String openid, double amount, String desc) {
        WxResult result = new WxResult();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            TransfersResData res = getTransferRes(partner_trade_no, openid, amount, desc);
            logger.info("----------->>Return_code:" + res.getReturn_code());
            logger.info("----------->>Result_code:" + res.getResult_code());
            if (ObjectUtils.isEmpty(res)) {
                logger.error("TransfersResData ====== null");
                result.setState(0);
                map.put("msg", "请求异常！");
            } else if (StringUtils.isNotBlank(res.getReturn_msg()) && !"OK".equals(res.getReturn_msg())) {
                result.setState(0);
                map.put("msg", res.getReturn_msg());
                logger.error(res.getReturn_msg());
            } else if (res.getReturn_code() != null && "SUCCESS".equals(res.getReturn_code()) && res.getResult_code() != null && "SUCCESS".equals(res.getResult_code())) {
                map.put("msg", "SUCCESS");
                map.put("partner_trade_no", res.getPartner_trade_no());
                map.put("payment_no", res.getPayment_no());
                result.setState(1);
            }
        } catch (Exception e) {
            result.setState(0);
            map.put("msg", "系统异常");
            System.out.println("系统异常");
            logger.error(e.getMessage());
        }
        result.setCont(map);
        return result;
    }

    private TransfersResData getTransferRes(String partner_trade_no, String openid, double amount, String desc) throws Exception {
        TransfersReqData reqData = new TransfersReqData();
        TransfersResData res = null;
        reqData.setMch_appid(PayInfoUtil.instants().getValue("wx_appId"));
        reqData.setMchid(PayInfoUtil.instants().getValue("mchID"));
        reqData.setNonce_str(Signature.getRandomStringByLength(30));
        reqData.setPartner_trade_no(partner_trade_no);
        reqData.setOpenid(openid);
        reqData.setCheck_name("NO_CHECK");
        amount = amount * 100;
        reqData.setAmount((int) amount);
        reqData.setDesc(desc);
        reqData.setSpbill_create_ip(PayInfoUtil.instants().getValue("ip"));
//        reqData.setSpbill_create_ip("192.168.1.120");
        String sign = Signature.getSign(reqData);
        reqData.setSign(sign);
        String resData = new TransfersService().request(reqData);
        res = (TransfersResData) Util.getObjectFromXML(resData, TransfersResData.class);
        return res;
    }

    /**
     *
     * @param out_trade_no 商户订单号
     * @param out_refund_no  商户退款单号	(与商户订单号一致)
     * @param total_fee  订单金额
     * @param refund_fee 退款金额
     * @param refund_desc 退款原因
     * @return
     */
    @RequestMapping("refund")
    @ResponseBody
    public WxResult refund(String out_trade_no, String out_refund_no, double total_fee, double refund_fee, String refund_desc) {
        WxResult result = new WxResult();
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            RefundResData res = getRefundRes(out_trade_no,out_refund_no,total_fee,refund_fee,refund_desc);
            logger.info("----------->>Return_code:" + res.getReturn_code());
            logger.info("----------->>Result_code:" + res.getResult_code());
            logger.info("----------->>return_msg:" + res.getReturn_msg());
            logger.info("----------->>err_code_des:" + res.getErr_code_des());
            if(ObjectUtils.isEmpty(res)){
                logger.error("RefundResData ====== null");
                result.setState(0);
                map.put("msg","请求异常！") ;
            }else if(StringUtils.isNotBlank(res.getReturn_msg()) && !"OK".equals(res.getReturn_msg())){
                result.setState(0);
                map.put("msg",res.getReturn_msg()) ;
                logger.error(res.getReturn_msg());
            }else if(res.getReturn_code() != null && "SUCCESS".equals(res.getReturn_code()) && res.getResult_code() != null && "SUCCESS".equals(res.getResult_code())){
                map.put("msg","SUCCESS") ;
                logger.info("订单号：" + res.getOut_trade_no() + "退款成功！ 实际退款金额====>" + ((double)res.getRefund_fee()/100D)) ;
                result.setState(1);
            }
        } catch (Exception e){
            result.setState(0);
            map.put("msg", "系统异常");
            System.out.println("系统异常");
            logger.error(e.getMessage());
        }
        result.setCont(map);
        return result;
    }
    private RefundResData getRefundRes (String out_trade_no, String out_refund_no, double total_fee, double refund_fee, String refund_desc){
        RefundReqData reqData = new RefundReqData();
        RefundResData res = null ;
        try{
            reqData.setAppid(PayInfoUtil.instants().getValue("wx_appId"));
            reqData.setMch_id(PayInfoUtil.instants().getValue("mchID"));
            reqData.setNonce_str(Signature.getRandomStringByLength(30));
            reqData.setOut_trade_no(out_trade_no);
            reqData.setOut_refund_no(out_refund_no);
            total_fee = total_fee * 100 ; //支付金额 单位为：分
            reqData.setTotal_fee((int) total_fee);
            refund_fee = refund_fee * 100 ; //实际退款金额 单位为：分
            reqData.setRefund_fee((int) refund_fee);
            reqData.setRefund_desc(refund_desc);
            String sign = Signature.getSign(reqData) ;
            reqData.setSign(sign);
            String resData = new RefundService().request(reqData) ;
            res = (RefundResData) Util.getObjectFromXML(resData,RefundResData.class);
        }catch (Exception e){
            logger.error("微信退款异常 ===>" + e.getMessage());
        }
        return  res ;
    }
    //统一下单
    @RequestMapping("unifiedOrder")
    @ResponseBody
    public WxResult getParameters(String openid, String notifyUrl, String outTradeNo, Integer totalFee, String body,String detail) {
        WxResult result = new WxResult();
        HashMap map = new HashMap();

        try {
            UnifiedOrderResData e = this.getPrepayId(openid, notifyUrl, outTradeNo, totalFee, body,detail);
            System.out.println("----------->>Return_code:" + e.getReturn_code());
            System.out.println("----------->>Result_code:" + e.getResult_code());
            if(e == null) {
                result.setState(0);
                map.put("msg", "请求异常");
            } else if(e.getReturn_code() != null && e.getResult_code() != null && "SUCCESS".equals(e.getReturn_code()) && "SUCCESS".equals(e.getResult_code())) {
                JsapiDto jsapiDto = new JsapiDto();
                map.put("appId", PayInfoUtil.instants().getValue("wx_appId"));
                jsapiDto.setAppId(PayInfoUtil.instants().getValue("wx_appId"));
                String ct = Signature.create_timestamp();
                map.put("timeStamp", ct);
                jsapiDto.setTimeStamp(ct);
                String ns = Signature.getRandomStringByLength(30);
                map.put("nonceStr", ns);
                jsapiDto.setNonceStr(ns);
                map.put("package", "prepay_id=" + e.getPrepay_id());
                jsapiDto.setWx_package("prepay_id=" + e.getPrepay_id());
                map.put("signType", "MD5");
                jsapiDto.setSignType("MD5");
                String paySign = Signature.getSign(jsapiDto);
                map.put("paySign", paySign);
                result.setState(1);
            } else if(e.getReturn_msg() != null && !"OK".equals(e.getReturn_msg())) {
                result.setState(0);
                map.put("msg", e.getReturn_msg());
            } else {
                result.setState(0);
                map.put("msg", e.getErr_code_des());
            }
        } catch (Exception var13) {
            result.setState(0);
            map.put("msg", "系统异常");
            System.out.println("系统异常");
            var13.printStackTrace();
        }

        result.setCont(map);
        return result;
    }
    private UnifiedOrderResData getPrepayId(String openid, String notifyUrl, String outTradeNo, int totalFee, String body,String detail) throws Exception {
        UnifiedOrderReqData req = new UnifiedOrderReqData();
        req.setNotify_url(notifyUrl);
        req.setBody(body);
        req.setOut_trade_no(outTradeNo);
        req.setOpenid(openid);
        req.setTotal_fee(totalFee);
        req.setDetail(detail);
        req.setTrade_type("JSAPI");
        UnifiedOrderResData res = (new UnifiedOrderService()).unifiedOrder(req);
        return res;
    }
}
