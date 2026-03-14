package com.patterns.dp_springboot.singleton.dto;

public record SnowflakeResponse(
        long   id,
        long   workerId,
        long   sequence,
        long   timestampMs,
        String readable
) {}
