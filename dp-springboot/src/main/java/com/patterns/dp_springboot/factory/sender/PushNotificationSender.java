package com.patterns.dp_springboot.factory.sender;

import com.patterns.dp_springboot.factory.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PushNotificationSender implements NotificationSender {

    @Override
    public String channel() {
        return "PUSH";
    }

    @Override
    public void send(NotificationRequest request) {
        // Real impl: firebaseMessaging.send(...)
        log.info("[PUSH] to={} message={}", request.to(), request.message());
    }
}
