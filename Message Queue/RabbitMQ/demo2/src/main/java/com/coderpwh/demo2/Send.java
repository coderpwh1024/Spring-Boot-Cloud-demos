package com.coderpwh.demo2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private final static String TASK_QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

        String message = getMessage(args);
        channel.basicPublish("", "hello", null, message.getBytes());
        System.out.println("[x] Sent'" + message + "'");


    }

    private static String getMessage(String[] strings) {
        if (strings.length < 1) {
            return "Hello World!";
        }
        return joinStrings(strings, "");
    }

    private static String joinStrings(String[] strings, String delimiter) {

        int length = strings.length;
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            sb.append(delimiter).append(strings[1]);
        }
        return sb.toString();
    }


}
