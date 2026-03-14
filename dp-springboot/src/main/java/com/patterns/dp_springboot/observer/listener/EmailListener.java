package com.patterns.dp_springboot.observer.listener;

import com.patterns.dp_springboot.observer.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailListener {

    // Async — runs in a separate thread from the task executor.
    // Use when the operation is slow (SMTP call) and should not block the HTTP response.
    @Async
    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        // Real impl: mailSender.send(confirmationEmail(event))
        log.info("[EMAIL] Sending confirmation to customer {} for order {}", event.customerId(), event.orderId());
    }
}
