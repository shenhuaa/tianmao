package com.tianmao.app.serviceImpl;

import com.tianmao.common.HttpCode;
import com.tianmao.service.app.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.app.mapper.VerificationCodeMapper;
import com.tianmao.model.sencode.VerificationCode;
import com.tianmao.service.exception.BaseServiceException;

@RestController
public class VerificationCodeServiceImpl implements VerificationCodeService {


    @Autowired
    private VerificationCodeMapper verificationCodeMapper;

    @Override
    public void verification(String username, Integer code) {
        VerificationCode verificationCode = verificationCodeMapper.getByUsernameAndCode(username, code);
        if (verificationCode == null) {
            throw new BaseServiceException(HttpCode.VERIFICATION_CODE_ERROR);
        }
        if (verificationCode.getDueTime().getTime() < System.currentTimeMillis()) {
            throw new BaseServiceException(HttpCode.VERIFICATION_CODE_EXPIRED);
        }
    }

    @Override
    public void destroy(String username, Integer code) {
        verificationCodeMapper.destroy(username, code);
    }

    @Override
    public VerificationCode getByUsername(String username) {
        return null;
    }

    @Override
    public void create(@RequestBody VerificationCode verificationCode) {

    }
}
