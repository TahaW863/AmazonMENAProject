package com.taha.kafkaproducertruckpositionservice.position.model;

import lombok.*;
import nonapi.io.github.classgraph.json.Id;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Position implements Serializable {
    @Id
    private String id;
    private String truckId;
    private Double latitude;
    private Double longitude;
    private List<CustomerPosition> customerPositions;
    private LocalDateTime timestamp;
}
