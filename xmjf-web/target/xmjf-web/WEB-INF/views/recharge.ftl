<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>充值</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/recharge.css">
    <link rel="stylesheet" href="/css/user_siderbar.css">
    <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/js/recharge.js"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>

</head>
<body>
<#include "include/header.ftl">
<div class="container clear">
<#include "include/user_siderbar.ftl">
    <div class="content fr">
        <div class="recharge-title">
            <div class="recharge-title-left">
                充值
            </div>
            <div class="recharge-title-right">
                <div class="record-but">
                    <a href="${ctx}/account/rechargeRecord">
                        <button class="but">
                            充值记录
                        </button>
                    </a>
                </div>
            </div>
        </div>
        <form id="fm" method="post" action="${ctx}/pay/toPay" >
        <div class="recharge-main">
            <div class="recharge-bankCard">
                <div class="third-pay">
                    <div class="third-pay-title">
                        充值金额：
                    </div>
                    <p class="recharge_input_wrap"><input type="number" name="total_amount" class="recharge-input" id="rechargeAmount"/></p>
                </div>
                <div class="third-pay">
                    <div class="third-pay-title">
                        验证码：
                    </div>
                    <input type="text" class="recharge-input" name="picCode" id="pictureCode"
                           style="margin-top: 18px;margin-left: 10px;float: left"/>
                    <div class="verification-img">
                        <img src="${ctx}/getKaptchaCode" id="validImg" style="cursor:pointer;"/>
                    </div>
                </div>
                <div class="third-pay">
                    <div class="third-pay-title">
                        交易密码：
                    </div>
                    <input type="password" name="payPassword" class="recharge-input" id="password"
                           style="margin-top: 18px;margin-left: 10px;float: left"/>
                </div>
                <div class="third-pay">
                    <input type="submit" value="确认充值" id="rechargeBut" class="recharge-but"/>
                    <span>${msg!}</span>
                </div>
                <div class="prompt">
                    <div class="prompt-content">
                        <div class="prompt-content-title">
                            温馨提示
                        </div>
                        <div class="prompt-content-text">
                            <div class="prompt-content-img">
                                <img src="/img/promptImg.png" style="margin-top: 12px"/>
                            </div>
                            <div class="prompt-text">
                                为保障资金安全，充值提现采用同卡进出原则，即提现银行卡需与充值银行卡一致。
                            </div>
                        </div>
                        <div class="prompt-content-text">
                            <div class="prompt-content-img">
                                <img src="/img/promptImg.png" style="margin-top: 12px"/>
                            </div>
                            <div class="prompt-text">
                                为了您的账户安全，请在充值前进行实名认证、银行卡绑定以及交易密码设置。
                            </div>
                        </div>
                        <div class="prompt-content-text">
                            <div class="prompt-content-img">
                                <img src="/img/promptImg.png" style="margin-top: 12px"/>
                            </div>
                            <div class="prompt-text">
                                充值前请确认您的银行卡限额。如充值成功后未能及时到账，请联系客服：400-0571-909。
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </form>
    </div>

</div>
<div class="rebox" style="height: 198%">
    <div class="popup">
        <div class="popup-title">
            <span>登录网上银行充值</span>
            <img src="/img/close-icon.png" id="popupclose">
        </div>
        <div class="popup-content">
            <span>
                请您在新打开的网上银行页面进行充值，充值完成前不要关闭该窗口
            </span>
            <div class="popup-botton">
                <a href="/user/assets?0?3"><button>已完成充值</button></a>
                <a  href="/question?5?4"><button>充值遇到问题</button></a>
            </div>
        </div>
    </div>
</div>
</body>
</html>