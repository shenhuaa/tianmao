package com.tianmao.service.type.user;
import java.io.Serializable;

/**
 * 验证码发送类型
 *
 * @author roach
 * @date 2017/11/15
 */
public enum SmsType implements Serializable {

    /**
     * 用户注册
     */
    REGISTER,

    /**
     * 找回密码
     */
    FORGET,

}
