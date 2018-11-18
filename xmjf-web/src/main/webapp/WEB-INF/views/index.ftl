<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${ctx}/css/reset.css">
    <link rel="stylesheet" href="${ctx}/css/common.css">
    <link rel="stylesheet" href="${ctx}/css/index.css">
    <link rel="stylesheet" href="${ctx}/css/percircle.css">
    <title>尚学堂金服【官网】-专注互联网金融汽车理财平台</title>
    <meta name="Keywords" content="互联网理财，汽车金融，互联网金融，理财平台，投资理财，金融理财，理财软件">
    <meta name="description" content="尚学堂金服融合”互联网+金融+汽车产业”模式，以互联网为媒介，为投资人与借款人搭建一座桥梁，通过严格的征信，资产审核，以小额、分散、高效、便捷为标准，给广大小微企业和白领提供优质的金融服务。">
    <script type="text/javascript" src="/js/assets/config.js"></script>
    <script type="text/javascript" src="${ctx}/js/assets/require.js" data-main="${ctx}/js/index"></script>
</head>
<body>
<div class="top_wrap">
    <div class="top">
        <p class="fl phone">欢迎致电：021-67690939</p>
    </div>
</div>
<div class="header" id="header">
    <div class="contain">
        <a href="/index?0?0" class="logo">
        </a>
        <div class="header_nav" id="indexNav">
            <a href="${ctx}/index?0?0" class="header_nav_active">首页</a>
            <a href="${ctx}/item/index">我要投资</a>
            <a href="${ctx}/security?0?2">安全保障</a>
            <a href="${ctx}/account/index">我的账户</a>
            <a href="${ctx}/introduce?0?4">关于我们</a>
        </div>
        <div class="header_button">
        <#if userInfo??>
            <div id="hasUserId">

                <div class='btn login' style="margin:36px auto 0; width: 164px;border:1px solid #fff;background: transparent">
                    <p>${userInfo.phone}<img style="margin:15px 0 0 5px;" src="/img/xl-icon.png" alt=""></p>
                </div>
                <div id="option" class="option display">
                    <div class="option-message">
                        <a class="selected" href="${ctx}/user/site?-1?3" style="cursor:pointer;">消息中心</a>
                    </div>
                    <div class="option-two">
                        <a class="selected2" href="${ctx}/user/log?3?3" style="cursor:pointer;">资金记录</a>
                    </div>
                    <div class="option-two">
                        <a class="selected3" href="${ctx}/user/inviteCode?5?3" style="cursor:pointer;">邀请好友</a>
                    </div>
                    <div class="option-two">
                        <a class="selected4" href="${ctx}/user/exit" id="exit" style="cursor:pointer;">我要退出</a>
                    </div>
                </div>
            </div>
        <#else>
            <div id="noUserId" style="width:142px;float: right">
                <a href="${ctx}/user/login"><input class='btn register' id='loginPage' type="button" value="登录"></a>
                <a href="${ctx}/user/register"><input class='btn register' id='registerPage' type="button" value="注册"></a>
            </div>
        </#if>
        </div>
    </div>
</div>

<div class="index_banner">

    <div class="banner">
        <ul class="pic_area"  >
            <a href="javascript:void(0)"><li style="background-image: url(${ctx}/img/item/1506490323436.jpg)"></li></a>
            <a href="javascript:void(0)"><li style="background-image: url(${ctx}/img/item/1508931304000.png)"></li></a>
        </ul>
        <ol class="picDot" id="dot">
        <#--<#list pictureList as item>
            <li></li>
        </#list>-->
        </ol>
    </div>
    <div class="show_area">
        <div class="center">
            <span>累计注册人数（人）<strong>${userNum!"10000"}</strong></span>
            <span>累计投资金额（元）<strong>${investAmount!"1000000"}</strong></span>
            <div class="announce_area fr" id="news">
                <ul class="announce">
             <#--   <#list ptdtArticles.list as item>
                    <li>
                        <a href="/article/${item.id?c}?4?4" style="color: #a2a2a2;">
                            <p class="content">${item.name}</p>
                            <p class="date">${item.addtime?string("yyyy-MM-dd")}</p>
                        </a>
                    </li>
                </#list>-->
                </ul>
                <a href="/notice?1?4" class="more_announce"></a>
            </div>

        </div>
    </div>
</div>



<div class="center">
    <div class="security_area clear">
        <a href="/quality"><div></div><P>优质平台</P></a>
        <a href="/safety"><div></div><P>安全保障</P></a>
        <a href="/transparent"><div></div><p>信息透明</p></a>
        <a href="/product"><div></div><p>产品丰富</p></a>
    </div>
    <div class="recommend">
        <div><img src="/img/tuijian.jpg" alt="" width="252" height="534"></div>
        <div class="right_part">
            <h2>热门推荐</h2>
            <div class="box-warp">
                <div class="box">
                    <h3>新手专享</h3>
                <#if isNewItem??>
                    <div class="circle">
                        <div class="circle_content">
                            <p>预期年化收益率</p>
                            <p>${isNewItem.itemRate}<em>%<#if isNewItem.itemAddRate!=0>+#{isNewItem.itemAddRate}%</#if></em></p>
                            <p>期限<em>&nbsp;&nbsp;${isNewItem.itemCycle}<#if isNewItem.itemCycleUnit==1>天<#elseif isNewItem.itemCycleUnit==2>月<#elseif isNewItem.itemCycleUnit==3>季<#elseif isNewItem.itemCycleUnit==4>年</#if></em></p>
                        </div>
                        <div  class='aa' id="newMember" data-value="${isNewItem.itemScale}"></div>
                        <div  id="newcountdown" data-time="<#if isNewItem.countdown??>${isNewItem.countdown?c}</#if>"></div>

                    </div>
                    <div class="count">
                        <p class="fl"><span>余额</span><strong>${(isNewItem.itemAccount-isNewItem.itemOngoingAccount)?c}元</strong></p>
                        <p class="fr"><span>总额</span><strong>${isNewItem.itemAccount?c}元</strong></p>
                    </div>
                    <#if isNewItem.countdown gt 0>
                        <div class="button_area" style="display: none;" id="newdotrailer"><a href="/item/details/${isNewItem.id?c}?${isNewItem.id?c}"><input style="cursor: pointer;" type="button" value='立即投标'></a></div>
                        <div class="button_area" id="newtime">
                            <p class="class_input">
                                <strong class="time">
                                    <time id="newhour">--</time>&nbsp;:
                                    <time id="newmin">--</time>&nbsp;:
                                    <time id="newsec">--</time>
                                </strong>
                                <strong>开抢</strong>
                            </p>
                        </div>
                    <#else>
                        <div class="button_area" id="newdotrailer"><a href="/item/details/${isNewItem.id?c}?${isNewItem.id?c}"><input style="cursor: pointer;" type="button" value='立即投标'></a></div>
                    </#if>
                <#--<div class="button_area"><a href="/item/details/${isNewItem.id?c}?${isNewItem.id?c}"><input style="cursor: pointer;" type="button" value='立即投资'></a></div>-->
                <#else>
                    <div class="circle">
                        <div class="circle_content">
                            <p>预期年化收益率</p>
                            <p>18<em>%</em></p>
                            <p>期限<em>&nbsp;&nbsp;7天</em></p>
                        </div>
                        <div  class='aa' id="newMember" data-value="1"></div>
                    </div>
                    <div class="count">
                        <p class="fl"><span>余额</span><strong>--元</strong></p>
                        <p class="fr"><span>总额</span><strong>--元</strong></p>
                    </div>
                    <div class="button_area"><input  type="button" value='即将开放' style="background: #c9c9c9"></div>
                </#if>
                </div>
            </div>
            <div class="box-warp">
                <div class="flipper">
                    <div class="box front" id="appdetails">
                        <h3>APP专享</h3>
                    <#if moveVipItem??>
                        <div class="circle">
                            <div class="circle_content">
                                <p>预期年化收益率</p>
                                <p>${moveVipItem.itemRate}<em>%<#if moveVipItem.itemAddRate!=0>+#{moveVipItem.itemAddRate}%</#if></em></p>
                                <p>期限<em>&nbsp;&nbsp;${moveVipItem.itemCycle}<#if moveVipItem.itemCycleUnit==1>天<#elseif moveVipItem.itemCycleUnit==2>月<#elseif moveVipItem.itemCycleUnit==3>季<#elseif moveVipItem.itemCycleUnit==4>年</#if></em></p>
                            </div>
                            <div  id="app" data-value="${moveVipItem.itemScale}"></div>
                        </div>
                        <div class="count">
                            <p class="fl"><span>余额</span><strong>${(moveVipItem.itemAccount-moveVipItem.itemOngoingAccount)?c}元</strong></p>
                            <p class="fr"><span>总额</span><strong>${moveVipItem.itemAccount?c}元</strong></p>
                        </div>
                        <div class="button_area"><input style="cursor: pointer;" type="button" id='gotoDownload' value='立即下载'></div>
                    <#else>
                        <div style="width:100%;">
                            <img src="/img/appqrcode.png" alt="" height='285' width='285' style="position: relative;left: 15px;top:-10px;">
                            <p style="font-size: 18px;color:#6d6d6d;text-align: center">扫描二维码下载APP</p>
                        </div>
                    </#if>

                    </div>
                    <div class="box back" id="appDownloadErweima">
                        <h3>APP专享</h3>
                        <img src="/img/appqrcode.png" alt="" height='285' width='285' style="position: relative;left: 15px;top:-10px;">
                        <p style="font-size: 18px;color:#6d6d6d;text-align: center">扫描二维码下载APP</p>
                    </div>
                </div>
            </div>
            <div class="box-warp">
                <div class="box">
                    <h3>发标预告</h3>
                <#if timeItem??>
                    <div class="circle">
                        <div class="circle_content">
                            <p>预期年化收益率</p>
                            <p>${timeItem.itemRate}<em>%<#if timeItem.itemAddRate!=0>+#{timeItem.itemAddRate}%</#if></em></p>
                            <p>期限<em>&nbsp;&nbsp;${timeItem.itemCycle}<#if timeItem.itemCycleUnit==1>天<#elseif timeItem.itemCycleUnit==2>月<#elseif timeItem.itemCycleUnit==3>季<#elseif timeItem.itemCycleUnit==4>年</#if></em></p>
                        </div>
                        <div  id="trailer" data-value="${timeItem.itemScale}"></div>
                        <div  id="countdown" data-time="${timeItem.countdown?c}"></div>
                    </div>
                    <div class="count">
                        <p class="fl"><span>余额</span><strong>${(timeItem.itemAccount-timeItem.itemOngoingAccount)?c}元</strong></p>
                        <p class="fr"><span>总额</span><strong>${timeItem.itemAccount?c}元</strong></p>
                    </div>
                    <#if timeItem.countdown gt 0>
                        <div class="button_area" style="display: none;" id="dotrailer"><a href="/item/details/${timeItem.id?c}?${timeItem.id?c}"><input style="cursor: pointer;" type="button" value='立即投标'></a></div>
                        <div class="button_area" id="time">
                            <p class="class_input">
                                <strong class="time">
                                    <time id="hour">--</time>&nbsp;:
                                    <time id="min">--</time>&nbsp;:
                                    <time id="sec">--</time>
                                </strong>
                                <strong>开抢</strong>
                            </p>
                        </div>
                    <#else>
                        <div class="button_area" id="dotrailer"><a href="/item/details/${timeItem.id?c}?${timeItem.id?c}"><input style="cursor: pointer;" type="button" value='立即投标'></a></div>
                    </#if>
                <#else>

                    <div class="circle">
                        <div class="circle_content">
                            <p>预期年化收益率</p>
                            <p><em>7.6<em style="font-size: 20px;"> %</em> ~ </em>12<em>%</em></p>
                            <p>期限<em>&nbsp;&nbsp;30~360天</em></p>
                        </div>
                        <div  id="trailer" data-value="1"></div>
                    </div>
                    <div class="count">
                        <p class="fl"><span>余额</span><strong>--元</strong></p>
                        <p class="fr"><span>总额</span><strong>--元</strong></p>
                    </div>
                    <div class="button_area"><input  type="button"  value='敬请期待' style="background: #c9c9c9"></div>
                </#if>
                </div>
            </div>

        </div>
    </div>
    <div class="recommend">
        <div><img src="/img/licai.jpg" alt="" width="252" height="534"></div>
        <div class="right_part">
            <h2>理财项目</h2>
            <a href="/item/investmentList?0?1" class="more_item">更多</a>
                    <div class="box-warp">
                        <div class="box">
                                <h3>飞猪理财</h3>
                                <div class="circle">
                                    <div class="circle_content">
                                        <p>预期年化收益率</p>
                                        <p>10<em>%</em></p>
                                        <p>期限<em>&nbsp;&nbsp;30天</em></p>
                                    </div>
                                      <#--  <div  id="chedaibao" data-value="${item.itemScale}"></div>
                                        <div  id="xuechebao" data-value="${item.itemScale}"></div>
                                        <div  id="cheshangbao" data-value="${item.itemScale}"></div>-->
                                </div>
                                <div class="count">
                                    <p class="fl"><span>余额</span><strong>100元</strong></p>

                                    <p class="fr"><span>总额</span><strong>100000元</strong></p>
                                </div>
                            <div class="button_area"><a href=""><input style="cursor: pointer;background: #c9c9c9" type="button" value='已抢完'></a></div>
                        </div>
                    </div>

            <div class="box-warp">
                <div class="box">
                    <h3>飞猪理财</h3>
                    <div class="circle">
                        <div class="circle_content">
                            <p>预期年化收益率</p>
                            <p>10<em>%</em></p>
                            <p>期限<em>&nbsp;&nbsp;30天</em></p>
                        </div>
                    <#--  <div  id="chedaibao" data-value="${item.itemScale}"></div>
                      <div  id="xuechebao" data-value="${item.itemScale}"></div>
                      <div  id="cheshangbao" data-value="${item.itemScale}"></div>-->
                    </div>
                    <div class="count">
                        <p class="fl"><span>余额</span><strong>100元</strong></p>

                        <p class="fr"><span>总额</span><strong>100000元</strong></p>
                    </div>
                    <div class="button_area"><a href=""><input style="cursor: pointer;background: #c9c9c9" type="button" value='已抢完'></a></div>
                </div>
            </div>

            <div class="box-warp">
                <div class="box">
                    <h3>飞猪理财</h3>
                    <div class="circle">
                        <div class="circle_content">
                            <p>预期年化收益率</p>
                            <p>10<em>%</em></p>
                            <p>期限<em>&nbsp;&nbsp;30天</em></p>
                        </div>
                    <#--  <div  id="chedaibao" data-value="${item.itemScale}"></div>
                      <div  id="xuechebao" data-value="${item.itemScale}"></div>
                      <div  id="cheshangbao" data-value="${item.itemScale}"></div>-->
                    </div>
                    <div class="count">
                        <p class="fl"><span>余额</span><strong>100元</strong></p>

                        <p class="fr"><span>总额</span><strong>100000元</strong></p>
                    </div>
                    <div class="button_area"><a href=""><input style="cursor: pointer;background: #c9c9c9" type="button" value='已抢完'></a></div>
                </div>
            </div>
        </div>
    </div>
    <div class="news clear">
        <div class="info fl">
            <h2>行业信息</h2>
            <a href="/notice?1?4" class="more_item">更多</a>
            <ul class="info_list clear">
                        <li>
                            <a href="javascript:void(0)">
                                <img src="${ctx}/img/hy/1506492138607.jpg" alt="" width="228" height="124">
                                <p>互联网金融迈向新时代</p>
                            </a>
                        </li>
                <li>
                    <a href="javascript:void(0)">
                        <img src="${ctx}/img/hy/1507777617312.jpg" alt="" width="228" height="124">
                        <p>p2p贷款方向路在何方?</p>
                    </a>
                </li>
                <li>
                    <a href="javascript:void(0)">
                        <img src="${ctx}/img/hy/1508470033557.png" alt="" width="228" height="124">
                        <p>p2p与o2o的姻缘</p>
                    </a>
                </li>
            </ul>
        </div>
        <div class="activity fr">
            <h2>平台动态</h2>
            <a href="/platform?4?4" class="more_item">更多</a>
            <ul class="activity_list">
                <li><a href="#">尚学堂金福即将上线</a></li>
                <li><a href="#">p2p贷款方向路在何方?</a></li>
                <li><a href="#">p2p与o2o的姻缘</a></li>
            </ul>
        </div>
    </div>
    <div class="tiyanbiao">
        <a href="/register"><img src="/img/tiyanbiao.png" alt="" width="1200" height="181"></a>
    </div>
    <div class="companies">
        <h2>合作伙伴</h2>
        <img src="/img/companise/companies.png" alt="" width="1200" height="132">
    </div>
</div>





</body>
</html>