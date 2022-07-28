package com.taha.orderservice.orders.services;

import com.taha.orderservice.orders.interfaces.OrderRepository;
import com.taha.orderservice.orders.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public Optional<Order> getOrder(String id) {
        log.info("Getting order with id: {}", id);
        return orderRepository.findById(id);
    }
    public Optional<Page<Order>> getOrders(int page, int size) {
        log.info("Getting orders with page: {} and size: {}", page, size);
        return Optional.of(orderRepository.findAll(PageRequest.of(page, size)));
    }
    public void saveOrder(Order order) {
        log.info("Saving order: {}", order);
        orderRepository.save(order);
    }
    public void deleteOrder(String id) {
        log.info("Deleting order with id: {}", id);
        orderRepository.deleteById(id);
    }
    public void saveOrders(List<Order> orders){
        log.info("Saving orders: {}", orders);
        orderRepository.saveAll(orders);
    }
}
