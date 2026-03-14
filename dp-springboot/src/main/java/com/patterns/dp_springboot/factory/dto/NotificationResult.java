package com.patterns.dp_springboot.factory.dto;

public record NotificationResult(
        String channel,
        String to,
        String status
) {}
