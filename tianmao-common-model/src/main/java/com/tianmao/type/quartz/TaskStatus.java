package com.tianmao.type.quartz;


import com.tianmao.type.BaseEnum;

/**
 * 任务状态
 *
 * @author roach
 * @date 2018/3/14
 */
public enum TaskStatus implements BaseEnum {

    /**
     * 运行中
     */
    RUNNING(0, "运行中"),

    /**
     * 完成
     */
    COMPLETE(1, "完成"),

    /**
     * 停止
     */
    STOP(2, "停止");


    private int index;

    private String remark;

    TaskStatus(int index, String remark) {
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
