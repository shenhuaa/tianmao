package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 红包领取状态
 *
 * @author roach
 * @date 2017/11/23
 */
public enum RedStatus implements BaseEnum {

    UNCLAIMED(0, "待领取"),

    HAVE_RECEIVED(1, "已领取"),

    EXPIRE(2, "已过期");

    private int index;

    private String remark;

    RedStatus(int index, String remark) {
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