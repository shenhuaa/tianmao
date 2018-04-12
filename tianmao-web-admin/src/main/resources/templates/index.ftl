<html>
<head>
    <title>爱士多后台</title>
    <link rel="icon" href="${ctx.adminUrl}/favicon.ico" mce_href="${ctx.adminUrl}/favicon.ico" type="image/x-icon"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <#include "common/header.ftl"/>
    <script type="text/javascript">
        //处理键盘事件 禁止后退键（Backspace）密码或单行、多行文本框除外
        function banBackSpace(e) {
            var ev = e || window.event;//获取event对象
            var obj = ev.target || ev.srcElement;//获取事件源

            var t = obj.type || obj.getAttribute('type');//获取事件源类型

            //获取作为判断条件的事件类型
            var vReadOnly = obj.getAttribute('readonly');
            var vEnabled = obj.getAttribute('enabled');
            //处理null值情况
            vReadOnly = (vReadOnly == null) ? false : vReadOnly;
            vEnabled = (vEnabled == null) ? true : vEnabled;

            //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
            //并且readonly属性为true或enabled属性为false的，则退格键失效
            var flag1 = (ev.keyCode == 8
            && (t == "password" || t == "text" || t == "textarea") && (vReadOnly == true || vEnabled != true)) ? true
                    : false;

            //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
            var flag2 = (ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea") ? true
                    : false;

            //判断
            if (flag2) {
                return false;
            }
            if (flag1) {
                return false;
            }
        }
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = banBackSpace;
        //禁止后退键 作用于IE、Chrome
        document.onkeydown = banBackSpace;
    </script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow: hidden">
<div id="wrapper">
    <#include "common/left.ftl"/>
    <div id="page-wrapper" class="gray-bg dashbard-1" style="overflow: hidden">
        <#include "common/top.ftl"/>
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe" id="iframeOne" width="100%" height="100%" src="${ctx.adminUrl}/home" frameborder="0" scrolling="no"  seamless></iframe>
        </div>
        <#include "common/footer.ftl"/>
    </div>
</div>
</body>
</html>