package com.coderpwh.kafka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.*;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;


/**
 * @author coderpwh
 */
@Configuration
public class KafkaConfiguration {


    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> template) {

        ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(template);

        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);

        return new SeekToCurrentErrorHandler(recoverer, backOff);

    }


}
