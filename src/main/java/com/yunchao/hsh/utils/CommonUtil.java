package com.yunchao.hsh.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunchao.hsh.utils.superdir.sub.Result;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.validation.ConstraintViolation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wangqi on 2017/11/7
 */
public class CommonUtil {
    public static boolean validate(Object obj, HttpServletRequest request) {
        Set<ConstraintViolation<Object>> set = BeanValidatorHolder.validate(obj);
        if (!set.isEmpty()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            Iterator<ConstraintViolation<Object>> it = set.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> error = it.next();
                errorMap.put(error.getPropertyPath().toString(), error.getMessage());
            }
            request.setAttribute("errors", errorMap);
            return false;
        }
        return true;
    }

    /**
     * IP地址是否合法
     *
     * @param ipAddress
     * @return
     */
    public static boolean isboolIp(String ipAddress) {
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    private static final void $forward(String url, HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        request.getRequestDispatcher(url).forward(request, response);
    }

    private static final void $redirect(String url, HttpServletResponse response)
            throws Exception {
        response.sendRedirect(url);
    }

    private static boolean $validate(Object obj, HttpServletRequest request) {
        Set<ConstraintViolation<Object>> set = BeanValidatorHolder.validate(obj);
        if (!set.isEmpty()) {
            Map<String, String> errorMap = new HashMap<String, String>();
            Iterator<ConstraintViolation<Object>> it = set.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> error = it.next();
                errorMap.put(error.getPropertyPath().toString(), error.getMessage());
            }
            request.setAttribute("errors", errorMap);
            return false;
        }
        return true;
    }

    private static Map<String, String> $validateErrors(Object obj, HttpServletRequest request) {
        Set<ConstraintViolation<Object>> set = BeanValidatorHolder.validate(obj);
        Map<String, String> errorMap = null;
        if (!set.isEmpty()) {
            errorMap = new HashMap<String, String>();
            Iterator<ConstraintViolation<Object>> it = set.iterator();
            while (it.hasNext()) {
                ConstraintViolation<Object> error = it.next();
                errorMap.put(error.getPropertyPath().toString(), error.getMessage());
            }
        }
        return errorMap;
    }

    private static final void $setAttribute(String key, Object value, HttpServletRequest request)
            throws Exception {
        request.setAttribute(key, value);
    }

    private static final void $referer(HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        String referer = request.getHeader("Referer");
        response.sendRedirect(referer);
    }

    private static final void $jsMessage(JspWriter out, String message,
                                         String Url) throws Exception {
        out.println("<script>alert('" + message + "')</script>");
        out.println("<script>location.href='" + Url + "';</script>");
    }

    /**
     * object对象转换为 json格式字符串
     *
     * @param object
     * @return
     * @throws IOException
     */
    @SuppressWarnings({"unchecked"})
    public String objectToJson(Object object) throws IOException {
        if (object == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
        mapper.writeValue(gen, object);
        gen.close();
        return sw.toString();
    }

    /**
     * 获取参数Map
     *
     * @param req    请求
     * @param fields 字段数组
     * @return
     */
    public Map $getParamMap(HttpServletRequest req, String[] fields) {
        Map params = new HashMap();
        for (String field : fields) {
            if (StringUtils.isBlank(field)) continue;
            String value = req.getParameter(field);
            if (value == null) continue;
            value = value.replaceAll("'", "");
            if (StringUtils.isBlank(value)) continue;
            params.put(field, value);
        }
        return params;
    }

    /**
     * 处理异常(用于Result)
     *
     * @param e      Exception
     * @param result Result
     */
    private static void $handleResultExcp(Exception e, Result result) {
        if ($hasChineseCharacter(e.getMessage())) {
            result.setMessage(e.getMessage()).setSuccess(false);
        } else {
            result.setMessage("系统出现异常，无法处理您的请求。").setSuccess(false);
            e.printStackTrace();
        }
    }

    /**
     * 是否包含汉字
     */
    private static boolean $hasChineseCharacter(String content) {
        if (content == null) {
            return false;
        }
        return content.getBytes().length > content.length();
    }

    /**
     * 获取不同字符组成的字符串
     *
     * @param len
     * @param type
     * @return
     */
    public static String $getRandom(int len, int type) {
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


    /**
     * 将时间2016-01-01 转换成2016-01-01 00：00：00
     */
    public Date weeHours(Date date, int flag) {
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

    /**
     * 文件上传工具
     */
    public static Result uploadPic(MultipartFile file, HttpServletRequest request, Logger logger) {
        System.out.println(" ====进入上传方法==== ");
        Result result = new Result();
        //在这里面文件存储的方案一般是：收到文件→获取文件名→在本地存储目录建立防重名文件→写入文件→返回成功信息
        //如果上面的步骤中在结束前任意一步失败，那就直接失败了。
        if (ObjectUtils.isEmpty(file)) {
            return result.setF("上传文件不能为空！");
        }
        System.out.println(" ====开始上传文件==== ");
        String fileName = file.getOriginalFilename(); //获取原文件名
//        System.out.println("原文件名： == >" + fileName);
        String suffix = fileName.substring(fileName.indexOf(".")); //后缀名
//        System.out.println("suffix ==" + suffix);
        String uploadDir = request.getSession().getServletContext().getRealPath("upload");
//        System.out.println("uploadDir ==" + uploadDir);
        String saveFileName = new SimpleDateFormat("yyyyMMdd").format(new Date()) + File.separator + CommonUtil.$getRandom(8, 4) + suffix; //保存的文件名
        FileOutputStream out = null;
        try {
            File targetFile = new File(uploadDir + File.separator + saveFileName);
            System.out.println(targetFile);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdir();
            }
            byte[] b = file.getBytes();
            out = new FileOutputStream(targetFile);
            out.write(b);
            out.flush();
            String retPath = request.getServletContext().getContextPath() + File.separator + "upload" + File.separator + saveFileName; //返回的图片路径
//            System.out.println("retPath == " +retPath );
            result.setS("", retPath);
            System.out.println(" ====上传完毕==== ");
        } catch (Exception e) {
            logger.error("文件上传异常 == " + e.getMessage());
            result.setF("文件上传异常，请联系管理员！");
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * author:隗鹏
     * date:2018-11-07
     * 生成6位随机码(全局不唯一)
     */
    public static String getSerialCode() {
        int count = 6;
        String str = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < count; i++) {
            int d = r.nextInt(31);//[0,31)
            sb.append(str.charAt(d));
        }
        return sb.toString();
    }

    /**
     * 获取不同字符组成的字符串
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

    /**
     * 获取logo图片的路径
     *
     * @return String
     */
    public static String getLogoPath(HttpServletRequest request) {
        String logoPath = request.getSession().getServletContext().getRealPath("/") + "page" + File.separator + "style" + File.separator + "image" + File.separator + "logo.png";
        return logoPath;
    }

}
