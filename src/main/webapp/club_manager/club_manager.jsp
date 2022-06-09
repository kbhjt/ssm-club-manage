<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2022-05-19
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>${club.cname}社团管理系统</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/layui-v2.6.3/css/layui.css">
</head>
<body class="layui-layout-body">

<!-- 顶部区域 -->
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">${club.cname}</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${user.uimage}" class="layui-nav-img">
                    ${user.uname}
                </a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="#" data-url="person_message" data-title="个人资料" data-id="88" class="site-demo-active"
                           data-type="tabAdd">
                            个人资料
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="update_password" data-title="修改密码" data-id="99" class="site-demo-active" data-type="tabAdd">
                            修改密码
                        </a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item">
                <a href="${pageContext.request.contextPath}/user/logout?utype=2">退出</a>
            </li>
        </ul>
    </div>


    <!-- 左侧导航栏 -->
    <div class="layui-tab  layui-side layui-tab-card site-demo-button">
        <ul class="layui-nav layui-side layui-nav-tree layui-nav-side">
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">社团管理</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="#" data-url="activity_message" data-title="活动信息" data-id="11" id="default-tab-activity" class="site-demo-active"
                           data-type="tabAdd">
                            活动信息
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="auditing_enlist" data-title="报名审核" data-id="22" class="site-demo-active"
                           data-type="tabAdd">
                            报名审核
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="activity_evaluate" data-title="活动评论" data-id="33" class="site-demo-active"
                           data-type="tabAdd">
                            活动评论
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="activity_data" data-title="活动统计" data-id="44" id="default-tab"  class="site-demo-active"
                           data-type="tabAdd">
                            活动统计
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="delete_message" data-title="删除留言" data-id="200" id="default-tab-message"  class="site-demo-active"
                           data-type="tabAdd">
                            社团留言
                        </a>
                    </dd>
                </dl>
            </li>

            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">用户管理</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="#" data-url="user_message" data-title="用户信息" data-id="55" class="site-demo-active"
                           data-type="tabAdd">
                            用户信息
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="club_apply" data-title="成员审核" data-id="77" class="site-demo-active"
                           data-type="tabAdd">
                            成员审核
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="apply_record" data-title="审核记录" data-id="88" class="site-demo-active"
                           data-type="tabAdd">
                            审核记录
                        </a>
                    </dd>
                </dl>
            </li>
            <li class="layui-nav-item layui-nav-itemed">
                <a href="javascript:;">个人管理</a>
                <dl class="layui-nav-child">
                    <dd>
                        <a href="#" data-url="person_message" data-title="个人资料" data-id="99" class="site-demo-active"
                           data-type="tabAdd">
                            个人资料
                        </a>
                    </dd>
                    <dd>
                        <a href="#" data-url="update_password" data-title="修改密码" data-id="100" class="site-demo-active"
                           data-type="tabAdd">
                            修改密码
                        </a>
                    </dd>

                </dl>
            </li>
        </ul>


    </div>


    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div class="layui-tab" lay-filter="demo" lay-allowClose="true">
            <!--tab页标题-->
            <ul class="layui-tab-title">
            </ul>
            <!--tab页标题上右键，出现下面的 关闭当前和关闭所有的选项-->
            <ul class="rightMenu" style="display: none;position: absolute;">
                <li data-type="closeThis" class="layui-bg-cyan">关闭当前</li>
                <li data-type="closeAll" class="layui-bg-cyan">关闭所有</li>
            </ul>
            <!--tab页主体内容区域-->
            <div class="layui-tab-content">
            </div>
        </div>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © nchu.com - 社团后台管理系统
    </div>

</div>

<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/css/layui-v2.6.3/layui.js"></script>

<script>

    layui.use(['element','layer'], function () {
        var $ = layui.jquery;
        var element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        //触发事件
        var active = {
            // 在这里给active绑定几项事件，后面可通过active调用这些事件
            tabAdd: function (url, id, name) {
                if (url == 'update_password') {
                    url = '${pageContext.request.contextPath}/update_password'
                }
                if(url == 'person_message') {
                    url = '${pageContext.request.contextPath}/person_message'
                }
                element.tabAdd('demo', {
                    title: name,
                    content: '<iframe data-frameid="' + id + '" scrolling="auto" frameborder="0" src="' + url + '.jsp" style="width:100%;height:99%;"></iframe>',
                    id: id // 规定好的id
                })
                CustomRightClick(id); // 自定义函数，给tab绑定右击事件
                FrameWH();  // 自定义函数，计算iframe层的大小
            },
            tabChange: function (id) {
                // 切换到指定Tab项
                element.tabChange('demo', id); // 根据传入的id传入到指定的tab项
            },
            tabDelete: function (id) {
                element.tabDelete("demo", id);// 删除
            }
            , tabDeleteAll: function (ids) {// 删除所有
                $.each(ids, function (i, item) {
                    element.tabDelete("demo", item); //ids是一个数组，里面存放了多个id，调用tabDelete方法分别删除
                })
            }

        };


        // 当点击有site-demo-active属性的标签时，即左侧菜单栏中内容 ，触发点击事件
        $('.site-demo-active').on('click', function () {
            var dataid = $(this);

            // 这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
            if ($(".layui-tab-title li[lay-id]").length <= 0) {
                // 如果比零小，则直接打开新的tab项
                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
            } else {
                // 否则判断该tab项是否已经存在

                var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                $.each($(".layui-tab-title li[lay-id]"), function () {
                    //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                    if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                        isData = true;
                    }
                })
                if (isData == false) {
                    //标志为false 新增一个tab项
                    active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                }
            }
            // 最后不管是否新增tab，最后都转到要打开的选项页面上
            active.tabChange(dataid.attr("data-id"));
        });


        function CustomRightClick(id) {

            //取消右键  rightMenu属性开始是隐藏的 ，当右击的时候显示，左击的时候隐藏
            $('.layui-tab-title li').on('contextmenu', function () {
                return false;
            })

            $('.layui-tab-title,.layui-tab-title li').click(function () {
                $('.rightMenu').hide();
            });

            //桌面点击右击
            $('.layui-tab-title li').on('contextmenu', function (e) {
                var popupmenu = $(".rightMenu");
                popupmenu.find("li").attr("data-id", id); //在右键菜单中的标签绑定id属性

                //判断右侧菜单的位置
                l = ($(document).width() - e.clientX) < popupmenu.width() ? (e.clientX - popupmenu.width()) : e.clientX;
                t = ($(document).height() - e.clientY) < popupmenu.height() ? (e.clientY - popupmenu.height()) : e.clientY;
                popupmenu.css({left: l, top: t}).show(); //进行绝对定位
                //alert("右键菜单")
                return false;
            });
        }

        $(".rightMenu li").click(function () {

            //右键菜单中的选项被点击之后，判断type的类型，决定关闭所有还是关闭当前。
            if ($(this).attr("data-type") == "closeThis") {
                //如果关闭当前，即根据显示右键菜单时所绑定的id，执行tabDelete
                active.tabDelete($(this).attr("data-id"))
            } else if ($(this).attr("data-type") == "closeAll") {
                var tabtitle = $(".layui-tab-title li");
                var ids = new Array();
                $.each(tabtitle, function (i) {
                    ids[i] = $(this).attr("lay-id");
                })
                //如果关闭所有 ，即将所有的lay-id放进数组，执行tabDeleteAll
                active.tabDeleteAll(ids);
            }

            $('.rightMenu').hide(); //最后再隐藏右键菜单
        })

        // 计算frame高度
        function FrameWH() {
            var h = $(window).height() - 41 - 10 - 60 - 10 - 44 - 10;
            $("iframe").css("height", h + "px");
        }


        $(window).resize(function () {
            FrameWH();
        })


        var layer = layui.layer;
        layer.ready(function(){
            // layer.msg('您已进入页面了！');
            // 触发点击事件，这样就可以设置默认显示的选项卡页面
            // TODO
            // 可能需要修改的地方，标签id值
            $("#default-tab").trigger( "click" );
        });

    });

</script>


</body>
</html>

