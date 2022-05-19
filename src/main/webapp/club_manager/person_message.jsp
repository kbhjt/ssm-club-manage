<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-19
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>基本资料</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" media="all">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/public.css" media="all">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">
        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">学号</label>
                <div class="layui-input-block">
                    <input type="text" name="uno" lay-verify="required" lay-reqtext="学号不能为空" placeholder="请输入管理账号"  value="${user.uno}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">班级</label>
                <div class="layui-input-block">
                    <input type="text" name="uclass"  placeholder="请输入班级"  value="${user.uclass}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">昵称</label>
                <div class="layui-input-block">
                    <input type="text" name="unick" lay-verify="required" lay-reqtext="昵称不能为空" placeholder="请输入昵称"  value="${user.unick}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">邮箱</label>
                <div class="layui-input-block">
                    <input type="email" name="uemail"  placeholder="请输入邮箱"  value="${user.uemail}" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label">头像上传</label>
                <div class="layui-input-block">
                    <button type="button" class="layui-btn" id="test1">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">个性签名</label>
                <div class="layui-input-block">
                    <textarea name="usign" class="layui-textarea" placeholder="请输入个性签名">${user.usign}</textarea>
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
    layui.use(['form','layer'], function () {
        var form = layui.form;
        var layer = layui.layer;
        var $ = layui.jquery;
        var upload = layui.upload;
        var uimage = '';
        //执行实例
        var uploadInst = upload.render({
            elem: '#test1' //绑定元素
            ,url: '${pageContext.request.contextPath}/file/upload' //上传接口
            ,done: function(res){
                //上传成功
                layer.msg("上传成功");
                uimage = res.msg;
            }
            ,error: function(){
                //请求异常回调
                layer.msg("上传失败");
            }
        });
        //监听提交
        form.on('submit(saveBtn)', function (data) {
            data = data.field;
            $.ajax({
                type: 'post',
                url: '${pageContext.request.contextPath}/user/update_user',
                data: {
                    uclass: data.uclass,
                    unick: data.unick,
                    uemail: data.uemail,
                    uimage: uimage,
                    usign: data.usign,
                    uno: data.uno
                },
                success: function(res) {
                    if(res == '保存成功') {
                        layer.msg('保存成功',{time:2000},function () {
                            window.location.reload();
                        })
                    }
                }
            })
            return false;
        });

    });
</script>
</body>
</html>
