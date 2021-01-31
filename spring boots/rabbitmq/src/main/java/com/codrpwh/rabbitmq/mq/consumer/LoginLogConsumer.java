package com.codrpwh.rabbitmq.mq.consumer;

import com.codrpwh.rabbitmq.mq.BaseConsumer;
import com.codrpwh.rabbitmq.mq.MessageHelper;
import com.codrpwh.rabbitmq.pojo.LoginLog;
import com.codrpwh.rabbitmq.service.LoginLogService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

@Component
@Slf4j
public class LoginLogConsumer implements BaseConsumer {

    @Resource
    private LoginLogService loginLogService;


    @Override
    public void consume(Message message, Channel channel) throws IOException {

        log.info("登录接收到的消息为:{}", message.toString());

        // 将消息转化成对应的实体类
        LoginLog loginLog = MessageHelper.msgToObj(message, LoginLog.class);
        loginLogService.insert(loginLog);
    }


}
