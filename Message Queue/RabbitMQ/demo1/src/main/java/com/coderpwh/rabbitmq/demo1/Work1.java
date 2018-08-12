package com.coderpwh.rabbitmq.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Work1 {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

        // 建立连接工厂
        final ConnectionFactory factory = new ConnectionFactory();

        // 指定ip
        factory.setHost("localhost");

        // 建立连接
        Connection connection = factory.newConnection();

        // 通道
        final Channel channel = connection.createChannel();

        // 声明队列(注:队列是持久化的)
        channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
        System.out.println("Worker1  Waiting for messages");
        //每次从队列获取的数量
        channel.basicQos(1);

        final Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Worker1  Received '" + message + "'");

                try {
//                     doWork(message);
                } catch (Exception E) {
                    channel.abort();
                } finally {
                    System.out.println("Worker1 Done");
                    channel.basicAck(envelope.getDeliveryTag(), false);
                }

            }
        };
        boolean autoAck = false;
        // 消息消费完成确认
        channel.basicConsume(TASK_QUEUE_NAME, autoAck, consumer);
    }

    private static void doWork(String task) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
