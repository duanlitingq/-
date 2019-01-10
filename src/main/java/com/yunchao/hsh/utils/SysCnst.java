package com.yunchao.hsh.utils;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class SysCnst {
    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat SDF_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final Random RANDOM = new Random();

    public SysCnst() {
    }

    public static class MenuCnst {
        public static final int MENU_TOP = 0;
        public static final int MENU_LEFT = 1;
        public static final int MENU_NAV = 2;
        public static final int OP_TYPE_MENU = 0;
        public static final int OP_TYPE_OPERA = 1;

        public MenuCnst() {
        }
    }

    public static class UserCnst {
        public static final int GENDER_FEMALE = 0;
        public static final int GENDER_MALE = 1;
        public static final int GENDER_SECRET = 2;
        public static final String[] GENDER_NAME_ARR = new String[]{"女", "男", "保密"};
        public static final Map<Integer, String> GENDER_MAP_ID_NAME = new LinkedHashMap<Integer, String>() {
            {
                this.put(Integer.valueOf(0), SysCnst.UserCnst.GENDER_NAME_ARR[0]);
                this.put(Integer.valueOf(1), SysCnst.UserCnst.GENDER_NAME_ARR[1]);
                this.put(Integer.valueOf(2), SysCnst.UserCnst.GENDER_NAME_ARR[2]);
            }
        };
        public static final Map<String, Integer> GENDER_MAP_NAME_ID = new LinkedHashMap<String, Integer>() {
            {
                this.put(SysCnst.UserCnst.GENDER_NAME_ARR[0], Integer.valueOf(0));
                this.put(SysCnst.UserCnst.GENDER_NAME_ARR[1], Integer.valueOf(1));
                this.put(SysCnst.UserCnst.GENDER_NAME_ARR[2], Integer.valueOf(2));
            }
        };

        public UserCnst() {
        }
    }
}
