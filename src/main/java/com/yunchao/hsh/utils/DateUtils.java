package com.yunchao.hsh.utils;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 日期转换工具类
 *
 * @ClassName: DateUtils
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/8 17:38
 * @Version: 1.0
 */
public class DateUtils {

    private static SimpleDateFormat sdf = null;

    public static final String YEAR_MONTH_DAY_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY_SECOND2 = "yyyy/MM/dd HH:mm:ss";
    public static final String YEAR_MONTH_DAY_SECOND3 = "yyyy年MM月dd日 HH时mm分ss秒";
    public static final String YEAR_MONTH_DAY_SECOND4 = "yyyyMMddHHmmss";
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String YEAR_MONTH_DAY2 = "yyyy年MM月dd日";
    private static final String PATTERN_FORMAT_1 = "YYYY-MM-DD";

    private static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    public static SimpleDateFormat getDateFormat(String format) {
        SimpleDateFormat sdf = threadLocal.get();
        if (sdf == null) {
            if (StringUtils.isBlank(format)) {
                sdf = new SimpleDateFormat(YEAR_MONTH_DAY_SECOND);
            } else {
                sdf = new SimpleDateFormat(format);
            }
            threadLocal.set(sdf);
        }
        return sdf;
    }

    public static SimpleDateFormat getDateFormat() {
        SimpleDateFormat df = threadLocal.get();
        df = new SimpleDateFormat(YEAR_MONTH_DAY_SECOND);

        threadLocal.set(df);

        return df;
    }
    public static SimpleDateFormat getDateFormatStamp() {
        SimpleDateFormat df = threadLocal.get();
        df = new SimpleDateFormat(YEAR_MONTH_DAY_SECOND4);

        threadLocal.set(df);

        return df;
    }

    /**
     * 日期转字符串 yyyy年MM月dd日 HH时mm分ss秒"
     *
     * @param date
     * @return
     */
    public static String dateToStr(Date date) {
        String str = "";
        try {
            str = getDateFormat().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 日期时间戳 yyyyMMddHHmmss"
     *
     * @param date
     * @return
     * by 隗鹏
     * time:2018-11-17 14:00
     */
    public static String dateToTimeStamp(Date date) {
        String str = "";
        try {
            str = getDateFormatStamp().format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public static void main(String[] args) {
        Date d = new Date();
        String s = dateToStr(d);
        System.out.println(s);
    }


    /**
     * 字符串转日期 yyyy年MM月dd日 HH时mm分ss秒"
     *
     * @param str
     * @return
     */
    public static Date StrToDate(String str) {

        sdf = new SimpleDateFormat(YEAR_MONTH_DAY_SECOND);
        Date date=new Date();
        try {
            date = sdf.parse(str);
//            System.out.println(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 格式为：yyyy-MM-dd
     * @return
     */
    public static String getToday(){
        sdf = new SimpleDateFormat(YEAR_MONTH_DAY);
        String format = sdf.format(new Date());
        return format;
    }
}
