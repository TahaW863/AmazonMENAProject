package com.taha.kafkaproducertruckpositionservice.kafkastream.processor;

import com.taha.kafkaproducertruckpositionservice.position.model.CustomerPosition;
import com.taha.kafkaproducertruckpositionservice.position.model.Position;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KafkaPositionProcessor {
    private final StreamsBuilder streamsBuilder;
    private static final Double KILOMETER_POSITION =3.0;
    public void streamTopology(){
        KStream<String, Position> stream = streamsBuilder.stream("truck-position",
                Consumed.with(
                        Serdes.String(),
                        Serdes.serdeFrom(Position.class)
                ));
        stream.map((k, v)-> KeyValue.pair(v.getTruckId(), v.getCustomerPositions().parallelStream().filter(customerPosition ->
                distance(v.getLatitude(), v.getLongitude(),
                        customerPosition.getLatitude(),
                        customerPosition.getLongitude(), 'K') < KILOMETER_POSITION)))
                .to("customer-position");
    }
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        if (unit == 'K') {
            dist = dist * 1.609344;
        } else if (unit == 'N') {
            dist = dist * 0.8684;
        }
        return (dist);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts decimal degrees to radians             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    /*::  This function converts radians to decimal degrees             :*/
    /*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
