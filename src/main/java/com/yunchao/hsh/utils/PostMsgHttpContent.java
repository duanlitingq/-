package com.yunchao.hsh.utils;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import static com.up72.framework.util.ObjectUtils.isNotEmpty;

/**
 * 短信接口
 */



public class PostMsgHttpContent {
    static Logger logger = Logger.getLogger(PostMsgHttpContent.class);

//    private static final String URL = "http://115.28.112.245:8082/SendMT/SendMessage" ;
//    private static final String USERNAME = "qituwl" ;
//    private static final String PASSWORD = "123456" ;

    private static final String URL = "http://115.28.112.245:8082/SendMT/SendMessage" ;
    private static final String USERNAME = "youyouyy" ;
    private static final String PASSWORD = "youyou@4171014" ;

    /**
     * 发送短信-------单条信息发送
     * @param iphone 手机号
     * @param message 短信内容
     */
    public static String sendMessage(String iphone, String message) {
        if(isNotEmpty(iphone)&&isNotEmpty(message)) {
            logger.info("进入-----单条短信发送");
            try {
                StringBuilder params = new StringBuilder();
                params.append("?UserName=");
                params.append(USERNAME);
                params.append("&UserPass=");
                params.append(PASSWORD);
                params.append("&Mobile=");
                params.append(iphone);
                params.append("&Content=");
                params.append(URLEncoder.encode(message, "UTF-8"));
                String httpcontent = URL + params.toString();
                String result = httpGetSend(httpcontent, "UTF-8");
                logger.info("短信返回结果 ： " + result.substring(0,2) );
                if ("03".equals(result.substring(0,2))) {
                    return "Success";
                } else {
                    logger.info("发送短信返回结果不正确");
                    return "Error";
                }
            } catch (Exception e) {
                logger.info(" ===短信参数有误 " + e.getMessage());
                return "Error";
            }
        }else {
            logger.info("====手机号或者短信为NULL===");
        }

        return "Error";
    }

    /**
     * 发送短信--------群发，多号码一内容
     * @param iphones 手机号集合
     * @param message 短信内容
     */
    public static String sendMessages(List<String> iphones, String message) {
        try {
            logger.info("进入-----群发");
            if(isNotEmpty(iphones)&&isNotEmpty(message)) {
                StringBuffer paramPhone = new StringBuffer();
                for (int i = 0; i < iphones.size(); i++) {
                    paramPhone.append(iphones.get(i));
                    if (i != iphones.size() - 1) {
                        paramPhone.append(",");
                    }
                }
                StringBuilder params = new StringBuilder();
                params.append("?UserName=");
                params.append(USERNAME);
                params.append("&UserPass=");
                params.append(PASSWORD);
                params.append("&Mobile=");
                params.append(paramPhone);
                params.append("&Content=");
                params.append(URLEncoder.encode(message, "UTF-8"));
                String httpcontent = URL + params.toString();
                String result = httpGetSend(httpcontent, "UTF-8");
                logger.info("短信返回结果 ： " + result.substring(0,2) );
                if ("00".equals(result.substring(0,2))) {
                    return "Success";
                } else {
                    logger.info("群发短信返回结果不正确");
                    return "Error";
                }
            }else{
                logger.info("====手机号或者短信为NULL===");
                return "Error";
            }
        }catch (IOException e){
            logger.info(" ===群发短信短信错误 " + e.getMessage());
            return "Error";
        }
    }


    /**
     * 发送短信后返回的结果
     * @param snedUrl
     * @param encoded
     * @return
     */
    public static String httpGetSend(String snedUrl,String encoded)
    {
        String urlPath = snedUrl;
        StringBuffer sbf = new StringBuffer("");
        BufferedReader reader = null;
        HttpURLConnection uc = null;

        try {
            URL url = new URL(urlPath);
            uc = (HttpURLConnection) url.openConnection();
            uc.setConnectTimeout(30000);
            uc.setReadTimeout(30000);
            uc.setRequestMethod("GET");
            uc.setDoOutput(true);
            uc.setDoInput(true);

            uc.connect();
            reader = new BufferedReader(new InputStreamReader(uc.getInputStream())); // 读取服务器响应信息
            String line;
            while ((line = reader.readLine()) != null) {
                sbf.append(line);
            }
            reader.close();
            uc.disconnect();
            return sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String str = "您好，您的验证码是[1234]。";
        sendMessage("17319022031",str);
    }



}


