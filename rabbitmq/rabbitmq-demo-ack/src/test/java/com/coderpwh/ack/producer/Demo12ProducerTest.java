package com.coderpwh.ack.producer;


import com.coderpwh.ack.RabbitmqDemoAckApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoAckApplication.class)
@Slf4j
public class Demo12ProducerTest {

    @Autowired
    private Demo12Producer producer;


    @Test
    public void testSyncSend() throws InterruptedException {
        for (int id = 1; id <= 2; id++) {
            producer.syncSend(id);
            log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        }
        new CountDownLatch(1).await();

    }

}