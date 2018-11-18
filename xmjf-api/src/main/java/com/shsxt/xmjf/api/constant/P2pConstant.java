package com.shsxt.xmjf.api.constant;

/**
 * 项目常量信息配置
 */
public class P2pConstant {

    // 图形验证码核实key
    public static final String PICTURE_VERIFY_KEY = "pictureVerifyKey";

    // 初始化ascClient需要的几个参数
    // 短信API产品名称（短信产品名固定，无需修改）
    public static final String PRODUCE = "Dysmsapi";
    // 短信API产品域名（接口地址固定，无需修改）
    public static final String DOMAIN = "dysmsapi.aliyuncs.com";

    // 替换成你的AK
    public static final String ACCESS_KEY_ID = "LTAIftPLNzhgsHmx";
    public static final String ACCESS_KEY_SECRET = "VgLO26MfrjUiEohAZVJ5YfLsjl8p3J";

    // 短信签名
    public static final String SIGN_NAME = "小马金服";

    // 短信注册模板
    public static final String TEMPLATE_REGISTER_CODE = "SMS_109545028";
    // 短信登录模板
    public static final String TEMPLATE_LOGIN_CODE = "SMS_115100107";

    // 返回前台消息模板
    public static final String OPS_SUCCESS_MSG = "操作成功！";
    public static final Integer OPS_SUCCESS_CODE = 200;

    public static final String OPS_ERROR_MSG = "操作失败！";
    public static final Integer OPS_ERROR_CODE = 300;





}
