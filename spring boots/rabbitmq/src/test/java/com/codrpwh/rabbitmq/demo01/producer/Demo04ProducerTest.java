package com.codrpwh.rabbitmq.demo01.producer;

import com.codrpwh.rabbitmq.RabbitmqApplication;
import com.codrpwh.rabbitmq.demo01.message.Demo04Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqApplication.class)
@Slf4j
public class Demo04ProducerTest {


    @Autowired
    private Demo04Producer producer;


    @Test
    public void testSyncSendSuccess() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);

        producer.syncSend(id, Demo04Message.HEADER_VALUE);
        log.info("[testSyncSend][发送编号:[{}] 发送成功]", id);

        new CountDownLatch(1).await();
    }

    @Test
    public void testSyncSendFailure() throws InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);

        producer.syncSend(id, "error");

        log.info("[testSyncSend][发送编号:[{}] 发送成功]", id);

        new CountDownLatch(1).wait();

    }


}
