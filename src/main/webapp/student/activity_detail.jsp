<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-25
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <title>活动详情</title>
    <link href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/activity_detail.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<div class="layui-container">
    <!-- 社团活动主要信息 -->
    <div class="layui-panel card-de">
        <div class="layui-card-header theme-info">
            <!-- 图标 -->
            <div>
                <img style="width: 60px;height: 60px;"
                     src="https://images.pexels.com/photos/12060992/pexels-photo-12060992.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260">
            </div>
            <!-- 活动名称+社团名称 -->
            <div class="theme-name">
                <div class="ac-name">
                    ${activity.activity.aname}
                </div>
                <div class="cl-name">
                    ${activity.club.cname}
                </div>
                <div id="add" style="display: inline-block;padding-left: 600px">
                    <button id="applyClub" class="layui-btn layui-btn-radius layui-btn-warm layui-icon layui-icon-add-circle" style="width: 150px">加入活动</button>
                </div>
            </div>
        </div>
        <!-- 具体详细信息 -->
        <div class="layui-card-body">
            <div>
                活动时间：<span>${activity.activity.abegintime} - ${activity.activity.aendtime}</span>
            </div>
            <div>
                活动地点：<span>${activity.activity.activityPosition}</span>
            </div>
            <div>
                活动人数：<span>${activity.activity.peopleLimit}</span>
            </div>
        </div>
    </div>
    <!-- 活动功能信息（讨论） -->
    <div class="layui-tab layui-tab-card card-de">
        <ul class="layui-tab-title">
            <li class="layui-this">信息</li>
            <li>详情</li>
            <li>讨论</li>
        </ul>
        <div class="layui-tab-content" style="">
            <!-- 信息 -->
            <div class="layui-tab-item layui-show info aso">
                <div>
                    签到方式：<span>外勤签到</span>
                </div>
                <div>
                    活动分类：<span>${activity.activity.activityType}</span>
                </div>
                <div>
                    活动院系：<span>全部</span>
                </div>
                <div>
                    活动负责：<span>${uname}</span>
                </div>
            </div>
            <!-- 详情 -->
            <div class="layui-tab-item info">
                <div class="layui-card">
                    <div class="layui-card-header intro">简介</div>
                    <div class="layui-card-body">
                       ${activity.activity.aintroduce}
                    </div>
                </div>
            </div>
            <!-- 讨论 -->
            <div class="layui-tab-item info">
                <!-- 提交讨论 -->
                <div class="talk-notice">参与讨论</div>
                <textarea name="" required lay-verify="required" placeholder="参与讨论" class="layui-textarea"></textarea>
                <div class="btn">
                    <button class="layui-btn layui-btn-normal btn-sub">提交</button>
                    <button class="layui-btn layui-btn-primary layui-border-blue btn-space">取消</button>
                </div>
            </div>
        </div>
    </div>
    <!-- 讨论列表 -->
    <div class="layui-panel card-de">
        <div class="layui-card-header talk-t">讨论区</div>
        <div class="layui-card-body talk">
            <div class="talk-name">
                <div class="talk-p">高小昊</div>
                <div>05-20 16:41</div>
            </div>
            <div class="talk-info">
                本次活动非常圆满，大家收获多多！
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var content = $('#applyClub').text()
    $('#applyClub').click(function() {
        $.ajax({
            url: "${pageContext.request.contextPath}/apply_activity/applyActivity",
            data: {
                uid: ${student.uid},
                aid: ${activity.aid},
                content: content
            },
            success: function(res) {
                console.log(res);
                layer.msg("已申请，正在审核中",function (){
                    $('#applyClub').attr("class","layui-btn layui-btn-disabled");
                    $('#applyClub').text("审核中")
                });
            }
        })
    })
</script>
</html>

