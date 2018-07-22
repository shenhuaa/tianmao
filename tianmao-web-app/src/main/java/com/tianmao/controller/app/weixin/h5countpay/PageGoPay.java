package com.tianmao.controller.app.weixin.h5countpay;

import com.tianmao.app.util.Rest;
import com.tianmao.utils.HttpCode;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("weixin")
public class PageGoPay {


    @RequestMapping("/show/pay")
    public String showPageGoPay() {
        return "weixin/pagegopay/showPay";
    }
    @RequestMapping("/go/pay")
    @ResponseBody
    public Rest goPay() {
        Rest.Builder rest = Rest.newBuilder();
        return rest.code(HttpCode.OK).build();
    }
}
