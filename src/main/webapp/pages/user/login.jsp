<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 辣鸡电脑
  Date: 2020/9/15
  Time: 8:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>李智恩小屋登陆注册</title>
    <%@include file="/pages/common/header.jsp"%>
    <script type="text/javascript">
        $(function () {

$("#codeimag").click(function () {
    this.src="${basepath}userservlet?action=createcode&d="+new Date();
})

$("#sub_btn").click(function () {
   var out=$("#login").prop("checked");
    var xmlhttprequest=new XMLHttpRequest();
xmlhttprequest.open("post","userservlet?action=login",true);
xmlhttprequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
    xmlhttprequest.send("username="+$("#username").val()+"&password="+$("#password").val()+"&code="+$("#code").val()+"&login="+out);
xmlhttprequest.onreadystatechange=function () {
if(xmlhttprequest.readyState==4&&xmlhttprequest.status==200)
{

var responseText=xmlhttprequest.responseText;
var json=JSON.parse(responseText);
console.log(json.flag);
if(json.flag==1)
{

    window.location.href="${basepath}pages/client/main2.jsp";
}
if(json.flag==2)
{

    $("#errorMsg").text(json.msg);
}
}
}

})
        })

    </script>
</head>
<body>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎登录</span>
        <input type="button" id="get" value="获取">
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>李智恩下载器</h1>
                    <a href="pages/user/regist.jsp">立即注册</a>
                </div>
                <div class="msg_cont">
                    <b></b>
                    <span class="errorMsg" id="errorMsg"><%=request.getAttribute("msg")==null?"请输入用户名和密码":request.getAttribute("msg")%></span>

                </div>
                <div class="form">
                    <form>
                        <input type="hidden" name="action" value="login"/>
                        <label>用户名称：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username"
                               value="<%=request.getAttribute("username")==null?"":request.getAttribute("username")%>"/>
                        <br />
                        <br />
                        <label>用户密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password"  id="password"/>
                        <br />
                        <br />
                        <label>验证码：</label>
                        <input class="itxt" id="code"  name="code" style="width: 80px;" type="text"/>
                        <img  alt="" id="codeimag" src="userservlet?action=createcode" style="float: right; margin-right: 40px;cursor:hand" width="120px" height="28px" title="看不清，换一个">
                        <br />
                        <br/>
                        <label><input type="checkbox"  name="login" id="login"><span>一周内是否免登陆</span></label>
                        <input type="button" value="登录" id="sub_btn" />
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
