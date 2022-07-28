package com.taha.orderservice.orders.interfaces;

import com.taha.orderservice.orders.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;


public interface OrderRepository extends MongoRepository<Order, String> {
}
