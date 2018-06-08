package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 用户提现状态
 *
 * @author roach
 * @date 2017/11/23
 */
public enum DrawStatus implements BaseEnum {

    PENDING(0, "待处理"),

    TRANSFERRED_ACCOUNT(1, "已转账"),

    REJECTED(2, "已驳回"),

    TRANSFER_FAILURE(3, "转账失败");

    private int index;

    private String remark;

    DrawStatus(int index, String remark) {
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