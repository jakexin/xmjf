<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>充值记录</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/rechargeRecord.css">
    <link rel="stylesheet" href="/css/page.css">
    <link rel="stylesheet" href="/css/user_siderbar.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/js/layer/layer.js"></script>
    <script type="text/javascript" >
        var ctx="${ctx}";
    </script>
    <script type="text/javascript" src="${ctx}/js/recharge.recode.js"></script>
</head>
<body>
<#include "include/header.ftl">
<div class="container clear">

<#include "include/user_siderbar.ftl">
    <div class="content fr">
        <div class="recharge-title">
            <div class="recharge-title-left">
                充值记录
            </div>
        </div>
        <div class="table-title">
            <div class="table-title-first">
                充值时间
            </div>
            <div class="table-title-center">
                充值金额
            </div>
            <div class="table-title-first">
                状态
            </div>
        </div>
        <div class="table-content" id="rechargeList">
        </div>

        <div class="pages">
            <nav>
                <ul id="pages" style=" margin-left: 80px;margin-top: 100px;" class="pagination">

                </ul>
            </nav>
        </div>
    </div>
</div>
</body>
</html>