package com.taha.orderservice.orders.model;

import com.taha.orderservice.orders.enums.OrderStatus;
import com.taha.orderservice.products.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

import java.util.List;

@Document(collection = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;
    @NonNull
    private String customerId;
    @NonNull
    private List<Product> products;
    private OrderStatus status;
    private String orderDate;
    private String deliveryDate;
    private String deliveryAddress;
}
