package com.patterns.dp_springboot.observer.dto;

public record OrderResponse(
        String orderId,
        String customerId,
        String product,
        int quantity,
        String status
) {}
