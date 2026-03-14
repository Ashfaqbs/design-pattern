package com.patterns.dp_springboot.strategy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record DispatchRequest(
        @NotBlank String channel,
        @NotBlank String topic,
        @NotNull  Map<String, Object> payload
) {}
