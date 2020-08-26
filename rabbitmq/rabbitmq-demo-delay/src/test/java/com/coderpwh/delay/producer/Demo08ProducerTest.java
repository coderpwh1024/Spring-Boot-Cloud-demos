package com.coderpwh.delay.producer;

import com.coderpwh.delay.RabbitmqDemoDelayApplication;
import com.coderpwh.delay.message.Demo08Message;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoDelayApplication.class)
@Slf4j
public class Demo08ProducerTest {

    @Autowired
    private Demo08Producer producer;

    @Autowired
    private IMessageProducerService iMessageProducerServicel;


    @Test
    public void testSyncSend01() throws InterruptedException {
        this.testSyncSendDelay(null);
    }


    @Test
    public void testSyncSend02() throws InterruptedException {
        this.testSyncSendDelay(5000);
    }


    public void testSyncSendDelay(Integer delay) throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producer.syncSend(id, delay);
        log.info("[testSyncSendDelay][发送编号：[{}] 发送成功]", id);

        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


    @Test
    public void test() throws InterruptedException {

//        void sendJsonMessage(String exchange, String routingKey, Integer delay, Object object, Map<String, Object> headers);

        Map<String, Object> map = new HashMap<>();
        map.put("搜索引擎", "ElastiSearch");
        map.put("微服务", "SpringCloud");
        map.put("消息中间件", "MQ");
        log.info("[test]----开始发送消息了");
        iMessageProducerServicel.sendJsonMessage(Demo08Message.EXCHANGE, Demo08Message.ROUTING_KEY, 10000, map, null);
        // 阻塞等待，保证消费
        new CountDownLatch(1).await();
    }


}