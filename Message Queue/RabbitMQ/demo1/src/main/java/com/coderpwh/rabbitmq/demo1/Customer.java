package com.coderpwh.rabbitmq.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Customer {

    private final  static  String QUEUE_NAME = "rabbitMQ.test";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 设置RabbitMQ地址
        factory.setHost("localhost");

        // 创建一个新的连接
        Connection connection = factory.newConnection();

        // 创建一个通道
        Channel channel = connection.createChannel();

        // 声明要关注的队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("Customer Waiting Received messages");

        // 告诉服务器需要那个频道的消息，如果有消息，就会执行回调函数 handleDelivery
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"UTF-8");
                System.out.println("Customer Received '" + message + "'");

            }
        };

         // 自动回复队列应答  -- RabbitMQ中的消息确认机制
        channel.basicConsume(QUEUE_NAME,true,consumer);

    }
}
