define(["jquery", "show", "layer", "paginator"], function($, sw, layer, paginator) {
	layer.config({
		path: '/js/layer/' //layer.js所在的目录，可以是绝对目录，也可以是相对目录
	});
	var pages = {
		//*页码，*每页显示条数，*接口地址，*模版接收id，*模版对应的show方法名，额外参数,分页器id,callback
		paging: function(page, pagesize, url, domid,template, paramdata,pagesid,_callback) {
			var param = {
				pageSize: pagesize,
				pageNum: page
			};
			if (paramdata != null || paramdata != undefined) {
				param = $.extend({}, param, paramdata);
			}
			if(!pagesid){
				pagesid = '#pages';
			}

			layer.load(2);
			$.ajax({
				type: "post",
				url: url,
				data: param,
				async: true,
				dataType: "json",
				success: function(data) {
					layer.closeAll('loading');
					if (data.list.length != 0) {
						var jsondata = JSON.stringify(data.list);
						eval('sw.' + template + '(' + jsondata + ')');
						var alldatas = data.paginator.total //这里data里面有数据总量  
						var element = $(pagesid); //对应下面ul的ID

						var options = {
							bootstrapMajorVersion: 3,
							currentPage: data.paginator.pageNum, //当前页面  
							numberOfPages: 4, //一页显示几个按钮（在ul里面生成5个li）
							totalPages: data.paginator.pages, //总页数 
							onPageClicked: function(event, originalEvent, type, tarpage) {

								//show the corresponding page and retrieve the newly built item related to the page clicked before for the event return
								var currentTarget = $(event.currentTarget);
								pages.paging(tarpage, pagesize, url, domid, template, paramdata,pagesid,_callback);
							},
						}
						element.bootstrapPaginator(options);
						if(_callback)
						{
							_callback(data);
						}
						
					}
					else{
						var li = "暂无数据";
						//$(pagesid).html('');
						$('#'+domid).html("");
						$(pagesid).html("<img style='margin-left: -70px;padding:40px;' src='/img/zanwushuju.png'>");
						if(_callback)
						{
							_callback(data);
						}
					}
				},
				error: function(e) {
					layer.msg("服务器通讯失败", {
						icon: 5
					});
					layer.closeAll('loading');
				}
			});
		},
	};
	return pages;
});