package com.patterns.dp_springboot.observer.listener;

import com.patterns.dp_springboot.observer.event.OrderDeletedEvent;
import com.patterns.dp_springboot.observer.event.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
public class AuditListener {

    // TransactionalEventListener — fires only AFTER the transaction commits.
    // Use when the side effect must not run if the order creation rolls back.
    // e.g. writing to an audit log, sending to a message broker.
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderPlaced(OrderPlacedEvent event) {
        // Real impl: auditRepository.save(new AuditEntry(event))
        log.info("[AUDIT] Order {} placed by customer {} — persisted to audit log", event.orderId(), event.customerId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderDeleted(OrderDeletedEvent event) {
        // Real impl: auditRepository.save(new AuditEntry(event))
        log.info("[AUDIT] Order {} deleted by {} — persisted to audit log", event.orderId(), event.deletedBy());
    }
}
