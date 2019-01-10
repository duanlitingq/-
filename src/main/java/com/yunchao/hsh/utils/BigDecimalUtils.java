package com.yunchao.hsh.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * BigDecimal 类型转换工具类
 *
 * @ClassName: BigDecimalUtils
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/9 9:54
 * @Version: 1.0
 */
public class BigDecimalUtils {


    public static final BigDecimal HUNDRED = new BigDecimal("100.0");
    public static final BigDecimal zeroBigDecmail = BigDecimal.ZERO;
    public static final BigDecimal oneBigDecmail = BigDecimal.ONE;
    public static final Integer decimalScale = 2;// 默认位数

    /**
     * 字符串转Bigdecimal
     *
     * @param value 字符串表示形式
     * @param scale 标度(小数点位数)
     * @return
     */
    public static BigDecimal toBigDecimal(String value, int scale) {
        value = StringUtils.trim(value);
        return (StringUtils.isNotEmpty(value) ? new BigDecimal(value) : BigDecimal.ZERO).setScale(scale,
                BigDecimal.ROUND_CEILING);
    }

    /**
     * 转换BigDecimal对象(4位小数的)
     *
     * @param value 字符串表示形式
     * @return BigDecimal对象
     */
    public static BigDecimal toBigDecimalFour(BigDecimal value) {
        if (value == null) {
            return null;
        } else {
            if (value.scale() <= 4) {
                return value;
            } else {
                return value.setScale(decimalScale, BigDecimal.ROUND_HALF_UP);
            }
        }
    }

    /**
     * Object转换为BigDecimal
     *
     * @param o
     * @return
     */
    static public BigDecimal obj2big(Object o) {
        try {
            if (o instanceof BigDecimal)
                return (BigDecimal) o;
            if (o == null)
                return BigDecimal.ZERO;
            if (o instanceof Number) {
                if (o instanceof java.math.BigInteger)
                    return new BigDecimal(((java.math.BigInteger) o).toString());
                // [start] edit by wlp 2011-07-08 用非String类型作为参数来new
                // BigDecimal对象,会有计算精度问题!
                // return new BigDecimal(((Number) o).doubleValue());
                return new BigDecimal(((Number) o).toString());
                // [end] wlp
            }
            if (o instanceof String) {
                String temp = obj2str(o);
                if (temp != null) {
                    temp = temp.trim();
                    if (temp.length() == 0 || temp.equalsIgnoreCase("null")) {
                        return BigDecimal.ZERO;
                    } else {
                        return new BigDecimal(temp);
                    }
                }
            }
            return new BigDecimal(o.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /**
     * 把对象转换为字符串输出，该对象建议实现toString()方法 示例：StrUtil.obj2str("abc"); 结果：abc
     *
     * @param text 要转换的对象
     * @return 转换为的字符串
     */
    public static String obj2str(Object text) {
        return text != null ? text.toString() : null;
    }

    /**
     * 将big数值设置固定的小数位转化成字符串返回。 1.如果big为空，则返回空；
     * 2.如果小数位小于零，则直接处理数值的字符串形式，否则设置数值的小数位； 3.如果不过滤末尾零，则直接返回，否则删除末尾零；
     *
     * @param num     数值
     * @param scale   小数位
     * @param thDel   是否添加千分位分隔符
     * @param delZero 是否删除小数部分的末尾的零
     * @return
     */
    public static String toDecStr(Number num, int scale, boolean thDel, boolean delZero) {
        if (num == null)
            return null;
        String str = scale < 0 ? num.toString() : String.format("%1$." + scale + "f", num);
        StringBuilder sb = new StringBuilder(str);
        int dotIdx = str.indexOf('.');
        // 千分位
        if (thDel && (dotIdx > 3 || dotIdx < 0))
            for (int idx = dotIdx > 0 ? dotIdx - 3 : str.length() - 3; idx > 0; idx -= 3) {
                sb.insert(idx, ',');
            }
        // 删除尾零
        if (delZero && dotIdx > -1 && str.endsWith("0"))
            for (int idx = sb.length() - 1; idx > -1; idx--) {
                char ch = sb.charAt(idx);
                if (ch == '0')
                    sb.deleteCharAt(idx);
                else if (ch == '.') {
                    sb.deleteCharAt(idx);
                    break;
                } else
                    break;
            }
        return sb.toString();
    }

    /**
     * @param o1
     * @param o2
     * @return 两数之和
     */
    public static BigDecimal add(Object o1, Object o2) {
        return obj2big(o1).add(obj2big(o2));
    }

    /**
     * @param o1
     * @param o2
     * @return 两数之差
     */
    public static BigDecimal subtract(Object o1, Object o2) {
        return obj2big(o1).subtract(obj2big(o2));
    }

    /**
     * @param o1
     * @param o2
     * @return 两数乘积
     */
    public static BigDecimal multiply(Object o1, Object o2) {
        return BigDecimalUtils.obj2big(o1).multiply(BigDecimalUtils.obj2big(o2));
    }

    /**
     * @param o1
     * @param o2
     * @param scale
     * @param roundingMode
     * @return 两数相除
     */
    public static BigDecimal divide(Object o1, Object o2, int scale, int roundingMode) {
        return obj2big(o1).divide(obj2big(o2), scale, roundingMode);
    }

    /**
     * 两数相除
     *
     * @param o1
     * @param o2
     * @return
     */
    public static BigDecimal divideRoundHalfUp(Object o1, Object o2) {
        return obj2big(o1).divide(obj2big(o2), 2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 用辗转相除法求取两个数之间的最大公约数,两个整数才有意义 2012年12月30日14:42:15 许小波添加
     */
    public static BigDecimal getCommonDenominator(Object o1, Object o2) {
        BigDecimal b1 = BigDecimalUtils.obj2big(o1);
        BigDecimal b2 = BigDecimalUtils.obj2big(o2);
        if (b1.compareTo(BigDecimal.ONE) > 0 && b2.compareTo(BigDecimal.ONE) > 0) {
            while (true) {
                if (b1.compareTo(b2) >= 0) {
                    b1 = b1.subtract(b2);
                }
                if (b1.compareTo(b2) <= 0) {
                    b2 = b2.subtract(b1);
                }
                if (b1.compareTo(BigDecimal.ZERO) == 0) {
                    return b2;
                }
                if (b2.compareTo(BigDecimal.ZERO) == 0) {
                    return b1;
                }
            }
        }
        return BigDecimal.ONE;
    }

    /**
     * 从第一个不为零的开始，到下一个不为零的为止，将之间的认为是数值进行解析；
     *
     * @param numStr 源字符串
     * @return
     */
    public static BigDecimal parse(String numStr) {
        if (numStr == null)
            return zeroBigDecmail;
        Pattern ptn = Pattern.compile("\\d+(\\.\\d+)*");
        Matcher m = ptn.matcher(numStr);
        if (m.find()) {
            String grp = m.group();
            return obj2big(grp);
        }
        return zeroBigDecmail;
    }

    public static void main(String[] args) {
        System.out.println(BigDecimalUtils.toBigDecimalFour(BigDecimal.valueOf(10.24356d)));
        System.out.println(BigDecimalUtils.toBigDecimalFour(BigDecimal.valueOf(10.243d)));
        System.out.println(BigDecimal.valueOf(10.2435454d).scale());

        BigDecimal bigDecimal = obj2big(445L);
        System.out.println(bigDecimal);
    }
}
