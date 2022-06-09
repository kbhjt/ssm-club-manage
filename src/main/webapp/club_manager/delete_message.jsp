<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-09
  Time: 9:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
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
            <a class="layui-btn layui-btn-xs layui-btn-danger data-count-delete" lay-event="delete">删除</a>
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
            url: '${pageContext.request.contextPath}/club/get_message?cid='+${club.cid},
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'mid', width: 200, title: 'ID', sort: true},
                {field: 'uname', width: 100, title: '留言人'},
                {field: 'cmessage',width: 300, title: '留言内容'},
                {field: 'mcreateTime', width: 300, title: '留言时间', sort: true},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 12,
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
            if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                    $.ajax({
                        type: 'post',
                        url: '${pageContext.request.contextPath}/club/delete_message',
                        data: {
                            mid: data.mid
                        },
                        success: function (res) {
                            if (res == 1) {
                                layer.msg("删除成功", {time: 1000}, function () {
                                    window.location.reload();
                                })
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

