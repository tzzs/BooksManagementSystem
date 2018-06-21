<%--
  Created by IntelliJ IDEA.
  User: tzz
  Date: 2018/6/22
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
    <link rel="stylesheet" href="/css/userlist.css"/>
    <script type="text/javascript" src="/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<div class="box-pwd">
    <form action="" id="form" method="post">
        <div class="pwd">修改密码</div>
        <button type="button" class="close-pwd" id="close">X</button>

        <div class="box-th">
            <p>用户</p>
            <input type="text" name="userName" id="userName" class="userName" readonly="readonly"/>
        </div>

        <div class="box-th">
            <p>原密码</p>
            <input type="password" name="userPassword" id="userPassword" class="passWord"/>
        </div>

        <div class="box-th">
            <p>新密码</p>
            <input type="password" name="npwd" id="npwd" class="phone"/>
        </div>
        <div class="box-th">
            <p>确认密码</p>
            <input type="password" name="rnpwd" id="rnpwd" class="phone" onkeyup="check()"/>
        </div>
        <div class="info-pwd" id="info-pwd">
        </div>
        <input type="submit" class="submit" value="提交"/>
    </form>
</div>
<div class="box-shadow"></div>
</body>

<script>
    $(".box-shadow").css({
        "display": "block"
    });

    $("#userName").val("${u.userName}");
    var id = "${u.id}";
    $(".submit").click(function (e) {
        e.preventDefault();
        var pwd = $("#userPassword").val();
        var newpwd = $("#npwd").val();
        console.log(newpwd);
        $.ajax({
            url: "${pageContext.request.contextPath}/users/updatePassword",
            type: "post",
            data: {"id": id, "pwd": pwd, "newpwd": newpwd},
            async: false,
            success: function (data) {
                alert(data.msg);
            }
        })
    });


    function check() {
        var npwd = $("#npwd").val();
        var rnpwd = $("#rnpwd").val();
        if (npwd != rnpwd) {
            $("#info-pwd").text("输入密码不相同");
            $(".submit").attr("disabled", true);
        } else {
            $("#info-pwd").text("");
            $(".submit").attr("disabled", false);
        }
    }
</script>
</html>