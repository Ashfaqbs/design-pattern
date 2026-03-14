package com.patterns.dp_springboot.observer.event;

public record OrderDeletedEvent(
        String orderId,
        String deletedBy
) {}
