package com.coderpwh.other.consumer;


import com.coderpwh.other.RabbitmqDemoOtherApplication;
import com.coderpwh.other.producer.DelayedSender;
import com.coderpwh.other.producer.Sender;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoOtherApplication.class)
@Slf4j
public class ReceiverTest {

    @Autowired
    private Sender sender;

    @Autowired
    private DelayedSender delayedSender;

    @Test
    public void contextLoads() throws InterruptedException {
        sender.send();

        new CountDownLatch(1).await();
    }


    @Test
    public void delayedSender() throws InterruptedException {
        log.info("[延迟消息]开始发送了");
        delayedSender.delayedMessage();
        new CountDownLatch(1).await();
    }


}