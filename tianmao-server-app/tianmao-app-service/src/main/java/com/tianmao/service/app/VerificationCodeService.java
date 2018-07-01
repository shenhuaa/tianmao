package com.tianmao.service.app;

import com.tianmao.service.model.sencode.VerificationCode;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface VerificationCodeService {

    void verification(@RequestParam("username") String username, @RequestParam("code") Integer code);

    void destroy(@RequestParam("username") String username, @RequestParam("code") Integer code);

    VerificationCode getByUsername(@RequestParam("username")String username);

    void create(VerificationCode verificationCode);
}
