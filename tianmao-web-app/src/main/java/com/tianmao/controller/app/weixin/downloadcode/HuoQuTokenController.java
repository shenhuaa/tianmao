package com.tianmao.controller.app.weixin.downloadcode;

import com.alibaba.fastjson.JSONObject;
import com.tianmao.app.util.FileDownloadUtil;
import com.tianmao.app.util.Qrcode;
import com.tianmao.app.util.ZxingUtil;
import com.tianmao.controller.app.weixin.getuser.WXAuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RestController
@RequestMapping("/wx")
public class HuoQuTokenController {

    @Value("${storage.path.out.door.qrcode}")
    private String outDoorQrcode;

    @Autowired
    private QrcodeUtil qrcodeUtil;

    /**
     * 下载微信公众号图片
     *
     * @return
     */
    @RequestMapping("token")
    public String HuoQu(HttpServletResponse response) throws Exception {
        String appid = WXAuthUtil.APPID;
        String appsecret = WXAuthUtil.APPSECRET;
        String chickUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + appsecret;
        JSONObject chickuserInfo = null;
        try {
            chickuserInfo = WXAuthUtil.doGetJson(chickUrl);
            System.out.println("access_token-----" + chickuserInfo.getString("access_token"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String accessToken = chickuserInfo.getString("access_token");

        File dir = new File(outDoorQrcode);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String qrcodeImagePath = outDoorQrcode + "/公众号二维码"+new Date().getTime() + ".png";
        File file = new File(qrcodeImagePath);
        if (!file.exists()) {
            //生成永久二维码
            Qrcode qrcode = qrcodeUtil.getAlwaysQrcode(accessToken);
            if (qrcode == null) {
                return null;
            }
            ZxingUtil.writeQRCodeImage(qrcode.getUrl(), qrcodeImagePath);
        }
        FileDownloadUtil.download("公众号二维码" + ".png", qrcodeImagePath, response);
        return "OK";
    }
}
