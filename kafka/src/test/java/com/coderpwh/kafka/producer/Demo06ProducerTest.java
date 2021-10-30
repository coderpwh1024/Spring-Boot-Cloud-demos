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
public class Demo06ProducerTest {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private Demo06Producer producer;


    @Test
    public void testSyncSend() throws InterruptedException, ExecutionException {

        for (int i = 0; i < 10; i++) {
            int id = (int) System.currentTimeMillis() / 1000;
            SendResult result = producer.send(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送结果：[{}]]", id, result);
        }


        new CountDownLatch(1).await();
    }


    @Test
    public void testSyncSendOrderly() throws ExecutionException, InterruptedException {

        for (int i = 0; i < 10; i++) {
            int id = 1;
            SendResult result = producer.syncSendOrderly(id);
            logger.info("[testSyncSendOrderly][发送编号:[{}],发送队列:[{}]]", id, result.getRecordMetadata().partition());
        }

        new CountDownLatch(1).await();
    }


}
