package com.coderpwh.rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.tools.json.JSONUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: coderpwh
 * @date: 2020/4/20 0020 11:13
 */
public class Recv {

    private  final  static  String QUEUE_NAME="hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.100.4.47");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

    }
}
