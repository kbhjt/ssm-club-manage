<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-09
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>layui</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" media="all">
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a id="agree" class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="agree">同意</a>
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">不同意</a>
        </script>

    </div>
</div>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form', 'table'], function () {
        var $ = layui.jquery,
            form = layui.form,
            table = layui.table;

        table.render({
            elem: '#currentTableId',
            url: '${pageContext.request.contextPath}/applyactivity/findapplyactivity?cid='+${club.cid},
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'applyid', width: 200, title: 'ID', sort: true},
                {field: 'uid', width: 200, title: '学号'},
                {field: 'uname', width: 200, title: '姓名', sort: true},
                {field: 'aname', width: 200, title: '活动名称'},
                {field: 'data', width: 200, title: '时间'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 3,
            page: true,
            skin: 'line'
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });
        var i = 1;
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            console.log(data)
            if (obj.event === 'agree') {
                $.ajax({
                    url: '${pageContext.request.contextPath}/applyactivity/agreeapplyactivity',
                    type: 'post',
                    data: {
                        applyid: data.applyid,
                    },
                    success : function (res) {
                        if(res == '操作成功') {
                            layer.msg("操作成功",{time:2000},function (){window.location.reload();});

                        }else {
                            layer.msg("服务器异常，操作失败",function (){});
                        }
                    }
                })

            } else if (obj.event === 'delete') {
                layer.confirm('不同意吗？', function (index) {
                    $.ajax({
                        url: '${pageContext.request.contextPath}/applyactivity/disagreeapplyactivity',
                        type: 'post',
                        data: {
                            applyid: data.applyid,
                        },
                        success : function (res) {
                            if(res == '操作成功') {
                                layer.msg("操作成功",{time:2000},function (){window.location.reload();});

                            }else {
                                layer.msg("服务器异常，操作失败",function (){});
                            }
                        }
                    })
                });
            }
        });

    });
</script>

</body>
</html>

