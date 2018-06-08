package com.tianmao.service.type.user;



import com.tianmao.service.type.BaseEnum;

import java.util.Arrays;
import java.util.List;

/**
 * 红包类型
 *
 * @author roach
 * @date 2017/11/23
 */
public enum RedType implements BaseEnum {

    FIXED(0, "固定"),

    RANDOM(1, "随机");

    private int index;

    private String remark;

    RedType(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    public static List<RedType> toList() {
        RedType[] orderTypes = RedType.values();
        return Arrays.asList(orderTypes);
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
