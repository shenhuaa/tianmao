<!DOCTYPE html>
<html>
<head>
    <title>首页</title>
    <#include 'common/header.ftl'>
</head>
<script src="/static/js/echarts/echarts.min.js"></script>
<script type="text/javascript">
    window.onload=function() {
        //放echarts图表的DIV
        ChartsData = echarts.init(document.getElementById('serviceRunChart'), 'vintage');
        option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    crossStyle: {
                        color: '#999'
                    }
                }
            },
            toolbox: {
                feature: {
                    dataView: {show: true, readOnly: false},
                    magicType: {show: true, type: ['line', 'bar']},
                    restore: {show: true},
                    saveAsImage: {show: true}
                }
            },
            legend: {
                data: ['营销额', '总收入', '进店总人数']
            },
            xAxis: [
                {
                    type: 'category',
                    data: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                    axisPointer: {
                        type: 'shadow'
                    }
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '营销额',
                    min: 0,
                    max: 250000,
                    interval: 50000,
                    axisLabel: {
                        formatter: '{value}'
                    }
                },
                {
                    type: 'value',
                    name: '总收入',
                    min: 0,
                    max: 250000,
                    interval: 50000,
                    axisLabel: {
                        formatter: '{value}'
                    }
                }
            ],
            series: [
                {
                    name: '营销额',
                    type: 'bar',
                    data: [2000.0, 4000.9, 7000.0, 23000.2, 25000.6, 76000.7, 135000.6, 162000.2, 32000.6, 20000.0, 6000.4, 3000.3]
                },
                {
                    name: '总收入',
                    type: 'bar',
                    data: [2000.6, 5000.9, 9000.0, 26000.4, 28000.7, 70000.7, 175000.6, 182000.2, 48000.7, 18000.8, 6000.0, 2000.3]
                },
                {
                    name: '进店总人数',
                    type: 'line',
                    yAxisIndex: 1,
                    data: [2000, 2000, 3000, 4000, 6000, 10000, 20000, 23000, 23000, 16000, 12000, 6000]
                }
            ]
        };
        ChartsData.setOption(option);
    }
</script>

<style>
    .echars{
        width: 100%;
        height: 800px;
        margin: 10px;
        clear: both;
    }

</style>
<body class="">

<div class="echars" id="serviceRunChart"></div>

<#--
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-success pull-right">月</span>
                    <h5>销售额</h5>

                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">40 886,200</h1>
                    <div class="stat-percent font-bold text-success">98% <i class="fa fa-bolt"></i>
                    </div>
                    <small>总收入</small>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-info pull-right">全年</span>
                    <h5>订单</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">275,800</h1>
                    <div class="stat-percent font-bold text-info">20% <i class="fa fa-level-up"></i>
                    </div>
                    <small>新订单</small>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-primary pull-right">今天</span>
                    <h5>进店人数</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">106,120</h1>
                    <div class="stat-percent font-bold text-navy">44% <i class="fa fa-level-up"></i>
                    </div>
                    <small>本月</small>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="ibox float-e-margins">
                <div class="ibox-title">
                    <span class="label label-danger pull-right">最近一个月</span>
                    <h5>入库数量</h5>
                </div>
                <div class="ibox-content">
                    <h1 class="no-margins">80,600</h1>
                    <div class="stat-percent font-bold text-danger">38% <i class="fa fa-level-down"></i>
                    </div>
                    <small>12月</small>
                </div>
            </div>
        </div>
    </div>
</div>

    <#include "com.tianmao.common/body.ftl" />
    <#include "com.tianmao.common/footer.ftl" />-->
</body>
</html>