package com.yunchao.hsh.utils.exception;

import com.yunchao.hsh.utils.annotation.ErrorDef;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * Created by wangqi on 2017/11/7
 */
public class BaseException extends RuntimeException implements Serializable {
    Enum errorCode;
    Object[] errorArgs;

    public BaseException() {
    }

    public BaseException(Enum errorCode, Object... errorArgs) {
        super(getLocalizedMessage(errorCode, errorArgs));
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }

    public BaseException(Throwable cause, Enum errorCode, Object... errorArgs) {
        super(getLocalizedMessage(errorCode, errorArgs), cause);
        this.errorCode = errorCode;
        this.errorArgs = errorArgs;
    }

    public Object[] getErrorArgs() {
        return this.errorArgs;
    }

    public String getErrorCode() {
        return this.errorCode.name();
    }

    public String getMessage() {
        return this.getLocalizedMessage();
    }

    public String getLocalizedMessage() {
        return getLocalizedMessage(this.errorCode, this.errorArgs);
    }

    private static String getDefaultFormatPattern(Enum errorCode, Object... errorArgs) {
        return errorCode.name() + ':' + buildDefaultFormatIndices(errorArgs != null ? errorArgs.length : 0);
    }

    private static String getLocalizedMessage(Enum errorCode, Object... errorArgs) {
        try {
            Field field = errorCode.getClass().getDeclaredField(errorCode.name());
            ErrorDef ed = (ErrorDef)field.getAnnotation(ErrorDef.class);
            if (ed != null) {
                int countMatches = BaseException.StringUtils.countMatches(ed.value(), "%s");
                String errorPattern = ed.value();
                if (errorArgs != null && errorArgs.length > countMatches) {
                    errorPattern = errorPattern + buildDefaultFormatIndices(errorArgs.length - countMatches);
                }

                return String.format(errorPattern, errorArgs);
            }
        } catch (NoSuchFieldException var6) {
            ;
        }

        return String.format(getDefaultFormatPattern(errorCode, errorArgs), errorArgs);
    }

    private static String buildDefaultFormatIndices(int num) {
        StringBuilder sb = new StringBuilder();
        sb.append(' ').append('[');

        for(int i = 0; i < num; ++i) {
            sb.append('%').append('s').append('|');
        }

        if (num > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        sb.append(']');
        return sb.toString();
    }

    static class StringUtils {
        public static final int INDEX_NOT_FOUND = -1;

        StringUtils() {
        }

        public static int countMatches(String str, String sub) {
            if (!isEmpty(str) && !isEmpty(sub)) {
                int count = 0;

                for(int idx = 0; (idx = str.indexOf(sub, idx)) != -1; idx += sub.length()) {
                    ++count;
                }

                return count;
            } else {
                return 0;
            }
        }

        public static boolean isEmpty(String str) {
            return str == null || str.length() == 0;
        }
    }
}

