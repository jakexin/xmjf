$(function () {
    /**
     * 点击更换验证码
     */
    $(".validImg").click(function () {
        $(this).attr("src", ctx + "/getKaptchaCode?time=" + new Date());
    });

    /**
     * 发送短信验证码
     */
    $("#clickMes").click(function () {

        var phone = $("#phone").val();
        var code = $("#code").val();

        // 手机号不为空
        if (isEmpty(phone)) {
            layer.tips("手机号不可以为空！", "#phone");
            return;
        }

        // 图形验证码不为空
        if (isEmpty(code)) {
            layer.tips("验证码不可以为空！", "#code");
            return;
        }

        // 发送ajax请求
        $.ajax({
            type: "POST",
            url: ctx + "/sendSms",
            data: {
                phone: phone,
                code: code,
                type: 1
            },
            dataType: "json",
            success: function(result) {
                console.log(result);
                if (200 == result.code) {
                    var time = 180;
                    var sid = setInterval(function() {
                        // 如果时间为0 恢复css样式和属性
                        if (0 == time) {
                            clearInterval(sid);
                            $("#clickMes").val("获取验证码");
                            $("#clickMes").removeClass("codeStyle");
                            $("#clickMes").addClass("obtain");
                            return;
                        }

                        // 短信发送成功后 修改css样式和属性
                        $("#clickMes").val("获取验证码(" + time + "s)");
                        // $("#clickMes").css("font-size", "12px");
                        // $("#clickMes").css("color", "black");
                        // $("#clickMes").css("background", "#DCDCDC");
                        // $("#clickMes").attr("disabled", true);
                        $("#clickMes").removeClass("obtain");
                        $("#clickMes").addClass("codeStyle");
                        time--;
                    }, 1000);
                }else {
                    clearInterval(sid);
                    layer.tips("短信发送失败！", "#clickMes");
                    $(".validImg").attr("src", ctx + "/getKaptchaCode?time=" + new Date());
                }
            }
        });
    });

    $("#register").click(function () {
        var phone = $("#phone").val();

        // 手机号不为空
        if (isEmpty(phone)) {
            layer.tips("手机号不可以为空！", "#phone");
            return;
        }

        var password=$("#password").val();

        if (isEmpty(password)) {
            layer.tips("密码不可以为空！", "#password");
            return;
        }
        var verification=$("#verification").val();
        if (isEmpty(verification)) {
            layer.tips("手机验证码不可以为空！", "#verification");
            return;
        }
        $.ajax({
            type:"post",
            url:ctx+"/user/register",
            data:{
                phone:phone,
                code:verification,
                password:password
            },
            dataType:"json",
            success:function (data) {
              if(data.code==200){
                  layer.msg("用户注册成功");
                  setTimeout(function () {
                      window.location.href=ctx+"/login";
                  },1000);
              } else{
                  layer.tips(data.message,"#register");
              }
            }
        })
    })
});