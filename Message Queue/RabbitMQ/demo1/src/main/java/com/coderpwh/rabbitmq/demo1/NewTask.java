package com.coderpwh.rabbitmq.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class NewTask {

    private static final  String TASK_QUEUE_NAME="task_queue";

    public static void main(String[] args) throws IOException, TimeoutException {

         // 建立连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        // 指定ip
        factory.setHost("localhost");

        // 建立连接
        Connection connection = factory.newConnection();

        // 建立通道
        Channel channel = connection.createChannel();

       // 声明消息队列(注:此时已经声明持久化了)
        channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);

         // 分发消息
        for (int i=0;i<10;i++){
            String message = "Hello RabbitMQ"+i;
            channel.basicPublish("",TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
            System.out.println("NewTask send '"+message+"'");
        }
        channel.close();
        connection.close();


    }
}
