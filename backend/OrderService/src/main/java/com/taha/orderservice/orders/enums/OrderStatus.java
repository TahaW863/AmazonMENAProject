package com.taha.orderservice.orders.enums;

public enum OrderStatus {
    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    CANCELLED("CANCELLED");
    String message;
    OrderStatus(String message) {
        this.message = message;
    }
}
