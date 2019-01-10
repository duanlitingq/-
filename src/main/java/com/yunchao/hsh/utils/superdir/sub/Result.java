package com.yunchao.hsh.utils.superdir.sub;

import org.apache.commons.lang.StringUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangqi on 2017/11/7
 */
public class Result  <T extends Object> extends com.yunchao.hsh.utils.superdir.Result{

    /** 设置成功的数据 */
    public Result setS(Object... data){
        setSuccess(true).setData(data.length == 0 ? "" : data).setMessage("操作成功");
        return this;
    }

    /** 设置失败的消息，如果需要返回数据，则放进第二个可选的参数里面 */
    public Result setF(String msg,Object... data) {
        setSuccess(false).setMessage(StringUtils.isBlank(msg) ? "操作失败" : msg).setData(data.length == 0 ? "" : data);
        return this;
    }

    /**
     * 获取Map
     * @param obj 某个Pojo类对象，如果：new AuthUser()
     * @param fields 对象中要存入Map中的字段列表，如:new String[]{"id","userName"};
     * @return Map<Object,Object>
     * @author HuKaiXuan 2014-8-12 下午5:58:57
     */
    public static Map<String,Object> getMap(Object obj, String[] fields) {
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        if(obj == null || fields == null || fields.length == 0) return map;
        for (String field : fields) {
            if(StringUtils.isBlank(field)) continue;
            String methodName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
            try {
                Method getMethod = obj.getClass().getDeclaredMethod(methodName, null);
                Object value = getMethod.invoke(obj, null);
                map.put(field, value == null ? "" : value.toString());
            } catch (Exception e) {
                System.out.println("反射获取方法异常，已忽略" + fields + "字段。异常信息为：" + e.getMessage());
                continue;
            }
        }
        return map;
    }

    /**
     * 获取Map
     * @param list 某个Pojo类对象的集合，如果：List<AuthUser>
     * @param fields 对象中要存入Map中的字段列表，如:new String[]{"id","userName"};
     * @return Map<Object,Object>
     * @author HuKaiXuan 2015-3-14 下午4:37:5
     */
    public static List<Map<String, Object>> getMapList(List<?> list, String[] fields) {
        List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
        if(list == null || list.size() == 0 || fields == null || fields.length == 0) return mapList;
        for(Object obj : list)
            mapList.add(getMap(obj,fields));
        return mapList;
    }


}