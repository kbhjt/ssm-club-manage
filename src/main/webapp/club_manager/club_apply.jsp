<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-27
  Time: 23:02
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

        <script type="text/html" id="toolbarDemo">
            <div class="layui-btn-container">
                <button class="layui-btn layui-btn-normal layui-btn-sm data-add-btn" lay-event="add"> 添加 </button>
                <button class="layui-btn layui-btn-sm layui-btn-danger data-delete-btn" lay-event="delete"> 删除 </button>
            </div>
        </script>

        <table class="layui-hide" id="currentTableId" lay-filter="currentTableFilter"></table>

        <script type="text/html" id="currentTableBar">
            <a id="agree" class="layui-btn layui-btn-normal layui-btn-xs data-count-edit" lay-event="agree">同意</a>
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
            url: '${pageContext.request.contextPath}/apply/getAllApplyClub?cid='+${club.cid},
            toolbar: '#toolbarDemo',
            defaultToolbar: ['filter', 'exports', 'print', {
                title: '提示',
                layEvent: 'LAYTABLE_TIPS',
                icon: 'layui-icon-tips'
            }],
            cols: [[
                {type: "checkbox", width: 50},
                {field: 'id', width: 200, title: 'ID', sort: true},
                // {field: 'uno', width: 200, title: '学号'},
                {field: 'uname', width: 200, title: '姓名', sort: true},
                {field: 'uemail', width: 200, title: '邮箱'},
                {field: 'isOut', width: 200, title: '申请内容'},
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
                    title: '添加活动',
                    type: 2,
                    shade: 0.2,
                    maxmin:true,
                    shadeClose: true,
                    area: ['100%', '100%'],
                    content: '../page/table/add.html',
                });
                $(window).on("resize", function () {
                    layer.full(index);
                });
            } else if (obj.event === 'delete') {  // 监听删除操作
                var checkStatus = table.checkStatus('currentTableId')
                    , data = checkStatus.data;
                layer.alert(JSON.stringify(data));
            }
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
                    url: '${pageContext.request.contextPath}/apply/agreeClubApply',
                    type: 'post',
                    data: {
                        id: data.id,
                        uid: data.uid,
                        cid: '${club.cid}',
                        isOut: data.isOut
                    },
                    success : function (res) {
                        if(res == '操作成功') {
                            layer.msg("操作成功",{time:2000},function (){
                                window.location.reload();
                            });

                        }else {
                            layer.msg("服务器异常，操作失败",function (){});
                        }
                    }
                })

            } else if (obj.event === 'delete') {
                layer.confirm('真的删除行么', function (index) {
                    obj.del();
                    layer.close(index);
                });
            }
        });

    });
</script>

</body>
</html>
