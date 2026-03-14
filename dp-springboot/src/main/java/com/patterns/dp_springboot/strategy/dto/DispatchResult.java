package com.patterns.dp_springboot.strategy.dto;

public record DispatchResult(
        String channel,
        String topic,
        String status,
        String message
) {}
