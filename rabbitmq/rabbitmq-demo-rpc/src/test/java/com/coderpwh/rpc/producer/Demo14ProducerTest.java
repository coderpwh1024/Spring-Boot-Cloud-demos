package com.coderpwh.rpc.producer;


import com.coderpwh.rpc.RabbitmqDemoRpcApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoRpcApplication.class)
@Slf4j
public class Demo14ProducerTest {


    @Autowired
    private Demo14Producer producer;


    @Test
    public void testSyncSend() throws InterruptedException {

         int id =(int)(System.currentTimeMillis()/1000);
         String result = producer.syncSend(id);

        log.info("[testSyncSend][发送编号：[{}] 发送成功 消费结果：[{}]]", id, result);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}