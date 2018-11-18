/**
 * Created by ROGSTAR on 2016/10/10.
 */
(function() {
    window.jshead = {
        baseUrl: '/js/',
        paths: {
            jquery: 'assets/jquery-3.1.0.min',
            paginator:'assets/bootstrap-paginator',
            handlebars:"assets/handlebars-v4.0.5",
            pages:'assets/pages',
            navigator:'include/navigator',
            show:"assets/show",
            layer: 'layer/layer',
            common: "assets/common",
            zclip:'assets/jquery-zclip/jquery.zclip',
            highChart:'assets/highchart',
            radialIndicator:'assets/radialIndicator',
            //datePiker:'assets/daterangepicker',
            //bootstrap:'assets/bootstrap.min',
            //moment:'assets/moment',
            laydate: 'laydate/laydate',
            birthday : 'tool/birthday',
        },
        shim: {
            'layer': {
                deps: ['jquery'],
                exports: 'layer'
            },
            'laydate': {
                deps: ['jquery'],
                exports: 'laydate',
            },
            //'moment': {
            //    exports: 'moment',
            //},
            //'datePiker': {
            //    deps: ['jquery','moment'],
            //},
            //'bootstrap': {
            //    deps: ['jquery'],
            //},
            'birthday': {
                deps: ['jquery'],
                exports: 'birthday',
            },
            'handlebars': {
                exports: 'Handlebars'
            },
            'paginator':{
                deps: ['jquery'],
                exports: 'paginator'
            },
            'navigator':{
                deps: ['jquery'],
            },
            'common':{
                exports: 'Common'
            },
            'zclip':{
                deps: ['jquery']
            },
            'highChart':{
                deps: ['jquery'],
                exports: 'highcharts'
            },
            'radialIndicator':{
                deps: ['jquery']
            }
        }
    }
})();