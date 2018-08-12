package com.coderpwh.rabbitmq.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";


    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 指定ip
        factory.setHost("localhost");

        // 连接
        Connection connection = factory.newConnection();

        // 通道
        Channel channel = connection.createChannel();

        // fanout 表示分发，所有的消费者得到同样的队列消息
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 分发消息
        for (int i = 0; i < 5; i++) {
            String message = "Hello World" + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("EmitLog Sent '" + message + "'");
        }
        channel.close();
        connection.close();

    }
}
