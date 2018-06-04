package com.tianmao.service.app;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.tianmao.model.sencode.VerificationCode;

@FeignClient(name = "${server.app.name}")
public interface VerificationCodeService {

    @RequestMapping(value = "/vercode/verification")
    void verification(@RequestParam("username") String username, @RequestParam("code") Integer code);

    @RequestMapping(value = "/vercode/destroy")
    void destroy(@RequestParam("username") String username, @RequestParam("code") Integer code);

    @RequestMapping(value = "/vercode/getByUsername")
    VerificationCode getByUsername(@RequestParam("username")String username);

    @RequestMapping(value = "/vercode/create")
    void create(VerificationCode verificationCode);
}
