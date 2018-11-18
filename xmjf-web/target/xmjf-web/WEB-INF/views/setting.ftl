<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>账户设置</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/user_siderbar.css">
    <link rel="stylesheet" href="/css/account_set.css">
    <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/js/setting.js"></script>
</head>
<body>
<#include "include/header.ftl">
    <div class="container clear">

    <#include "include/user_siderbar.ftl">
    <div class="account fr">
        <div class="account_top">
            <h2>账户设置</h2>
            <img src="/img/account_set.png" alt="">
        </div>
        <div class="account_set_area">
            <div class="div90">
                <span class="col1">手机号码</span>
                <span class="col2">保障账户与资金安全，是您在汇诚金服重要的身份凭证</span>
                <span class="col3 setted">已设置</span>
                <span class="col4" id="phone"></span>
                <span class="last_modify modify show_modify_modal">修改</span>
            </div>
            <div class="panel_area">
                <div class="panel panel1">
                    <p class="height28"><span>原手机号</span><span id="oldphone"></span></p>
                    <p><span>验证码</span><input id="oldCode" type="text" class="short_text"><input style="cursor:pointer;font-size: 13px;" id="oldclickMes" type="button" class="short_button" value="发送验证码"></p>
                    <p><span>新手机号</span><input type="text" id="newphone"></p>
                    <p><span>验证码</span><input type="text" id="inputRandomCode" class="short_text"><input style="cursor:pointer;" type="button" id="newclickMes" class="short_button" value="发送验证码"></p>
                    <p><span></span><input type="button" style="cursor:pointer;" class="long_button show_modify_modal" id='modifyLoginPass' value="修改"></p>
                </div>
            </div>

            <div  class="div90" style="display: none;">
                <span class="col1">登录密码</span>
                <span class="col2">保障账户安全，建议您定期更换密码</span>
                <#if userInfo.password??>
                    <span class="col3 set_tip">未设置</span>
                <#else>
                    <span class="col3 setted">已设置</span>
                </#if>
                <span class="modify show_modify_modal">修改</span>
            </div>
            <div class="panel_area" style="display: none;">
                <div class="panel panel2">
                    <p><span>原密码</span><input id="oldpassword" type="password" maxlength="20"></p>
                    <p class="no_margin"><span>新密码</span><input id="newpassword" type="password" maxlength="20"></p>
                    <p class="safe">
                        <#--<span></span><strong class="active"></strong><strong></strong><strong></strong>安全等级-->
                    </p>

                    <p><span></span><input type="button" id="modify" class="long_button" value="修改"></p>
                </div>
            </div>
            <div  class="div90">
                <span class="col1">实名认证</span>
                <span class="col2">保障账户安全，只有通过实名认证，才能充值投资</span>
                <#if security.realnameStatus==0>
                    <span class="col3 set_tip">未设置</span>
                <#else>
                    <span class="col3 setted">已设置</span>
                </#if>
                <#if security.realnameStatus==0>
                <span id="rz" class="modify show_modify_modal11">我要认证</span>
                <#else>
                    <span class="modify show_modify_modal">查看</span>
                </#if>
            </div>
            <div class="panel_area">
                <div class="panel panel3">
                    <#if security.realnameStatus==0>
                    <p><span>姓名</span><input id="name" type="text"></p>
                    <p><span>身份证号</span><input id="idCard" type="text" maxlength="18"></p>
                    <p><span></span><input type="button" style="cursor:pointer;" id="realnamecertification" class="long_button" value="确认"></p>
                    <#else>
                        <p><span>姓名</span>${security.realname}</p>
                        <p><span>身份证号</span>${security.identifyCard}</p>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>