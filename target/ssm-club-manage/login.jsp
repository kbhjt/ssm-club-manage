<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-10
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>后台管理-登陆</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" media="all">
    <style>
        .main-body {
            top: 50%;
            left: 50%;
            position: absolute;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
            overflow: hidden;
        }

        .login-main .login-bottom .center .item input {
            display: inline-block;
            width: 227px;
            height: 22px;
            padding: 0;
            position: absolute;
            border: 0;
            outline: 0;
            font-size: 14px;
            letter-spacing: 0;
        }

        .login-main .login-bottom .center .item .icon-1 {
            background: url(images/icon-login.png) no-repeat 1px 0;
        }

        .login-main .login-bottom .center .item .icon-2 {
            background: url(images/icon-login.png) no-repeat -54px 0;
        }

        .login-main .login-bottom .center .item .icon-3 {
            background: url(images/icon-login.png) no-repeat -106px 0;
        }

        .login-main .login-bottom .center .item .icon-4 {
            background: url(images/icon-login.png) no-repeat 0 -43px;
            position: absolute;
            right: -10px;
            cursor: pointer;
        }

        .login-main .login-bottom .center .item .icon-5 {
            background: url(images/icon-login.png) no-repeat -55px -43px;
        }

        .login-main .login-bottom .center .item .icon-6 {
            background: url(images/icon-login.png) no-repeat 0 -93px;
            position: absolute;
            right: -10px;
            margin-top: 8px;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .icon-nocheck {
            display: inline-block;
            width: 10px;
            height: 10px;
            border-radius: 2px;
            border: solid 1px #9abcda;
            position: relative;
            top: 2px;
            margin: 1px 8px 1px 1px;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .icon-check {
            margin: 0 7px 0 0;
            width: 14px;
            height: 14px;
            border: none;
            background: url(images/icon-login.png) no-repeat -111px -48px;
        }

        .login-main .login-bottom .center .item .icon {
            display: inline-block;
            width: 33px;
            height: 22px;
        }

        .login-main .login-bottom .center .item {
            width: 288px;
            height: 35px;
            border-bottom: 1px solid #dae1e6;
            margin-bottom: 35px;
        }

        .login-main {
            width: 428px;
            position: relative;
            float: left;
        }

        .login-main .login-top {
            height: 117px;
            background-color: #148be4;
            border-radius: 12px 12px 0 0;
            font-family: SourceHanSansCN-Regular;
            font-size: 30px;
            font-weight: 400;
            font-stretch: normal;
            letter-spacing: 0;
            color: #fff;
            line-height: 117px;
            text-align: center;
            overflow: hidden;
            -webkit-transform: rotate(0);
            -moz-transform: rotate(0);
            -ms-transform: rotate(0);
            -o-transform: rotate(0);
            transform: rotate(0);
        }

        .login-main .login-top .bg1 {
            display: inline-block;
            width: 74px;
            height: 74px;
            background: #fff;
            opacity: .1;
            border-radius: 0 74px 0 0;
            position: absolute;
            left: 0;
            top: 43px;
        }

        .login-main .login-top .bg2 {
            display: inline-block;
            width: 94px;
            height: 94px;
            background: #fff;
            opacity: .1;
            border-radius: 50%;
            position: absolute;
            right: -16px;
            top: -16px;
        }

        .login-main .login-bottom {
            width: 428px;
            background: #fff;
            border-radius: 0 0 12px 12px;
            padding-bottom: 53px;
        }

        .login-main .login-bottom .center {
            width: 288px;
            margin: 0 auto;
            padding-top: 40px;
            padding-bottom: 15px;
            position: relative;
        }

        .login-main .login-bottom .tip {
            clear: both;
            height: 16px;
            line-height: 16px;
            width: 288px;
            margin: 0 auto;
        }

        body {
            background: url(images/loginbg.png) 0% 0% / cover no-repeat;
            position: static;
            font-size: 12px;
        }

        input::-webkit-input-placeholder {
            color: #a6aebf;
        }

        .login-main .login-bottom .tip {
            clear: both;
            height: 16px;
            line-height: 16px;
            width: 288px;
            margin: 0 auto;
        }

        .login-main .login-bottom .tip .login-tip {
            font-family: MicrosoftYaHei;
            font-size: 12px;
            font-weight: 400;
            font-stretch: normal;
            letter-spacing: 0;
            color: #9abcda;
            cursor: pointer;
        }

        .login-main .login-bottom .tip .forget-password {
            font-stretch: normal;
            letter-spacing: 0;
            color: #1391ff;
            text-decoration: none;
            position: absolute;
            right: 62px;
        }

        .login-main .login-bottom .login-btn {
            width: 288px;
            height: 40px;
            background-color: #1E9FFF;
            border-radius: 16px;
            margin: 24px auto 0;
            text-align: center;
            line-height: 40px;
            color: #fff;
            font-size: 14px;
            letter-spacing: 0;
            cursor: pointer;
            border: none;
        }

        .login-main .login-bottom .center .item .validateImg {
            position: absolute;
            right: 1px;
            cursor: pointer;
            height: 36px;
            border: 1px solid #e6e6e6;
        }
    </style>
</head>

<body>
<div class="main-body">
    <div class="login-main">
        <div class="login-top">
            <span>社团管理后台登录</span>
            <span class="bg1"></span>
            <span class="bg2"></span>
        </div>
        <form class="layui-form login-bottom">
            <div class="center">
                <div class="item">
                    <span class="icon icon-2"></span>
                    <input type="text" name="username" lay-verify="required" placeholder="请输入登录账号" maxlength="24" />
                </div>

                <div class="item">
                    <span class="icon icon-3"></span>
                    <input type="password" name="password" lay-verify="required" placeholder="请输入密码" maxlength="20">
                    <span class="bind-password icon icon-4"></span>
                </div>
                <div style="margin-top: -20px;">
                    <input type="radio" name="utype" value="1" title="超级管理员" checked>
                    <input type="radio" name="utype" value="2" title="社团管理员" >
                </div>

            </div>
            <div class="tip">
                <span class="icon-nocheck"></span>
                <span class="login-tip">保持登录</span>
                <a href="javascript:" class="forget-password">忘记密码？</a>
            </div>
            <div class="layui-form-item" style="text-align:center; width:100%;height:100%;margin:0px;">
                <button class="login-btn" lay-submit lay-filter="login">立即登录</button>
            </div>
            <div class="tip">
                <a href="#" id="register" class="forget-password" style="margin-top: 10px;margin-right: 17px">立即注册</a>
            </div>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script>
    layui.use(['form', 'jquery'], function () {
        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        $('.bind-password').on('click', function () {
            if ($(this).hasClass('icon-5')) {
                $(this).removeClass('icon-5');
                $("input[name='password']").attr('type', 'password');
            } else {
                $(this).addClass('icon-5');
                $("input[name='password']").attr('type', 'text');
            }
        });

        $('.icon-nocheck').on('click', function () {
            if ($(this).hasClass('icon-check')) {
                $(this).removeClass('icon-check');
            } else {
                $(this).addClass('icon-check');
            }
        });

        // 进行登录操作
        form.on('submit(login)', function (data) {
            data = data.field;
            console.log(data.username+' ' + data.password + ' ' + data.utype)
            if (data.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (data.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/login',
                data: {
                    username: data.username,
                    password: data.password,
                    utype: data.utype
                },
                success : function (data){
                    console.log(data);
                    if (data == '登录成功') {
                        layer.msg('登录成功', function () {
                            window.location = '${pageContext.request.contextPath}/role/findAll'
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
        })
    });
</script>
</body>

</html>
