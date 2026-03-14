package com.patterns.dp_springboot.singleton.generator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Singleton Snowflake ID Generator.
 *
 * 64-bit ID layout:
 *  [ 1 bit sign | 41 bits timestamp | 10 bits workerId | 12 bits sequence ]
 *
 * State that MUST be shared across all callers:
 *  - lastTimestamp: prevents going back in time
 *  - sequence:      prevents duplicate IDs within the same millisecond
 *
 * Two instances = independent lastTimestamp + sequence = duplicate IDs.
 */
@Component
public class SnowflakeIdGenerator {

    private static final long EPOCH          = 1700000000000L; // 2023-11-15 as custom epoch
    private static final long WORKER_BITS    = 10L;
    private static final long SEQUENCE_BITS  = 12L;

    private static final long MAX_WORKER     = ~(-1L << WORKER_BITS);   // 1023
    private static final long MAX_SEQUENCE   = ~(-1L << SEQUENCE_BITS); // 4095

    private static final long WORKER_SHIFT    = SEQUENCE_BITS;                   // 12
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_BITS;     // 22

    private final long workerId;
    private long lastTimestamp = -1L;
    private long sequence      = 0L;

    public SnowflakeIdGenerator(@Value("${snowflake.worker-id:1}") long workerId) {
        if (workerId < 0 || workerId > MAX_WORKER) {
            throw new IllegalArgumentException("workerId must be between 0 and " + MAX_WORKER);
        }
        this.workerId = workerId;
    }

    public synchronized long nextId() {
        long now = System.currentTimeMillis();

        if (now < lastTimestamp) {
            throw new IllegalStateException(
                    "Clock moved backwards by " + (lastTimestamp - now) + "ms. Refusing to generate id.");
        }

        if (now == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                now = waitNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = now;

        return ((now - EPOCH)  << TIMESTAMP_SHIFT)
                | (workerId    << WORKER_SHIFT)
                | sequence;
    }

    private long waitNextMillis(long lastTs) {
        long now = System.currentTimeMillis();
        while (now <= lastTs) {
            now = System.currentTimeMillis();
        }
        return now;
    }
}
