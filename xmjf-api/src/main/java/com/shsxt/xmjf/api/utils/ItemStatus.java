package com.shsxt.xmjf.api.utils;

public class ItemStatus {
    public static  final int NEW=0; //开始 项目平台刚刚创建
    public static  final int WAITOPEN=1; //等待开放  项目进入倒计时 倒计时结束 项目即可进行投资
    public static  final int FIRST=2;//初审中  平台人员进行审核操作
    public static final int OPEN=10;  //开放
    public static final int FAILURE=11; //初审失败
    public static final int CANCEL=13; //撤回审核
    public static final int CANCEL_SUCCESS=14; //撤回成功
    public static final int INTERCEPT_COMPLETE=18; //截标   项目金额未投满  当做截标进行处理
    public static final int FULL_COMPLETE=20;  //满标
    public static final int FULL_SUCCESS=21;   //满标成功
    public static final int FULL_FAILURE=22;   //复审失败
    public static final int FULL_AUDIT=23;   //复审审核   审核通过 放款
    public static final int THIRD_FULL_AUDIT=24;   //第三方放款失败
    public static final int REPAYING=30; //复审通过待还款
    public static final int REPAY_PART=31; //部分还款
    public static final int REPAY_COMPLETE=32; //还款成功

}
