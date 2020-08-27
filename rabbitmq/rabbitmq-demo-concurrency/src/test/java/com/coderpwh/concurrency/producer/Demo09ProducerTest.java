package com.coderpwh.concurrency.producer;

import com.coderpwh.concurrency.RabbitmqDemoConcurrencyApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.synth.SynthInternalFrameUI;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoConcurrencyApplication.class)
@Slf4j
public class Demo09ProducerTest {


    @Autowired
    private Demo09Producer producer;


    @Test
    public void testSyncSend() throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.syncSend(id);
        }
        new CountDownLatch(1).await();


    }

}