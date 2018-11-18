/**
 * Created by ROGSTAR on 2016/10/10.
 */
//handlebar处理中心
define(["jquery", "handlebars" ,'common'], function($, Handlebars, Common) {
    var show = {

        //邀请好友列表
        friendsListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].inviteAddtime = Common.changemsTodate(data[i].inviteAddtime);
            }
            var source=$('#friendsListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#friendsList").html(html);
        },
        //投标记录列表
        itemautoListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemstime(data[i].addtime);
                if(data[i].investStatus==0){
                    data[i].investStatus='投标成功'
                }
                else if(data[i].investStatus==1){
                    data[i].investStatus='复审通过'
                }
                else if(data[i].investStatus==2){
                    data[i].investStatus='流标'
                }
                else if(data[i].investStatus==3){
                    data[i].investStatus='已还款'
                }

               if(data[i].itemLockStatus==0){
                    data[i].autoItemAddRate=(parseFloat(data[i].itemRate)+parseFloat(data[i].itemAddRate)).toFixed(2)
                }
            }

            var source=$('#itemautoListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#autorecordList").html(html);
        },
        //日历列表
        bottomTableTemp:function(data){

            var source=$('#bottomTableTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#bottomTable").html(html);
        },
        //投标记录列表
        recordListFunc:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemstime(data[i].addtime);
                if(data[i].investType==7){
                    data[i].investType='/img/app_phone.png'
                }if(data[i].investType==8){
                    data[i].investType='/img/andirod.png'
                }
                else if(data[i].investType==1){
                    data[i].investType='/img/pcduan.png'
                } else if(data[i].investType==4){
                    data[i].investType='/img/weixinduan.png'
                } else if(data[i].investType==10 || data[i].investType==5){
                    data[i].investType='/img/zidongtoubiao.png'
                }else if(data[i].investType==9){
                    data[i].investType='/img/suotou.png'
                }else{
                    data[i].investType=''
                }
            }
            var source=$('#recordListTemp').html();
            var template=Handlebars.compile(source);
            Handlebars.registerHelper('compare', function (left, operator, right, options) {
                if (arguments.length < 3) {
                    throw new Error('Handlerbars Helper "compare" needs 2 parameters');
                }
                var operators = {
                    '==': function (l, r) {
                        return l == r;
                    },
                    '===': function (l, r) {
                        return l === r;
                    },
                    '!=': function (l, r) {
                        return l != r;
                    },
                    '!==': function (l, r) {
                        return l !== r;
                    },
                    '<': function (l, r) {
                        return l < r;
                    },
                    '-': function (l, r) {
                        return l - r;
                    },
                    '>': function (l, r) {
                        return l > r;
                    },
                    '<=': function (l, r) {
                        return l <= r;
                    },
                    '>=': function (l, r) {
                        return l >= r;
                    },
                    'typeof': function (l, r) {
                        return typeof l == r;
                    }
                };

                if (!operators[operator]) {
                    throw new Error('Handlerbars Helper "compare" doesn\'t know the operator ' + operator);
                }

                var result = operators[operator](left, right);

                if (result) {
                    return options.fn(this);
                } else {
                    return options.inverse(this);
                }
            });
            var html=template(data);
            $("#recordList").html(html);
        },
        //合同列表
        contractsListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodate(data[i].addtime);
            }
            var source=$('#contractsListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#contractsList").html(html);
        },
        //项目列表
        pcItemListTemp:function(data){

            for(var i=0;i<data.length;i++){
                if(data[i].itemType==1){
                    data[i].itemType='学车宝'
                }else  if(data[i].itemType==2){
                    data[i].itemType='车商宝'
                }else  if(data[i].itemType==3){
                    data[i].itemType='车贷宝'
                }
                if(data[i].itemCycleUnit==1){
                    data[i].itemCycleUnit='天'
                }else if(data[i].itemCycleUnit==2){
                    data[i].itemCycleUnit='月'
                }

                if(data[i].guaranteeUsername=='台州爱车在线汽车服务有限公司'){
                    data[i].guaranteeUsername='/img/aichezaixian.png'
                }
                if(data[i].guaranteeUsername=='浙江爱车在线实业投资有限公司'){
                    data[i].guaranteeUsername='/img/aichezaixian.png'
                }
                if(data[i].guaranteeUsername=='浙江正合汽车销售服务有限公司株洲分公司'){
                    data[i].guaranteeUsername='/img/zhengheqiche.png'
                }
                if(data[i].guaranteeUsername=='杭州安保资产管理有限公司'){
                    data[i].guaranteeUsername='/img/anbao.png'
                }
                if(data[i].guaranteeUsername=='杭州御腾汽车服务有限公司'){
                    data[i].guaranteeUsername='/img/yuteng.png'
                }
                if(data[i].guaranteeUsername=='浙江驾通网络科技有限公司'){
                    data[i].guaranteeUsername='/img/jiageiwo.png'
                }
                if(data[i].guaranteeUsername=='宁波金元宝汽车服务有限公司'){
                    data[i].guaranteeUsername='/img/jinyuanbaojituan.png'
                }
                if(data[i].guaranteeUsername=='天威担保有限公司'){
                    data[i].guaranteeUsername='/img/tianweidanbao.png'
                }
                if(data[i].guaranteeUsername=='上饶市掌讯汽车贸易有限公司'){
                    data[i].guaranteeUsername='/img/Zxun.png'
                }
                if(data[i].guaranteeUsername=='江西聚丰贸易有限公司'){
                    data[i].guaranteeUsername='/img/jufeng.png'
                }
                if(data[i].guaranteeUsername=='昆明宏邦融信汽车服务有限责任公司'){
                    data[i].guaranteeUsername='/img/hongbang.png'
                }
                if(data[i].guaranteeUsername=='安徽三合汽车贸易有限公司'){
                    data[i].guaranteeUsername='/img/sanheguoji.png'
                }
                if(data[i].guaranteeUsername=='遂昌大昌汽车贸易有限公司'){
                    data[i].guaranteeUsername='/img/dachang.png'
                }
            }


            var source=$('#pcItemListTemp').html();
            var template=Handlebars.compile(source);
            Handlebars.registerHelper('compare', function (left, operator, right, options) {
                if (arguments.length < 3) {
                    throw new Error('Handlerbars Helper "compare" needs 2 parameters');
                }
                var operators = {
                    '==': function (l, r) {
                        return l == r;
                    },
                    '===': function (l, r) {
                        return l === r;
                    },
                    '!=': function (l, r) {
                        return l != r;
                    },
                    '!==': function (l, r) {
                        return l !== r;
                    },
                    '<': function (l, r) {
                        return l < r;
                    },
                    '-': function (l, r) {
                        return l - r;
                    },
                    '>': function (l, r) {
                        return l > r;
                    },
                    '<=': function (l, r) {
                        return l <= r;
                    },
                    '>=': function (l, r) {
                        return l >= r;
                    },
                    'typeof': function (l, r) {
                        return typeof l == r;
                    }
                };

                if (!operators[operator]) {
                    throw new Error('Handlerbars Helper "compare" doesn\'t know the operator ' + operator);
                }

                var result = operators[operator](left, right);

                if (result) {
                    return options.fn(this);
                } else {
                    return options.inverse(this);
                }
            });
            var html=template(data);
            $("#pcItemList").html(html);

        },


        //红包列表
        rewardListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodayslash(data[i].addtime);
                data[i].expiredTime = Common.changemsTodayslash(data[i].expiredTime);

            }
            var source=$('#rewardListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#rewardList").html(html);
        },
        //加息券列表
        redenvelopeListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodayslash(data[i].addtime);
                data[i].expiredTime = Common.changemsTodayslash(data[i].expiredTime);
            }
            var source=$('#redenvelopeListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#redenvelopeList").html(html);
        },

        //资金记录列表
        moneyListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodayslash(data[i].addtime);
                data[i].expiredTime = Common.changemsTodayslash(data[i].expiredTime);
                if(data[i].budgetType=='1'){
                    data[i].budgetType='收入'
                }else if(data[i].budgetType=='2'){
                    data[i].budgetType='支出'
                }
            }
            var source=$('#moneyListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#moneyList").html(html);
        },

        //提现记录列表
        recordListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemstime(data[i].addtime);
                data[i].expiredTime = Common.changemstime(data[i].expiredTime);
                if(data[i].status==0){
                    data[i].status='失败'
                }else if(data[i].status==1){
                    data[i].status='成功'
                }else if(data[i].status==2){
                    data[i].status='审核中'
                }
            }
            var source=$('#recordListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#recordList").html(html);
        },

        //充值记录列表
        rechargeListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodayslash(data[i].addtime);
                data[i].expiredTime = Common.changemsTodayslash(data[i].expiredTime);
                if(data[i].status==0){
                    data[i].status='失败'
                }else if(data[i].status==1){
                    data[i].status='成功'
                }else if(data[i].status==2){
                    data[i].status='审核中'
                }
            }
            var source=$('#rechargeListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#rechargeList").html(html);
        },

        //自动投标列表
        autoListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodayslash(data[i].addtime);
                data[i].expiredTime = Common.changemsTodayslash(data[i].expiredTime);
                if(data.length==0){

                }
            }
            var source=$('#autoListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#autoList").html(html);
        },


        //消息中心列表
        messageListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemstime(data[i].addtime);
                if(data[i].status==0){
                    $('.messageImg').attr('src','/img/weidu.png')
                }else{
                    $('.messageImg').attr('src','/img/yidu.png')
                }
            }
            var source=$('#messageListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#messageList").html(html);
        },
        
        //行业信息列表
        noticeListTemp:function(data){
            for(var i=0;i<data.length;i++){
                data[i].addtime = Common.changemsTodate(data[i].addtime);
            }
            var source=$('#noticeListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#noticeList").html(html);
        },
        
        //平台列表
        platformListTemp:function(data){
            for(var i=0;i<data.length;i++){
            	var name = data[i].name;
            	var summary = data[i].summary;
            	if(name.length>=20 && name!=null){
            		data[i].name= name.substring(0,19)+"..."
            	}
            	if(summary!=null && summary.length>=40){
	            	data[i].summary= summary.substring(0,39)+"..."
            	}
                data[i].addtime = Common.changemsTodate(data[i].addtime);
            }
            var source=$('#platformListTemp').html();
            var template=Handlebars.compile(source);
            var html=template(data);
            $("#platformList").html(html);
        },

    };
    return show;
});
