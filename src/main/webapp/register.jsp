<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-16
  Time: 20:51
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
        line-height: 90px;
        text-align: center;
        overflow: hidden;
    }
</style>
<body>
<div class="login-top">
    <span>注册</span>
</div>
<div class="layui-row">
    <div class="layui-col-md6">
        <div class="layui-card">
            <div class="layui-card-body">
                <form class="layui-form" action="" onsubmit="return false">
                    <div class="layui-form-item">
                        <label class="layui-form-label">姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="uname" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">学号</label>
                        <div class="layui-input-block">
                            <input type="text" name="uno" required  lay-verify="required" placeholder="请输入学号" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-block">
                            <input type="text" name="uemail" required  lay-verify="required" placeholder="请输入邮箱" autocomplete="off" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="usex" value="男" title="男" checked>
                            <input type="radio" name="usex" value="女" title="女">
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">密码</label>
                        <div class="layui-input-inline">
                            <input type="password" name="upassword" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script>
    layui.use(['form','layer'], function(){   //导入模块
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        //监听提交
        form.on('submit(formDemo)', function(data){
            data = data.field;
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/register",
                data: {
                    uname: data.uname,
                    uno: data.uno,
                    uemail: data.uemail,
                    usex: data.usex,
                    upassword: data.upassword
                },
                success: function (result) {
                    console.log(result)
                    if (result == "注册成功") {
                        layer.msg(result,function (index) {
                            // layer.close(index)
                            <%--window.location = '${pageContext.request.contextPath}/index.jsp'--%>
                            location.replace('${pageContext.request.contextPath}/index.jsp');
                        })

                    } else {
                        layer.msg(result, {icon: 5})
                    }
                }
            })
            return false;
        });
    });

</script>
</body>
</html>
