package com.tianmao.service.app.controller;

import com.tianmao.api.app.VerificationCodeClient;
import com.tianmao.service.app.VerificationCodeService;
import com.tianmao.service.app.mapper.VerificationCodeMapper;
import com.tianmao.service.common.HttpCode;
import com.tianmao.service.exception.BaseServiceException;
import com.tianmao.service.model.sencode.VerificationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationCodeController implements VerificationCodeClient {


    @Autowired
    private VerificationCodeService verificationCodeService;
    @Override
    public void verification(String username, Integer code) {

        verificationCodeService.verification(username,code);
    }

    @Override
    public void destroy(String username, Integer code) {
        verificationCodeService.destroy(username,code);
    }

    @Override
    public VerificationCode getByUsername(String username) {
        return verificationCodeService.getByUsername(username);
    }

    @Override
    public void create(@RequestBody VerificationCode verificationCode) {

        verificationCodeService.create(verificationCode);
    }
}
