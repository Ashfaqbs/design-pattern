package com.patterns.dp_springboot.factory.sender;

import com.patterns.dp_springboot.factory.dto.NotificationRequest;

public interface NotificationSender {

    String channel();

    void send(NotificationRequest request);
}
