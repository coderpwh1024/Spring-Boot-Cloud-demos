package com.coderpwh.kafka.producer;

import com.coderpwh.kafka.KafkaApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KafkaApplication.class)
public class Demo01ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo01Producer producer;


    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);

        SendResult result = producer.syncSend(id);

        logger.info("[testSyncSenc][发送编号:[{}] 发送结果:[{}]]", id, result);

        new CountDownLatch(1).await();
    }

    @Test
    public void testASyncSend() throws InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);

        producer.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                logger.info("[testASyncSend][发送编号:[{}] 发送异常]", id, e);
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
                logger.info("[testASyncSend][发送编号:[{}] 发送成功,结果为:[{}]]", id, result);
            }
        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();

    }


    @Test
    public void testSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);

        producer.send(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable e) {
                logger.info("[testASyncSend][发送编号:[{}] 发送异常]", id, e);
            }

            @Override
            public void onSuccess(SendResult<Object, Object> result) {
//                logger.info("[testASyncSend][发送编号:[{}] 发送成功,结果为:[{}]]", id, result);
                logger.info("[testSned][发送编号:[{}]]，发送成功，对应的topic为:[{}],分区为:[{}],分区偏移量为:[{}]",id,result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),result.getRecordMetadata().offset());

            }
        });

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}