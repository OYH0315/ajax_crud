var nowpage//当前页码
var id;
var username;
var email;
var province;
var orderflag=0;
$(function () {
    goto_page(1,id,username,email,province);//页面加载完成去首页
});


function goto_page(pn,id,username,email,province,flag){//查询ajax，利用模糊查询，包括首页数据显示以及按照条件查询显示

    $.ajax({
        url:"query",
        data:{pn:pn,id:id,username:username,email:email,province:province,flag:flag},
        type:"GET",
        success:function (result) {//请求成功回调函数
             if(result.code==100)
             {
                 build_user_table(result);//构建用户列表
                 pageinfomation(result);//构建页码数表
                 build_page_nav(result);//构建分页条信息表
             }
                else if(result.code==200)
             {
                 alert("不存在该条件用户")
             }

        }

    });
}
//ajax请求省份信息调用网络上的api
function showprovince(selecttype) {
    $.ajax({
        url:"https://api.xiaohuwei.cn/test.php",
        data:{type:"province"},
        type:"GET",
        success:function (json2) {
            addtooptions(json2,selecttype);
        }
    })

}
//ajax请求api获得城市的json数据
function showcity(pid,selecttype) {
    $.ajax({
        url:"https://api.xiaohuwei.cn/test.php",
        data:{type:"city",pid:pid},
        type:"GET",
        success:function (json3) {
            addtocity(json3,selecttype);
        }
    })
}
//ajax发送请求删除数据库数据
function deleteuser(id) {
    $.ajax({
        url:"delete",
        data:{id:id},
        type:"POST",
        success:function (result4) {

        }
    })

}
//ajax请求保存员工到数据库
function saveusertodatabase(username2,email2,province2,city2) {
    $.ajax({
        url:"insert",
        data:{username:username2,email:email2,province:province2,city:city2},
        type:"POST",
        success:function (json4) {
            if (json4.extend.flag == 0) {
                showmsg("#username", "error", "用户名已存在")
            } else if (json4.extend.flag== 1) {
                if (orderflag == 1) {
                    goto_page(1, null, null, null, null, 1);
                } else {
                    goto_page(99999);
                }
                $("#username").val("");
                $("#username").parent().removeClass("has-success");
                $("#email").val("");
                $("#email").parent().removeClass("has-success");
                alert("处理成功");
                //关闭遮罩层；;
                $("#adduser").modal("hide");

            }
        }
    })

}
//ajax 请求回显员工信息到修改遮罩层
function returnuser(id) {
    $.ajax({
        url:"returnuser",
        data:{id:id},
        type:"GET",
        success:function (userinfo) {
            showreturndata(userinfo);
        }

    })

}
//ajax请求后台保存更改后的信息
function updateuserinfo(id,email,province,city) {
    $.ajax({
        url:"update",
        data:{id:id,email:email,province:province,city:city},
        type:"POST",
        success:function (info) {
            if(info.code==100)
            {
                $("#updateusermodel").modal("hide");
                if(orderflag==1)
                {
                    goto_page(nowpage,null,null,null,null,1);

                }
                else
                    goto_page(nowpage);
            }
            else if(info.code==200)
            {
                alert("修改失败")
            }
        }
    })

}
//ajax请求批量删除员工
function deletelists(ids) {
    $.ajax({
        url:"deletelist",
        data:{ids:ids},
        type:"POST",
        success:function () {
        }
    })

}
//讲回显到的员工json数据回显到修改表单中
function showreturndata(userinfo) {
    $("#updateusername").text(userinfo.extend.userinfo.username);
    $("#upemail").val(userinfo.extend.userinfo.email);
    $("#upselectcity").empty();
    $("#upselectprovince").empty();
    var updaprovinceop=$("<option></option>").append(userinfo.extend.userinfo.province);
    updaprovinceop.appendTo("#upselectprovince");
    var upcityop=$("<option></option>").append(userinfo.extend.userinfo.city);
    upcityop.appendTo("#upselectcity");
}
//向省份 下拉框中添加从ajax请求获取到的json省份信息
function addtooptions(provinces,selecttype) {
    $("#selectprovince").empty();
    $.each(provinces.provincelist,function (index,item) {
        var poption=$("<option></option>").append(item.province).attr("value",item.pid);
        poption.appendTo(selecttype);
    })

}
//向城市下拉款中添加从api中获得的json数据中的城市信息
function addtocity(citys,selecttype) {
    selecttype.empty();
    $.each(citys.citylist,function (index,item2) {
        var ctoption=$("<option></option>").append(item2.city).attr("value",item2.id);
        ctoption.appendTo(selecttype);
    })

}
function build_user_table(aa) {//构建用户列表
    $("#usertable tbody").empty();
    var users=aa.extend.pageinfo.list;//获取json数据中用户信息集合；
    $.each(users,function (index,items) {
        var chexTD=$("<td><input type='checkbox' class='check_item'/></td>");
        var useridTD=$("<td></td>").append(items.id);
        var usernameTD=$("<td></td>").append(items.username);
        var emailTD=$("<td></td>").append(items.email);
        var provinceTD=$("<td></td>").append(items.province);
        var cityTD=$("<td></td>").append(items.city);
        // <button  class="btn btn-danger"><span class="glyphicon glyphicon-trash">删除</span></button>
        var debt=$("<button></button>").addClass("btn btn-danger delete_bt").append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除");
        var updabt=$("<button></button>").addClass("btn btn-warning edit_bt2").append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("修改");
        updabt.attr("userid",items.id);
        debt.attr("user_id",items.id);
        debt.attr("user_name",items.username);
        var optd=$("<td></td>").append(updabt).append(debt);
        $("<tr></tr>").append(chexTD).append(useridTD).append(usernameTD).append(emailTD).append(provinceTD).append(cityTD).append(optd).appendTo("#usertable tbody");

    })
}
function build_page_nav(bb) {//构建分页信息
    $("#navdiv").empty();
    var ul=$("<ul></ul>").addClass("pagination");//分页
    var firstpageLI=$("<li></li>").append($("<a></a>").append("首页"));
    var lastpageLI=$("<li></li>").append($("<a></a>").append("末页"));
    var beforepageLI=$("<li></li>").append($("<a></a>").append("&laquo;"));
    //判断当前页码是否为第一页是的话则首页和前一页无法点击
    if(bb.extend.pageinfo.hasPreviousPage==false)
    {
        firstpageLI.addClass("disabled");
        beforepageLI.addClass("disabled");
    }
    else
    {
        firstpageLI.click(function () {//添加首页单击事件
            if(orderflag==1)
            {
                goto_page(1,id,username,email,province,1);
            }
            else
                goto_page(1,id,username,email,province);

        })
        beforepageLI.click(function () {//添加上一页单击事件
            if(orderflag==1)
            {
                goto_page(bb.extend.pageinfo.pageNum-1,id,username,email,province,1);
            }
            else
                goto_page(bb.extend.pageinfo.pageNum-1,id,username,email,province);

        });
    }
    var nextpageLI=$("<li></li>").append($("<a></a>").append("&raquo;"));
    //判断是否有下页添加不能点击属性
    if(bb.extend.pageinfo.hasNextPage==false)
    {
        nextpageLI.addClass("disabled");
        lastpageLI.addClass("disabled");
    }
    else
    {
        nextpageLI.click(function () {//添加下一页单击事件
            if(orderflag==1)
            {
                goto_page(bb.extend.pageinfo.pageNum+1,id,username,email,province,1);
            }
            else
                goto_page(bb.extend.pageinfo.pageNum+1,id,username,email,province);

        })

        lastpageLI.click(function () {//添加最后一页单击事件
            if(orderflag==1)
            {
                goto_page(bb.extend.pageinfo.pages,id,username,email,province,1);
            }
            else
                goto_page(bb.extend.pageinfo.pages,id,username,email,province);
        })
    }
    //添加首页和前一页
    ul.append(firstpageLI).append(beforepageLI);
    //遍历添加每一页
    $.each(bb.extend.pageinfo.navigatepageNums,function (index,items) {

        var numLI=$("<li></li>").append($("<a></a>").append(items));
        //判断如果当前页码为正在遍历的页码则给其添加高亮显示
        if(bb.extend.pageinfo.pageNum==items)
        {
            numLI.addClass("active");
        }
        numLI.click(function () {
            if(orderflag==1)
            {
                goto_page(items,id,username,email,province,1);
            }
            else
                goto_page(items,id,username,email,province);
        })
        ul.append(numLI);
    });
    //添加下一页和末页
    ul.append(nextpageLI).append(lastpageLI);
    var nav=$("<nav></nav>").append(ul);
    nav.appendTo("#navdiv");
}
//显示页码数据
function pageinfomation(cc) {
    nowpage=cc.extend.pageinfo.pageNum;
    $("#pagedetail").empty();
    $("#pagedetail").append("当前"+cc.extend.pageinfo.pageNum+"页，总"+cc.extend.pageinfo.pages+"页，总"+cc.extend.pageinfo.total+"条");
}
//校验输入信息格式
function showmsg(ele,status,msg) {
    $(ele).parent().removeClass("has-success has-error");
    $(ele).next("span").text("");
    if("success"==status)
    {
        $(ele).parent().addClass("has-success");
        $(ele).next("span").text(msg);
    }
    else if("error"==status)
    {
        $(ele).parent().addClass("has-error");
        $(ele).next("span").text(msg);
    }
}

$("#querybt").click(function () {
    id=$("#id").val();
    username=$("#usernames").val();
    email=$("#emails").val();
    province=$("#provinces").val();
    if(orderflag==1)
    {

        goto_page(1,id,username,email,province,1);

    }
    else if(orderflag==0) {

        goto_page(1, id, username, email, province);

    }

})
//修改按钮绑定单击事件弹出遮罩层，回显员工信息
$(document).on("click",".edit_bt2",function () {
    $("#updateusermodel").modal({
        backdrop: "static"
    })
    var select=$("#upselectprovince");
    var id=$(this).attr("userid");
    $.ajax({
        url:"returnuser",
        data:{id:id},
        type:"GET",
        success:function (result) {
            if(result!=null)
            {
                showreturndata(result);
                showprovince(select);

            }
        }
    })
    $("#updateuser").attr("user_id",id);//将用户id传给遮罩层保存按钮
})
//弹出模态框
$("#adduserbt").click(function () {
    var seletype=$("#selectprovince");//获取向哪个下拉款增加数据的dom对象
    showprovince(seletype);
    $("#adduser").modal({
        backdrop:"static"
    })
})
//给对应的省份添加城市
$("#selectprovince").change(function () {
    var selectypes=$("#selectcity");
    var pid=$(this).val();
    showcity(pid,selectypes);
})
//给保存按钮绑定添加用户单击事件
$("#saveuser").click(function () {
    var usnames=$("#username").val();
    var emailss=$("#email").val();
    var provincess=$("#selectprovince option:checked").text();
    var cityss=$("#selectcity option:checked").text()
    saveusertodatabase(usnames,emailss,provincess,cityss);

})
//绑定修改遮罩层省份信息发生改变时修改城市下拉框对应的城市数据；
$("#upselectprovince").change(function () {
    var pid=$(this).val();
    var selecttypes=$("#upselectcity");
    showcity(pid,selecttypes);
})
//绑定修改板寸按钮将数据库信息进行更新
$("#updateuser").click(function () {
    var user_id=$(this).attr("user_id");
    var email=$("#upemail").val();
    var province=$("#upselectprovince option:checked").text();
    var city=$("#upselectcity option:checked").text();
    updateuserinfo(user_id,email,province,city);

})

//绑定删除单击事件
$(document).on("click",".delete_bt",function () {
    var id=$(this).attr("user_id");
    var username=$(this).attr("user_name");
    var message=window.confirm("你确定要删除"+id+"号"+username+"的信息吗？");
    if(message)
    {

        deleteuser(id);
        alert("删除成功");
        if(orderflag==1)
        {
            goto_page(nowpage,null,null,null,null,1);
        }
        else
            goto_page(nowpage);
        return;
    }
    else
    {
        return;
    }

})
//绑定排序键
$("#orderby").click(function () {
//glyphicon glyphicon-chevron-down
//glyphicon glyphicon-chevron-up;
    var noworder=$("#orderby span").attr("class");//获取当前图标装态
    if(noworder=="glyphicon glyphicon-chevron-down")
    {
        $("#orderby span").attr("class","glyphicon glyphicon-chevron-up");
        orderflag=0;
        goto_page(1,id,username,email,province);
    }
    if(noworder=="glyphicon glyphicon-chevron-up")
    {
        orderflag=1;
        $("#orderby span").attr("class","glyphicon glyphicon-chevron-down");
        goto_page(1,id,username,email,province,1);
    }
})
//绑定全选事件//
$("#checkall").click(function () {
    $(".check_item").prop("checked",$(this).prop("checked"));
})
$(document).on("click",".check_item",function () {
    if($(".check_item:checked").length==$(".check_item").length)
    {
        $("#checkall").prop("checked",true);
    }
        // var flag=$(".check_item:checked").length==$(".check_item").length;
        // if(flag)
    // $("#checkall").prop("checked",flag);
    else   $("#checkall").prop("checked",false);
})
$("#deleteall").click(function () {
    var ids="";
    var i=0;
    $.each($(".check_item:checked"),function () {
        ids=ids+$(this).parents("tr").find("td:eq(1)").text()+"-";
        i=i+1;
    })
    ids=ids.substring(0,ids.length-1);
    var flag=window.confirm("确认删除"+ids+"号"+"共"+i+"个人吗？");
    if(flag)
    {
        deletelists(ids);
        alert("删除成功");
        if(orderflag==1)
        {
            goto_page(nowpage,null,null,null,null,1);
        }
        else
            goto_page(nowpage);
        return;
    }
    else
        return;
})

//绑定校验事件失去焦点事件
$("#username").blur(function () {
    var usernamevalue=$("#username").val();
    var regist=/(^[a-zA-Z0-9_-]{3,16}$)|(^[\u4e00-\u9fa5]{2,5}$)/;
    if(!regist.test(usernamevalue))
    {
        showmsg("#username","error","用户名为3-16为字母或者2-5位中文字符");
    }
    else
    {
        showmsg("#username","success","")
    }
})
$("#email").blur(function () {
    var emailval=$("#email").val();
    var regist=/^[a-zA-Z0-9]+([._\\]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/;
    if(!regist.test(emailval))
    {
        showmsg("#email","error","请使用正确的邮箱格式");
    }
    else
    {
        showmsg("#email","success","");
    }
})
//绑定清空查询条件单击事件
$("#clear").click(function () {
$("#id").val("");
$("#usernames").val("");
$("#emails").val("");
$("#provinces").val("");
id=null;
username=null;
email=null;
province=null;
if(orderflag==1)
{
    goto_page(1,null,null,null,null,1);
}
else if(orderflag==0) {
    goto_page(1);
}
})