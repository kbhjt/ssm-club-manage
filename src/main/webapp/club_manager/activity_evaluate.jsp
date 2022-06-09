<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-09
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="referrer" content="never">
    <title>首页</title>
    <link href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" rel="stylesheet">
</head>
<style>
    .card {
        margin: 20px 20px 20px 90px;
        box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
        transition: 0.3s;
        width: 250px;
        height: 100px;
        border-radius: 5px;
        display: flex;
        flex-direction: row;
        float: left;
    }

    .card:hover {
        box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
    }

    .poto{
        margin-top: 10px;
        width: 100px;
        height: 80px;
        border-radius: 10%;
        margin-left: 10px;
    }
    .message{
        color: black;
        font-size: smaller;
        width:100px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        /*2行*/
        -webkit-line-clamp: 2;
        overflow: hidden;
    }
    .container {
        margin-top: -5px;
        margin-left: 10px;
        padding-top: 7%;
        width: 120px;
        height: 150px;

    }
    .btn {
        margin-top: 5px;
    }
    #content {
        height: 300px;
    }
</style>
<body>
<!-- 活动列表 -->
<div id="content"></div>
<div id="page"></div>
</body>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    $(function() {
        layui.use('carousel', function() {
            var carousel = layui.carousel;
            //建造实例
            carousel.render({
                elem: '#test1',
                width: '100%', //设置容器宽度
                height:'36%',
                autoplay: 'true',
                interval:3000,
                arrow: 'always' //始终显示箭头
                //,anim: 'updown' //切换动画方式
            });
        });
        showData();     //请求数据
        showPage();
    })
    let pageNum = 1; //设置首页页码
    let pageSize = 6;  //设置一页显示的条数
    let total; //总共的记录数
    let pages; //总共的页数
    function showData() {
        $.ajax({
            url: '${pageContext.request.contextPath}/activity/getAllClubActivity',
            async: false,
            data: {
                page: pageNum,
                limit: pageSize
            },
            success: function (res) {
                console.log(res)
                var data = res;
                total = '${total}';
                pages = total / data.length
                var strs = '';
                for (let i = 0; i < data.length; i++) {
                    var str = '<div class="card">\n' +
                        '        <div>\n' +
                        '            <img src="'+data[i].aimage+'" alt="Avatar" class="poto">\n' +
                        '        </div>\n' +
                        '        <div class="container">\n' +
                        '            <h4><b>'+data[i].aname+'</b></h4>\n' +
                        '            <p class="message">'+data[i].aintroduce+'<i class="layui-icon layui-icon-next"\n' +
                        '                                                        style="color: rgb(14, 166, 248); font-size: smaller;" title="详情"></i></p>\n' +
                        '            <a class="layui-btn layui-btn-sm layui-btn-warm layui-btn-xs btn" href="${pageContext.request.contextPath}/activity/delete?aid='+data[i].aid+'">查看</a>\n' +
                        '        </div>\n' +
                        '    </div>'
                    strs += str
                }
                $('#content').html(strs);
            }
        })
    }
    function showPage() {
        layui.use('laypage', function(){
            var laypage = layui.laypage;
            //执行一个laypage实例
            laypage.render({
                elem: 'page' //注意，这里的 test1 是 ID，不用加 # 号
                ,count: total //数据总数，从服务端得到
                ,limit: pageSize,   //每页条数设置
                limits: [3, 6, 9],			//可选每页显示条数
                curr: 1,                      //起始页
                groups: 3,                     //连续页码个数
                prev: '上一页',               //上一页文本
                next: '下一页',                //下一页文本
                first: 1,                    //首页文本
                last: pages,                   //尾页文本
                layout: ['prev', 'page', 'next', 'limit', 'refresh', 'skip'],
                jump: function (obj, first) {  //触发分页后的回调
                    //obj包含了当前分页的所有参数，第一次加载first为true
                    console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                    console.log(obj.limit); //得到每页显示的条数
                    pageNum = obj.curr;  //改变当前页码
                    pageSize = obj.limit;
                    //首次不执行，一定要加此判断，否则初始时会无限刷新
                    if (!first) {
                        showData()  //加载数据
                    }
                    // $('#page').css({"text-align":"center"})
                }
            });
        });
    }
</script>
</html>
