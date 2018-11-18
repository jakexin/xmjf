$(function () {
    /**
     * 加载投资列表数据到前台
     */
    loadItemListDataInfo();

    $(".tab").click(function () {
        $(this).addClass("list_active");
        $(".tab").not(this).removeClass("list_active");
        var isHistory=0;
        var itemCycle=$(this).index();// 0 1 2 3 4
        if(itemCycle==4){
            isHistory=1;
        }
        var itemType=$("#itemType").val();

        loadItemListDataInfo(itemCycle,itemType,isHistory);

    })
});


function loadItemListDataInfo(itemCycle,itemType,isHistory,pageNum,pageSize) {
    var params={};
    params.isHistory=0;// 默认查询可投项目
    params.pageNum=1;
    params.pageSize = 10;
    if(!isEmpty(itemCycle)){
        params.itemCycle=itemCycle;
    }
    if(!isEmpty(itemType)){
        params.itemType=itemType;
    }
    if(!isEmpty(isHistory)){
        params.isHistory=isHistory;
    }

    if(!isEmpty(pageNum)){
       params.pageNum=pageNum;
    }
    if(!isEmpty(pageSize)){
        params.pageSize=pageSize;
    }





    $.ajax({
        type:"post",
        url:ctx+"/item/list",
        data:params,
        dataType:"json",
        success:function (data) {
           var list=data.list;
           var paginator=data.paginator;
           if(paginator.total>0){
                initTrHtml(list);
                initPageHtml(paginator);

               /**
                * 渲染原型进度框
                */
               initItemScale();

               /**
                * 渲染倒计时 待开放记录
                */
               initSyTime();

           }else{
               $("#pages").html("<img style='margin-left: -70px;padding:40px;' " +
                   "src='/img/zanwushuju.png'>");
               $("#pcItemList").html("");

           }

        }
    })
}

function initTrHtml(list) {
    if(null !=list && list.length>0){
        var trs="";
        for(var i=0;i<list.length;i++){
            var item=list[i];
            trs=trs+"<tr>";
            // 年化率显示
            trs=trs+"<td>";
            trs=trs+"<strong>"+item.item_rate+"%</strong>";
            if(item.item_add_rate>0){
                trs=trs+"<span>+"+item.item_add_rate+"%</span>";
            }
            trs=trs+"</td>";

            // 期限值
            trs=trs+"<td>";
            trs=trs+item.item_cycle+"|";
            if(item.item_cycle_unit==1){
                trs=trs+"天";
            }
            if(item.item_cycle_unit==2){
                trs=trs+"月";
            }
            if(item.item_cycle_unit==3){
                trs=trs+"季";
            }
            if(item.item_cycle_unit==4){
                trs=trs+"年";
            }
            trs=trs+"</td>";

            // 项目名称
            trs=trs+"<td>"+item.item_name;
            // 新手标
            if(item.item_isnew==1){
                trs=trs+"<strong class='colorful' new>NEW</strong>";
            }
            // 手机端
            if(item.item_isnew==0 && item.move_vip==1){
                trs=trs+"<strong class='colorful' app>APP</strong>";
            }
            // hot
            if(item.item_isnew==0 && item.move_vip==0 && item.item_isrecommend==1){
                trs=trs+"<strong class='colorful' hot>HOT</strong>";
            }
            // 定向标
            if(item.item_isnew==0 && item.move_vip==0 && item.item_isrecommend==0 && !(isEmpty(item.password))){
                trs=trs+"<strong class='colorful' psw>LOCK</strong>";
            }
            trs=trs+"</td>";

            // 信用等级
            trs=trs+"<td class='trust_range'>";
            if(item.total>90){
                trs=trs+"A+";
            }
            if(item.total>85 && item.total<=90){
                trs=trs+"A";
            }
            if(item.total>75 && item.total <=85){
                trs=trs+"A-";
            }
            if(item.total>65 && item.total <=75){
                trs=trs+"B";
            }
            trs=trs+"</td>";

            // 担保机构
            trs=trs+"<td class='company'><image  src='/img/logo.png'/></td>";

            // 投资进度
            if(item.item_status==1){
                trs=trs+"<td class='syTime' data-item='"+item.id+"'>" +
                    "<strong class='countdown time' data-time="+item.syTime+">" +
                    " <time class='hour'></time>&nbsp;:" +
                    " <time class='min'></time>&nbsp;:" +
                    "<time class='sec'></time> " +
                    "</td>";
            }else{
                trs=trs+"<td class='ic' data-val='"+item.item_scale+"'>";
                trs=trs+"</td>";
            }


            // 操作
            trs=trs+"<td>";
            var href=ctx+"/item/details/"+item.id;
              if(item.item_status==1){
                  trs=trs+"<p><a href='"+href+"' ><input class='countdownButton' valid type='button' value='即将开标'></a></p>";
              }
              if(item.item_status ==10){
                  trs=trs+"<p class='left_money'>可投金额"+item.syAmount+"元</p>";
                  trs=trs+"<p><a  href='"+href+"'><input valid type='button' value='立即投资'></a></p>";
              }

            if(item.item_status ==20){
                trs=trs+"<p><a  href='"+href+"'><input not_valid type='button' value='已抢完'></a></p>";
            }
            if(item.item_status == 30 ||item.item_status==31){
                trs=trs+"<p><a  href='"+href+"'><input not_valid type='button' value='还款中'></a></p>";
            }
            if(item.item_status ==32){
                trs=trs+"<p><a  href='"+href+"'><input not_valid type='button' value='已还款'></a></p>";
            }
            if(item.item_status ==23){
                trs=trs+"<p><a  href='"+href+"' ><input not_valid type='button' value='已满标'></a></p>";
            }


            trs=trs+"</td>";





            trs=trs+"</tr>";

        }
        $("#pcItemList").html(trs);
    }
}


function initPageHtml(page) {
    if(page.total>0){
        /**
         *   <li class="active"><a title="第一页" >1</a></li>
         <li><a title="第二页">2</a></li>
         <li><a title="第三页">3</a></li>
         */
        var pageArray=page.navigatepageNums;
        var lis="";
        for(var i=0;i<pageArray.length;i++){
            var p=pageArray[i];
            var href="javascript:toCurrentPageInfo("+p+")";
            if(p==page.pageNum){
                lis=lis+"<li class='active'><a href="+href+" title='第"+p+"页'>"+p+"</a></li>";
            }else{
                lis=lis+"<li ><a href="+href+" title='第"+p+"页'>"+p+"</a></li>";
            }

        }
        $("#pages").html(lis);
    }
}


/**
 * 下拉框改变事件
 */
function initItemData(itemType) {
    var _itemType=itemType;
    var itemCycle=0;
    var isHistory=0;
    $(".tab").each(function () {
        if($(this).hasClass("list_active")){
           itemCycle=$(this).index();
        }
    });

    if(itemCycle==4){
        isHistory=1;
    }
    loadItemListDataInfo(itemCycle,itemType,isHistory)

}


/**
 * 指定页数据刷新
 */
function toCurrentPageInfo(pageNum) {
    var itemType=$("#itemType").val();
    var itemCycle=0;
    var isHistory=0;
    $(".tab").each(function () {
        if($(this).hasClass("list_active")){
            itemCycle=$(this).index();
        }
    });

    if(itemCycle==4){
        isHistory=1;
    }
    loadItemListDataInfo(itemCycle,itemType,isHistory,pageNum);
}


/**
 * 渲染原型进度框
 */
function initItemScale() {
    $(".ic").each(function(){
        $(this).radialIndicator({
            barColor: 'orange',
            barWidth: 5,
            roundCorner: true,
            radius:40,
            format: '####%'
        });
        var radialObj = $(this).data('radialIndicator');
        var val=$(this).attr("data-val");
       // console.log(val);
        radialObj.animate(val);
    })
}

function initSyTime() {
    $(".countdown").each(function () {
        var syTime= $(this).attr("data-time");
        var itemId=$(this).attr("data-item");
        timer(syTime,$(this),itemId);
    })
}



function timer(intDiff,obj,itemId){
    if( obj.timers){
        clearInterval( obj.timers);
    }

    obj.timers=setInterval(function(){
        var day=0,
            hour=0,
            minute=0,
            second=0;//时间默认值
        if(intDiff > 0){
            day = Math.floor(intDiff / (60 * 60 * 24));
            hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
            minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }

        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        obj.find('.hour').html(hour);
        obj.find('.min').html(minute);
        obj.find('.sec').html(second);
        intDiff--;
        if(intDiff==-1){
            $.ajax({
                url : ctx+'/item/updateItemToOpen',
                dataType : 'json',
                type : 'post',
                data:{
                    itemId:itemId
                },
                success : function(data) {
                    if(data.code==200){
                        window.location.reload()
                    }
                },
                error : function(textStatus, errorThrown) {

                }
            });
        }
    }, 1000);
}
