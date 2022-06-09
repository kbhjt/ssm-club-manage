<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-06-09
  Time: 21:38
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
                    <button class="layui-btn layui-btn-radius layui-btn-warm layui-icon layui-icon-add-circle"
                            style="width: 150px">加入活动
                    </button>
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
                <div class="talk-notice">参与留言</div>
                <textarea id="message" name="message" required lay-verify="required" placeholder="大家可以畅所欲言！"
                          class="layui-textarea"></textarea>
                <div class="btn">
                    <button id="submit" class="layui-btn layui-btn-normal btn-sub" lay-event="submit">提交</button>
                    <button id="cancel" class="layui-btn layui-btn-primary layui-border-blue btn-space">取消</button>
                </div>

            </div>

        </div>
    </div>
    <!-- 讨论列表 -->
    <div id="box" class="layui-panel card-de">
        <div class="layui-card-header talk-t">留言板
            <div style="display: inline-block;float: right;">
                <form class="layui-form" action="">
                    <input type="checkbox" name="my_checkbox" checked="false" id="my_checkbox" lay-skin="switch" lay-filter="switchStatus"
                           lay-text="开启|关闭">
                </form>
            </div>
        </div>
        <div id="talk-area" class="layui-card-body talk">
        </div>
    </div>
</div>
</body>
<script>

    layui.use(['layer', 'form'], function() {
        var form = layui.form;
        //监听事件
        form.on('switch', function(data){
            layer.alert(data.value+":"+data.elem.checked)
            var iscomment;
            if(data.elem.checked){
                iscomment = 1;
                layer.alert("打开评论:"+data.elem.checked)

            }else{
                iscomment = 0;
                layer.alert("关闭讨论:"+data.elem.checked)
            }
            $.ajax({
                url: "${pageContext.request.contextPath}/activity/isOpenComment",
                data: {
                    iscomment: iscomment,
                    aid: ${activity.activity.aid},
                },
                success: function (res) {
                    if(res.data == "操作成功"){
                        layer.msg("提交成功", function () {
                        });
                    }
                }
            })
            comment();
        });
        //当你需要使用ajax提交表单时,可监听表单提交,否则无需监听
        //立即提交按钮要设置lay-submit属性,才能被监听
        //submit(formDemo) 表示只监听 含有lay-filter="formDemo" 的按钮
        form.on('submit(formDemo)', function(data) {
            //data.field 是一个json对象,批量获取表单的值{'表单的name':'对应的值'}
            layer.msg(JSON.stringify(data.field));
            //return false 可以阻止 表单提交(刷新页面)
            return false;
        });
    })

    function getTalk() {
        $.ajax({
            url: "${pageContext.request.contextPath}/activity/getCTalkByAid",
            data: {
                aid: ${activity.activity.aid},
            },
            success: function (res) {
                console.log(res.length);
                var htmlstr = '';
                for (let i = 0; i < res.length; i++) {
                    var html = '<div class="talk-name">\n' +
                        '     <div class="talk-p">' + res[i].tid + "   " +  res[i].uid + '</div>\n' +
                        ' <div>' + res[i].mcreateTime + '</div>\n' +
                        '</div>\n' +
                        '<div ><div class="talk-info">\n' +
                        res[i].cmessage +'<div class="layui-icon layui-icon-delete"  style="float: right;font-size: 20px;" title="删除"></div>'+
                        '</div></div>'
                    htmlstr += html;
                }
                $("#talk-area").html(htmlstr)
            }

        })
    }

    function comment(){
        $.ajax({
            url: "${pageContext.request.contextPath}/activity/findisopen",
            data: {
                aid: ${activity.activity.aid},
            },
            success: function (res) {
                if(res == 1){
                    console.log(res + "ssss")
                    $("#my_checkbox").attr("checked")
                    form.render('checkbox')
                }else{
                    //关闭
                    console.log(res + "ssss")
                    $('#my_checkbox').removeAttr("checked");					//关闭
                    form.render('checkbox')
                }
            }
        })
    }

    $(document).ready(function () {
        getTalk()
        comment()
        $("#finish").click(function () {
            $(this).css("background-image", "linear-gradient(to top, #48c6ef 0%, #6f86d6 100%)");
            $(this).css("color", "white")
        });
        $("#cancel").click(function () {
            $("#message").val("");
        });
        $("#submit").click(function () {
            var content = $("#message").val();
            console.log(content)
            console.log(${activity.activity.aid}+" "+${user.uid})
            $.ajax({
                url: "${pageContext.request.contextPath}/activity/addCTalk",
                data: {
                    aid: ${activity.activity.aid},
                    uid: ${user.uid},
                    cmessage: content
                },
                success: function (res) {
                    console.log(res);
                    layer.msg("提交成功", function () {
                    });
                    getTalk();
                }
            })

        })
    })

</script>
</html>

