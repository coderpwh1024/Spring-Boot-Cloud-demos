package com.coderpwh.rabbitmq.demo4;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogsTopic1 {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME,"topic");

        String queueName  = channel.queueDeclare().getQueue();

        String [] routingKeys = new String[]{"*.orange.*"};

        //绑定路由
        for (String routingKey : routingKeys) {
            channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
            System.out.println("ReceiveLogsTopic1 exchange:" + EXCHANGE_NAME + ", queue:" + queueName + ", BindRoutingKey:" + routingKey);
        }

        System.out.println("ReceiveLogsTopic1 Waiting for messages");

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
            String message = new String(body,"UTF-8");
                System.out.println("ReceiveLogsTopic1 Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };

        channel.basicConsume(queueName, true, consumer);





    }
}
