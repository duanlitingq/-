package com.yunchao.hsh.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ParamUtils {
    public ParamUtils() {
    }

    public static String getParameter(HttpServletRequest request, String name) {
        String temp = request.getParameter(name);
        return temp == null ? "" : temp;
    }

    public static String getParameter(HttpServletRequest request, String name, String defaultValue) {
        String temp = request.getParameter(name);
        return temp != null && !"".equals(temp) ? temp : defaultValue;
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String name) {
        return getBooleanParameter(request, name, false);
    }

    public static boolean getBooleanParameter(HttpServletRequest request, String name, boolean defaultVal) {
        String temp = request.getParameter(name);
        if (!"true".equals(temp) && !"on".equals(temp)) {
            return !"false".equals(temp) && !"off".equals(temp) ? defaultVal : false;
        } else {
            return true;
        }
    }

    public static int getIntParameter(HttpServletRequest request, String name, int defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            int num = defaultNum;

            try {
                num = Integer.parseInt(temp);
            } catch (Exception var6) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static int[] getIntParameters(HttpServletRequest request, String name, int defaultNum) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        } else if (paramValues.length < 1) {
            return new int[0];
        } else {
            int[] values = new int[paramValues.length];

            for(int i = 0; i < paramValues.length; ++i) {
                try {
                    values[i] = Integer.parseInt(paramValues[i]);
                } catch (Exception var7) {
                    values[i] = defaultNum;
                }
            }

            return values;
        }
    }

    public static double getDoubleParameter(HttpServletRequest request, String name, double defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            double num = defaultNum;

            try {
                num = Double.parseDouble(temp);
            } catch (Exception var8) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static long getLongParameter(HttpServletRequest request, String name, long defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            long num = defaultNum;

            try {
                num = Long.parseLong(temp);
            } catch (Exception var8) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static Short getShortParameter(HttpServletRequest request, String name, Short defaultNum) {
        String temp = request.getParameter(name);
        if (temp != null && !temp.equals("")) {
            Short num = defaultNum;

            try {
                num = Short.valueOf(temp);
            } catch (Exception var6) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static long[] getLongParameters(HttpServletRequest request, String name, long defaultNum) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues == null) {
            return null;
        } else if (paramValues.length < 1) {
            return new long[0];
        } else {
            long[] values = new long[paramValues.length];

            for(int i = 0; i < paramValues.length; ++i) {
                try {
                    values[i] = Long.parseLong(paramValues[i]);
                } catch (Exception var8) {
                    values[i] = defaultNum;
                }
            }

            return values;
        }
    }

    public static String getAttribute(HttpServletRequest request, String name) {
        return getAttribute(request, name, false);
    }

    public static String getAttribute(HttpServletRequest request, String name, boolean emptyStringsOK) {
        String temp = (String)request.getAttribute(name);
        if (temp != null) {
            return temp.equals("") && !emptyStringsOK ? null : temp;
        } else {
            return null;
        }
    }

    public static boolean getBooleanAttribute(HttpServletRequest request, String name) {
        String temp = (String)request.getAttribute(name);
        return temp != null && temp.equals("true");
    }

    public static int getIntAttribute(HttpServletRequest request, String name, int defaultNum) {
        String temp = (String)request.getAttribute(name);
        if (temp != null && !temp.equals("")) {
            int num = defaultNum;

            try {
                num = Integer.parseInt(temp);
            } catch (Exception var6) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static long getLongAttribute(HttpServletRequest request, String name, long defaultNum) {
        String temp = (String)request.getAttribute(name);
        if (temp != null && !temp.equals("")) {
            long num = defaultNum;

            try {
                num = Long.parseLong(temp);
            } catch (Exception var8) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static int getIntAttribute(HttpSession session, String name, int defaultNum) {
        String temp = (String)session.getAttribute(name);
        if (temp != null && !temp.equals("")) {
            int num = defaultNum;

            try {
                num = Integer.parseInt(temp);
            } catch (Exception var6) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static double getDoubleAttribute(HttpSession session, String name, double defaultNum) {
        String temp = (String)session.getAttribute(name);
        if (temp != null && !temp.equals("")) {
            double num = defaultNum;

            try {
                num = Double.parseDouble(temp);
            } catch (Exception var8) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static long getLongAttribute(HttpSession session, String name, long defaultNum) {
        String temp = (String)session.getAttribute(name);
        if (temp != null && !temp.equals("")) {
            long num = defaultNum;

            try {
                num = Long.parseLong(temp);
            } catch (Exception var8) {
                ;
            }

            return num;
        } else {
            return defaultNum;
        }
    }

    public static String getAttribute(HttpSession session, String name) {
        String temp = (String)session.getAttribute(name);
        return temp == null ? "" : temp;
    }

    public static String getAttribute(HttpSession session, String name, String defaultValue) {
        String temp = (String)session.getAttribute(name);
        return temp == null ? defaultValue : temp;
    }

    public static String getUTF8Parameter(HttpServletRequest request, String name, String defaultValue) {
        String temp = request.getParameter(name);
        if (temp != null && !"".equals(temp)) {
            String result = temp;

            try {
                byte[] b = temp.getBytes("ISO-8859-1");
                result = new String(b, "UTF-8");
            } catch (Exception var6) {
                ;
            }

            return result;
        } else {
            return "";
        }
    }

    public static String[] getParameters(HttpServletRequest request, String name, String defaultValue) {
        String[] paramValues = request.getParameterValues(name);
        if (paramValues != null && paramValues.length > 0) {
            for(int i = 0; i < paramValues.length; ++i) {
                String temp = paramValues[i];
                if (temp == null || "".equals(temp)) {
                    paramValues[i] = defaultValue;
                }
            }
        }

        return paramValues;
    }
}
