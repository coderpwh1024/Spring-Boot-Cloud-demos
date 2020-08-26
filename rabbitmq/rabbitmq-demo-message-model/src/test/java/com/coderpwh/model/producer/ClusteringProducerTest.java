package com.coderpwh.model.producer;


import com.coderpwh.model.RabbitmqDemoMessageModelApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoMessageModelApplication.class)
@Slf4j
public class ClusteringProducerTest {


    @Autowired
    private ClusteringProducer producer;

    @Test
    public void testSyncSend() throws InterruptedException {

        for(int i=0;i<3;i++){
            int id=(int)(System.currentTimeMillis()/1000);
            producer.syncSend(id);
            log.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}