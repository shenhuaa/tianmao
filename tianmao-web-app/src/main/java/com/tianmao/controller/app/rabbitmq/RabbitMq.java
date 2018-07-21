package com.tianmao.controller.app.rabbitmq;

import com.tianmao.service.JmsConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
public class RabbitMq {

    /**
     * 监听接收mq的数据
     *
     */
    @RabbitListener(bindings = @QueueBinding(
            key = JmsConstant.SYSTEM_PUSH_ROUTING_KEY,
            value = @Queue(value = JmsConstant.SEND_JPUSH_QUEUE, durable = "true"),
            exchange = @Exchange(value = JmsConstant.APP_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC))
    )
    @RabbitHandler
    private void getMQData(long userId) {
        System.out.println("-----------------------------------------------监听接收到rabbitmq的数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到rabbitmq的数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到rabbitmq的数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到rabbitmq的数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到rabbitmq的数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到rabbitmq的数据id是"+userId);
    }
}
