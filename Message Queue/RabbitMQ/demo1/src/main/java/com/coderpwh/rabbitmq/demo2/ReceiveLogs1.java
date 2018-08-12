package com.coderpwh.rabbitmq.demo2;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ReceiveLogs1 {

    private static final String EXCHANGE_NAME = "logs";

    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

         //  随机产生一个队列的名称
         String queueName = channel.queueDeclare().getQueue();
         // 对队列进行绑定
         channel.queueBind(queueName,EXCHANGE_NAME,"");


         System.out.println("ReceiveLogs1 Waiting for messages");

        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("ReceiveLogs1 Received '" + message + "'");


            }
        };

          //队列会自动删除
        channel.basicConsume(queueName, true, consumer);


    }
}
