package com.patterns.dp_springboot.observer.event;

public record OrderPlacedEvent(
        String orderId,
        String customerId,
        String product,
        int quantity
) {}
