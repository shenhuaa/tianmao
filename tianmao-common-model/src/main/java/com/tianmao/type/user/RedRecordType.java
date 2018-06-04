package com.tianmao.type.user;


import com.tianmao.type.BaseEnum;

/**
 * 红包记录类型
 *
 * @author roach
 * @date 2017/12/7
 */
public enum RedRecordType implements BaseEnum {

    POST_REWARD(0, "文章打赏"),

    RECEIVE_REWARD(1, "收到打赏"),

    RECEIVE_RED_ENVELOPES(2, "领取红包"),

    PRESENT_RECORD(3, "提现记录");

    private int index;

    private String remark;

    RedRecordType(int index, String remark) {
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