package com.coderpwh.orderly.producer;


import com.coderpwh.orderly.RabbitmqDemoOrderlyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoOrderlyApplication.class)
public class Demo10ProducerTest {


    @Autowired
    private Demo10Producer producer;

    @Test
    public void testSyncSend() throws InterruptedException {

        for (int i = 0; i < 2; i++) {
            for (int id = 0; id < 4; id++) {
                producer.syncSend(id);
            }
        }
        new CountDownLatch(1).await();
    }

}