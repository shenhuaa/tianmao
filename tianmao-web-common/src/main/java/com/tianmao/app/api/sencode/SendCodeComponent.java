package com.tianmao.app.api.sencode;

import com.tianmao.api.app.UserClient;
import com.tianmao.app.util.Rest;
import com.tianmao.app.util.ValidateUtil;
import com.tianmao.service.model.user.User;
import com.tianmao.service.type.VerificationCodeType;
import com.tianmao.utils.HttpCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 发送验证码公共类
 *
 * @author roach
 * @date 2017/12/13
 */
@Component
public class SendCodeComponent {

    @Autowired
    private UserClient userClient;

    @Autowired
    private SendCommon sendCommon;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 发送验证码
     *
     * @param mobile               手机号
     * @param verificationCodeType 验证码发送类型
     * @return
     */
    public Rest send(String mobile, VerificationCodeType verificationCodeType) {
        return send(mobile, null, verificationCodeType);
    }

    /**
     * 发送验证码
     *
     * @param mobile          手机号
     * @param verifyUserExist 验证用户是否存在
     * @return
     */
    public Rest send(String mobile, Boolean verifyUserExist, VerificationCodeType verificationCodeType) {
        Rest.Builder rest = Rest.newBuilder();
        if (StringUtils.isEmpty(mobile)) {
            return rest.code(HttpCode.MISSING_PARAMETERS).message("手机不能为空").build();
        }
        if (!ValidateUtil.isMobile(mobile)) {
            return rest.code(HttpCode.ILLEGAL_PARAMETERS).message("手机格式有误").build();
        }
        User user = userClient.getUserByUsername(mobile);
        if (verifyUserExist != null) {
            if (verifyUserExist && user == null) {
                return rest.code(HttpCode.MOBILE_NO_REGISTERED).build();
            } else if (!verifyUserExist && user != null) {
                return rest.code(HttpCode.MOBILE_ALREADY_REGISTERED).build();
            }
        }

        //发送验证码
        threadPoolTaskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                sendCommon.sendCode(mobile, verificationCodeType);
            }
        });

        return rest.code(HttpCode.OK).build();
    }
}
