<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 辣鸡电脑
  Date: 2020/10/26
  Time: 11:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="/pages/common/header.jsp"%>
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js">
    </script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
        <ul class="nav nav-tabs">

            <c:if test="${empty sessionScope.user}"><!--判断是否登陆登陆成功的话显示功能按钮-->
            <li role="presentation"><h4>Welocome To Home </h4></li>
            <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-home">首页</span></a></li>
            <li role="presentation"><a href="pages/user/login.jsp"><span class="glyphicon glyphicon-user">登陆</span></a></li>
            <li role="presentation"><a href="pages/user/regist.jsp"><span class="glyphicon glyphicon-plus">注册</span></a></li>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <li role="presentation"><h4> Welcome ${user.username} to use </h4></li>
                <li role="presentation" class="active"><a href="#"><span class="glyphicon glyphicon-home">首页</span></a></li>
                <li role="presentation"><a href="userservlet?action=logout"><span class="glyphicon glyphicon-off">安全退出</span></a></li>
                <li role="presentation" class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    用户操作 <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li role="presentation"><a href="down?action=showpage"><span class="glyphicon glyphicon-download-alt">下载图片</span></a></li>
                    <li role="presentation"><a href="pages/user/personal.jsp"><span class="glyphicon glyphicon-user">个人中心</span></a></li>
                </ul>
                </li>
                <li role="presentation" class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                        管理员操作<span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu">
                        <li role="presentation"><a href="pages/manager?action=showdownpage"><span class="glyphicon glyphicon-hdd">资源管理</span></a></li>
                        <li role="presentation"><a href="pages/manager/user_manager.jsp"><span class="glyphicon glyphicon-info-sign">用户管理</span></a></li>
                    </ul>
                </li
            </c:if>

        </ul>
        </div>
    </div>
    <div class="row">

            <img src="static/img/44.jpg" alt="" class="img-responsive">


    </div>
</div>
</body>
</html>
