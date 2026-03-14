package com.patterns.dp_springboot.factory.sender;

import com.patterns.dp_springboot.factory.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailNotificationSender implements NotificationSender {

    @Override
    public String channel() {
        return "EMAIL";
    }

    @Override
    public void send(NotificationRequest request) {
        // Real impl: javaMailSender.send(...)
        log.info("[EMAIL] to={} message={}", request.to(), request.message());
    }
}
