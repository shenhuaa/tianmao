package com.tianmao.service.type;
import java.io.Serializable;

/**
 * 验证码发送类型
 *
 * @author roach
 * @date 2017/11/27
 */
public enum VerificationCodeType implements Serializable {

    PC_REGISTER_MOBILE(0, "pc端手机注册发送验证码"),

    APP_REGISTER_MOBILE(1, "app端手机注册发送验证码"),

    PC_FORGET_MOBILE(2, "pc端手机找回密码发送验证码"),

    APP_FORGET_MOBILE(3, "app端手机找回密码发送验证码"),

    PC_ACCOUNT_BIND_MOBILE(4, "pc端账号绑定发送验证码"),

    APP_ACCOUNT_BIND_MOBILE(5, "app端账号绑定发送验证码");


    private int index;

    private String remark;

    VerificationCodeType(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "VerificationCodeType{" +
                "index=" + index +
                ", remark='" + remark + '\'' +
                '}';
    }

}
