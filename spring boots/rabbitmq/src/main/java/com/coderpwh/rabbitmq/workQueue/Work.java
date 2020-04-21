package com.coderpwh.rabbitmq.workQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;

/**
 * @description:
 * @author: coderpwh
 * @date: 2020/4/20 0020 11:33
 */
public class Work {

    private  static  final  String TASK_QUEUE_NAME ="task_queue";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.2.103");

        final Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        channel.basicQos(1);

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");

            System.out.println(" [x] Received '" + message + "'");
            try {
                doWork(message);
            } finally {
                System.out.println(" [x] Done");
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });

    }

    private  static  void  doWork(String task){
        for (char ch:task.toCharArray()){
            if(ch=='.'){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                     Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
        }
    }


}
