package com.codrpwh.rabbitmq.mq.listener;

import com.codrpwh.rabbitmq.config.RabbitConfig;
import com.codrpwh.rabbitmq.mq.BaseConsumer;
import com.codrpwh.rabbitmq.mq.BaseConsumerProxy;
import com.codrpwh.rabbitmq.mq.consumer.LoginLogConsumer;
import com.codrpwh.rabbitmq.service.MsgLogService;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author coderpwh
 */
@Component
public class LoginLogListener {


    @Resource
    private LoginLogConsumer loginLogConsumer;

    @Resource
    private MsgLogService msgLogService;

    @RabbitListener(queues = RabbitConfig.LOGIN_LOG_QUEUE_NAME)
    public void consume(Message message, Channel channel) throws Exception {

        BaseConsumerProxy baseConsumerProxy = new BaseConsumerProxy(loginLogConsumer, msgLogService);
        BaseConsumer proxy = (BaseConsumer) baseConsumerProxy.getProxy();
        if (proxy != null) {
            proxy.consume(message, channel);
        }

    }


}
