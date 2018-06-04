package com.tianmao.type.user;


import com.tianmao.type.BaseEnum;

/**
 * 举报原因
 *
 * @author roach
 * @date 2017/11/30
 */
public enum ReportWhy implements BaseEnum {

    PERSONAL_ABUSE(0, "人身攻击"),

    DEFRAUD(1, "诈骗"),

    SALACITY_VULGAR(2, "色情低俗"),

    ADVERT(3, "广告"),

    POLITICALLY_SENSITIVE(4, "政治敏感"),

    RUMOUR(5, "谣言"),

    OTHER(6, "其他 ");

    private int index;

    private String remark;

    ReportWhy(int index, String remark) {
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