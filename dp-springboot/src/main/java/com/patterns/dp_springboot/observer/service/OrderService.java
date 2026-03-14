package com.patterns.dp_springboot.observer.service;

import com.patterns.dp_springboot.observer.dto.OrderRequest;
import com.patterns.dp_springboot.observer.dto.OrderResponse;
import com.patterns.dp_springboot.observer.event.OrderDeletedEvent;
import com.patterns.dp_springboot.observer.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public OrderResponse place(OrderRequest request) {
        String orderId = UUID.randomUUID().toString();

        // Real impl: orderRepository.save(order)

        // Publish once — all listeners react independently, no coupling between them
        eventPublisher.publishEvent(new OrderPlacedEvent(orderId, request.customerId(), request.product(), request.quantity()));

        return new OrderResponse(orderId, request.customerId(), request.product(), request.quantity(), "PLACED");
    }

    @Transactional
    public void delete(String orderId, String deletedBy) {
        // Real impl: orderRepository.deleteById(orderId)
        eventPublisher.publishEvent(new OrderDeletedEvent(orderId, deletedBy));
    }
}
