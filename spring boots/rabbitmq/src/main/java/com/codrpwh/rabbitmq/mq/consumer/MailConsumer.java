package com.codrpwh.rabbitmq.mq.consumer;

import com.codrpwh.rabbitmq.exception.ServiceException;
import com.codrpwh.rabbitmq.mq.BaseConsumer;
import com.codrpwh.rabbitmq.mq.MessageHelper;
import com.codrpwh.rabbitmq.pojo.Mail;
import com.codrpwh.rabbitmq.util.MailUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class MailConsumer implements BaseConsumer {

    @Autowired
    private MailUtil mailUtil;


    @Override
    public void consume(Message message, Channel channel) throws IOException {

        Mail mail = MessageHelper.msgToObj(message, Mail.class);

        log.info("收到消息:{}", mail.toString());

        boolean succss = mailUtil.send(mail);

        if (!succss) {
            throw new ServiceException("send mail error");
        }

    }


}
