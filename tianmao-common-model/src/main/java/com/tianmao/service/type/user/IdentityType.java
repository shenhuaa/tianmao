package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 身份类型
 *
 * @author roach
 * @date 2017/11/28
 */
public enum IdentityType implements BaseEnum {

    /**
     * 普通
     */
    ORDINARY(0, ""),

    /**
     * 网红
     */
    RED_NET(1, "V");

    private int index;

    private String remark;

    IdentityType(int index, String remark) {
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
