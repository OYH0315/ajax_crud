<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>李智恩小屋注册</title>
    <%@include file="/pages/common/header.jsp" %>
    <script type="text/javascript">
        $(function () {
            //页面加载事件加载中完成省份加载
            var usernameflag;
            var passwordflag;
            var repwordflag;
            var emailflag;
            var pid;
            var xmlhttprequest2 = new XMLHttpRequest();
            xmlhttprequest2.open("post", "https://api.xiaohuwei.cn/test.php?type=province", true);//调用免费api获得中国城市得json数据
            xmlhttprequest2.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xmlhttprequest2.send();
            xmlhttprequest2.onreadystatechange = function () {
                if (xmlhttprequest2.readyState == 4 && xmlhttprequest2.status == 200) {
                    var responseText2 = xmlhttprequest2.responseText;
                    var json2 = JSON.parse(responseText2);
                    $("#province").empty();
                    $("#province").append($("<option>").val("111").text("请选择省份"));

                    for (var i = 0; i < json2.provincelist.length; i++) {
                        $("#province").append($("<option>").val(json2.provincelist[i].province + "/" + json2.provincelist[i].pid).text(json2.provincelist[i].province));
                        console.log(json2.provincelist[i].province);//测试使用
                    }
                }
            }

            $("#province").change(function () {
                pid = $(this).val().lastIndexOf("/");
                pid = $(this).val().substring(pid + 1);//获取当前选择省份的编号；
                var xmlhttprequest3 = new XMLHttpRequest();
                xmlhttprequest3.open("post", "https://api.xiaohuwei.cn/test.php?type=city&pid=" + pid, true);//调用免费api获得当前省份城市的json数据
                xmlhttprequest3.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                xmlhttprequest3.send();
                xmlhttprequest3.onreadystatechange = function () {
                    if (xmlhttprequest3.readyState == 4 && xmlhttprequest3.status == 200) {
                        var responseText3 = xmlhttprequest3.responseText;
                        var json3 = JSON.parse(responseText3);
                        $("#city").empty();
                        $("#city").append($("<option>").val("111").text("请选择城市"));
                        for (var i = 0; i < json3.citylist.length; i++) {
                            //val值加“/”方便后台解析分割字符串解析出城市名称
                            $("#city").append($("<option>").val(json3.citylist[i].city + "/" + json3.citylist[i].pid).text(json3.citylist[i].city));

                        }
                    }
                }

            })
			//判断是否选择了省份，否则提示请选择省份
            $("#city").click(function () {
                if (pid == null) {
                    $("#errormsg").text("请先选择城市");
                    return;
                }
				$("#errormsg").text("");
            })
            //切换验证码
            $("#codeimag").click(function () {
                this.src = "${basepath}userservlet?action=createcode&d=" + new Date();
            })
            //单击注册事件发起ajax请求；
            $("#sub_btn").click(function () {
                if (usernameflag && passwordflag && repwordflag && emailflag) {
                    $.ajax({
                        type: "post",
                        url: "userservlet?action=regist",
                        data: $("#registform").serialize(),
                        dataType: "json",
                        success: function (response) {
                            if (response.flag == 2) {

                                window.location.href = "${basepath}pages/client/main2.jsp";
                            } else {
                                $("#errormsg").text(response.msg);
                            }

                        }

                    });
                } else {
                    $("#errormsg").text("注册失败请检查格式");
                }

            })

            //判断用户名是否合法
            $("#username").blur(function () {

                if ($(this).val() == "") {
                    $("#errormsg").text("用户名不能为空");
                    usernameflag = false;
                    return;

                }

                var usernametext = /^[a-zA-Z][a-zA-Z\d]{3,14}$/;//用户名正则表达式；
                if (usernametext.test(this.value) == false) {
                    $("#errormsg").text("用户名只能使用英文字母和数字，以字母开头，长度为4到15个字符");
                    usernameflag = false;
                    return;
                }
                $("#errormsg").text("");//如果都合法则不显示错误信息，下面的验证类似；
                usernameflag = true;
            })
            //判断密码是否合法
            $("#password").blur(function () {
                if ($(this).val() == "") {
                    $("#errormsg").text("密码不能为空");
                    passwordflag = false;
                    return;
                }
                if ($(this).val().length < 3) {
                    $("#errormsg").text("密码长度至少为4");
                    passwordflag = false;
                    return;
                }
                passwordflag = true;
                $("#errormsg").text("");
            })
            //判断两次密码是否一致
            $("#repwd").blur(function () {
                if ($(this).val() == "") {
                    $("#errormsg").text("确认密码不能为空");
                    repwordflag = false;
                    return;
                }
                if ($(this).val() != $("#password").val()) {
                    $("#errormsg").text("两次密码不一致");
                    repwordflag = false;
                    return;
                }
                repwordflag = true;
                $("#errormsg").text("");
            })
            //判断邮箱是否合法
            $("#email").blur(function () {
                if ($(this).val() == "") {
                    emailflag = false;
                    $("#errormsg").text("邮箱不能为空");
                    return;
                }
                var emailtext = /^[a-zA-Z0-9]+([._\\]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
                if (emailtext.test(this.value) == false) {
                    emailflag = false;
                    $("#errormsg").text("邮箱格式不合法");
                    return;
                }
                emailflag = true;
                $("#errormsg").text("");
            })
        })

    </script>
    <style type="text/css">
        .login_form {
            height: 420px;
            margin-top: 25px;
        }

    </style>
</head>
<body>
<div id="login_header">
</div>

<div class="login_banner">

    <div id="l_content">
        <span class="login_word">欢迎注册</span>
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>注册李智恩小屋会员</h1>
                    <span class="errorMsg" id="errormsg"></span>
                </div>
                <div class="form">
                    <form id="registform">
                        <input type="hidden" name="action" value="regist"/>
                        <label>用户名称：</label>
                        <input autocomplete="off" class="itxt" id="username" name="username" placeholder="请输入用户名"
                               tabindex="1" type="text"/>
                        <br/>
                        <br/>
                        <label>用户密码：</label>
                        <input autocomplete="off" class="itxt" id="password" name="password" placeholder="请输入密码"
                               tabindex="1" type="password"/>
                        <br/>
                        <br/>
                        <label>确认密码：</label>
                        <input autocomplete="off" class="itxt" id="repwd" name="repwd" placeholder="确认密码" tabindex="1"
                               type="password"/>
                        <br/>
                        <br/>
                        <label>电子邮件：</label>
                        <input autocomplete="off" class="itxt" id="email" name="email" placeholder="请输入邮箱地址"
                               tabindex="1" type="text"/>
                        <br/>
                        <br/>
                        <label>省份：</label>
                        <select id="province" name="province">
                            <option id="begin">请选择省份</option>
                            <option>湖北</option>
                        </select>
                        <label>城市：</label>
                        <select id="city" name="city">
                            <option id="begin2">请选择城市</option>
                        </select>
                        <br/>
                        <br/>
                        <label>验证码：</label>
                        <input class="itxt" id="code" name="code" style="width: 80px;" type="text"/>
                        <img alt="" id="codeimag" src="userservlet?action=createcode"
                             style="float: right; margin-right: 40px" width="120px" height="28px">
                        <br/>
                        <input type="button" value="注册" id="sub_btn">
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
<div id="bottom">
			<span>
				李智恩下载器.Copyright &copy;2020
			</span>
</div>
</body>
</html>