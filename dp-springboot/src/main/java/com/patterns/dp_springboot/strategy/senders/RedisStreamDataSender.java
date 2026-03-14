package com.patterns.dp_springboot.strategy.senders;

import com.patterns.dp_springboot.strategy.dto.DispatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisStreamDataSender implements DataSenderStrategy {

    @Override
    public String channel() {
        return "redis";
    }

    @Override
    public void send(DispatchRequest request) {
        // Real impl: redisTemplate.opsForStream().add(request.topic(), request.payload())
        log.info("[REDIS STREAM] stream={} payload={}", request.topic(), request.payload());
    }
}
