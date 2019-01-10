package com.yunchao.hsh.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by songyanan on 2015/4/2.
 */
public class MnUtil {

    private static Log logger =  LogFactory.getLog(MnUtil.class);
    /** 汉字匹配模式 */
    private static final Pattern CHINESE_CHARACTER_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]");
    /**
     * 处理返回结果为错误页面（或登录页面）的异常，会返回到errorPage.jsp或login.jsp页面
     * @param e 异常对象
     * @return String "/admin/lmsp/common/errorPage" 或
     * @author HuKaiXuan 2014-9-16 下午3:06:08
     */
    public static String handleExceptionOfErrorPage(Exception e, ModelMap model) {
        String msg = "";
        if(e == null || StringUtils.isBlank(e.getMessage())) {
            msg = MnMsgCnst.SYSTEM_ERROR;
        } else {
            msg = e.getMessage();
            if(!hasChineseCharacter(msg)) { // 如果没有汉字
                msg =MnMsgCnst.SYSTEM_ERROR;
            }
        }
        logger.error(msg,e);
        e.printStackTrace();
        model.addAttribute("errorMsg", msg);
        return "/admin/shop/include/errorPage";
    }

    /** 是否包含汉字 */
    public static boolean hasChineseCharacter(String content){
        if(content == null) return false;
        return content.getBytes().length > content.length();
    }

    /**
     * 获取properties文件对应的Properties对象
     * @return Properties
     */
    public static Properties getProperties(String fileName) throws IOException{
        InputStream fis = null;
        try {
            Properties properties = new Properties();
            String path = new File(MnUtil.class.getResource("/").getFile()).getAbsolutePath() + "/" + fileName;
            properties.load(new InputStreamReader(new FileInputStream(path), "UTF-8"));
            return properties;
        } finally {
            if(fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 保存properties文件
     * @param properties
     * @author HuKaiXuan 2014-10-21 下午3:08:49
     */
    public static void saveProperties(Properties properties,String fileName) throws Exception {
        if(properties == null) {
            return;
        }
        OutputStream fos = null;
        try {
            String path = new File(MnUtil.class.getResource("/").getFile()).getAbsolutePath() + "/" + fileName;
            fos = new FileOutputStream(path);
            properties.store(fos, null);
        } finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getIpAddr(HttpServletRequest request)  {
        String ip = request.getHeader(" x-forwarded-for ");
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getHeader(" WL-Proxy-Client-IP ");
        }
        if (ip == null || ip.length() == 0 || " unknown ".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public static String md5(String source) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(source.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        byte[] byteArray = messageDigest.digest();

        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                md5StrBuff.append("0").append(
                        Integer.toHexString(0xFF & byteArray[i]));
            } else {
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        }

        return md5StrBuff.toString();
    }

    /*
   *获取指定长度位的随机数
   */
    public static String codeNum(int count){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<count;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }

    /** 获取方便识别的当前时间毫秒数，如20151103173941521 */
    /** 将日期格式化为20121212121212222的格式，用于转换成long类型的数据 */
    //by 隗鹏
    // time:2018-11-21 14:20
    public static final SimpleDateFormat SDF_TIME_NUM = new SimpleDateFormat("yyyyMMddHHmmssSSS");
    public static Long getCurTime(){
        return Long.parseLong(SDF_TIME_NUM.format(new Date()));
    }
}
