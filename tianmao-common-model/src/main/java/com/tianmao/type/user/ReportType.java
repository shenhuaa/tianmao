package com.tianmao.type.user;


import com.tianmao.type.BaseEnum;

/**
 * 举报类型
 *
 * @author roach
 * @date 2017/11/30
 */
public enum ReportType implements BaseEnum {

    POST(0, "文章"),

    TALK(1, "微说"),

    ACTIVITY(2, "活动"),

    COMMENT(3, "评论"),

    USER(4, "用户");

    private int index;

    private String remark;

    ReportType(int index, String remark) {
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