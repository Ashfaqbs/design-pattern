package com.patterns.dp_springboot.factory.sender;

import com.patterns.dp_springboot.factory.dto.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SmsNotificationSender implements NotificationSender {

    @Override
    public String channel() {
        return "SMS";
    }

    @Override
    public void send(NotificationRequest request) {
        // Real impl: twilioClient.messages().create(...)
        log.info("[SMS] to={} message={}", request.to(), request.message());
    }
}
