$(function () {
   $("#rate").radialIndicator({
       barColor: 'orange',
       barWidth: 5,
       roundCorner: true,
       radius:40,
       format: '####%'
   });
    var radialObj = $("#rate").data('radialIndicator');
    radialObj.animate($("#rate").attr("data-val"));


    //tab切换
    $('#tabs div').click(function () {
        $(this).addClass('tab_active');
        $('#tabs div').not($(this)).removeClass('tab_active');
        var show=$('#contents .tab_content').eq($(this).index());
        show.show();

        $('#contents .tab_content').not(show).hide();
        if($(this).index()==2){
            /**
             * 获取项目投资记录
             *   ajax 拼接tr
             *    追加tr 到 recordList
             */
            loadInvestRecodesList($("#itemId").val());
        }
    });

});


function loadInvestRecodesList(itemId) {
    alert("投资记录展示:"+itemId);
}

function play(){
    var rightbtn=$('#next')
    var leftbtn=$('#pre')
    var oUl=$('#slider');
    var n=0;
    var w=oUl.find('li').width()+12;
    var l=oUl.find('li').length;
    oUl.width(w*l);
    if(l<=4){
        rightbtn.unbind('click');
        leftbtn.unbind('click');
        rightbtn.hide();
        leftbtn.hide();
    }else{
        rightbtn.show();
        leftbtn.show();
        rightbtn.bind('click', function () {
            n++;
            oUl.css( 'left',''- n*w +'px' );
            if(n>0){
                leftbtn.show();
            }
            if(n==l-4){
                rightbtn.hide();
                leftbtn.show();
            }
        });
        leftbtn.bind('click',function () {
            n--;
            if(n<l-4){
                rightbtn.show();
            }
            oUl.css( 'left',''+ (-n)*w +'px' );
            leftbtn.show();
            if(n==0){
                leftbtn.hide();
            }
        })
    }
}


function picTab(ele,allNum,currentNum) {
    var ele=$('#imgLarge');
    var allNum=$('#slider').find('li');
    var  currentNum=0;
    allNum.click(function () {
        currentNum = $(this).index();
        ele.show(300);
        var ImgSrc = $(this).attr('data-url');

        ele.css('background-image', 'url('+ImgSrc+')');
    });
    $('.close').click(function () {
        ele.hide(300);
    });
    $('.left').click(function () {
        currentNum--;
        if (currentNum < 0) {
            currentNum = allNum.length - 1;
        }
        var ImgSrc = allNum.eq(currentNum).attr('data-url');
        ele.css('background-image', 'url('+ImgSrc+')');
    });

    $('.right').click(function () {
        currentNum++;
        if (currentNum > allNum.length - 1) {
            currentNum = 0;
        }
        var ImgSrc = allNum.eq(currentNum).attr('data-url');
        ele.css('background-image', 'url('+ImgSrc+')');
    })
}


function toRecharge() {
    $.ajax({
        type:"post",
        url:ctx+"/user/checkUserIsConfirmInfo",
        dataType:"json",
        success:function (data) {
            if(data.code==200){
                window.location.href=ctx+"/account/recharge";
            }else{
                layer.confirm('用户信息未认证,是否执行认证操作?', {
                    btn: ['确定','取消'] //按钮
                }, function(index){
                    layer.close(index);
                    window.location.href=ctx+"/user/auth";
                }, function(){

                });

            }
        }
    })
}


/**
 * 立即投资
 */
function doInvest() {

    if(isEmpty($("#usableMoney").val())){
        layer.tips("请输入投资金额!","#usableMoney");
        return;
    }
    var amount=parseFloat($("#usableMoney").val());// 投资金额
    var usableMoney=parseFloat($("#ye").attr("data-value"));// 账户可用金额
    if(usableMoney ==0){
        layer.tips("账户可用余额不足,请先执行充值操作!","#invest");
        return;
    }

    if(amount>usableMoney){
        layer.tips("投资金额不能大于账户余额!","#invest");
        return;
    }

    var singleMinInvestment=$("#minInvestMoney").attr("data-value");
    if(!isEmpty(singleMinInvestment)){
        singleMinInvestment=parseFloat(singleMinInvestment);
        if(amount <singleMinInvestment){
            layer.tips("投资金额不能小于单笔最小投资!","#invest");
            return;
        }
    }

    var singleMaxInvestment=$("#maxInvestMoney").val();


        if(!isEmpty(singleMaxInvestment)){
        singleMaxInvestment=parseFloat(singleMaxInvestment);
        if(amount>singleMaxInvestment){
            layer.tips("投资金额不能大于单笔最大投资!","#invest");
            return;
        }
    }
    //prompt层
    layer.prompt({title: '输入任何口令，并确认', formType: 1}, function(pass, index){
        layer.close(index);
        var itemId=$("#itemId").val();

        $.ajax({
            type:"post",
            url:ctx+"/invest/doInvest",
            data:{
                amount:amount,
                itemId:itemId,
                payPwd:pass
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    alert("项目投资成功");
                }else{
                    layer.tips(data.message,"#invest");
                }
            }
        })
    });
}
