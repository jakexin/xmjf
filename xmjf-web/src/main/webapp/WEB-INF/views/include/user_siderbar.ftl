<script src="/js/assets/jquery-3.1.0.min.js"></script>
<div class="user_siderbar fl">
    <div class="top_header">
        <a class="messageBox_center" href="/user/site?-1?3">

        </a>
        <div class="portrait"></div>
        <p class="text"><span id="siderTime"></span><span id="siderUsername"></span></p>
        <#--<p class="text">积分：5555</p>-->
        <ul class="rechargeWays">
            <li id="siderRealname">
                <div class="siderTips" id="realnameTips">请完成实名认证！<a href="/user/security?6?3">立即认证</a></div>
            </li>
            <li id="siderPhoneSet">
                <div class="siderTips" id="phoneTips">您已绑定手机号<span id="siderMobile"></span>。<a href="/user/security?6?3">更改</a></div>
            </li>
            <li id="siderBankCard">
                <div class="siderTips" id="bankTips">请先实名认证！<a href="/user/security?6?3">立即认证</a></div>
            </li>
            <li></li>
        </ul>
        <div class="button_area" id="rechargeAndCash">
            <a href="${ctx}/account/recharge"><input class='fl'  type="button" value="充值"></a>
            <a href="javascript:void(0)"><input class='fr' type="button"  value="提现"></a>
        </div>
    </div>
    <div class="nav" id="userNav">
        <ul class='nav_classify'>
            <li class="nav_head wallet">钱包</li>
            <li><a href="/user/assets?0?3">资产详情</a></li>
            <li><a href="/user/reward?1?3">红包卡券</a></li>
            <li><a href="/user/calendar?2?3">投资日历</a></li>
            <li><a href="/user/log?3?3">资金记录</a></li>
            <li><a href="/user/auto?4?3">自动投标</a></li>
            <#--<li><a href="calendar?5">积分商城</a></li>-->
        </ul>
        <ul class='nav_classify'>
            <li class="nav_head manage">管理中心</li>
            <li><a href="/user/inviteCode?5?3">邀请好友</a></li>
            <li><a href="/user/security?6?3">账户设置</a></li>
            <li><a href="/user/contracts?7?3">合同查看</a></li>
        </ul>
    </div>
</div>


