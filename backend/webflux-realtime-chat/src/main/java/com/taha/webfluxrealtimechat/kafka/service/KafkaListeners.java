package com.taha.webfluxrealtimechat.kafka.service;

import com.taha.webfluxrealtimechat.messages.model.HotMessage;
import org.springframework.kafka.annotation.KafkaListener;


public class KafkaListeners {

    @KafkaListener(topics = "chat_message", groupId = "chat_message_consumer_group")
    void listen(HotMessage hotMessage) {
        // save it in database
        // send it to a consumer
    }
}
