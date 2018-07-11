<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>支付中...</title>
</head>
<body>
<script>
    //https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=7_7&index=6 文档地址
    function onBridgeReady() {
        try {
            WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": "${appId}",
                        "timeStamp": "${timeStamp}",
                        "nonceStr": "${nonceStr}",
                        "package": "${package}",
                        "signType": "${signType}",
                        "paySign": "${paySign}",
                    },
                    function (res) {
                        //document.write(JSON.stringify(res));
                        var msg = res.err_msg;
                        if (msg == 'get_brand_wcpay_request:ok') {
                            window.location.href = '${ctx.shopUrl}/pay/success?type=mobile';
                        } else if (msg == 'get_brand_wcpay_request:fail') {
                            window.location.href = '${ctx.shopUrl}/pay/fail?type=mobile';
                        }
                    }
            );
        } catch (err) {
            document.write(err);
        }
    }

    if (typeof window.WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }
</script>
</body>
</html>
