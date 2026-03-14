package com.patterns.dp_springboot.factory.service;

import com.patterns.dp_springboot.factory.dto.NotificationRequest;
import com.patterns.dp_springboot.factory.dto.NotificationResult;
import com.patterns.dp_springboot.factory.factory.NotificationSenderFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationSenderFactory factory;

    public NotificationResult send(NotificationRequest request) {
        factory.create(request.channel()).send(request);
        return new NotificationResult(request.channel(), request.to(), "SENT");
    }
}
