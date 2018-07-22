package com.tianmao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * 消息通知
 *
 * @author roach
 * @date 2018/1/12
 */
@Service
public class RabbitmqServiceImpl implements RabbitmqService, RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    private static final Logger logger = LoggerFactory.getLogger(RabbitmqServiceImpl.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void send(final String exchange, final NotifyMessage notifyMessage) {
        String messageId = UUID.randomUUID().toString().replace("-", "");
        CorrelationData correlationData = new CorrelationData(messageId);
        try {
            logger.info("jms[{}]发送消息中：[{}]", correlationData, notifyMessage);
            notifyMessage.setMessageId(correlationData.getId());
            rabbitTemplate.convertAndSend(exchange, "", notifyMessage, correlationData);
        } catch (Exception e) {
            logger.error("jms消息发送失败：", e);
        }
    }

    @Override
    public <T> void send(final String exchange, final T t) {
        send(exchange, "", t);
    }

    @Override
    public <T> void send(final String exchange, final String routingKey, final T t) {
        String messageId = UUID.randomUUID().toString().replace("-", "");
        CorrelationData correlationData = new CorrelationData(messageId);
        try {
            logger.info("jms[{}]发送消息中：[{}]", correlationData, t);
            rabbitTemplate.convertAndSend(exchange, routingKey, t, correlationData);
        } catch (Exception e) {
            logger.error("jms消息发送失败：", e);
        }
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.debug("消息发送成功：[{}]", correlationData);
        } else {
            logger.error("消息发送失败：[{}]", cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        logger.error(message.getMessageProperties().getCorrelationIdString() + "消息发送失败");
    }

}
