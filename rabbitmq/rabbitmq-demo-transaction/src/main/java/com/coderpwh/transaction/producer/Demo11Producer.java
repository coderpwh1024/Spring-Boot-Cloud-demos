package com.coderpwh.transaction.producer;

import com.coderpwh.transaction.message.Demo11Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
public class Demo11Producer {


    @Autowired
    private RabbitTemplate rabbitTemplate;


    @Transactional
    public void syncSend(Integer id) throws InterruptedException {
        Demo11Message message = new Demo11Message();
        message.setId(id);

        rabbitTemplate.convertAndSend(Demo11Message.EXCHANGE, Demo11Message.ROUTING_KEY, message);
        log.info("[syncSend][发送编号：[{}] 发送成功]", id);

        // 等待
        Thread.sleep(10 * 1000L);

    }


}
