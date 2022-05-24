<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-23
  Time: 21:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生页面-登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" media="all">
    <style>
        body {background-image:url(../images/bg.jpg);height:100%;width:100%;}
        #container{height:100%;width:100%;}
        input:-webkit-autofill {-webkit-box-shadow:inset 0 0 0 1000px #fff;background-color:transparent;}
        .admin-login-background {width:300px;height:300px;position:absolute;left:50%;top:40%;margin-left:-150px;margin-top:-100px;}
        .admin-header {text-align:center;margin-bottom:20px;color:#ffffff;font-weight:bold;font-size:40px}
        .admin-input {border-top-style:none;border-right-style:solid;border-bottom-style:solid;border-left-style:solid;height:50px;width:300px;padding-bottom:0px;}
        .admin-input::-webkit-input-placeholder {color:#a78369}
        .layui-icon-username {color:#a78369 !important;}
        .layui-icon-username:hover {color:#9dadce !important;}
        .layui-icon-password {color:#a78369 !important;}
        .layui-icon-password:hover {color:#9dadce !important;}
        .admin-input-username {border-top-style:solid;border-radius:10px 10px 0 0;}
        .admin-input-verify {border-radius:0 0 10px 10px;}
        .admin-button {margin-top:20px;font-weight:bold;font-size:18px;width:300px;height:50px;border-radius:5px;background-color:#a78369;border:1px solid #d8b29f}
        .admin-icon {margin-left:260px;margin-top:10px;font-size:30px;}
        i {position:absolute;}
        .admin-captcha {position:absolute;margin-left:205px;margin-top:-40px;}
    </style>
</head>
<body>
<div id="container">
    <div></div>
    <div class="admin-login-background">
        <div class="admin-header">
            <span>昌航社团</span>
        </div>
        <form class="layui-form" action="">
            <div>
                <i class="layui-icon layui-icon-username admin-icon"></i>
                <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input admin-input admin-input-username" value="">
            </div>
            <div>
                <i class="layui-icon layui-icon-password admin-icon"></i>
                <input type="password" name="upassword" placeholder="请输入密码" autocomplete="off" class="layui-input admin-input" value="">
            </div>
            <div style="width: 300px;height:30px;background-color: #ffffff;">
                <div style="padding-top: 5px;">
                    <span class="login-tip" style="color: #1391ff;margin-left: 10px;" id="register">立即注册</span>
                    <a href="#" id="forget" class="forget-password" style="margin-left: 160px;">忘记密码？</a>
                </div>
            </div>
            <button class="layui-btn admin-button" lay-submit="" lay-filter="login">登 陆</button>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script>
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) top.location = self.location;

        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.upassword == '') {
                layer.msg('密码不能为空');
                return false;
            }
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/login',
                data: {
                    username: data.username,
                    password: data.upassword,
                    utype: '4'
                },
                success : function (data){
                    console.log(data);
                    if (data == '登录成功') {
                        layer.msg('登录成功', {time: 2000}, function () {
                            window.location = '${pageContext.request.contextPath}/student/student.jsp'
                        })
                    }else {
                        layer.msg(data);
                    }
                }
            })
            return false;
        });
    });
    $(document).ready(function(){
        $('#register').click(function() {
            var index = layer.open({
                title: '用户注册',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['40%', '85%'],
                content: '${pageContext.request.contextPath}/register.jsp'
            });
        });
        $('#forget').click(function() {
            var index = layer.open({
                title: '密码重置',
                type: 2,
                shade: 0.2,
                maxmin:true,
                shadeClose: true,
                area: ['35%', '60%'],
                content: '${pageContext.request.contextPath}/forget_password.jsp'
            });
        })
    });
</script>
</body>
</html>
