package com.coderpwh.kafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;

/***
 *   构建原始的Kafka consumer  
 *
 *
 */
@Slf4j
public class ConsumerTest {

    public static void main(String[] args) {

        String topicName = "test-topic";

        String groupID = "test-group";

        Properties properties = new Properties();

        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("group.id", groupID);
        properties.put("enable.auto.commit", true);
        properties.put("auto.commit.interval.ms", "1000");
        // 从最早的消息开始读取
        properties.put("auto.offset.reset", "earliest");

        // key 进行序列化
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        // value 序列化
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 订阅对应的topic主题
        consumer.subscribe(Arrays.asList(topicName));

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(1000);

                for (ConsumerRecord<String, String> record : records) {

                    log.info("offset:[{}],key=[{}],value=[{}]", record.offset(), record.key(), record.value());
                }

            }

        } catch (Exception e) {
            log.error("异常消息为:{}", e.getMessage());
        } finally {
            consumer.close();
        }


    }
}
