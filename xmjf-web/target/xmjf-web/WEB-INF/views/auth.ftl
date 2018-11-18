<!DOCTYPE html>
<!-- saved from url=(0046)https://www.hcjinfu.com/user/security/identity -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>实名认证</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/identity.css">
    <link rel="stylesheet" href="/css/common.css">
    <script language="javascript" src="/js/assets/jquery-3.1.0.min.js"></script>
    <script language="javascript" src="${ctx}/js/layer/layer.js"></script>
    <script language="JavaScript" src="${ctx}/js/common.js"></script>
    <script language="javascript" src="${ctx}/js/auth.js"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>
</head>
<body>
<#--<div class="top_wrap">
    <div class="top">
        <p class="fl phone">欢迎致电：400-0571-909</p>
        <p class="fr concern">关注我们：
            <a class="qq" target="_blank" href="https://shang.qq.com/wpa/qunwpa?idkey=708fc66cc5ad175ea62bca1eaaf205b3892566c8b0b795ad52881044e9421234">
            </a><a class="weixin"></a>
            <a class="weibo" target="_blank" href="http://weibo.com/p/1006065695883970/home?from=page_100606&amp;mod=TAB&amp;is_all=1#place"></a>
        </p>

    </div>
</div>-->
<#include "include/header.ftl">
<div id="first" class="login-content">
    <div class="login-content-center">
        <div class="forget-title">
        </div>
        <div class="identity-prompt">
            <span>为了您的资金安全，请填写真实的身份信息，以便享受安全保障服务。平台已通过CFTA认证，您的隐私信息将严格保密</span>
        </div>
        <div class="forget-content">
            <div class="forget-content-center">
                <div class="blank"></div>
                <div class="phoneNum">
                    <div class="phoneNum-text">真实姓名:</div>
                    <input type="text" placeholder="请输入您的真实姓名" id="realName" class="phoneNum-input" maxlength="8">
                </div>
                <div class="login_input_code">
                    <div class="phoneNum-text">身份证号:</div>
                    <input type="text" placeholder="请输入您的身份证号" id="card" class="phoneNum-input" maxlength="18" >
                </div>
              <#--  <div class="login_input_code">
                    <div class="phoneNum-text">身份证正面照:</div>
                    <div class="file phoneNum-input a-msg-upload">
                        <span id="fileFgText">点击上传身份证正面照</span>
                        <input type="file" placeholder="请上传身份证正面照" name="fileFg" id="fileFg" class="phoneNum-input a-msg-upload-ipt">
                    </div>
                </div>-->
           <#--     <div class="login_input_code">
                    <div class="phoneNum-text">身份证反面照:</div>
                    <div class="file phoneNum-input a-msg-upload">
                        <span id="fileBgText">点击上传身份证反面照</span>
                        <input type="file" placeholder="请上传身份证反面照" name="fileBg" id="fileBg" class="phoneNum-input a-msg-upload-ipt">
                    </div>
                </div>-->
                <div class="phoneNum-text">设置交易密码:</div>
                <div class="phoneNum"><input type="password"  id="_ocx_password" style="ime-mode:disabled" tabindex="2" class="ocx_style"></div>
                <div class="phoneNum-text">确认交易密码:</div>
                <div class="phoneNum"><input type="password"  id="_ocx_password1" style="ime-mode:disabled" tabindex="2" class="ocx_style"></div>
                <input name="front" id="front" type="hidden">
                <input name="back" id="back" type="hidden">
                <div class="login-but">
                    <div class="login-but-center">
                        <button id="identityNext" class="but">
                            认证
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>