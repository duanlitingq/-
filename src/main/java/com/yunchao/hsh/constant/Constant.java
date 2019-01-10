package com.yunchao.hsh.constant;

/**
 * Created by 付鹏 on 2017/10/24.
 * 常量
 */
public class  Constant {

    //1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭'',',
    public static final String UN_PAY_1 = "1";
    //已付款--待发货
    public static final String YES_PAY_2 = "2";
    //未发货
    public static final String NO_DELIVER_GOODS_3 = "3";
    //已发货
    public static final String YES_DELIVER_GOODS_4 = "4";
    //交易成功
    public static final String PAY_SUCCESS_5 = "5";
    //交易关闭
    public static final String PAY_OFF_6 = "6";
    /**
     * 购物车rediskey
     */
    public static final String SHOP_KEY = "shopcar_";

    public static final String ORDER_SUFFIX = "hsh_";
    //供应商登录
    public static final String SUPPLIER_LOGIN = "supplier_login";

    public static final String CAI_TING_XIAO_FEN = "餐厅消费";
    public static final String JI_FEN_DUI_HUAN = "积分兑换";
    public static final String YAO_QING_HAO_YOU = "邀请好友";

    public static final String WX_PAY = "微信";
    public static final String YUE_PAY = "余额";
    public static final String INTEGRAL_PAY = "积分";
    public static final String YUE_INTEGRAL_PAY = "余额+积分";
    public static final String ALI_PAY = "支付宝";

    public static final int YUE_PAY_MODE =1;//余额
    public static final int WX_PAY_MODE = 2;//微信
    public static final int INTEGRAL_PAY_MODE = 3;//积分
    /**
     * 订单前缀
     */
    //自营订单
    public static final String ZY_ORDER = "ZY_";
    //驿站订单
    public static final String STATION_ORDER = "YZ_";

    //活动订单
    public static final String ACTIVE_ORDER = "HD_";

    public static final String WALLET_IN = "收入";

    public static final String WALLET_OUT = "支出";

    //1支出;2收入;3提现;4充值
    public static Integer PAY_OUT = 1;
    public static Integer PAY_IN = 2;
    public static Integer PAY_WITHDRAW = 3;
    public static Integer PAY_TOPUP = 4;

    /**
     * 积分/余额规则
     */
    //2352订单购买返余额规则
    public static final Long ACTIVITY_RULES_MONEY = 15L;


    /***
     * 短信模板编号
     */
    public static final String LOGIN_CODE_MSG = "SMS_152286355";//登陆验证码
    public static final String INVITATION_USE_MSG = "SMS_152286581";//邀请好友提示
    public static final String IN_MONEY_MSG = "SMS_152286583";//转账到账
    public static final String WITHDRAWAL_MSG = "SMS_152548161";//提现申请提示
    public static final String SUPPLIER_ORDER_MSG = "SMS_152830037";//店铺订单提示
    public static final String NEW_ORDER_MSG = "SMS_152850395";//店铺订单提示


}
