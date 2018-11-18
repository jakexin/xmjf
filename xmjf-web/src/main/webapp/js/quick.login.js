$(function () {
   $("#clickMes02").click(function () {
       var phone=$("#phone").val();
       if(isEmpty(phone)){
           layer.tips("请输入手机号!","#phone");
           return;
       }
       var code=$("#code").val();
       if(isEmpty(code)){
           layer.tips("请输入图形验证码!","#code");
           return;
       }

       $.ajax({
           type:"post",
           url:ctx+"/sendSms",
           data:{
                phone:phone,
                type:2,
               imageCode:code
           },
           dataType:"json",
           success:function (data) {
               if(data.code==200){
                   var time = 180;
                   var sid = setInterval(function() {
                       // 如果时间为0 恢复css样式和属性
                       if (0 == time) {
                           clearInterval(sid);
                           $("#clickMes02").val("获取验证码");
                           $("#clickMes02").removeClass("codeStyle");
                           $("#clickMes02").addClass("obtain");
                           return;
                       }
                       // 短信发送成功后 修改css样式和属性
                       $("#clickMes02").val(time+"s");
                       $("#clickMes02").removeClass("obtain");
                       $("#clickMes02").addClass("codeStyle");
                       time--;
                   }, 1000);
               }else{
                   layer.tips(data.message,"#clickMes02");
               }
           }
       })
   })



    $("#login").click(function () {
        var phone=$("#phone").val();
        var code=$("#verification").val();
        if(isEmpty(phone)){
            layer.tips("请输入手机号!","#phone");
            return;
        }

        if(isEmpty(code)){
            layer.tips("请输入短信验证码!","#verification");
            return;
        }

        $.ajax({
            type:"post",
            url:ctx+"/user/quickLogin",
            data:{
                phone:phone,
                code:code
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    window.location.href=ctx+"/index";
                }else{
                    layer.tips(data.message,"#login");
                }
            }
        })
    })
});
