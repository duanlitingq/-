package com.yunchao.hsh.controller.wxapp;

import com.github.pagehelper.PageInfo;
import com.up72.framework.util.ObjectUtils;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.controller.BaseController;
import com.yunchao.hsh.model.Customer;
import com.yunchao.hsh.model.CustomerWalletLog;
import com.yunchao.hsh.model.IntegralRules;
import com.yunchao.hsh.model.Wallet;
import com.yunchao.hsh.service.ICustomerService;
import com.yunchao.hsh.service.ICustomerWalletLogService;
import com.yunchao.hsh.service.IIntegralRulesService;
import com.yunchao.hsh.service.IWalletService;
import com.yunchao.hsh.utils.ALiYunSendMsgUtils;
import com.yunchao.hsh.utils.BigDecimalUtils;
import com.yunchao.hsh.utils.SystemConfig;
import com.yunchao.hsh.utils.ToolsUtil;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * @Description: 用户controller
 * @Author: 隗鹏
 * @CreateDate: 2018/11/7 0007 13:07
 * @UpdateUser:
 * @UpdateDate:
 * @UpdateRemark:
 * @Version: 1.0
 */
@Api
@Controller(value = "appCustomerController")
@RequestMapping("/page/wxapp/customer/")
public class CustomerController extends BaseController {
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IWalletService walletService;
    @Autowired
    private IIntegralRulesService integralRulesService;
    @Autowired
    private ICustomerWalletLogService customerWalletLogService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取用户列表", tags = {"用户管理"}, notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "nickname", value = "用户昵称", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "img", value = "用户头像", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "query"),
    })
    public Result login(Customer customer, String token) {
        Result result = new Result();
        Logger log = Logger.getLogger("CustomerController.class");
        try {
            if (ObjectUtils.isNotEmpty(token)) {
                //用户昵称过滤特殊字符
                String nickname = "";
                if (ObjectUtils.isNotEmpty(customer.getNickname())) {
                    nickname = ToolsUtil.strFilter(customer.getNickname());
                }
                //根据openID获取用户
                String redisOpenId = ToolsUtil.getRedisOpenId(token);
                log.info("login=token=>" + token + ";redisOpenId=>" + redisOpenId);
                if (ObjectUtils.isEmpty(redisOpenId)) {
                    result.setF("");
                    result.setData(-1);
                    log.info("==服务器缓存丢失,重新发起登录==");
                    return result;
                }
                Customer exCustomer = customerService.getByOpenID(redisOpenId);
                if (ObjectUtils.isNotEmpty(exCustomer)) {
                    log.info("======更新用户登录信息,昵称=>" + nickname + ",手机号=>" + exCustomer.getPhone());
                    exCustomer.setNickname(nickname);
                    exCustomer.setImg(customer.getImg());
                    customerService.update(exCustomer);
                } else {
                    log.info("======用户首次登录保存用户信息,昵称=>" + nickname);
                    Customer newCustomer = new Customer();
                    newCustomer.setNickname(nickname);
                    newCustomer.setOpenId(redisOpenId);
                    newCustomer.setImg(customer.getImg());
                    customerService.save(newCustomer);
                }
                //刷新Redis信息
                String redis_token_time = SystemConfig.instants().getValue("redis_token_time");
                RedisUtil.getInstance().putInRedis(token, (String) RedisUtil.getInstance().getFromRedis(token), Integer.parseInt(redis_token_time));
                result.setS("");
                result.setData(token);
                log.info("==login===刷新Redis登录状态===");
                return result;
            } else {
                log.info("用户token为空");
                result.setF("用户token为空");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("参数异常");
            return result;
        }
        return result;
    }

    /**
     * 通过token获取用户信息
     *
     * @param token
     * @return
     */

    @RequestMapping(value = "findCustomer", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "wxapp获取用户信息", tags = {"用户信息"}, notes = "用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "query")
    })
    public Result getCustomerByToken(String token) {
        Result result = new Result();
        if (token != null) {
            String redisOpenId = ToolsUtil.getRedisOpenId(token);
            if (redisOpenId != null) {
                Customer customer = customerService.getCustomerByOpenId(redisOpenId);

                result.setS("", customer);
                return result;
            }
        }

        return null;
    }

    //通过邀请,已注册,请求接口
    @RequestMapping(value = "saveSuprior")
    @ResponseBody
    public Result saveSuprior(String token, Long supriorId) {
        Result result = new Result();
        Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
        if (ObjectUtils.isEmpty(customer)) {
            result.setF("用户未登录");
            result.setData(-1);
            return result;
        }
        if (supriorId != null) {
            Customer parentCustomer = customerService.getIdCustomer(supriorId);
            //获取积分返现值  (显示)
            IntegralRules integralRules = integralRulesService.selOneIntegralRules(16L);
            if (integralRules != null) {
                //积分规则 奖励数量
                Double integral = integralRules.getIntegral();
                //获取上级用户的钱包
                Wallet customerWallet = walletService.getByCustomerId(supriorId);
                if (customerWallet != null) {
                    //获取当前钱包积分
                    Double score = customerWallet.getScore();
                    //计算积分
                    Double balance = BigDecimalUtils.add(score, integral).doubleValue();
                    //钱包出入账记录
                    CustomerWalletLog customerWalletLog = new CustomerWalletLog();
                    customerWalletLog.setCustomerId(supriorId);
                    customerWalletLog.setAmount(integral);
                    customerWalletLog.setPayMode(3);
                    customerWalletLog.setType(2);
                    customerWalletLog.setCreateDate(new Date());
                    customerWalletLog.setStatus(1);
                    customerWalletLog.setCurrentBalance(balance);
                    customerWalletLog.setRemark("邀请人奖励!");
                    customerWalletLogService.save(customerWalletLog);
                    customerWallet.setScore(balance);
                    walletService.update(customerWallet);
                }
            }
            customer.setSuperiorId(supriorId);
            customer.setInvitationTime(new Date());
        }
        customerService.update(customer);
        result.setS(0, "手机绑定操作成功");
        return result;
    }

    //保存用户手机(注:通过邀请,用户未注册请求接口)
    @RequestMapping(value = "savePhone", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取用户列表", tags = {"用户管理"}, notes = "获取用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "validateCode", value = "验证码", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "id", value = "上级id", required = false, dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "token", value = "token", required = false, dataType = "String", paramType = "query")
    })
    public Result savePhone(String token, String phone, String validateCode, @RequestParam(value = "supriorId", required = false) String supriorId, HttpServletRequest request) {
        Logger log = Logger.getLogger("CustomerController.class");
        Result result = new Result();
        Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
        if (ObjectUtils.isEmpty(customer)) {
            result.setF("用户未登录");
            result.setData(-1);
            return result;
        }
        //根据手机号查询使用是否已注册
        Customer byPhone = customerService.getByPhone(phone);
        if (ObjectUtils.isNotEmpty(byPhone)) {
            result.setF("手机号已被注册");
            result.setData(0);
            return result;
        }
        RedisUtil redisUtil = RedisUtil.getInstance();
        String redisCode = (String) redisUtil.getFromRedis(phone + "time");
        if (validateCode == null || !validateCode.equals(redisCode)) {
            result.setF("验证码错误");
            return result;
        }
        customer.setPhone(phone);
        //判断是否通过邀请码注册
        if (supriorId != null && !supriorId.equals("undefined")) {
            Long supriorid = Long.valueOf(supriorId);
            Customer parentCustomer = customerService.getIdCustomer(supriorid);//上级对象
            //获取积分返现值  (显示)
            IntegralRules integralRules = integralRulesService.selOneIntegralRules(16L);
            if (integralRules != null) {
                //积分规则 奖励数量
                Double integral = integralRules.getIntegral();
                //获取上级用户钱包
                Wallet customerWallet = walletService.getByCustomerId(supriorid);
                //判断上级是否有钱包
                if (customerWallet != null) {
                    //获取上级钱包当前积分
                    Double amount = customerWallet.getScore();
                    Double balance = BigDecimalUtils.add(amount, integral).doubleValue();
                    customerWallet.setScore(balance);
                    walletService.update(customerWallet);
                    //钱包出入账记录
                    CustomerWalletLog customerWalletLog = new CustomerWalletLog();
                    customerWalletLog.setCustomerId(supriorid);
                    customerWalletLog.setAmount(integral);
                    customerWalletLog.setPayMode(3);
                    customerWalletLog.setType(2);
                    customerWalletLog.setCreateDate(new Date());
                    customerWalletLog.setStatus(1);
                    customerWalletLog.setCurrentBalance(balance);
                    customerWalletLog.setRemark("邀请人奖励!");
                    customerWalletLogService.save(customerWalletLog);
                }else {
                    Wallet wallet = new Wallet();
                    wallet.setCustomerId(supriorid);
                    wallet.setBurse(0.0);
                    wallet.setScore(1.0);
                    wallet.setAmountInBurse(0.0);
                    wallet.setAmountInScore(0.0);
                    wallet.setAmountOutBurse(0.0);
                    wallet.setAmountOutScore(0);
                    walletService.save(wallet);
                    //钱包出入账记录
                    CustomerWalletLog customerWalletLog = new CustomerWalletLog();
                    customerWalletLog.setCustomerId(supriorid);
                    customerWalletLog.setAmount(1.0);
                    customerWalletLog.setPayMode(3);
                    customerWalletLog.setType(2);
                    customerWalletLog.setCreateDate(new Date());
                    customerWalletLog.setStatus(1);
                    customerWalletLog.setCurrentBalance(1.0);
                    customerWalletLog.setRemark("邀请人奖励!");
                    customerWalletLogService.save(customerWalletLog);
                }
            }
            customer.setSuperiorId(Long.valueOf(supriorId));
            customer.setInvitationTime(new Date());
        } else {
            Wallet byCustomerId = walletService.getByCustomerId(customer.getId());
            if (byCustomerId == null) {
                //不是通过邀请码注册
                Wallet wallet = new Wallet();
                wallet.setCustomerId(customer.getId());
                wallet.setBurse(0.0);
                wallet.setScore(0.0);
                wallet.setAmountInBurse(0.0);
                wallet.setAmountInScore(0.0);
                wallet.setAmountOutBurse(0.0);
                wallet.setAmountOutScore(0);
                walletService.save(wallet);
            }
        }
        String errorMsg = "";
        /** 生成邀请码以及收款二维码 如果此处创建失败 在用户点击查看二维码时候再次生成**/
        Result invitationCodeUrl = new Result();
        invitationCodeUrl = WxUtil.createInvitationCode(customer, request, result);
        if (invitationCodeUrl.isSuccess()) {
            JSONObject jsonObject = JSONObject.fromObject(invitationCodeUrl);
            String data = jsonObject.get("data").toString();
            String da = data.substring(5, data.length() - 2);
            customer.setInviteCode(da);
        } else {
            log.info("创建邀请码失败!");
            errorMsg = "创建邀请码失败!";
        }
        Result receivablesCodeUrl = new Result();
        String receivablesCodePath = SystemConfig.instants().getValue("receivablesCodeUrl") + "id=" + customer.getId();
        receivablesCodeUrl = WxUtil.createReceivablesCode(customer, receivablesCodePath, request, result);
        if (receivablesCodeUrl.isSuccess()) {
            JSONObject jsonObject = JSONObject.fromObject(receivablesCodeUrl);
            customer.setReceivablesCode(jsonObject.get("data").toString());
        } else {
            log.info("创建收款码失败!");
            errorMsg += "创建收款码失败!";
        }

        customerService.update(customer);
        result.setS("手机绑定操作成功", errorMsg);
        return result;
    }

    //wxLogin
    @RequestMapping("wxLogin")
    @ResponseBody
    public Result wxLogin(String jsCode, HttpServletRequest request) {
        Result result = new Result();
        Logger log = Logger.getLogger("CustomerController.class");
        try {
            //获取openID 和sessionkey
            String s = ToolsUtil.getopenID(jsCode);
            JSONObject jsonObject = JSONObject.fromObject(s);
            String openid = (String) jsonObject.get("openid");
            String session_key = (String) jsonObject.get("session_key");
            String redis_token_time = SystemConfig.instants().getValue("redis_token_time");
            String random = ToolsUtil.getRandom(32, 4);
            String wxOpenId = openid + ";" + session_key;
            RedisUtil.getInstance().putInRedis(random, wxOpenId, Integer.parseInt(redis_token_time));
            if (openid != null && openid != "") {
                Customer byOpenID = customerService.getByOpenID(openid);
                if (byOpenID == null) {
                    Customer customer = new Customer();
                    customer.setOpenId(openid);
                    int save = customerService.save(customer);
                    Customer customers = customerService.getCustomerByOpenId(openid);
                    Wallet wallet = new Wallet();
                    wallet.setCustomerId(customers.getId());
                    wallet.setBurse(0.0);
                    wallet.setScore(0.0);
                    wallet.setAmountInBurse(0.0);
                    wallet.setAmountInScore(0.0);
                    wallet.setAmountOutBurse(0.0);
                    wallet.setAmountOutScore(0);
                    walletService.save(wallet);
                }
            }
            result.setS("操作成功");
            log.info("==wxLogin===用户授权登录更新登录状态===");
            result.setData(random);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //用户是否授权过手机号
    @RequestMapping("isPhone")
    @ResponseBody
    public Result isPhone(String token) {
        Result result = new Result();
        String openid_sessionKey = (String) RedisUtil.getInstance().getFromRedis(token);
        System.out.println("验证手机,openid_sessionKey=>" + openid_sessionKey);
        Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
        if (ObjectUtils.isEmpty(customer)) {
            result.setF("用户未登录");
            result.setData(-1);
            return result;
        }
        String redis_token_time = SystemConfig.instants().getValue("redis_token_time");
        RedisUtil.getInstance().putInRedis(token, openid_sessionKey, Integer.parseInt(redis_token_time));
        if (ObjectUtils.isEmpty(customer.getPhone())) {
            result.setS("用户未授权手机号");
            result.setData(1);
            return result;
        } else {
            result.setF("用户已授权手机号");
            result.setData(0);
            return result;
        }
    }

    //获取用户openID
    @RequestMapping("getopenID")
    @ResponseBody
    public Result getopenID(String jsCode) {
        String resultStr = ToolsUtil.getopenID(jsCode);
        Logger log = Logger.getLogger("CustomerController.class");
        Result result = new Result();
        if (ObjectUtils.isEmpty(resultStr)) {
            log.info("网络异常,请求失败");
            result.setF("网络异常,请求失败");
            return result;
        }
        JSONObject jsonObject = JSONObject.fromObject(resultStr);
        log.info("返回的结果==>" + jsonObject);
        if (resultStr.indexOf("errcode") != -1 || resultStr.indexOf("errmsg") != -1) {
            //参数异常
            result.setF("请求错误");
            Object errcode = jsonObject.get("errcode");
            Object errmsg = jsonObject.get("errmsg");
            log.info("请求错误,errcode==>" + errcode + ",errmsg==>" + errmsg);
            return result;
        }
        if (resultStr.indexOf("openid") != -1 && resultStr.indexOf("session_key") != -1) {
            log.info("请求成功");
            String openid = (String) jsonObject.get("openid");
            result.setS("请求成功");
            result.setData(openid);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "getValidateCode", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "wxapp发送验证码", tags = {"wxapp发送验证码"}, notes = "发送验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "手机号", required = false, dataType = "String", paramType = "query"),
    })
    public Result getValidateCode(@RequestParam("phone") String phone) {
        Result result = new Result();
        //String addr = request.getRemoteAddr();
        try {
            if (com.yunchao.hsh.utils.ObjectUtils.isNotEmpty(phone)) {
                boolean pflag = phone.matches("^1[0-9]{10}$");
                if (ObjectUtils.isEmpty(phone) || !pflag) {
                    result.setF("号码输入错误");
                } else {
                    Customer byPhone = customerService.getByPhone(phone);
                    if (ObjectUtils.isNotEmpty(byPhone)) {
                        result.setF("号码已被注册");
                    } else {
                        RedisUtil redisUtil = RedisUtil.getInstance();
                        String redisCode = (String) redisUtil.getFromRedis(phone + "time");
                        if (ObjectUtils.isEmpty(redisCode)) {
                            String random = ALiYunSendMsgUtils.sendMsg(phone, Constant.LOGIN_CODE_MSG, "");
                            if ("OK".equals(random)) {
//                                redisUtil.putInRedis(phone + "time", random, 60); //限制60秒内只允许一个手机号码发送一次验证码
//                                redisUtil.putInRedis(phone, random, 60 * 5); //验证码5分钟之内有效
                                result.setS("操作成功");
                            } else {
                                result.setF("验证码发送失败");
                            }
                        } else {  //60秒内只能对一个IP发送一次短信
                            result.setF("60秒内只能发送一次");
                        }
                    }
                }
            } else {
                result.setF("手机号数据异常");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setF("系统异常");
        }
        return result;
    }

    /**
     * 个人中心(手机号码  积分  邀请人  余额)
     */
    @RequestMapping(value = "getPersonal", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "wxapp个人中心", tags = {"wxapp个人中心"}, notes = "个人中心")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", required = false, dataType = "String", paramType = "query"),
    })
    public Result getPersonal(String token) {
        Result result = new Result();
        try {
            Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
            if (ObjectUtils.isEmpty(customer)) {
                result.setF("用户未登录");
                result.setData(-1);
                return result;
            } else {
                int count = customerService.getCount(customer.getId());
                customer.setCount(count);
                result.setS("", customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 邀请人明细
     */
    @RequestMapping(value = "getInviter")
    @ResponseBody
    @ApiOperation(value = "wxapp获取邀请人明细", tags = {"wxapp获取邀请人明细"}, notes = "获取邀请人明细")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", required = false, dataType = "String", paramType = "query"),
    })
    public Result getInviter(String token, int page, @RequestParam(defaultValue = "10") int pageSize) {
        Result result = new Result();
        try {
            if (StringUtils.isNotBlank(token)) {
                Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
                if (customer != null) {
                    Long id = customer.getId();
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("superiorId", id);
                    PageInfo<Customer> pageSuperior = customerService.findPageSuperior(map, page, pageSize);
                    result.setS("", pageSuperior);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取余额
     */
    @RequestMapping(value = "getBalance", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "wxapp获取余额", tags = {"wxapp获取余额"}, notes = "获取余额")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户token", required = false, dataType = "String", paramType = "query"),
    })
    public Result getBalance(String token) {
        Result result = new Result();
        if (token != null) {
            Customer customer = customerService.getCustomerByOpenId(ToolsUtil.getRedisOpenId(token));
            if (customer != null) {
                Long id = customer.getId();
                Wallet wallet = walletService.getByCustomerId(id);
                if (wallet == null) {
                    result.setF("余额获取失败");
                }
                result.setS("", wallet);
                return result;
            }
            return result.setF("用户未登录");
        }
        return null;
    }

    /**
     * 通过id获取用户信息
     */
    @RequestMapping(value = "getCustomerById")
    @ResponseBody
    public Result getCustomerById(Long id) {
        Result result = new Result();
        if (id != null) {
            Customer customer = customerService.getIdCustomer(id);
            if (customer != null) {
                result.setS("", customer);
                return result;
            }
        }
        return null;
    }

    /**
     * 获取用户邀请码
     */
    @RequestMapping("findInviteCode")
    @ResponseBody
    public Result findInviteCode(String token, HttpServletRequest request) {
        Result result = new Result();
        Logger log = Logger.getLogger("CustomerController.class");
        try {
            Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
            //获取积分返现值  (显示)
            IntegralRules integralRules = integralRulesService.selOneIntegralRules(16L);
           //图片地址
            String inviteCode = null;
            //用户id
            Long customerId=null;
            if (customer != null) {
                inviteCode = customer.getInviteCode();//获取邀请码地址
                customerId=customer.getId();
                if (inviteCode != null && !"".equals(inviteCode)) {
                    String replace = inviteCode.replace("\\\\", "/");//图片地址转译
                    return result.setS("", replace, integralRules.getIntegral(),customerId);
                } else {
                    String errorMsg = "";
                    Result invitationCodeUrl = new Result();
                    invitationCodeUrl = WxUtil.createInvitationCode(customer, request, result);
                    if (invitationCodeUrl.isSuccess()) {
                        JSONObject jsonObject = JSONObject.fromObject(invitationCodeUrl);
                        String data = jsonObject.get("data").toString();
                        String da = data.substring(5, data.length() - 2);
                        customer.setInviteCode(da);
                        customerService.update(customer);
                        return result.setS("", da.replace("\\\\", "/"), integralRules.getIntegral(),customerId);
                    } else {
                        log.info("创建邀请码失败!");
                        errorMsg = "创建邀请码失败!";
                        return result.setF("邀请码生成失败!");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户收款码
     */
    @RequestMapping("findReceivablesCode")
    @ResponseBody
    public Result findReceivablesCode(String token, HttpServletRequest request) {
        Result result = new Result();
        Logger log = Logger.getLogger("CustomerController.class");
        try {
            Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
            String receivablesCode = null;
            if (customer != null) {
                receivablesCode = customer.getReceivablesCode();
                if (receivablesCode != null && receivablesCode != "") {
                    return result.setS("", receivablesCode.replace("\\", "/"));
                } else {
                    String errorMsg = "";
                    Result receivablesCodeUrl = new Result();
                    String receivablesCodePath = SystemConfig.instants().getValue("receivablesCodeUrl") + "id=" + customer.getId();
                    receivablesCodeUrl = WxUtil.createReceivablesCode(customer, receivablesCodePath, request, result);
                    if (receivablesCodeUrl.isSuccess()) {
                        JSONObject jsonObject = JSONObject.fromObject(receivablesCodeUrl);
                        receivablesCode = jsonObject.get("data").toString();
                        customer.setReceivablesCode(jsonObject.get("data").toString());
                        customerService.update(customer);
                        return result.setS("", receivablesCode.replace("\\", "/"));
                    } else {
                        log.info("创建收款码失败!");
                        errorMsg += "创建收款码失败!";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 扫码接口
     * <p>
     * //  1  已经有上级跳index   2    已登陆，已绑手机号， 3 没有上级，没有绑定手机号
     */
    @RequestMapping(value = "sweepCode", method = RequestMethod.GET)
    @ResponseBody
    public Result sweepCode(@RequestParam(value = "token", required = false) String token, @RequestParam(value = "parid", required = false) String parid) {
        log.info("邀请扫码请求参数：===============token === :{} + ==== + parid============{}", token, parid);
        Result result = new Result();
        if (StringUtils.isBlank(token)) {
            // token 是空的，
            if (!parid.equals("undefined")) {
                Customer supperCustomer = this.customerService.getIdCustomer(Long.valueOf(parid));
                if (supperCustomer != null) {
                    return result.setS(supperCustomer, 3);
                } else {
                    return result.setS(supperCustomer, 3);
                }
            } else {
                return result.setF("生成邀请码失败", 1);
            }
        } else {
            //token 不是空的
            Customer customer = customerService.getByOpenID(ToolsUtil.getRedisOpenId(token));
            if(StringUtils.isNotBlank(parid) && customer.getId().equals(Long.valueOf(parid))){
                return result.setF("自己不能邀请自己", 1);
            }
            if (customer != null) {
                //判断手机号是否绑定
                if (customer.getPhone() != null) {
                    if (customer.getSuperiorId() != null) {
                        return result.setF("已绑定上级", 1);
                    } else {
                        Customer supperCustomer = this.customerService.getIdCustomer(Long.valueOf(parid));
                        return result.setS(supperCustomer, 2);
                    }
                } else {
                    //判断是否判定上级关系
                    if (customer.getSuperiorId() != null) {
                        return result.setF("已绑定上级", 1);
                    } else {
                        if (!parid.equals("undefined")){
                            Customer supperCustomer = this.customerService.getIdCustomer(Long.valueOf(parid));
                            return result.setS(supperCustomer, 3);
                    }

                    }
                }
            }
        }
        return null;
    }
}


