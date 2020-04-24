package com.coderpwh.rabbitmq.producer;

import com.coderpwh.rabbitmq.message.Demo01Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/24 0024 9:30
 */
@Component
public class Demo01Producer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void syncSend(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);

        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE, Demo01Message.ROUTING_KEY, message);
    }


    public void syncSendDefault(Integer id) {
        Demo01Message message = new Demo01Message();
        message.setId(id);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE, message);
    }

    @Async
    public ListenableFuture<Void> asyncSend(Integer id) {
        try {
            this.syncSend(id);
            // 返回成功
            return AsyncResult.forValue(null);
        } catch (Throwable ex) {
            // 返回异常的 Future
            return AsyncResult.forExecutionException(ex);
        }

    }


}
