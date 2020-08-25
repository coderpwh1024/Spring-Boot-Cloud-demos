package com.coderpwh.rabbitmqdemo.producer;


import com.coderpwh.rabbitmqdemo.RabbitmqDemoApplication;
import com.coderpwh.rabbitmqdemo.message.Demo01Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoApplication.class)
public class Demo01ProducerTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Demo01Producer producer;

    @Resource
    private IMessageProducerService iMessageProducerService;


    @Test
    public void testSyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id);
        logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void tesSyncSendDefault() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSendDefault(id);
        logger.info("[tesSyncSendDefault][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }

    @Test
    public void testAsyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);

        producer.asyncSend(id).addCallback(new ListenableFutureCallback<Void>() {

            @Override
            public void onSuccess(Void e) {
                logger.info("[testASyncSend][发送编号：[{}] 发送异常]]", id, e);
            }

            @Override
            public void onFailure(Throwable throwable) {
                logger.info("[testASyncSend][发送编号：[{}] 发送成功，发送成功]", id);
            }
        });
        logger.info("[testASyncSend][发送编号：[{}] 调用完成]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();

    }

    @Test
    public void nothing() throws InterruptedException {
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


    @Test
    public void nothing02() throws InterruptedException {

        for (int i = 0; i < 1000; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            producer.syncSend(id);
            logger.info("[testSyncSend][发送编号：[{}] 发送成功]", id);
            Thread.sleep(5000L);
        }
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


    @Test
    public void test() {
        Map<String,Object> map = new HashMap<>();
        map.put("搜索引擎","ES");
        map.put("消息中间件","MQ");
        iMessageProducerService.sendJsonMessage( Demo01Message.EXCHANGE,Demo01Message.ROUTING_KEY,60,map,null);

    }


}