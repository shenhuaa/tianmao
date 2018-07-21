package com.tianmao.service;

/**
 * 消息通知
 *
 * @author roach
 * @date 2018/1/12
 */
public interface RabbitmqService {

    void send(String exchange, NotifyMessage notifyMessage);

    <T> void send(String exchange, T t);

    <T> void send(String exchange, String routingKey, T t);

}