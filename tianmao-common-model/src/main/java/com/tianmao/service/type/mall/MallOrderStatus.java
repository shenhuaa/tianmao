package com.tianmao.service.type.mall;


import com.tianmao.service.type.BaseEnum;

/**
 * 订单状态
 *
 * @author roach
 * @date 2017/11/28
 */
public enum MallOrderStatus implements BaseEnum {

    NO_PAY(0, "等待买家付款"),

    STAY_SEND(1, "买家已经付款"),

    STAY_GET(2, "卖家已经发货"),

    TRADE_OK(3, "交易成功"),

    CLOSE(4, "交易关闭"),

    REFUND(5, "已退款");

    private int index;
    private String remark;

    MallOrderStatus(int index, String remark) {
        this.index = index;
        this.remark = remark;
    }

    @Override
    public int getIndex() {
        return index;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
