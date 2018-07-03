package com.tianmao.service.app.serviceImpl;

import com.tianmao.utils.HttpCode;
import com.tianmao.service.app.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.tianmao.service.app.mapper.VerificationCodeMapper;
import com.tianmao.service.model.sencode.VerificationCode;
import com.tianmao.service.exception.BaseServiceException;

@Service
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
