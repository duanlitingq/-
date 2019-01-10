package com.yunchao.hsh.utils;

public class MnMsgCnst {
    public static final String SYSTEM_ERROR = "系统出现异常，无法处理您的请求。";
    public static final String RECORD_NOT_EXISTS = "记录不存在";
    public static final String RECORD_IS_DEL = "记录已删除";
    public static final String VALIDATE_FAIL = "数据校验失败";
    public static final String NAME_NOT_BLANK = "名称不能为空";
    public static final String NOT_LOGIN = "用户没有登录";

    /** 方案 */
    public static class PlanCnst {
        public static final String EVENT_CONTENT_NOT_BLANK = "事件内容不能为空";

    }

    /** 用户 */
    public static class MemberCnst {
        public static final String PASSWORD_NOT_BLANK = "密码不能为空";
    }

    /** 问卷调查 */
    public static class QaCnst {
        public static final String PLEASE_INPUT_NUMBER = "请输入数值";
        public static final String INTRO_NOT_BLANK = "简述不能为空";
        public static final String CONTENT_NOT_BLANK = "内容不能为空";
        public static final String ALREADY_ANSWERED_TODAY = "您今天已经答过这道题了";
    }

    /** 服务定制 */
    public static class CustomizeCnst {
        public static final String CANT_BEFORE_TODAY = "日期不能小于今天";
    }

    /** 方案回复 */
    public static class PlanReplyCnst {
        public static final String REMARK_NOT_BLANK = "备注不能为空";
    }
}
