package com.taha.kafkaproducertruckpositionservice.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;


public class KafkaTopicConfig {
    @Bean
    public NewTopic ChatMessageTopic() {
        return TopicBuilder.name("truck_position")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
