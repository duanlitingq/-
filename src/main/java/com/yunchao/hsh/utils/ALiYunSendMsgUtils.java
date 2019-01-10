package com.yunchao.hsh.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.yunchao.hsh.constant.Constant;
import com.yunchao.hsh.utils.redis.RedisUtil;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ALiYunSendMsgUtils {
    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIWZLJnTDbRLUr";
    static final String accessKeySecret = "XNZDui9V4l91jbqPhttfFir2UmF0fA";

    public static String sendMsg(String phone,String msgCode,String money){
        System.out.println(phone+":"+msgCode+":"+money+"=============================================================");
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e1) {
            e1.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //随机生成六位验证码
        int cods = (int)((Math.random()*9+1)*100000);
        String code=cods+"";
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到，你在签名管理里的内容
        request.setSignName("越用越有平台");
        //必填:短信模板-可在短信控制台中找到，你模板管理里的模板编号
        request.setTemplateCode(msgCode);
        String message = "";
        JSONObject obj = new JSONObject();
        SimpleDateFormat ymh = new SimpleDateFormat("yyyyMMdd");
        String time = ymh.format(new Date());
        if("SMS_152286355".equals(msgCode)){//登陆验证码
            obj.put("code",code);
            RedisUtil redisUtil = RedisUtil.getInstance();
            redisUtil.putInRedis(phone+"time",code,60);
            String fromRedis =(String) redisUtil.getFromRedis(phone + "time");
            System.out.println(fromRedis+"=============================================================");
        }else if("SMS_152286359".equals(msgCode)){//提现展示

        }else if("SMS_152286581".equals(msgCode)){//邀请好友提示

        }else if("SMS_152286583".equals(msgCode)){//转账到账
            obj.put("money",money);
        }else if("SMS_152548161".equals(msgCode)){//提现申请提示
            obj.put("money",money);
        }else if("SMS_152850395".equals(msgCode)){
            obj = JSONObject.fromObject(money);
        }
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //"{\"code\":\""+code+"\"}"
        System.out.println(obj.toString());
        request.setTemplateParam(obj.toString());
        //hint 此处可能会抛出异常，注意catch

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        //获取发送状态
        String cod = sendSmsResponse.getCode();
        System.out.println(cod);
        return cod;
    }

}
