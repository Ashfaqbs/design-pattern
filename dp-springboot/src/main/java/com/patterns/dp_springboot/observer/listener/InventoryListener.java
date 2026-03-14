package com.patterns.dp_springboot.observer.listener;

import com.patterns.dp_springboot.observer.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InventoryListener {

    // Synchronous — runs in the same thread as the publisher, within the same transaction.
    // Use when the operation must succeed or fail together with order creation.
    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {
        // Real impl: inventoryService.reserve(event.product(), event.quantity())
        log.info("[INVENTORY] Reserving {} x {} for order {}", event.quantity(), event.product(), event.orderId());
    }
}
