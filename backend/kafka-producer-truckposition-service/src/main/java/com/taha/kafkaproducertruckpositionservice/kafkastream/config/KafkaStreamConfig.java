package com.taha.kafkaproducertruckpositionservice.kafkastream.config;

import org.apache.kafka.streams.StreamsConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;

public class KafkaStreamConfig {
    @Bean
    public StreamsConfig kafkaStreamProcessorBranches(KafkaProperties kafkaProperties) {
        return new StreamsConfig(kafkaProperties.buildAdminProperties());
    }
}
