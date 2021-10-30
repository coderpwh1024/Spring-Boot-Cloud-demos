package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.KafkaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class Demo08ProducerTest {


    public Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private Demo08Producer producer;

    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {

        for (int i = 1; i <= 2; i++) {
            SendResult result = producer.syncSend(i);

            logger.info("[DEMO08--testSyncSend][发送编号:[{}] 发送内容:[{}]]", i, result);
        }

        new CountDownLatch(1).await();

    }

}
