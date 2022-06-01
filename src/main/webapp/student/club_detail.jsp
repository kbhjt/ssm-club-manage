<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-26
  Time: 22:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="referrer" content="never">
    <link href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/activity_detail.css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
    <title>社团页</title>
</head>
<body>
<div class="layui-container">
    <!-- 社团活动主要信息 -->
    <div class="layui-panel card-de">
        <div class="layui-card-header theme-info">
            <!-- 图标 -->
            <div>
                <img style="width: 60px;height: 60px;"
                     src="${club.cimage}">
            </div>
            <!-- 社团名称 -->
            <div class="theme-name">
                <div class="club-name">
                    ${club.cname}
                </div>
                <div style="font-size: 16px;color: #7c7d81;">
                    社长 <span>${userName}</span>
                </div>
            </div>
            <div style="display: inline-block;margin-left: 300px">
                <a id="apply" class="layui-btn layui-btn-radius layui-btn-warm layui-icon layui-icon-add-circle" style="width: 125px" href="#">加入社团</a>
            </div>
            <div id="helper" style="display: none;margin-left: 50px">
                <a id="apply_helper" class="layui-btn layui-btn-radius layui-btn-warm layui-icon layui-icon-add-circle" style="width: 125px" href="#">申请助理</a>
            </div>
        </div>
        <!-- 具体详细信息 -->
        <div class="layui-card-body">
            <div>
                创立时间时间：<span>${club.createTime}</span>
            </div>
            <div>
                办公地点：<span>学生公寓23栋一楼</span>
            </div>
            <div>
                社团人数：<span>50人</span>
            </div>
        </div>
    </div>
    <!-- 活动功能信息（讨论） -->
    <div class="layui-tab layui-tab-card card-de">
        <ul class="layui-tab-title">
            <li class="layui-this">简介</li>
            <li>印象标签</li>
            <li>留言板</li>
        </ul>
        <div class="layui-tab-content" style="">
            <!-- 信息 -->
            <div class="layui-tab-item layui-show info aso">
                <div>
                    ${club.cintroduce}
                </div>
            </div>
            <!-- 添加标签 -->
            <div class="layui-tab-item info">
                <div class="layui-card">
                    <div class="layui-card-header intro tag"><span style="width: 100%;">添加印象标签</span>
                        <span style="margin-left: 40%;width: 20%;font-weight: 300;font-size: 16px;">
								<div class="layui-btn layui-btn-primary layui-btn-sm">
								    <i class="layui-icon">&#xe605;完成</i>
								  </div>
							</span>
                    </div>
                    <div class="layui-card-body">
                        <div class="layui-btn-container" style="margin-top: 20px;">
                            <button type="button" class="layui-btn btn-con btnw-color">仙气十足</button>
                            <button type="button" class="layui-btn btn-con btnw-color">手工</button>
                            <button type="button" class="layui-btn btn-con btnw-color">聚会</button>
                            <button type="button" class="layui-btn btn-con btnw-color">乐器达人</button>
                            <button type="button" class="layui-btn btn-con btnw-color">行走的CD</button>
                            <button type="button" class="layui-btn btn-con btnw-color">桌游</button>
                            <button type="button" class="layui-btn btn-con btnw-color">中二病晚期</button>
                            <button type="button" class="layui-btn btn-con btnw-color">背包客</button>
                            <button type="button" class="layui-btn btn-con btnw-color">民谣</button>
                            <button type="button" class="layui-btn btn-con btnw-color">二次元</button>
                            <button type="button" class="layui-btn btn-con btnw-color">小动物</button>
                            <button type="button" class="layui-btn btn-con btnw-color">男神</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="layui-tab-item info">

                <!-- 提交留言 -->
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
    <div class="layui-panel card-de">
        <div class="layui-card-header talk-t">留言板</div>
        <div id="talk-area" class="layui-card-body talk">
        </div>
    </div>
</div>
<script>
    $(document).ready(function(){
        getTalk();
        var exist = '${exist}'
        if(exist == 1) { //该用户已经加入了该社团
            $('#apply').attr("class","layui-btn layui-btn-danger layui-btn-radius  layui-icon layui-icon-reduce-circle");
            $('#apply').text('申请退出')
            $('#helper').css('display','')
        }
        var examine = '${examine}'
        if(examine == 1) {
            $('#apply').attr("class","layui-btn layui-btn-disabled");
            $('#apply').text('审核中')
        }
        $("button").click(function(){
            $(this).css("background-image","linear-gradient(to top, #48c6ef 0%, #6f86d6 100%)");
            $(this).css("color","white")
        });
        $('#apply').click(function() {
            var content = $('#apply').text();
            $.ajax({
                url: "${pageContext.request.contextPath}/apply/applyClub",
                data: {
                    uid: ${student.uid},
                    cid: ${club.cid},
                    isOut: content
                },
                success: function(res) {
                    console.log(res);
                    $('#apply').attr("class","layui-btn layui-btn-disabled");
                    $('#apply').text("审核中")
                    layer.msg("已申请，正在审核中",function (){});
                }
            })
        });
        var applyExist = '${applyExist}'
        if (applyExist == 1) {
            $('#apply_helper').attr("class","layui-btn layui-btn-disabled");
            $('#apply_helper').text("审核中")
            $('#apply').attr("class","layui-btn layui-btn-danger layui-btn-radius  layui-icon layui-icon-reduce-circle");
            $('#apply').text('申请退出')
        }else if(applyExist == 2){
            $('#apply_helper').attr("class","layui-btn layui-btn-radius");
            $('#apply_helper').text("进入社团管理")
            $('#apply').attr("class","layui-btn layui-btn-danger layui-btn-radius  layui-icon layui-icon-reduce-circle");
            $('#apply').text('申请退出')
        }
        var content = $('#apply_helper').text();
        console.log(content);
        if (content != '进入社团管理') {
            $('#apply_helper').click(function() {
                $.ajax({
                    url: "${pageContext.request.contextPath}/apply/applyClub",
                    data: {
                        uid: ${student.uid},
                        cid: ${club.cid},
                        isOut: content
                    },
                    success: function(res) {
                        console.log(res);
                        $('#apply_helper').attr("class","layui-btn layui-btn-disabled");
                        $('#apply_helper').text("审核中")
                        layer.msg("已申请，正在审核中",function (){});
                    }
                })
            })
        }else {

        }

    });
    function getTalk(){
        $.ajax({
            url: "${pageContext.request.contextPath}/club/getCMessageByCid",
            data: {
                cid: ${club.cid},
            },
            success: function(res) {
                console.log(res.length);
                var htmlstr = '';
                for(let i = 0;i < res.length; i++){
                    var html = '<div class="talk-name">\n'+
                        '     <div class="talk-p">'+'${student.uname}'+'</div>\n'+
                        ' <div>'+res[i].mcreateTime+'</div>\n'+
                        '</div>\n'+
                        '<div class="talk-info">\n'+
                        res[i].cmessage+
                        '</div>\n'
                    htmlstr += html;
                }
                $("#talk-area").html(htmlstr)
            }
        })
    }
    $("#finish").click(function(){
        $(this).css("background-image","linear-gradient(to top, #48c6ef 0%, #6f86d6 100%)");
        $(this).css("color","white")
    });
    $("#cancel").click(function (){
        $("#message").val("");
    });
    $("#submit").click(function (){
        var content = $("#message").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/club/addCMessage",
            data: {
                cid: ${club.cid},
                uid: ${student.uid},
                cmessage: content
            },
            success: function(res) {
                console.log(res);

                layer.msg("提交成功",function (){});
                getTalk();
            }
        })

    })
</script>
</body>
</html>

