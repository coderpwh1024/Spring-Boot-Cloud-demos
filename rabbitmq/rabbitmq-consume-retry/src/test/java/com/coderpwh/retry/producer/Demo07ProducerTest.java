package com.coderpwh.retry.producer;


import com.coderpwh.retry.RabbitmqConsumeRetryApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqConsumeRetryApplication.class)
@Slf4j
public class Demo07ProducerTest {

    @Autowired
    private Demo07Producer producer;


    @Test
    public void testSyncSend() throws InterruptedException {

        int id =(int)(System.currentTimeMillis()/1000);
        producer.syncSend(id);
        log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}