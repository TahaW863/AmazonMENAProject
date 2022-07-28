package com.taha.kafkaproducertruckpositionservice;

import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaProducerTranspositionServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(KafkaProducerTranspositionServiceApplication.class, args);
  }
}
