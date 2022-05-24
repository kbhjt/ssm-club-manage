<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-24
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>社团活动信息统计</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    </head>
    <body style="margin-top: 40px">
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main" style="width: 500px;height:400px;float: left"></div>
        <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
        <div id="main1" style="width: 600px;height:400px;float: right"></div>
        <script>
            $.ajax({
                url: "${pageContext.request.contextPath}/activity/getActivityData",
                data: {
                    cid: '${club.cid}'
                },
                success: function (res) {
                    console.log(res)
                    loadData(res.activityPeopleNumVoList)
                    loadSex(res.activityList,res.manList,res.womanList)
                }
            })
            //加载人数分布
            function loadData(data) {
                // 基于准备好的dom 初始化实例
                var mychart = echarts.init(document.getElementById('main'));
                var option = {
                    title: {
                        text: '${club.cname}'+'社团活动',
                        subtext: '人数分布',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    color: ['#fc8251', '#5470c6', '#91cd77', '#ef6567', '#f9c956', '#75bedc'],
                    series: [
                        {
                            name: '${club.cname}',
                            type: 'pie',
                            radius: '50%',
                            data: data,
                            label: {
                                normal: {
                                    show: true,
                                    formatter: '{b}: {c}({d}%)', // 格式化数值百分比输出
                                },
                            },
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)',
                                }
                            }
                        }
                    ]
                };
                // 将参数设置进去
                mychart.setOption(option);
            }
            //加载性别分布
            function loadSex(activity,man,woman) {
                // 基于准备好的dom 初始化实例
                var mychart = echarts.init(document.getElementById('main1'));
                var option = {
                    title: {
                        text: '${club.cname}' + '社团活动',
                        subtext: '性别分布'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    legend: {
                        data: ['男生', '女生']
                    },
                    color: ['#5470c6', '#ef6567'],
                    xAxis: {
                        type: 'category',
                        data: activity
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '男生',
                            data: man,
                            type: 'bar'
                        },
                        {
                            name: '女生',
                            data: woman,
                            type: 'bar'
                        }
                    ]
                };
                // 将参数设置进去
                mychart.setOption(option);
            }
        </script>
    </body>
</html>
