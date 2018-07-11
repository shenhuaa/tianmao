package com.tianmao.controller.app.tiaozhuan;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("tiao")
public class TiaoZhuanController {


    @RequestMapping("/home")
    public String showHome() {
        return "home";
    }
}
