package com.tianmao.service;

import com.rabbitmq.http.client.domain.ExchangeType;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 消息通知
 *
 * @author roach
 * @date 2018/1/16
 */
@Data
@Builder
public final class NotifyMessage<T> implements Serializable {

    private static final long serialVersionUID = -5055986041044268845L;

    /**
     * 消息id
     */
    private String messageId;

    /**
     * 推送到指定的用户
     */
    private String user;

    /**
     * 推送类型
     */
    private NotifyType notifyType;

    private ExchangeType exchangeType;

    /**
     * 订阅地址
     */
    private String subscribe;

    /**
     * 携带的数据
     */
    private T notifyData;

}
