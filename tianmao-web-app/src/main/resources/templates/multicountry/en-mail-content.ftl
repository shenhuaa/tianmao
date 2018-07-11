<!doctype html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=0.5, maximum-scale=1, user-scalable=no"/>
    <meta name="format-detection" content="telephone=no"/>
    <meta name="format-detection" content="email=no"/>
    <title >Mail registration</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        .email-sig {
            width: 100%;
            height: auto;
            font-size: 0.16rem;
            max-width: 800px;
            margin: 0 auto;
            padding: 30px 34px 0;
        }
        .email-sig-logo {
            width: 120px;
            height: 96px;
            margin-bottom: 30px;
        }
        .email-sig-conten {
            width: 100%;
            border-radius: 8px;
            background-color: #F6F6F6;
            border: 1px solid #D5D5D5;
            overflow: hidden;
            padding-bottom: 42px;
        }
        .sig-conten-title {
            width: 100%;
            height: 97px;
            padding: 0 30px;
            margin-bottom: 19px;
            display: flex;
            align-items: center;
            justify-content: space-between;
            background-color: #3699FF;
            font-size: 32px;
            color: #FCFCFC;
            line-height: 1;
        }
        .sig-conten-title-help {
            font-size: 28px;
        }
        .sig-conten-text,.sig-conten-but {
            padding: 0 32px;
            font-size: 28px;
            color: #252525;
            line-height: 1;
        }
        .sig-conten-text {
            margin-bottom: 37px;

        }
        .sig-conten-but {
            margin-bottom: 39px;
            font-size: 34px;
            color: #090808;
        }
        .sig-conten-text {
            line-height: 1.5;
        }
    </style>

</head>
<body>
<div class="email-sig">
    <div class="email-sig-logo">
        <img src="${appUrl}/static/image/istore_loge.png" alt="">
    </div>
    <div class="email-sig-conten">
        <div class="sig-conten-title">
            <span >Dear Austin User：</span>
            <span class="sig-conten-title-help" >Password help</span>
        </div>
        <div class="sig-conten-text">To verify your identity, please use the verification code below：</div>
        <div class="sig-conten-but">${verifyCode}</div>
        <div class="sig-conten-text">
            Astar is very concerned about the security of your account. OTTO will never ask you to open or verify your escrow password, credit card or bank by email. If you receive a suspicious email with a link to update your account information, please do not use a stand-alone link, but you should email this The report is for investigation by Christopher.
        </div>
    </div>
</div>

</body>
</html>