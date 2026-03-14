# Observer Pattern — Order Placement Events

## The Problem

An order is placed. Multiple things need to happen: reserve inventory, send a
confirmation email, write an audit log. The naive approach puts all of this in
`OrderService`:

```java
public OrderResponse place(OrderRequest request) {
    orderRepository.save(order);
    inventoryService.reserve(...);   // coupled
    mailSender.send(...);            // coupled
    auditRepository.save(...);       // coupled
}
```

`OrderService` now knows about inventory, email, and audit. Every new side effect
bloats it further. Tests become harder because you must mock every dependency.

## Why Observer

Observer defines a one-to-many dependency: when one object changes state, all
dependents are notified automatically. The publisher knows nothing about its listeners.

Spring implements this natively via `ApplicationEventPublisher` / `@EventListener`.

```
OrderService.place()
    → eventPublisher.publishEvent(new OrderPlacedEvent(...))
          ↓                  ↓                    ↓
  InventoryListener    EmailListener        AuditListener
  (synchronous)        (@Async)             (@TransactionalEventListener)
```

`OrderService` publishes one event and is done. It has zero imports from inventory,
email, or audit packages.

## Three Listener Modes — Pick the Right One

| Annotation | Thread | Timing | Use when |
|-----------|--------|--------|----------|
| `@EventListener` | Same as publisher | Within transaction | Side effect must roll back with the main operation |
| `@Async @EventListener` | New thread (executor) | Within transaction | Slow I/O (SMTP, HTTP) that should not block the response |
| `@TransactionalEventListener(AFTER_COMMIT)` | Same as publisher | After commit | Side effect must NOT run if transaction rolls back (audit, broker publish) |

### The `@TransactionalEventListener` trap

If you use plain `@EventListener` for audit logging and the transaction rolls back,
your audit entry still gets written — you've logged an order that doesn't exist.
`AFTER_COMMIT` prevents this.

## Adding a New Reaction

One class, one annotation. Zero changes to `OrderService` or any existing listener.

```java
@Component
public class LoyaltyPointsListener {

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onOrderPlaced(OrderPlacedEvent event) {
        // loyaltyService.credit(event.customerId(), event.quantity())
    }
}
```

## Package Structure

```
observer/
  event/        ← OrderPlacedEvent (plain record — no need to extend ApplicationEvent)
  listener/     ← InventoryListener / EmailListener / AuditListener
  service/      ← OrderService (publisher — only imports ApplicationEventPublisher)
  controller/   ← REST endpoint
  dto/          ← OrderRequest / OrderResponse
```

## API

```
POST /api/v1/orders
{
  "customerId": "user-123",
  "product":    "Laptop",
  "quantity":   1
}
```
