package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.KafkaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class Demo02ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private Demo02Producer producer;


    @Test
    public void testASyncSend() throws InterruptedException {
        logger.info("[testASyncSend][开始执行]");

        for (int i = 0; i < 3; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);

            producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    logger.info("[testASyncSend][发送编号:[{}]发送异常]", id, throwable);
                }

                @Override
                public void onSuccess(SendResult<Object, Object> result) {
                    logger.info("[testASyncSend][发送编号：[{}] 发送成功，结果为：[{}]]", id, result);
                }
            });

            Thread.sleep(10 * 1000L);

        }
        new CountDownLatch(1).await();

    }

}
