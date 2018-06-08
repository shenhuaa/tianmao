package com.tianmao.service.type.user;

import com.tianmao.service.type.BaseEnum;

/**
 * 账号类型
 *
 * @author roach
 * @date 2017/11/28
 */
public enum AccountType implements BaseEnum {

    WEIXIN_ACCOUNT(0, "微信账号");


    private int index;

    private String remark;

    AccountType(int index, String remark) {
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


}
