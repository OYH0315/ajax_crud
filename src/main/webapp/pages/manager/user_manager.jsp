<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 辣鸡电脑
  Date: 2020/10/19
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>用户管理</title>
    <%@include file="/pages/common/header.jsp"%>
    <link href="static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
    <script src="static/bootstrap-3.3.7-dist/js/bootstrap.min.js">
    </script>
        </head>
<body>
<%--用户修改遮罩层--%>
<div class="modal fade" id="updateusermodel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改用户</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="updateusername" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="updateusername"></p>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="upemail" placeholder="请输入邮箱">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="selectprovince" class="col-sm-2 control-label">省份</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="upselectprovince">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="selectcity" class="col-sm-2 control-label">城市</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="upselectcity">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="updateuser">保存</button>
            </div>
        </div>
    </div>
</div>

<!-- 遮罩层 -->
<div class="modal fade" id="adduser" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">用户添加</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" placeholder="请输入用户名">
                            <span id="helpBlock2" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="email" placeholder="请输入邮箱">
                            <span id="helpBlock3" class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="selectprovince" class="col-sm-2 control-label">省份</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="selectprovince">

                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="selectcity" class="col-sm-2 control-label">城市</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="selectcity">
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Sign in</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="saveuser">保存</button>
            </div>
        </div>
    </div>
</div>

<div class="container">
<%--    标题--%>
    <div class="row">
        <div class="col-md-12">
            <ul class="nav nav-tabs">
                <li role="presentation" ><a href="#">首页</a></li>
                <li role="presentation" class="active"><a href="#">用户管理</a></li>
                <li role="presentation"><a href="#">返回</a></li>
            </ul>
        </div>
    </div>
<%--    按钮--%>
    <div class="row">
        <div class="col-md-12">
            <form class="form-inline" id="queryform">
                <div class="form-group">
                    <label for="id">ID</label>
                    <input type="text" class="form-control" id="id" placeholder="员工编号" name="id">
                </div>
                <div class="form-group">
                    <label for="usernames">姓名</label>
                    <input type="text" class="form-control" id="usernames" placeholder="请输入姓名" name="username">
                </div>
                <div class="form-group">
                    <label for="emails">邮箱</label>
                    <input type="text" class="form-control" id="emails" placeholder="请输入邮箱" name="email">
                </div>
                <div class="form-group">
                    <label for="provinces">省份</label>
                    <input type="text" class="form-control" id="provinces" placeholder="请输入省份" name="province">
                </div>
            </form>
        </div>

    </div>
    <div class="row">
        <div class="col-md-8 col-md-offset-8" >
            <button  class="btn btn-success"><span class="glyphicon glyphicon-search" id="querybt">查询</span></button>
            <button  class="btn btn-primary"><span class="glyphicon glyphicon-remove" id="clear">清空查询条件</span></button>
            <button  class="btn btn-warning" id="adduserbt"><span class="glyphicon glyphicon-plus ">新增</span></button>
            <button  class="btn btn-danger" id="deleteall"><span class="glyphicon glyphicon-trash">删除</span></button>
        </div>

    </div>
<%--    表格数据--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover table-striped table-bordered" id="usertable">
                <thead>
                <tr class="warning">
                    <th><input type="checkbox" id="checkall"/></th>
                    <th>ID<button class="btn btn-default btn-xs" id="orderby"><span class="glyphicon glyphicon-chevron-up"></span></button></th>
                    <th>姓名</th>
                    <th>邮箱</th>
                    <th>省份</th>
                    <th>城市</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6" id="pagedetail">

        </div>
        <div class="col-md-6 col-md-offset-8" id="navdiv">

        </div>
    </div>
</div>
<script src="static/js/usermanager.js">

</script>
</body>
</html>
