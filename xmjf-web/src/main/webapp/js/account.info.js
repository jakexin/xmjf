$(function () {
   loadAccountInfoData(); 
   
   loadFiveMonthInvestInfo();
});


function loadFiveMonthInvestInfo() {

    $.ajax({
        type:"post",
        url:ctx+"/invest/queryFiveMonthInvestInfo",
        dataType:"json",
        success:function (data) {
            var data1=data.data1;
            var data2=data.data2;
            Highcharts.chart('line_chart', {
                chart: {
                    type: 'spline'
                },
                title: {
                    text: '用户最近5个月投资趋势图'
                },
                subtitle: {
                    text: '数据来源: 小码金服'
                },
                xAxis: {
                    categories: data1
                },
                yAxis: {
                    title: {
                        text: '金额'
                    },
                    labels: {
                        formatter: function () {
                            return this.value +"￥";
                        }
                    }
                },
                tooltip: {
                    crosshairs: true,
                    shared: true
                },
                plotOptions: {
                    spline: {
                        marker: {
                            radius: 4,
                            lineColor: '#666666',
                            lineWidth: 1
                        }
                    }
                },
                series: [
                    {
                        name: '投资',
                        marker: {
                            symbol: 'square'
                        },
                        data: data2
                    }
                ]
            });
        }
    })



}

function loadAccountInfoData() {
    $.ajax({
        type:"post",
        url:ctx+"/account/queryBusAccountInfoByUserId",
        dataType:"json",
        success:function (data) {
            var data1=data.data1;
            var data2=data.data2;

            Highcharts.chart('pie_chart', {
                chart: {
                    spacing : [40, 0 , 40, 0]
                },
                title: {
                    floating:true,
                    text: '总资产:'+data2+"￥"
                },
                tooltip: {
                    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
                },
                plotOptions: {
                    pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                            enabled: true,
                            format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                            style: {
                                color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                            }
                        },
                        point: {
                            events: {
                            }
                        },
                    }
                },
                series: [{
                    type: 'pie',
                    innerSize: '80%',
                    name: '资产比例',
                    data:data1
                }]
            }, function(c) { // 图表初始化完毕后的会掉函数
                // 环形图圆心
                var centerY = c.series[0].center[1],
                    titleHeight = parseInt(c.title.styles.fontSize);
                // 动态设置标题位置
                c.setTitle({
                    y:centerY + titleHeight/2
                });
            });



        }
    })
}


