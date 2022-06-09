<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-09
  Time: 22:45
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
            <label class="layui-form-label">类型名称</label>
            <div class="layui-input-block">
                <input id="name" type="text" name="name" required lay-verify="required" placeholder="请输入活动类型名称"
                       autocomplete="off" class="layui-input input-width">
            </div>
        </div>
        <div class="layui-form-item input-width">
            <label class="layui-form-label">人数限制</label>
            <div class="layui-input-block">
                <input id="number" type="text" name="number" required lay-verify="required" placeholder="请输入人数限制"
                       autocomplete="off" class="layui-input input-width">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">时间限制</label>
            <div class="layui-input-block">
                <input id="time" type="text" name="time" required lay-verify="required" placeholder="请输入时间限制"
                       autocomplete="off" class="layui-input input-width">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即创建</button>
                <button type="reset" class="layui-btn layui-btn-primary">取消</button>
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
        form.on('submit(formDemo)', function (data) {
            if (!data.field.name){
                layer.alert('请填写活动类型');
            }
            if (!data.field.time){
                layer.alert('请填写人数限制');
            }
            $.ajax({
                url:'${pageContext.request.contextPath}/type/addType',
                type:'get',
                data:{
                    type: data.field.name,
                    number: data.field.number,
                    time: data.field.time
                },
                success:function(data){
                    if(data){
                        layer.alert("提交成功！")
                        layer.msg("操作成功",{time:2000},function (){window.location.reload();});
                    }
                    else{
                        layer.alert("提交失败！")
                    }
                },
                error:function(e){
                    console.log(data.field.name)
                    layer.alert("提交失败！")
                },

            });
            return false;
        });
    });
</script>
</body>
</html>


