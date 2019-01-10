package com.yunchao.hsh.utils;

/**
 * Created by wangqi on 2017/11/7
 */
public class SysMsgCnst {
    public static final String VALIDATE_FAIL = "数据校验失败";

    public SysMsgCnst() {
    }

    public static class UserCnst {
        public static final String USER_NOT_EXISTS = "用户不能为空";
        public static final String USER_NAME_NOT_BLANK = "用户不能为空";
        public static final String USER_NAME_EXISTS = "用户已经被占用，请修改";
        public static final String REAL_NAME_NOT_BLANK = "姓名不能为空";
        public static final String PASSWORD_NOT_BLANK = "密码不能为空";
        public static final String EMAIL_ILLEGAL = "邮箱格式不正确";
        public static final String EMAIL_EXISTS = "邮箱已经被占用，请修改";
        public static final String MOBILE_ILLEGAL = "手机格式不正确";
        public static final String MOBILE_EXISTS = "手机已经被占用，请修改";

        public UserCnst() {
        }
    }
}
