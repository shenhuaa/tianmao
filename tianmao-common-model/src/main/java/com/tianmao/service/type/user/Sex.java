package com.tianmao.service.type.user;


import com.tianmao.service.type.BaseEnum;

/**
 * 性别类型
 *
 * @author roach
 * @date 2017/11/23
 */
public enum Sex implements BaseEnum {

    MAN(0, "男"),

    WOMAN(1, "女"),

    UNKNOWN(2, "未知");

    private int index;

    private String remark;

    Sex(int index, String remark) {
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
        return "Sex{" +
                "index=" + index +
                ", remark='" + remark + '\'' +
                '}';
    }
}