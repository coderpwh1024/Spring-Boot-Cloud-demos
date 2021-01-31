package com.codrpwh.rabbitmq.mq.listener;

import com.codrpwh.rabbitmq.config.RabbitConfig;
import com.codrpwh.rabbitmq.mq.BaseConsumer;
import com.codrpwh.rabbitmq.mq.BaseConsumerProxy;
import com.codrpwh.rabbitmq.mq.consumer.MailConsumer;
import com.codrpwh.rabbitmq.service.MsgLogService;
import com.rabbitmq.client.Channel;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

import java.io.IOException;


@Component
public class MailListener {


    @Autowired
    private MailConsumer mailConsumer;

    @Autowired
    private MsgLogService msgLogService;


    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE_NAME)
    public void Consume(Message message, Channel channel) throws IOException {

        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(mailConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();

        if (proxy != null) {
            proxy.consume(message, channel);
        }
    }


}
