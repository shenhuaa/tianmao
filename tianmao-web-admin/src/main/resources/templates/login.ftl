<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="${ctx.adminUrl}/favicon.ico" mce_href="${ctx.adminUrl}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <meta http-equiv="Expires" content="0"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Cache-control" content="no-cache"/>
    <meta http-equiv="Cache" content="no-cache"/>
    <title>用户登录</title>
    <link href="/static/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="/static/css/font-awesome.min.css" rel="stylesheet">
    <link href="/static/css/login.css" rel="stylesheet"/>
    <script type="text/javascript">
        var adminUrl = "${ctx.adminUrl}";
    </script>

</head>
<body class="signin ">
<div class="signinpanel ">
    <div class="row">
        <div class="col-sm-4">
            <div class="signin-info"></div>
        </div>
        <div class="col-sm-5">
            <form id="loginForm" autocomplete="off">
                <h4 class="no-margins">用户登陆</h4>
                <input id="username" name="username" type="text" class="form-control uname" datatype="*" placeholder="用户名" nullmsg="用户名不能为空"/>
                <input name="password" type="password" class="form-control pword m-b" placeholder="密码" datatype="*6-18" nullmsg="密码不能为空"/>
                <#--<span>
                    <input type="checkbox" name="remember" value="remember" checked="checked">
                    <label for="remember">记住密码</label>
                </span>-->
                <button type="submit" class="btn btn-info btn-block">登录</button>
                <div class="col-md-12" style="margin-top: 5px"></div>
            </form>
        </div>
    </div>
</div>
<div class="signup-footer">
    &copy; 2017 All Rights Reserved.
</div>
<script type="text/javascript" src="/static/js/jquery.min.js"></script>
<#include "/common/validate.ftl"/>
<script type="text/javascript" src="/static/js/login.js"></script>
</body>
</html>
