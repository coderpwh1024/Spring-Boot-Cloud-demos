package com.coderpwh.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/***
 * 线程消费任务(伪代码)
 */
@Slf4j
public class ConsumerRunable implements Runnable {


    private final KafkaConsumer<String, String> consumer;

    public ConsumerRunable(String brokerList, String groupId, String topic) {

        Properties properties = new Properties();

        properties.put("bootstrap.servers", brokerList);
        properties.put("group.id", groupId);
        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.interval.ms", "1000");
        properties.put("session.timeout.ms", "30000");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        this.consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList(topic));

    }

    @Override
    public void run() {

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);

            for (ConsumerRecord<String, String> record : records) {

                log.info(Thread.currentThread().getName() + " consumed " + record.partition() + " the message with offset:" + record.offset());
            }

        }
    }


}
