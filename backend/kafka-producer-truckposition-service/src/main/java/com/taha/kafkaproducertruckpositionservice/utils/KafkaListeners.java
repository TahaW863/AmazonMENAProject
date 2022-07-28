package com.taha.kafkaproducertruckpositionservice.utils;

import com.taha.kafkaproducertruckpositionservice.position.model.Position;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "truck_position", groupId = "truck-position-consumer")
     void listen(Position message) {
        System.out.println("Received truck position: " + message);
    }
}
