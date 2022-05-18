<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-17
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" media="all">
</head>
<style>
    .login-top {
        font-size: 30px;
        font-weight: 400;
        font-stretch: normal;
        letter-spacing: 0;
        line-height: 80px;
        margin-left: 180px;
        overflow: hidden;
    }
</style>

<body>
<div class="login-top">
    <span>重置密码</span>
</div>
<div class="layui-row">
    <div class="layui-col-md6">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form" action="" onsubmit="return false">
                    <div class="layui-form-item">
                        <span style="margin-left: 20px;">请输入邮箱或用户名，点击确定。找回密码链接将发送至您的邮箱</span>
                        <div class="layui-input-block" style="margin-top: 20px;">
                            <input type="text" name="uemail" required lay-verify="required" placeholder="请输入邮箱"
                                   autocomplete="off" class="layui-input" style="margin-left: -50px">
                        </div>
                    </div>

            <div class="layui-form-item" style="margin-left: 70px;">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="formDemo">确定</button>
                </div>
            </div>
            </form>
            </div>
            <a href="#" id="back" style="margin-top: 10px;margin-left: 155px;color:cornflowerblue">无需重置密码，现在登录？</a>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script>
    layui.use(['form', 'layer'], function () {   //导入模块
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;
        $('#back').on('click',function () {
            window.parent.location.reload();
        })
        //监听提交
        form.on('submit(formDemo)', function (data) {
            layer.msg("正在发送邮件请稍等" ,{time: 1000},function () {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/user/forgetpwd",
                    data: {
                        uemail: data.field.uemail,
                    },
                    success: function (result) {
                        var msg = result['msg'];
                        layer.msg(msg ,function () {
                            window.parent.location.reload();
                        })
                    }
                })
            })

        });
    });

</script>
</body>

</html>