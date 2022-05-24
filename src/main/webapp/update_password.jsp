<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-23
  Time: 20:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {
            width: auto;
            padding-right: 10px;
            line-height: 38px;
        }
    </style>
</head>

<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">旧的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="old_password" lay-verify="required" lay-reqtext="新的密码不能为空"
                           placeholder="请输入旧的密码" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="new_password" lay-verify="required" lay-reqtext="新的密码不能为空"
                           placeholder="请输入新的密码" value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" name="again_password" lay-verify="required" lay-reqtext="新的密码不能为空"
                           placeholder="请输入新的密码" value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;

        //监听提交
        form.on('submit(saveBtn)', function (data) {
            let new_password = data.field.new_password;
            let again_password = data.field.again_password;
            let old_password = data.field.old_password;
            if(old_password != '${user.upassword}') {
                layer.msg("原密码错误，请重新输入",function() {

                })
            }else if(new_password != again_password) {
                layer.msg("两次密码不一致",function() {

                })
            }else {
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/user/updatepwd",
                    data: {
                        uemail: '${user.uemail}',
                        upassword: new_password
                    },
                    success: function (result) {
                        if (result == "密码修改成功，3秒后返回登录页面") {
                            layer.msg("密码修改成功，3秒后返回登录页面",function() {
                                parent.location.href = '${pageContext.request.contextPath}/login.jsp'
                            })
                        }else {
                            layer.msg("密码修改失败，服务器异常",function() {})
                        }
                    }
                })
            }
            return false;
        });

    });
</script>
</body>

</html>

