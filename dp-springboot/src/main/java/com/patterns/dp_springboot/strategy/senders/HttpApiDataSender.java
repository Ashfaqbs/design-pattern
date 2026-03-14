package com.patterns.dp_springboot.strategy.senders;

import com.patterns.dp_springboot.strategy.dto.DispatchRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpApiDataSender implements DataSenderStrategy {

    private final RestClient restClient;

    @Override
    public String channel() {
        return "api";
    }

    @Override
    public void send(DispatchRequest request) {
        // topic is treated as the target URL for HTTP channel
        restClient.post()
                .uri(request.topic())
                .body(request.payload())
                .retrieve()
                .toBodilessEntity();

        log.info("[HTTP API] POST url={} payload={}", request.topic(), request.payload());
    }
}
