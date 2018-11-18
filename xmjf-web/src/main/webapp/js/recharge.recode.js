$(function () {
});

function loadRechargesDataInfo(pageNum) {
    var params={};
    params.pageNum=1;
    if(!isEmpty(pageNum)){
        params.pageNum=pageNum;
    }
    $.ajax({
        type:"post",
        url:ctx+"/account/recharges",
        data:params,
        dataType:"json",
        success:function (data) {
            var list=data.list;
            var paginator=data.paginator;
            if(paginator.total>0){
                initDivsHtml(list);
                initPageHtml(paginator);
            }
        }
    })
}

function initDivsHtml(list) {
    /**
     *  <div class="table-content-first">
     2017-12-01
     </div>
     <div class="table-content-center">
     1元
     </div>
     <div class="table-content-first">
     成功
     </div>
     */
    if(list.length>0){
        var divs="";
        for(var i=0;i<list.length;i++){
            var temp=list[i];
            divs=divs+"<div class='table-content-first'> "+temp.time+"</div>";
            divs=divs+"<div class='table-content-center'>"+temp.amount+"元</div>";
            divs=divs+"<div class='table-content-first'>"+temp.status+"</div>";
        }
        $("#rechargeList").html(divs);
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
 * 指定页数据刷新
 */
function toCurrentPageInfo(pageNum) {
    loadRechargesDataInfo(pageNum);
}
