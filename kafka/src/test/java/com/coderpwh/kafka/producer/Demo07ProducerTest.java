package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.KafkaApplication;
import com.coderpwh.kafka.message.Demo07Message;
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
public class Demo07ProducerTest {


    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private Demo07Producer producer;


    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);

        Demo07Message message = new Demo07Message();
        message.setId(id);

        SendResult result = producer.syncSend(id);

        logger.info("[DEMO07-testSyncSend][发送编号:[{}] 发送内容为:[{}]]", id, result);

        new CountDownLatch(1).await();

    }

    @Test
    public void testSyncSendInTransaction() throws ExecutionException, InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);

        producer.syncSendInTransaction(id, new Runnable() {
            @Override
            public void run() {
                logger.info("[run][我要开始睡觉了]");
                try {
                    Thread.sleep(10 * 1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logger.info("[run][我睡醒了]");
            }
        });

        new CountDownLatch(1).await();
    }


}
