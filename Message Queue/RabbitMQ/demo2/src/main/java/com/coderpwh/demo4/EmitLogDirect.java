package com.coderpwh.demo4;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author coderpwh
 * @version V1.0
 * @date 2018/9/4 15:04
 */
public class EmitLogDirect {

     private  static final  String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory  factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection  connection  = factory.newConnection();
        Channel channel =  connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String severity =getSeverity(args);
        String message = getMessage(args);

        channel.basicPublish(EXCHANGE_NAME,severity,null,message.getBytes("UTF-8"));
        System.out.println(" [x] Sent '"+severity+"':'"+message+"'");

        channel.close();
        connection.close();

    }

    private static  String getSeverity(String[] strings){

        if(strings.length<1){
            return  "info";
        }
        return  strings[0];
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
