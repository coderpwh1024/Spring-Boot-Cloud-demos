package com.coderpwh.rabbitmqdemo.producer;


import com.coderpwh.rabbitmqdemo.message.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

@Component
public class Demo01Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void  syncSend(Integer id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE,Demo01Message.ROUTING_KEY,message);

    }

    public void syncSendDefault(Integer id){
        Demo01Message message = new Demo01Message();
        message.setId(id);
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE,message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id){
        try{
            this.syncSend(id);
            return AsyncResult.forValue(null);
        }catch (Exception e){
            // 返回异常的 Future
            return AsyncResult.forExecutionException(e);
        }
    }





}
