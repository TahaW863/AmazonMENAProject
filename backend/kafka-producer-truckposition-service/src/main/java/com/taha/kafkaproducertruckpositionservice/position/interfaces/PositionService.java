package com.taha.kafkaproducertruckpositionservice.position.interfaces;

import com.taha.kafkaproducertruckpositionservice.position.model.Position;

public interface PositionService {
    void sendPosition(Position position);
}
