$(document).ready(function () {
   /* 
	
	 * 注册用户统计
	 
	var monthStr = $("#month").val();
    var monthList = getList(monthStr);
    var enrollment =$("#enrollment").val()
    //获取年份
    var date=new Date;
    var year=date.getFullYear(); 
    $("#title1").html(year+'年注册人数统计');
    $("#title2").html(year+'年总金额统计(目标1000万)');
    var userChart = echarts.init(document.getElementById("echarts-bar-chart-user"));
    var useroption = {
        title : {
            text: '当前总注册人数：'+enrollment
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['注册人数']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        grid:{
            x:30,
            x2:40,
            y2:24
        },
        xAxis : [
            {
                type : 'category',
                data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'注册人数',
                type:'bar',
                data: monthList,
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            }
        ]
    };
    userChart.setOption(useroption);
    window.onresize = userChart.resize;*/

	var userRedAmountList = getList(userRedAmountStr);
	var userCountList = getList(userCountStr);
    
    //获取近30天的日期
    var dayList = new Array();
    for (var i = 30; i >= 0; i--) {
    	dayList[ 30 - i] = getDay(i);
	}
    $("#title3").html('红包领取统计');
    var amountChart = echarts.init(document.getElementById("echarts-bar-chart-amount"));
    var amountoption = {
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['领取金额','用户数']
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        dataZoom : {
            show : true,
            realtime : true,
            y: 400,
            height: 20,
            dataBackgroundColor: '#eee',
            start : 90,
            end : 100
        },
        grid:{
            x:70,
            x2:70,
            y2:40
        },
        xAxis : [
            {
                type : 'category',
                data : dayList
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
			{
			    name:'领取金额',
			    type:'bar',
			    data: userRedAmountList,
			    /*itemStyle: {
		        	normal: {
		        		label : {
		        			show: true, 
		        			textStyle: {  
                                fontWeight:'bolder',  
                                fontSize : '12',  
                                fontFamily : '微软雅黑',  
                            } 
		        		}
		        	}
		        },*/
			    markPoint : {
			        data : [
			            {type : 'max', name: '最大值'},
			            {type : 'min', name: '最小值'}
			        ]
			    },
			    markLine : {
			        data : [
			            {type : 'average', name: '平均值'}
			        ]
			    }
			},
			{
			    name:'用户数',
			    type:'bar',
			    data: userCountList,
			    /*itemStyle: {
		        	normal: {
		        		label : {
		        			show: true, 
		        			textStyle: {  
                                fontWeight:'bolder',  
                                fontSize : '12',  
                                fontFamily : '微软雅黑',  
                            } 
		        		}
		        	}
		        },*/
			    markPoint : {
			        data : [
			            {type : 'max', name: '最大值'},
			            {type : 'min', name: '最小值'}
			        ]
			    },
			    markLine : {
			        data : [
			            {type : 'average', name: '平均值'}
			        ]
			    }
			}
        ]
        
    };
    amountChart.setOption(amountoption);
    window.onresize = amountChart.resize;  
});


//将字符串转换成数组，按","分割
function getList(data){
	 var monthList =data.split(",");
	    for ( var i in monthList) {
			var a = Number(monthList[i])
			monthList[i] = a;
		}
	    return monthList;
};

//获取前几天的日期
function getDay(n){
    var now = new Date();
    now.setDate(now.getDate() - n);
	var month = now.getMonth() + 1;
	var date = now.getDate();
    return fixZero(month, 2) + "-" + fixZero(date, 2);
}
