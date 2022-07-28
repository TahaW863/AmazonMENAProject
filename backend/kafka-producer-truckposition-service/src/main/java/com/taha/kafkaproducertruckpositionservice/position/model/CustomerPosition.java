package com.taha.kafkaproducertruckpositionservice.position.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerPosition {
    private String customerId;
    private Double latitude;
    private Double longitude;
}
