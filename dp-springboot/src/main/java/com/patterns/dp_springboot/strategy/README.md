# Strategy Pattern — Multi-Channel Data Dispatcher

## The Problem

You fetch data from an upstream API. The response includes a `channel` key that tells
you *where* to forward the data — `kafka`, `redis`, or `api`. The naive solution is:

```java
if (channel.equals("kafka")) {
    kafkaTemplate.send(...);
} else if (channel.equals("redis")) {
    redisTemplate.opsForStream().add(...);
} else if (channel.equals("api")) {
    restClient.post()...;
}
```

This breaks the **Open/Closed Principle**: every new destination requires editing
existing code, and the method grows unboundedly. In a real system you might have
10+ destinations and a 100-line if-else block.

## Why Strategy Pattern

The Strategy pattern defines a **family of algorithms** (here: sending strategies),
encapsulates each one, and makes them interchangeable. The caller (`DispatchService`)
doesn't know or care which concrete sender runs — it just calls `send()`.

### How it maps to this problem

| Strategy concept | This codebase |
|-----------------|---------------|
| Strategy interface | `DataSenderStrategy` |
| Concrete strategies | `KafkaDataSender`, `RedisStreamDataSender`, `HttpApiDataSender` |
| Context | `DispatchService` |
| Strategy selector | `DataSenderRegistry` |

### The Spring twist: Registry over Factory

Instead of a factory with if-else, Spring's DI does the wiring for free:

```java
// Spring collects every DataSenderStrategy @Component bean into this list
public DataSenderRegistry(List<DataSenderStrategy> strategies) {
    senders = strategies.stream()
        .collect(Collectors.toMap(DataSenderStrategy::channel, s -> s));
}
```

`DataSenderRegistry` holds a `Map<String, DataSenderStrategy>`. At runtime:

```
"kafka" → KafkaDataSender
"redis" → RedisStreamDataSender
"api"   → HttpApiDataSender
```

`resolve(channel)` does a single map lookup — O(1), no branching.

## Adding a New Destination

Create one class, annotate it. Zero changes to existing code.

```java
@Component
public class SqsDataSender implements DataSenderStrategy {

    @Override
    public String channel() { return "sqs"; }

    @Override
    public void send(DispatchRequest request) {
        // sqsClient.sendMessage(...)
    }
}
```

The registry picks it up automatically on the next startup.

## Package Structure

```
strategy/
  senders/        ← DataSenderStrategy interface + all implementations
  registry/       ← DataSenderRegistry (map-based selector)
  service/        ← DispatchService (context — calls registry + strategy)
  controller/     ← REST endpoint (thin, delegates to service)
  dto/            ← DispatchRequest / DispatchResult records
  exception/      ← UnsupportedChannelException
```

## API

```
POST /api/v1/dispatch
{
  "channel": "kafka",          // "kafka" | "redis" | "api"
  "topic":   "orders.created", // Kafka topic / Redis stream key / HTTP URL
  "payload": { "orderId": 1 }
}

GET /api/v1/dispatch/channels  → ["kafka", "redis", "api"]
```

## When NOT to Use Strategy

- Only 2 channels that will never grow → a simple if-else is fine, don't over-engineer.
- The variations differ in *data shape* not just *behavior* → consider a Visitor.
- You need to compose multiple behaviors together → consider Decorator or Chain of Responsibility.
