$(function () {
   $("#identityNext").click(function () {
       var realName= $("#realName").val();
       var card=$("#card").val();
       var payPassword=$("#_ocx_password").val();
       var conformPwd=$("#_ocx_password1").val();
       if(isEmpty(realName)){
         layer.tips("请输入姓名","#realName");
         return;
      }
       if(isEmpty(card)){
           layer.tips("请输入身份证号!","#card");
           return;
       }
       if(isEmpty(payPassword)){
           layer.tips("请输入密码!","#_ocx_password");
           return;
       }
       if(isEmpty(conformPwd)){
           layer.tips("请输入确认密码!","#_ocx_password1");
           return;
       }
       if(payPassword !=conformPwd){
           layer.tips("密码输入不一致!","#identityNext");
           return;
       }
       $.ajax({
           type:"post",
           url:ctx+"/user/doAuth",
           data:{
               realName:realName,
               cardNo:card,
               payPassword:payPassword,
               confirmPassword:conformPwd
           },
           dataType:"json",
           success:function (data) {
               if(data.code==200){
                  window.location.href=ctx+"/account/index";
               }else{
                  layer.tips(data.message,"#identityNext");
               }
           }
       })




   })
});
