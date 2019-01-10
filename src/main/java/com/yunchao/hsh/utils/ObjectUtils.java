package com.yunchao.hsh.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtils {
    public ObjectUtils() {
    }

    public static boolean isNullOrEmptyString(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                String str = (String)o;
                if (str.length() == 0) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        } else {
            if (o instanceof String) {
                if (((String)o).length() == 0) {
                    return true;
                }
            } else if (o instanceof Collection) {
                if (((Collection)o).isEmpty()) {
                    return true;
                }
            } else if (o.getClass().isArray()) {
                if (Array.getLength(o) == 0) {
                    return true;
                }
            } else {
                if (!(o instanceof Map)) {
                    return false;
                }

                if (((Map)o).isEmpty()) {
                    return true;
                }
            }

            return false;
        }
    }

    public static boolean isNotEmpty(Object c) throws IllegalArgumentException {
        return !isEmpty(c);
    }

    public static String toDate(String date,int flag){
        if (!(date == null || "".equals(date))) {
            if (flag == 0) {
                date = date + " 00:00:00";
                return date;
            } else if (flag == 1) {
                date = date + " 23:59:59";
                return date;
            }
        }
        return "";
    }

}
