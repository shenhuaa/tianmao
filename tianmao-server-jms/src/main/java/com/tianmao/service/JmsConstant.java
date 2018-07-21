package com.tianmao.service;

/**
 * jms常量
 */
public class JmsConstant {

    /**
     * app-topic单播
     */
    public static final String APP_TOPIC_EXCHANGE = "appTopicExchange";

    /**
     * admin-topic单播
     */
    public static final String ADMIN_TOPIC_EXCHANGE = "adminTopicExchange";

    /**
     * shop-fanout广播
     */
    public static final String SHOP_EXCHANGE = "shopFanoutExchange";

    /**
     * vendor-fanout广播
     */
    public static final String VENDOR_EXCHANGE = "vendorFanoutExchange";


    /**
     * 云信消息队列名称
     */
    public static final String SEND_JPUSH_QUEUE = "sendJpushQueue";


    /**
     * 云信消息队列名称
     */
    public static final String TIMING_REMINDER_QUEUE = "timingReminderQueue";

    /**
     * 红包退还提醒 routingKey
     */
    public static final String RED_EXPIRE_RETURN_KEY = "red.expire.return";

    /**
     * 消息提醒 routingKey
     */
    public static final String TIMING_REMINDER_ROUTING_KEY = "timing.reminder";

    /**
     * 后台消息推送 routingKey
     */
    public static final String SYSTEM_PUSH_ROUTING_KEY = "system.push";

}
