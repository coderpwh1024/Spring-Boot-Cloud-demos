package com.coderpwh.batch.producer;


import com.coderpwh.batch.RabbitmqDemoBatchApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoBatchApplication.class)
@Slf4j
public class Demo05ProducerTest {

    @Autowired
    private Demo05Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.syncSend(id);

            // 故意每条消息之间，隔离 10 秒
            log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(10 * 1000L);
        }
        new CountDownLatch(1).await();
    }


}