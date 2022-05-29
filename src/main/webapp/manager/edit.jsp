<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-15
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <label class="layui-form-label">社团名称</label>
            <div class="layui-input-block">
                <input  id="cname" type="text" name="title" required  lay-verify="required" placeholder="请输入社团名称" autocomplete="off" class="layui-input input-width">
            </div>
        </div>
        <div class="layui-form-item input-width">
            <label class="layui-form-label">社团负责人</label>
            <div class="layui-input-block">
                <select name="city" lay-verify="required">
                    <option value=""></option>
                    <c:forEach items="${clubUser}" var="cu">
                        <option value="${cu.uid}">${cu.uname}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">社团图片</label>
            <div class="layui-input-block">
                <input type="file">
            </div>
        </div>
        <div class="layui-form-item layui-form-text input-width">
            <label class="layui-form-label">社团介绍</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入社团介绍" class="layui-textarea"></textarea>
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
            console.log(data)
            // layer.msg(JSON.stringify(data.field));
            return false;
        });
    });
</script>
</body>
</html>
