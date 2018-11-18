$(function () {
   $("#validImg").click(function () {
       $(this).attr("src",ctx+"/getKaptchaCode?time="+new Date());
   })
});
