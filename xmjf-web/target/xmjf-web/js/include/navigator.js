require.config(window.jshead);
define(["jquery","layer"], function($,layer) {
    layer.config({
        path: '/js/layer/'
    });

    //顶部
    $('.weixin').click(function () {
        $('.toperweima').slideToggle(300);
        $(this).toggleClass('weixin_acitive');

    });
//小细节右侧
    $('#smallDetailsNav li').hover(function () {
        var _this=$(this).find('div');
        _this.show();
    },function () {
        var _this=$(this).find('div');
        _this.hide();
    });
    $("#smallDetailsNav .gotop").click(function(){
        $('html,body').animate({'scrollTop':0},500);
    });
//我的账户导航

    var index=decodeURI(window.location.href).split('?')[1];
    if(index!=-1){
        $('#userNav').find('a').eq(index).addClass('user_active');
    }

    $('#siderRealname').hover(function () {
        $('#realnameTips').show();
    },function () {
        $('#realnameTips').hide();
    });

    $('#siderPhoneSet').hover(function () {
        $('#phoneTips').show();
    },function () {
        $('#phoneTips').hide();
    });

    $('#siderBankCard').hover(function () {
        $('#bankTips').show();
    },function () {
        $('#bankTips').hide();
    });

//关于我们导航

    var index2=decodeURI(window.location.href).split('?')[1];
    $('#aboutSiderbar').find('a').eq(index2).addClass('active');

//头部导航
    var index3=decodeURI(window.location.href).split('?')[2];
    $('#indexNav').find('a').eq(index3).addClass('header_nav_active');

    if(decodeURI(window.location.href).indexOf('details')!=-1){
        $('#indexNav').find('a').eq('1').addClass('header_nav_active');
    }


//充值和提现
    var index4=decodeURI(window.location.href).split('?')[3];
    $('#rechargeAndCash').find('a input').eq(index4).addClass('button_active');


    //退出登录
    $('#hasUserId').hover(function(){
        $('#option').removeClass('display')
    },function(){
        $('#option').addClass('display');
    });

    $('#exit').bind('click',function(){
        $.ajax({
            type: "POST",
            url: "/loginOut",
            data: {},
            dataType: "json",
            success: function (data) {

                //window.location.href='/login'
            }
        });
    })

    var oDate=new Date();
    var hour=oDate.getHours();
    var hello='';
    if(0<hour<12){
        hello='上午好：'
    }else if(hour==12){
        hello='中午好：'
    }else if(12<hour<18){
        hello='下午好：'
    }else{
        hello='晚上好：'
    }

//用户信息
    $.ajax({
        type: "POST",
        dataType: "JSON",
        data: {},
        url: "/user/security/information",
        success: function (data) {
            $('#siderMobile').text(data.user.mobile);
            $('#siderUsername').text(data.user.mobile);
            $('#siderTime').text(hello);
            if(data.user.mobile){
                var src= 'https://qiyukf.com/script/cb83fc6792a4c8f3a2387d3a94f6abfe.js?uid=['+data.security.userId+']&name=['+data.user.username+']&email=['+data.user.email+']&mobile=['+data.user.mobile+']';
                $('#onlineService').attr('src',src)
            }

            if(data.security.realnameStatus==1){
                $('#realnameTips').html('您已实名认证。<a href="/account/bank?-1?3">立即绑卡</a>');
                $('#realnameTips').parent().addClass('liAcive');
                //银行卡信息
                $.ajax({
                    type: "POST",
                    dataType: "JSON",
                    data: {},
                    url: "/account/bank/getBankByUserId",
                    success: function (data) {
                        if(data.result=='success'){
                            $('#siderBankCard').addClass('liAcive');
                            $('#siderBankCard').unbind("mouseenter").unbind("mouseleave");
                            $('#realnameTips').html('您已实名认证');
                        }else{
                            $('#bankTips').html('点击图标去绑定银行卡');
                            $('#siderBankCard').removeClass('liAcive').click(function () {
                                window.location.href='/account/bank?-1?3'
                            });
                        }
                        }
                    })
            }else if(data.security.realnameStatus==0){
                $('#realnameTips').html('请完成实名认证！<a href="/user/security?6?3">立即认证</a>');
                $('#realnameTips').parent().removeClass('liAcive');
                $('#bankTips').html('请先实名认证！<a href="/user/security?6?3">立即认证</a>');
            }


        }
    });

    //头部登录注册按钮

    if(decodeURI(window.location.href).indexOf('register')!=-1){
        $('#registerPage').addClass('login').removeClass('register');
    }
    if(decodeURI(window.location.href).indexOf('login')!=-1){
        $('#loginPage').addClass('login').removeClass('register');
    }

    //文章
    if(decodeURI(window.location.href).indexOf('article')!=-1){
        $('#indexNav').find('a').eq(4).addClass('header_nav_active');
    }

    //底部
    $('#footerMore').click(function () {
        $('#moreContent').slideDown(100);
        $(this).hide();
        $('#footerLess').show();
    });
    $('#footerLess').click(function () {
        $('#moreContent').slideUp(100);
        $(this).hide();
        $('#footerMore').show();
    });
//切换年份获取列表
    $('#changeBtnleft').on('click',function() {
        $(this).addClass('year_checked');
        $('#changePagenext').addClass('yunying_change');
        $('#changePage').removeClass('yunying_change');
        $('#changeBtnright').removeClass('year_checked');
    });
    $('#changeBtnright').on('click',function() {
        $(this).addClass('year_checked');
        $('#changePagenext').removeClass('yunying_change');
        $('#changePage').addClass('yunying_change');
        $('#changeBtnleft').removeClass('year_checked');
    })
//在线客服
    $('#onlineService').attr('src','https://qiyukf.com/script/cb83fc6792a4c8f3a2387d3a94f6abfe.js?uid=[weidenglu]&name=[未登录]&email=[未登录]&mobile=[未登录]')

});

