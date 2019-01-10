package com.yunchao.hsh.utils;

import com.up72.common.util.Base64Util;
import com.up72.framework.util.ObjectUtils;
import com.yunchao.hsh.utils.redis.RedisUtil;
import com.yunchao.hsh.utils.superdir.sub.Result;
import net.sf.json.JSONObject;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.*;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Created by wangqi on 2017/12/5
 */
public class ToolsUtil {
    //获取参数相关

    /**
     * 获取参数Map
     *
     * @param req    请求
     * @param fields 字段数组
     * @return
     */
//    public static Map getParamMap(HttpServletRequest req, String[] fields) {
//        Map params = new HashMap();
//        for (String field : fields) {
//            if (StringUtils.isBlank(field)) continue;
//            String value = req.getParameter(field);
//            if (value == null) continue;
//            value = value.replaceAll("'", "");
//            if (StringUtils.isBlank(value)) continue;
//            params.put(field, value);
//        }
//        return params;
//    }
//    ----------------------------------------------------------------------------------------------------------------
    //时间相关

    /**
     * 标记如果为0将时间2016-01-01 转换成2016-01-01 00：00：00，如果为1将时间2016-01-01 转换成2016-01-01 23：59：59
     *
     * @param date date 对象
     * @param flag 标记如果为0将时间2016-01-01 转换成2016-01-01 00：00：00，如果为1将时间2016-01-01 转换成2016-01-01 23：59：59
     * @return date 对象
     */
    public static Date weeHours(Date date, int flag) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        //时分秒（毫秒数）
        long millisecond = hour * 60 * 60 * 1000 + minute * 60 * 1000 + second * 1000;
        //凌晨00:00:00
        cal.setTimeInMillis(cal.getTimeInMillis() - millisecond);

        if (flag == 0) {
            return cal.getTime();
        } else if (flag == 1) {
            //凌晨23:59:59
            cal.setTimeInMillis(cal.getTimeInMillis() + 23 * 60 * 60 * 1000 + 59 * 60 * 1000 + 59 * 1000);
        }
        return cal.getTime();
    }

//    ----------------------------------------------------------------------------------------------------------

    /**
     * 获取验证码用到了
     * 获取不同字符组成的字符串
     *
     * @param len
     * @param type
     * @return
     */
    public static String getRandom(int len, int type) {
        StringBuffer buffer = null;
        Random r = new Random();
        //设计随机数种子
        r.setSeed(new Date().getTime());

        switch (type) {
            case 1:
                buffer = new StringBuffer("0123456789");
                break;
            case 2:
                buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
                break;
            case 3:
                buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            case 4:
                buffer = new StringBuffer(
                        "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
                break;
            default:
                buffer = new StringBuffer(UUID.randomUUID().toString());
                break;
        }

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(buffer.charAt(r.nextInt(buffer.length())));
        }

        return sb.toString();
    }
//---------------------------------------------------------------------------------------------------------

    /**
     * 是否包含汉字
     */
    public static boolean hasChineseCharacter(String content) {
        if (content == null) {
            return false;
        }
        return content.getBytes().length > content.length();
    }


    /**
     * 处理异常(用于Result)
     *
     * @param e      Exception
     * @param result Result
     */
    public static void handleResultExcp(Exception e, Result result) {
        if (hasChineseCharacter(e.getMessage())) {
            result.setMessage(e.getMessage()).setSuccess(false);
        } else {
            result.setMessage("系统出现异常，无法处理您的请求。").setSuccess(false);
            e.printStackTrace();
        }
    }

    public static String parseToPath(String str) {
        str = str.replace('\\', '/');
        str = str.replaceAll("/{2,}", "/");
        return str;
    }

    /**
     * 发送http get请求
     *
     * @param getUrl
     * @return
     */
    public static String sendGetRequest(String getUrl) {
        StringBuffer sb = new StringBuffer();
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            URL url = new URL(getUrl);
            URLConnection urlConnection = url.openConnection();
            urlConnection.setAllowUserInteraction(false);
            isr = new InputStreamReader(url.openStream());
            br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {
        JSONObject wxPhone = getWxPhone("2chn96wNYKTQSJ5vGdYA+Uzsw/vvvl0Jb2DqqrHlefombjASVLxlF3eC4BIWuSynRJXwlkaom+OJ64noKNessrfDs8UabSd4W+9cFi1bhTPzkshy9JLBDn+ZbijbedFKSuAVBqD4BOK1xD6VEdWN4NVgqdZSV1Ldgh58jEwe7oOokXMQA4aXvwCW60E1LHslQbI6RTr09OTS29/zdEx1Tg==", "LYLAce4xP92isv+o0lPgIQ==", "hFDOkYrcdAsuUSr3eefFEA==");
        Object purePhoneNumber = wxPhone.get("purePhoneNumber");
        System.err.println("purePhoneNumber:" + purePhoneNumber);
        System.err.println("wxPhone:" + wxPhone);
    }

    public static String getopenID(String jsCode) {
        java.util.logging.Logger log = java.util.logging.Logger.getLogger("ToolsUtil.class");
        String wx_appId = SystemConfig.instants().getValue("wx_appId");
        String wx_appSecret = SystemConfig.instants().getValue("wx_appSecret");
        String getOpenIdUrl = Constant.GET_OPENID_URL;
        log.info("换取openID,wx_appId==>" + wx_appId + "," + "wx_appSecret==>" + wx_appSecret);
        getOpenIdUrl = getOpenIdUrl.replace("[APPID]", wx_appId).replace("[SECRET]", wx_appSecret).replace("[JSCODE]", jsCode);
        log.info("请求地址==>" + getOpenIdUrl);
        // {"openid": "OPENID", "session_key": "SESSIONKEY",}  返回正常的数据
        String openid_key = ToolsUtil.sendGetRequest(getOpenIdUrl);
        if (ObjectUtils.isEmpty(openid_key)) {
            log.info("网络异常,请求失败");
            return null;
        }
        return openid_key;
    }

    public static JSONObject getWxPhone(String encryptedData, String sessionKey, String iv) throws Exception {
        // 被加密的数据
        byte[] dataByte = Base64Util.getFromBASE64(encryptedData);
        // 加密秘钥
        byte[] keyByte = Base64Util.getFromBASE64(sessionKey);
        // 偏移量
        byte[] ivByte = Base64Util.getFromBASE64(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化
            Security.addProvider(new BouncyCastleProvider());
            //   Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding","BC");
            Cipher cipher = Cipher.getInstance("AES/CBC/NOPadding");
            //    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "ISO-8859-1");
                System.err.println("手机号结果:" + result);
                return JSONObject.fromObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    public static String getRedisOpenId(String key) {
        String fromRedis = (String) RedisUtil.getInstance().getFromRedis(key);
        if (ObjectUtils.isNotEmpty(fromRedis)) {
            return fromRedis.split(";")[0];
        }
        return null;
    }

    public static String getRedisSessionKey(String key) {
        String fromRedis = (String) RedisUtil.getInstance().getFromRedis(key);
        if (ObjectUtils.isNotEmpty(fromRedis)) {
            return fromRedis.split(";")[1];
        }
        return null;
    }
    /** 过滤表情与其他特殊字符，只保留汉字、字母、数字、下划线 */
    public static String strFilter(String srcString) throws PatternSyntaxException {
        if (srcString == null) return null;
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5_\\-!a-zA-Z0-9]+$");
        StringBuilder newName = new StringBuilder();
        for (int i = 0; i < srcString.length(); i++) {
            String tmp = srcString.substring(i, i + 1);
            if (pattern.matcher(tmp).matches()) {
                newName.append(tmp);
            }
        }
        return newName.toString();
    }

    /**
     * ShiroHttpServletRequest强转为MultipartHttpServletRequest
     */
//    public static MultipartHttpServletRequest getMultipartRequest(HttpServletRequest request) {
//        ShiroHttpServletRequest shiroRequest = (ShiroHttpServletRequest) request;
//        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
//        MultipartHttpServletRequest multipartRequest = commonsMultipartResolver.resolveMultipart((HttpServletRequest) shiroRequest.getRequest());
//        return multipartRequest;
//    }


}
