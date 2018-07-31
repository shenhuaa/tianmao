package com.tianmao.controller.app.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.util.concurrent.TimeoutException;

public class demo1 {

    private final static String QUEUE_NAME = "queue-test";



    public static void main(String[] argv) throws java.io.IOException, TimeoutException {

        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
        connectionFactory.setHost("localhost");
        //      connectionFactory.setPort(5762); //指定端口
        //      connectionFactory.setUsername("admin");//用户名
        //      connectionFactory.setPassword("admin");//密码
        //3.通过connectionFactory创建一个连接connection
        Connection connection = connectionFactory.newConnection();
        //4.通过connection创建一个频道channel
        Channel channel = connection.createChannel();
        //5.通过channel指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送的消息
        String message = "hello world!你好哇！！！！！！";
        //6.通过channel向队列中添加消息，第一个参数是转发器，使用空的转发器（默认的转发器，类型是direct）
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("向" + QUEUE_NAME + "中添加了一条消息:" + message);
        //7.关闭频道
        channel.close();
        //8.关闭连接
        connection.close();
    }

    public static void main2(String[] argv) throws java.io.IOException, java.lang.InterruptedException, TimeoutException {
        //1.创建一个ConnectionFactory连接工厂connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.通过connectionFactory设置RabbitMQ所在IP等信息
        connectionFactory.setHost("localhost");
        //3.通过connectionFactory创建一个连接connection
        Connection connection = connectionFactory.newConnection();
        //4.通过connection创建一个频道channel
        Channel channel = connection.createChannel();
        //5.通过channel指定队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //与发送消息不同的地方
        //6.创建一个消费者队列consumer,并指定channel
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //7.为channel指定消费者
        channel.basicConsume(QUEUE_NAME, true, consumer);
        while (true) {
            //从consumer中获取队列中的消息,nextDelivery是一个阻塞方法,如果队列中无内容,则等待
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("接收到了" + QUEUE_NAME + "中的消息:" + message);
        }

    }


}
