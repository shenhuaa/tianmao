package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 意见反馈状态
 *
 * @author roach
 * @date 2017/11/23
 */
public enum FeedbackStatus implements BaseEnum {

    PENDING(0, "待处理"),

    PROCESSED(1, "已处理");

    private int index;

    private String remark;

    FeedbackStatus(int index, String remark) {
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