package com.tianmao.service;

/**
 * 消息通知类型
 *
 * @date 2018/1/16
 */
public enum NotifyType {

    /**
     * websocket心跳检测
     */
    WEBSOCKET_HEARTBEAT("/websocket/heartbeat"),

    /**
     * 扫码进门
     */
    SCAN_QRCODE_OPEN_DOOR("/order/list/notification"),

    /**
     * 扫码出门
     */
    SCAN_QRCODE_OUT_OPEN_DOOR("/order/list/notification"),

    /**
     * 订单支付
     */
    ORDER_PAY("/order/list/notification"),

    /**
     * 刷新门店订单列表
     */
    RELOAD_ORDER_LIST_PAGE("/reload/order/list/notification"),

    /**
     * 刷新门店首页
     */
    RELOAD_INDEX_PAGE("/reload/index/notification"),

    /**
     * 扫码开门
     */
    SCAN_QRCODE_OPEN_DOOR_INDEX("/reload/index/notification"),

    /**
     * 售货机支付通知 ws://10.10.20.9:8084/socket/vendor/user/xxxxx/vendor/pay/notification
     */
    VENDOR_PAY_NOTIFICATION("/vendor/pay/notification");

    private String destination;

    NotifyType(String destination) {
        this.destination = destination;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "NotifyType{" +
                "destination='" + destination + '\'' +
                '}';
    }
}
