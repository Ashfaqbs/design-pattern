package com.patterns.dp_springboot.factory.dto;

import jakarta.validation.constraints.NotBlank;

public record NotificationRequest(
        @NotBlank String channel,
        @NotBlank String to,
        @NotBlank String message
) {}
