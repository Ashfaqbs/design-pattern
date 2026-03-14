# Singleton Pattern — Snowflake ID Generator

## The Problem

You need globally unique, sortable IDs across your system — without a DB
auto-increment (too slow) and without UUID (too long, not sortable).

Twitter's Snowflake algorithm generates 64-bit IDs that are:
- Unique across nodes (worker ID embedded in the ID)
- Time-sortable (timestamp is the most significant bits)
- Fast (no DB round-trip, no coordination needed)

## Why Singleton Is Critical Here

The generator holds two stateful fields:

```
lastTimestamp  — the last millisecond an ID was generated
sequence       — counter within that millisecond (0–4095)
```

**What happens with two instances:**

```
Thread A → Instance 1: timestamp=1000, sequence=0  → ID: ...1000...0
Thread B → Instance 2: timestamp=1000, sequence=0  → ID: ...1000...0  ← DUPLICATE
```

Both instances start with `lastTimestamp=-1` and `sequence=0`. If they generate
an ID in the same millisecond, they produce identical IDs. One instance = one
shared state = guaranteed uniqueness.

## 64-bit ID Layout

```
 63        22 21      12 11       0
  |--41 bits--|--10 bits--|--12 bits--|
   timestamp   worker ID   sequence
```

| Segment | Bits | Max value | Meaning |
|---------|------|-----------|---------|
| Timestamp | 41 | ~69 years | ms since custom epoch |
| Worker ID | 10 | 1023 | unique per node in cluster |
| Sequence | 12 | 4095 | IDs per millisecond per node |

**Max throughput: 4096 IDs/ms per node = ~4 million IDs/second.**

## Thread Safety

`nextId()` is `synchronized` — only one thread generates an ID at a time.
This is safe because the method is nanosecond-fast. Lock contention is negligible.

## Clock Drift

If the system clock moves backwards (NTP sync), the generator throws rather than
produce an ID with a timestamp in the past — which would break sort order.

```java
if (now < lastTimestamp) {
    throw new IllegalStateException("Clock moved backwards...");
}
```

## What Breaks If You Use Prototype Scope

```java
@Scope("prototype")  // DO NOT DO THIS
@Component
public class SnowflakeIdGenerator { ... }
```

Every bean that injects `SnowflakeIdGenerator` gets its own instance.
Each has its own `lastTimestamp` and `sequence`. Duplicates are guaranteed
under any concurrent load.

## Cluster Setup

In a multi-node deployment, each instance gets a unique `worker-id` via config:

```yaml
# node-1
snowflake.worker-id=1

# node-2
snowflake.worker-id=2
```

In Kubernetes, derive it from the pod ordinal:
`snowflake.worker-id=${POD_ORDINAL:0}`

## Limitations at Scale — What Actually Breaks

The Singleton solves the **within-JVM** problem. The moment you have multiple nodes,
the hard problem shifts to **worker ID coordination** — and singleton can't help there.

```
Node 1 (worker-id=1) → fine
Node 2 (worker-id=1) → same worker-id = duplicates regardless of singleton
```

Manually assigning worker IDs via config works for small fixed clusters, but breaks
when nodes autoscale (Kubernetes HPA spins up pod-5, what's its worker-id?).

### What real systems do at scale

| Approach | How it works | Trade-off |
|----------|-------------|-----------|
| **Redis `INCR`** | Each node atomically claims a worker-id from Redis on startup | Redis becomes a dependency |
| **DB sequence** | `SELECT nextval('worker_id_seq')`, claimed once at boot | DB round-trip at startup only |
| **Zookeeper / etcd** | Distributed lock to claim a worker-id slot | Heavy infra for just an ID |
| **ULID** | Sortable + random, no coordination needed at all | Slightly longer than Snowflake |
| **DB-native sequence** | `SELECT nextval('order_seq')` per ID | DB round-trip per ID, but simple |

### Recommended approach for most systems

If you're already using Redis → claim worker-id via `INCR` at startup, release on shutdown.

```java
// on startup
long workerId = redisTemplate.opsForValue().increment("snowflake:worker-id-counter") % 1024;

// wire into SnowflakeIdGenerator
```

If you don't want the coordination complexity at all → switch to **ULID**.
Same properties (sortable, unique) without needing a worker-id.

### The honest takeaway

Singleton pattern is valid here — shared mutable state inside one JVM must be
one instance. But the pattern only covers half the problem. In distributed systems,
you still need an external coordination mechanism for worker-id assignment.

## API

```
GET /api/v1/ids
→ { "id": 389530841907200, "workerId": 1, "sequence": 0, "timestampMs": ..., "readable": "2026-03-14T..." }

GET /api/v1/ids/batch?count=10
→ [ { ... }, { ... }, ... ]
```
