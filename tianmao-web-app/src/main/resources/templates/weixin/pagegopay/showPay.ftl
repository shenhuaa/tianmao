<!DOCTYPE html>
<html lang="en">
<head>
    <meta>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="404 文件没找到"/>
    <meta name="author" content="爱士多"/>
    <link rel="icon" href="${ctx.appUrl}/favicon.ico" mce_href="${ctx.appUrl}/favicon.ico" type="image/x-icon"/>
    <title>去结算页面 爱士多</title>
    <script type="text/javascript" src="/static/js/jquery-1.11.1.min.js"></script>

    <script>
        var appUrl =  '${ctx.appUrl}';
        $(function(){

        })
       function wxPay() {
           $.post(appUrl + '/weixin/go/pay', {userId: 2}, function (data) {
               var code = data.code;
               alert(code);
           });
       }

    </script>
</head>
<body>

<div style="padding-left: 30%;"><span style="color: red; size: 200px;font-size: 100px;" onclick="wxPay()">H5去支付页面</span></div>


</body>
</html>