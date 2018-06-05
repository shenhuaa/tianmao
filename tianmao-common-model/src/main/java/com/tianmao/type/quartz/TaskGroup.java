package com.tianmao.type.quartz;


import com.tianmao.type.BaseEnum;

/**
 * 任务组类型
 * @author roach
 * @date 2018/3/14
 */
public enum TaskGroup implements BaseEnum {
    MALL_ORDER_EXPIRE(0, "商城总订单自动过期成功"),

    ADMIN_MESSAGE_SEND(1, "系统消息推送"),

    MALL_AUTO_HARVEST(2, "商城订单自动确认收货成功"),

    MALL_RETURN_MONEY(3, "商城订单自动退款成功"),

    MALL_RETURN_GOODS_MONEY(4, "商城详情订单自动同意退货");

    private int index;

    private String remark;

    TaskGroup(int index, String remark) {
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
