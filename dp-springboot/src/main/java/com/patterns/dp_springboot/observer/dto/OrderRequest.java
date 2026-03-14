package com.patterns.dp_springboot.observer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public record OrderRequest(
        @NotBlank String customerId,
        @NotBlank String product,
        @Positive int quantity
) {}
