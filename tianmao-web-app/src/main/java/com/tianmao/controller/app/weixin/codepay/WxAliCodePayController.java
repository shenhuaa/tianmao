package com.tianmao.controller.app.weixin.codepay;

import com.tianmao.app.util.CookieUtil;
import com.tianmao.app.util.HeadUtil;
import com.tianmao.app.util.ZxingUtil;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/pay")
public class WxAliCodePayController {


    /**
     * 获取支付二维码
     *
     * @param response
     */
    @GetMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public void qrcode(HttpServletResponse response) {
        String redirectUrl = "http://w19r935245.iok.la/pay/qrcode/callback" + "/" + "BBB888";
        ZxingUtil.getQRCodeImage(redirectUrl, response);
    }

    /**
     * 扫码后回调地址
     * @param shopNumber 门店编号
     */
    @RequestMapping("/qrcode/callback/{shopNumber}")
    public void callback(@PathVariable String shopNumber, HttpServletRequest request, HttpServletResponse response) {

        try {
            String url = "http://w19r935245.iok.la:80";
            if (HeadUtil.isWeixin(request)) {
                String openId = CookieUtil.getCookie(request, "openId");
                url += "/html/wxpay.html?shopNumber=" + shopNumber;
                url += "&openId=" + openId;
            } else {
                String buyerId = CookieUtil.getCookie(request, "buyerId");
                url += "/html/alipay.html?shopNumber=" + shopNumber;
                url += "&openId=" + buyerId;
            }
            response.sendRedirect("/wx/token");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
