package com.patterns.dp_springboot.singleton.service;

import com.patterns.dp_springboot.singleton.dto.SnowflakeResponse;
import com.patterns.dp_springboot.singleton.generator.SnowflakeIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class SnowflakeService {

    private static final long EPOCH           = 1700000000000L;
    private static final long SEQUENCE_BITS   = 12L;
    private static final long WORKER_BITS     = 10L;
    private static final long SEQUENCE_MASK   = ~(-1L << SEQUENCE_BITS);
    private static final long WORKER_MASK     = ~(-1L << WORKER_BITS);
    private static final long TIMESTAMP_SHIFT = SEQUENCE_BITS + WORKER_BITS;
    private static final long WORKER_SHIFT    = SEQUENCE_BITS;

    private final SnowflakeIdGenerator generator;

    public SnowflakeResponse generate() {
        return parse(generator.nextId());
    }

    public List<SnowflakeResponse> generateBatch(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> parse(generator.nextId()))
                .toList();
    }

    // Decompose the 64-bit ID back into its parts — useful for debugging
    private SnowflakeResponse parse(long id) {
        long timestampMs = (id >> TIMESTAMP_SHIFT) + EPOCH;
        long workerId    = (id >> WORKER_SHIFT) & WORKER_MASK;
        long sequence    = id & SEQUENCE_MASK;
        String readable  = Instant.ofEpochMilli(timestampMs).toString();
        return new SnowflakeResponse(id, workerId, sequence, timestampMs, readable);
    }
}
