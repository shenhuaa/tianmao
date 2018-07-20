package com.tianmao.controller.app.weixin.codepay;

import cn.wlxsh.common.util.lang.BigDecimalUtil;
import cn.wlxsh.common.util.random.SerialNumberUtil;
import cn.wlxsh.common.util.servlet.HttpCode;
import cn.wlxsh.common.util.servlet.I18nMessageUtil;
import cn.wlxsh.common.util.servlet.RemoteClientIpUtil;
import cn.wlxsh.common.util.servlet.Rest;
import cn.wlxsh.pay.weixin.common.WeixinPayConstants;
import cn.wlxsh.pay.weixin.model.request.UnifiedOrderRequest;
import cn.wlxsh.pay.weixin.model.response.UnifiedOrderResponse;
import cn.wlxsh.pay.weixin.sdk.WeixinPay;
import com.tianmao.app.util.ZxingUtil;
import com.tianmao.controller.app.weixin.config.MallWxpayConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

@Controller
@RequestMapping("/pay")
public class WxAliCodePayController {


    @Autowired
    private MallWxpayConfig wxpayConfig;


    /**
     * 获取支付二维码
     *
     * @param response
     */
    @GetMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE})
    public void qrcode(HttpServletResponse response) {
        String sumOrderNumber = SerialNumberUtil.generate();
        //String redirectUrl = "http://w19r935245.iok.la/pay/show/action?money=0.05&orderNumber="+sumOrderNumber;
        String redirectUrl = "weixin：//wxpay/bizpayurl?appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=f6808210402125e30663234f94c87a8c&product_id=1&time_stamp=1415949957&sign=512F68131DD251DA4A45DA79CC7EFE9D";
        ZxingUtil.getQRCodeImage(redirectUrl, response);
    }

    /**
     */
    @RequestMapping("/wx/attr")
    @ResponseBody
    public Rest wxAttr(BigDecimal money, String orderNumber) {
        Rest.Builder rest = Rest.newBuilder();
        BigDecimal totalFee = BigDecimalUtil.mul(money, new BigDecimal(100));
        try {
            WeixinPay wxpay = new WeixinPay(wxpayConfig, WeixinPayConstants.SignType.HMACSHA256);
            UnifiedOrderRequest request = new UnifiedOrderRequest();
            request.setBody(wxpayConfig.getBody());

            request.setOutTradeNo(orderNumber);
            request.setTotalFee(String.valueOf(totalFee.intValue()));
            request.setSpbillCreateIp(RemoteClientIpUtil.getRemoteIP());
            UnifiedOrderResponse response = wxpay.unifiedOrder(request);
            return rest
                    .put("wxData", response.getRestult())
                    .put("orderNumber", orderNumber)
                    .code(HttpCode.OK).message(I18nMessageUtil.getMessage("success")).build();
        } catch (Exception e) {
            return rest.code(HttpCode.ERROR).build();
        }
    }

    //回显地址
    @RequestMapping("/show/action")
    public String showPayAction(BigDecimal money, String orderNumber, ModelMap model) {
        model.put("money",money);
        model.put("orderNumber",orderNumber);
        return "/weixin/showAction";
    }
}
