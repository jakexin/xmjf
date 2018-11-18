<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>投资列表</title>
    <link rel="stylesheet" href="${ctx}/css/reset.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/investmentList.css">
    <link rel="stylesheet" href="${ctx}/css/page.css">
    <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript" src="${ctx}/js/radialIndicator.js"></script>
    <script type="text/javascript" >
        var ctx="${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/js/invest.list.js"></script>
</head>

<body>

<div class="top_wrap">
    <div class="top">
        <p class="fl phone">欢迎致电：021-67690939</p>
    </div>
</div>
<div class="header">
    <div class="contain">
        <a href="http://localhost:9999/index?0?0" class="logo">
        </a>
        <div class="header_nav" id="indexNav">
            <a href="${ctx}/index">首页</a>
            <a href="${ctx}/basItem/basItemListPage">我要投资</a>
            <a href="${ctx}/security?0?2">安全保障</a>
            <a href="${ctx}/account/accountInfo">我的账户</a>
            <a href="${ctx}/introduce?0?4">关于我们</a>
        </div>
        <div class="header_button">
            <div id="noUserId" style="width:142px;float: right">
                <a href="http://localhost:9999/user/toLoginPage"><input class="btn register" id="loginPage" type="button" value="登录"></a>
                <a href="http://localhost:9999/user/toRegisterPage"><input class="btn register" id="registerPage" type="button" value="注册"></a>
            </div>
        </div>
    </div>
</div>

<div class="list_container">
    <div class="date_tab" id="dateTab">
        <div>
            <div class="fl ketou" id="validItem" style="">
                <div class="tab list_active" id="quanbu">全部期限</div>
                <div class="tab ">0-30天</div>
                <div class="tab">30-90天</div>
                <div class="tab">90天以上</div>
                <div class="tab">历史项目</div>
            </div>
            <#--<div id="historyItem" class="tab" style="float: right">历史项目</div>-->
        </div>
    </div>
    <table class="list_table">
        <thead>
        <tr class="table_header">
            <th style="width: 196px;" id="itemRate" class="order_item ketou" data-name="item.item_rate">
                预期年化收益</th>
            <th id="itemCycle" class="order_item ketou" data-name="item.item_cycle" style="">期限</th>
            <th class="lishi" style="display: none;">预期年化收益</th>
            <th class="lishi" style="display: none;">期限</th>
            <th>
                <span class="lishi" style="display: none;">产品</span>
                <select id="itemType" class="ketou" style=""  onchange="initItemData(this.value)">
                    <option value="">产品</option>
                    <option value="2">车商宝</option>
                    <option value="5">车信宝</option>
                    <option value="3">车贷宝</option>
                    <option value="1">学车宝</option>
                </select>
            </th>
            <th>信用等级</th>
            <th>担保机构</th>
            <th>投资进度</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="pcItemList">

        </tbody>
    </table>
    <div class="pages">
        <nav>
            <ul id="pages" style="margin:75px auto 110px;" class="pagination">

            </ul>
        </nav>
    </div>
</div>


</body>
</html>