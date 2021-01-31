package com.codrpwh.rabbitmq.publish;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLog {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.36.2");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");

        String message = args.length < 1 ? "info: Hello World!" :
                String.join(" ", args);

        channel.basicPublish(EXCHANGE_NAME,"",null,message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '" + message + "'");


    }
}
