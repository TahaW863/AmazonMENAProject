package com.taha.webfluxrealtimechat.kafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


public class KafkaTopicConfig {
    @Bean
    public NewTopic ChatMessageTopic() {
        return TopicBuilder.name("chat_message")
                .partitions(3)
                .replicas(1)
                .build();
    }
}
