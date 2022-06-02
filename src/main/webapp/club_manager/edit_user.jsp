<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-02
  Time: 10:59
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
    .input-width {
        width: 90%;
    }
</style>
<body>
<div style="margin-top: 20px;">
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input  id="uname" readonly type="text" name="title" required  lay-verify="required" autocomplete="off" class="layui-input input-width" value="${editUser.uname}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级</label>
            <div class="layui-input-block">
                <input type="text" readonly class="layui-input input-width" value="${editUser.uclass}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" readonly class="layui-input input-width" value="${editUser.uemail}">
            </div>
        </div>
        <div class="layui-form-item input-width">
            <label class="layui-form-label">设置角色</label>
            <div class="layui-input-block">
                <select name="role" lay-verify="required">
                    <option value=""></option>
                    <option value="3">社团助理</option>
                    <option value="4">一般学生</option>
                </select>
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
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js" charset="utf-8"></script>
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js" charset="utf-8"></script>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            data = data.field;
            console.log(data.role)
            $.ajax({
                url: '${pageContext.request.contextPath}/user/updateUserRole',
                type: 'post',
                data: {
                    uid: '${editUser.uid}',
                    cid: '${club.cid}',
                    rid: data.role
                },
                success: function(res) {
                    if (res > 0) {
                        layer.msg("设置成功",{time: 2000},function (){
                            window.parent.location.reload()
                        })
                    }else {
                        layer.msg("服务器异常，操作失败",{time: 2000},function (){})
                    }
                }
            })
            return false;
        });
    });
</script>
</body>
</html>

