<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <link rel="stylesheet" href="${ctx}/css/reset.css">
    <link rel="stylesheet" href="${ctx}/css/register.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript">
        var ctx = "${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/js/register.js"></script>
</head>
<body>
<#include "include/header.ftl">
<div class="login-content">
    <div class="login-content-center">
        <div class="whole-img">
            <img src="/img/registerBackground.png"/>
        </div>
        <div class="login-box">
            <div class="box-title">
                用户注册
            </div>
            <div class="box-input">
                <div class="box-input-center">
                    <p class="login_input_wrap"><input placeholder="手机号" type="text" id="phone" class="login-input1" /></p>
                    <p class="login_input_code">
                        <input id="code" placeholder="请输入验证码" type="text"  class="login-input login-check-code"/>
                        <img class="validImg" alt="点击更换" title="点击更换" src="${ctx}/getKaptchaCode"/>
                    </p>
                    <p class="login_input_password">
                        <input type="text" id="verification" placeholder="请输入验证码" class="login-input-verification"/>
                        <input type="button" id="clickMes" class="obtain" value="获取验证码"/>
                    </p>
                    <input type="hidden" id="invite" />
                    <p class="login_setting_password"><input placeholder="请设置密码" type="password" id="password" class="login-input" maxlength="20" />

                </div>
                <br>

                <div class="login-but">
                    <div class="login-but-center">
                        <button id="register" class="but">
                            注册
                        </button>
                    </div>
                </div>
                <div class="login-text">
                    <div class="login-text-center" style="font-size: 16px">
                        <span style="color: #606060">已有账户？</span><a href="${ctx}/login" style="color: #ff5e5e;cursor:pointer;">立即登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>