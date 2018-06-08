package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 用户状态
 *
 * @author roach
 * @date 2017/11/23
 */
public enum UserStatus implements BaseEnum {

    NORMAL(0, "正常"),

    FREEZE(1, "冻结");;

    private int index;

    private String remark;

    UserStatus(int index, String remark) {
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
