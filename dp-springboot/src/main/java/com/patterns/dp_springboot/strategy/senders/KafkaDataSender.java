package com.patterns.dp_springboot.strategy.senders;

import com.patterns.dp_springboot.strategy.dto.DispatchRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaDataSender implements DataSenderStrategy {

    @Override
    public String channel() {
        return "kafka";
    }

    @Override
    public void send(DispatchRequest request) {
        // Real impl: kafkaTemplate.send(request.topic(), request.payload())
        log.info("[KAFKA] topic={} payload={}", request.topic(), request.payload());
    }
}
