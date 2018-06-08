package com.tianmao.service.type.user;

import com.fasterxml.jackson.annotation.JsonValue;
import com.tianmao.service.type.BaseEnum;

/**
 * 是否已读
 *
 * @author roach
 * @date 2017/11/23
 */
public enum ReadType implements BaseEnum {

    UNREAD(0, "未读"),

    READ(1, "已读");

    private int index;
    private String remark;

    ReadType(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @JsonValue
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}