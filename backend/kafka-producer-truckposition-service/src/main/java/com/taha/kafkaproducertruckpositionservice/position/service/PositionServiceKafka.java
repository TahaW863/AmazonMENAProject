package com.taha.kafkaproducertruckpositionservice.position.service;

import com.taha.kafkaproducertruckpositionservice.position.interfaces.PositionService;
import com.taha.kafkaproducertruckpositionservice.position.model.Position;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PositionServiceKafka implements PositionService {
    private final KafkaTemplate<String, Position> kafkaTemplate;
    @Override
    public void sendPosition(Position position) {
        log.info("Sending position to kafka: {}", position);
        kafkaTemplate.send("truck_position", position);
    }
}
