package com.taha.kafkaproducertruckpositionservice.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.Objects;


public class KafkaListeners {

    @KafkaListener(topics = "chat_message", groupId = "chat_message_consumer_group")
    void listen(Object hotMessage) {
        // save it in database
        // send it to a consumer
    }
}
