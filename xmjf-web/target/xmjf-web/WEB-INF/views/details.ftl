<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>投资详情</title>
    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/details.css">
    <link rel="stylesheet" href="/css/page.css">
   <script type="text/javascript" src="${ctx}/js/assets/jquery-3.1.0.min.js"></script>
    <script type="text/javascript" src="${ctx}/js/radialIndicator.js"></script>
    <script type="text/javascript" src="${ctx}/js/common.js"></script>
   <script type="text/javascript" src="${ctx}/js/assets/layer/layer.js"></script>
 <script type="text/javascript" src="${ctx}/js/assets/config.js"></script>
<script type="text/javascript" src="${ctx}/js/assets/require.js"></script>
    <script type="text/javascript" src="${ctx}/js/details.js"></script>
    <script type="text/javascript">
        var ctx="${ctx}";
    </script>

</head>

<body>

<#include "include/header.ftl">
<div class="invest_container">
    <div class="invest_item">
        <h2 class="item_name">${(item.itemName)!""}
            <#if item.itemIsnew==1>
                <span class="hot" new>NEW</span>
               <#elseif item.itemIsnew==0 && item.moveVip==1>
                   <span class="hot" app>APP</span>
              <#elseif item.itemIsnew==0 && item.moveVip==0 && item.itemIsrecommend ==1>
                <span class="hot" hot>HOT</span>
              <#elseif item.itemIsnew==0 && item.moveVip==0 && item.itemIsrecommend ==0 && item.password??>
                <span class="hot" lock>LOCK</span>
            </#if>
        </h2>
        <div class="invest_detail">
            <div>
                <p class="rate"><strong class="rate_int">${item.itemRate}</strong>
                	<span class="percent">%
                        <#if item.itemAddRate?? && item.itemAddRate gt 0 >
                            +${item.itemAddRate}%
                        <#else>
                        </#if>
                	</span></p>
                <p class="details_text">预期年化收益率</p>
            </div>
            <div class="circle">
                <p class="cash_num"><span id="itemCycleForCoupon">${item.itemCycle}</span>
                   <#if item.itemCycleUnit == 1>
                     天
                   <#elseif item.itemCycleUnit == 2>
                     月
                   <#elseif item.itemCycleUnit == 3>
                       季
                   <#elseif item.itemCycleUnit == 4>
                       年
                   </#if>
                </p>
                <p class="details_text">项目期限</p>
            </div>
            <div class="total_money">
                <p class="cash_num">${item.itemAccount}元</p>
                <p class="details_text">项目总额</p>
            </div>
            <div class="radialIndicator" id='rate' data-val=" ${item.itemScale}">
            </div>
        </div>
        <div class="invest_table">
            <table>
                <tr>
                    <td width="76">还款方式</td>
                    <td width="120">
                        <#if item.itemRepayMethod==1>
                            一次性还款
                            <#elseif  item.receiptMethod==2>
                              等额本息
                            <#elseif  item.receiptMethod==3>
                              先息后本
                            <#else >
                              每日付息
                        </#if>

                    </td>
                    <td width="76">起投金额</td>
                    <td width="154">
                            <#if item.itemSingleMinInvestment?? && item.itemSingleMinInvestment gt 0>
                            <em id="minInvestMoney" data-value=${item.itemSingleMinInvestment}>
                                   ${item.itemSingleMinInvestment}元
                              <#else >
                              <em id="minInvestMoney">
                                 无限制
                            </#if>
                   </em>
                    </td>

                    <td width="76">最大投标</td>
                    <td width="77">
                        <#if item.itemSingleMaxInvestment?? && item.itemSingleMaxInvestment gt 0>
                        <em id="maxInvestMoney" data-value=${item.itemSingleMaxInvestment?c}>
                                ${item.itemSingleMaxInvestment}元
                                <#else>
                                <em id="maxInvestMoney">
                               无限制
                        </#if>
                        </em>
                    </td>
                </tr>
                <tr>
                    <td>投标奖励</td>
                    <td >
                    	<#if item.itemAddRate?? && item.itemAddRate gt 0>
                    	      ${item.itemAddRate}%
                            <#else >
                             无
                    	</#if>
                    </td>
                    <td>发布时间</td>
                    <td>
                        <#if item.releaseTime??>
                             ${item.releaseTime?string("yyyy-MM-dd")}
                        </#if>

                    </td>
                    <td>有效期</td>
                    <td style="width: 100px">
                        <#if item.endTime??>
                          ${item.endTime?string("yyyy-MM-dd")}
                        </#if>

                    </td>
                </tr>
            </table>
        </div>
        <div class="invest_panel">
            <p class="text">剩余金额：${item.itemAccount-item.itemOngoingAccount}元</p>
             <#if userInfo??>
                 <p class="text left_account"><span id="ye" data-value=${account.usable?c}>账户余额：${account.usable}元</span>
                 <a class="charge" href="javascript:toRecharge()">充值</a>
                 </p>
             </#if>

            <p class="input_wrap left_account"><input type="text" id='usableMoney' placeholder="请输入投资金额"></p>

            <p class="input_wrap clear" style="margin-top:-12px;">
                <#if  userInfo??>
                     <#if item.itemStatus ==1>
                         <a href="javascript:void(0)"><input class='invest_button fl' style="background: #c9c9c9;cursor: default" type="button"  value="即将开放"></a>
                        <#elseif  item.itemStatus==10>
                            <a href="javascript:void(0)"><input class='invest_button fl'  onclick="doInvest()" type="button"  value="立即投资" id="invest"></a>
                        <#elseif item.itemStatus==20>
                         <a href="javascript:void(0)"><input class='invest_button fl' style="background: #c9c9c9;cursor: default" type="button"  value="已抢完"></a>
                        <#elseif  item.itemStatus==30 || item.itemStatus==31 >
                         <a href="javascript:void(0)"><input class='invest_button fl' style="background: #c9c9c9;cursor: default" type="button"  value="还款中"></a>
                     <#elseif  item.itemStatus==23 >
                         <a href="javascript:void(0)"><input class='invest_button fl' style="background: #c9c9c9;cursor: default" type="button"  value="已满标"></a>
                     <#else>
                         <a href="javascript:void(0)">
                             <input class='invest_button fl' style="background: #c9c9c9;cursor: default" type="button"  value="已还款">
                         </a>

                     </#if>
                 <#else >
                     <a href="/login"><input class='invest_button fl' type="button" value="请登录"></a>
                </#if>

               <span class="caculator fl"></span>
            </p>
            <p class="no_account">没有账号？<a href="/register">立即注册</a></p>
        </div>
    </div>
    <div class="item_introduce">
        <div class="tab" id="tabs">
            <div class="tab_active" >借款详情</div>
            <div>风险措施</div>
            <div >投标记录</div>
        </div>
        <div id="contents">
            <div class="tab_content"  >
            <h3 class="title">关于车贷宝</h3>
            <div class="type_box">
               <img src="/img/cheshangbao.png" alt="">
            </div>

            <h3 class="title">基本信息</h3>
          <div class="table_box">
              <table class="table_base">
                  <thead>
                  <tr><th colspan="2">借款人信息</th><th colspan="2">车辆信息</th></tr>
                  </thead>

                  <tbody>
                  <tr><td>姓&nbsp;&nbsp;&nbsp;&nbsp;名</td><td>${(loanUser.realname)!}</td>
                      <td>车型</td><td>${(busItemLoan.carType)!}</td></tr>
                  <tr>
                      <td>身份证号</td><td>${(loanUser.identifyCard)!}</td><td>上牌时间</td><td>
                      <#if busItemLoan??>
                          <#if busItemLoan.licensingTime??>
                           ${busItemLoan.licensingTime?string("yyyy-MM-dd")}
                          </#if>
                      </#if>
                  </td></tr>
                  <tr><td></td><td></td><td>公里数</td><td>${(busItemLoan.kilometers)!"0"}万公里</td></tr>
                  <tr><td>首付金额</td><td>${(busItemLoan.firstPayAmount)!""}元</td><td>评估价</td><td>${(busItemLoan.assessPrice)!}元</td>
                  </tr>
                  </tbody>

                </table>
            </div>
            <h3 class="title" id="anquanshenheType">安全审核</h3>
            <ul class="security_check clear">
               <if pics??>
                  <#list pics as pic>
                        <#if pic.itemPictureType==1>
                            <li style="background-image: url(/img/shenfenzheng.png)">身份证</li>
                          <#elseif pic.itemPictureType==2>
                              <li style="background-image: url(/img/xue.png)">学生证</li>
                        </#if>
                  </#list>
               </if>




            </ul>

            <h3 class="title">相关文件</h3>
            <div class="lunbo_wrap">
                <button class="click_button pre" id="pre" onclick="play()" >
                        <
                </button>
                <button class="click_button next" id="next"  onclick="play()" >
                    >
                </button>
                <div class="image_large" id="imgLarge">
                    <div class="left"></div>
                    <div class="right"></div>
                    <div class="close"></div>
                </div>
                <div class="over_hidden">
                    <ul class="lunbo" id="slider">
                        <#if pics??>
                            <#list pics as pic>
                                <li style="background-image: url(${pic.picturePath})" onclick="picTab()" data-url="${pic.picturePath}" ></li>
                            </#list>
                        </#if>

                    </ul>
                </div>
            </div>
        </div style="display: none">
            <div class="tab_content" style="display: none">
                <div class="all_security_img">
                    <img src="/img/fengxianbaozhang.png" alt="">
                </div>
                <div class="security_bottom">
                    <ul class="clear">
                        <li style="background-image: url(/img/gongsi.png)">项目公司
                            本息担保</li>
                        <li style="background-image: url(/img/disanfang.png)">三方保证
                            无限连带</li>
                        <li style="background-image: url(/img/zichang.png)">安保资产
                            清收保障</li>
                        <li style="background-image: url(/img/gongkaitouming.png)">实地信审
                            公开透明</li>
                    </ul>
                </div>
                <div class="special">
                    <p class="shenming">特别申明：</p>
                    <p class="text">1、汇诚金服及其合作机构将始终秉持客观公正的原则，严控风险，最大程度的尽力确保借款人信息的真实性。同时由与第三方征信机构合作动态数据对接，筛查借款人信息。</p>
                    <p class="text">2、借款人若逾期，其个人信息将被公布。</p>
                </div>
            </div>
            <div class="tab_content" style="display: none">
                <table class="record">
                    <thead>
                    <tr><th width="400">投资用户</th><th>投资金额（元）</th><th width="400">投资时间</th></tr>
                    </thead>
                    <tbody id="recordList">
                        <tr>
                            <td>王大锤</td>
                            <td>2000</td>
                            <td>2018-08-15</td>
                        </tr>
                        <tr>
                            <td>王大锤</td>
                            <td>2000</td>
                            <td>2018-08-15</td>
                        </tr>
                        <tr>
                            <td>王大锤</td>
                            <td>2000</td>
                            <td>2018-08-15</td>
                        </tr>
                    </tbody>

                </table>
                <div class="pages">
                    <nav>
                        <ul id="pages" style="margin:84px auto" class="pagination">
                            <li class="active"><a>1</a></li>
                            <li ><a>2</a></li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>





<input type="hidden" value="${item.id?c}" id="itemId"/>
</body>
</html>