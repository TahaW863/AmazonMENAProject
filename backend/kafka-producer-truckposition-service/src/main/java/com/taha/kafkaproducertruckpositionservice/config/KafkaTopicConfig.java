package com.taha.kafkaproducertruckpositionservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
    @Bean
    public NewTopic truckPositionTopic() {
        return TopicBuilder.name("truck_position")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
