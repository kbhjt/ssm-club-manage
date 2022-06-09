<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-09
  Time: 22:31
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

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="edit">编辑</a>
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
            url: '${pageContext.request.contextPath}/type/getTypeData',
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'aid', width: 200, title: 'ID', sort: true},
                {field: 'type', width: 200, title: '活动类型'},
                {field: 'number', width: 200, title: '人数上限', sort: true},
                {field: 'time', width: 200, title: '时间下限'},
                {title: '操作', minWidth: 150, toolbar: '#currentTableBar', align: "center"}
            ]],
            limits: [10, 15, 20, 25, 50, 100],
            limit: 3,
            page: true,
            skin: 'line'
        });

        /**
         * toolbar监听事件
         */
        table.on('toolbar(currentTableFilter)', function (obj) {
            if (obj.event === 'add') {  // 监听添加操作
                var index = layer.open({
                    title: '添加活动类型',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['50%', '85%'],
                    content: '../manager/format_add.jsp',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            }
        });

        //监听表格复选框选择
        table.on('checkbox(currentTableFilter)', function (obj) {
            console.log(obj)
        });
        var i = 1;
        table.on('tool(currentTableFilter)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                i = 1;
                var index = layer.open({
                    title: '编辑活动类型',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['40%', '85%'],
                    content: '${pageContext.request.contextPath}/type/updateType?tid='+data.tid,
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
                return false;
            } else if (obj.event === 'delete') {
                var data = obj.data;
                $.ajax({
                    url: '${pageContext.request.contextPath}/type/deleteType',
                    type: 'get',
                    data: {
                        atid: data.atid,
                    },
                    success : function (res) {
                        if(res == '操作成功') {
                            layer.msg("操作成功",{time:2000},function (){window.location.reload();});
                        }else {
                            layer.msg("服务器异常，操作失败",function (){});
                        }
                    }
                })
            }
        });

    });
</script>

</body>
</html>


