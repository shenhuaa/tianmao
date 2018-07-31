package com.tianmao.controller.app.rabbitmq;

import com.tianmao.service.JmsConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;


@Component
public class RabbitMq {
    @RabbitListener(
            bindings = @QueueBinding(
                    key = JmsConstant.SYSTEM_PUSH_ROUTING_KEY,
                    value = @Queue(value = JmsConstant.SEND_JPUSH_QUEUE,durable = "true"),
                    exchange = @Exchange(value = JmsConstant.APP_TOPIC_EXCHANGE, type = ExchangeTypes.TOPIC)
            )
    )
    @RabbitHandler
    private void sendJPushUtil(Long userId) {
        System.out.println("-----------------------------------------------监听接收到数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到数据id是"+userId);
        System.out.println("-----------------------------------------------监听接收到数据id是"+userId);
    }
}
