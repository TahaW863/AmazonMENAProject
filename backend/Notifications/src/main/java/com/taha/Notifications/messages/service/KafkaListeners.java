package com.taha.Notifications.messages.service;

import com.taha.Notifications.messages.model.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "chat_message", groupId = "chat_message_consumer_group")
    void listen(Message message) {
        // save it in database
        // send it to a consumer
    }
}
