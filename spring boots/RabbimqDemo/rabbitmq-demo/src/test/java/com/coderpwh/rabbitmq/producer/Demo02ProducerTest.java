package com.coderpwh.rabbitmq.producer;

import com.coderpwh.rabbitmq.Application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: pengwenhao
 * @date: 2020/4/24 0024 15:20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class Demo02ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo02Producer producer;

    @Test
    public void testSyncSendSuccess() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, "coderpwh");
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        new CountDownLatch(1).await();
    }


    @Test
    public void testSyncSendFailure() throws InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, "coderpwh");
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        new CountDownLatch(1).await();

    }


}