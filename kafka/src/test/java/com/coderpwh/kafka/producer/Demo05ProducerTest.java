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
public class Demo05ProducerTest {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private Demo05Producer producer;


    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);

        SendResult result = producer.syncSend(id);

        logger.info("[Demo5-testSyncSend][发送编号为:[{}]发送结果为:[{}] ]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();

    }


}
