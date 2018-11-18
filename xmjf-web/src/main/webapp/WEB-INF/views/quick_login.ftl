<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>快捷登陆</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/quickLogin.css">
    <link rel="stylesheet" href="/css/common.css">
    <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/js/quick.login.js"></script>
</head>
<body>
<#include "include/header.ftl">
<div class="login-content">
    <div class="login-content-center">
        <div class="whole-img">
            <img src="/img/loginBackground.png"/>
        </div>
        <div class="login-box">
            <div class="box-title">
                快捷登录
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
                        <input type="button" id="clickMes02" class="obtain" value="获取验证码"/>
                    </p>
                </div>
                <div class="keep-password">
                    <div class="keep-password-center">
                        <a href="/login" class="passwrod-login">
                            密码登录
                        </a>
                    </div>
                </div>

                <div class="login-but">
                    <div class="login-but-center">
                        <button id="login" class="but">
                            登录
                        </button>
                    </div>
                </div>
                <div class="function">
                    <div class="function-center">
                        <div class="function-register">
                            没有账号？<span><a style="color: #ff5e5e;cursor:pointer;" href="/register">免费注册</a></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>