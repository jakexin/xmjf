<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>资产详情</title>
    <link rel="stylesheet" href="${ctx}/css/reset.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/user_siderbar.css">
    <link rel="stylesheet" href="${ctx}/css/assets.css">

<#--    <script type="text/javascript" src="${ctx}/js/account.info.js"></script>-->

</head>
<body>
<#include "include/header.ftl">
<div class="container clear">

<#include "include/user_siderbar.ftl">

    <div class="assets fr">
        <div class="assets_detail">
            <div class="center_text">
                <p class="num"></p>
            </div>
            <h2>资产详情</h2>
            <div id="pie_chart" style="width:100%;height:398px;">
            </div>

        </div>

        <div class="trend_detail">
            <h2 id="title">投资趋势图</h2>

            <div class="date">
                <span id="earnings">收益</span>
                <span id='investment' class='active'>投资</span>
            </div>
            <div id="line_chart" style="width:100%;height:368px;"></div>
        </div>

    </div>
</div>

<script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
<script src="${ctx}/js/assets/highchart.js"></script>
<script type="text/javascript">
    var ctx="${ctx}";
</script>
<script type="text/javascript" src="${ctx}/js/account.info.js"></script>

</body>
</html>