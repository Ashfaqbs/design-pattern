package com.patterns.dp_springboot.strategy.registry;

import com.patterns.dp_springboot.strategy.exception.UnsupportedChannelException;
import com.patterns.dp_springboot.strategy.senders.DataSenderStrategy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DataSenderRegistry {

    private final Map<String, DataSenderStrategy> senders;

    // Spring injects every DataSenderStrategy bean — no if-else, no manual wiring
    public DataSenderRegistry(List<DataSenderStrategy> strategies) {
        senders = strategies.stream()
                .collect(Collectors.toMap(DataSenderStrategy::channel, s -> s));
    }

    public DataSenderStrategy resolve(String channel) {
        DataSenderStrategy sender = senders.get(channel);
        if (sender == null) {
            throw new UnsupportedChannelException(channel);
        }
        return sender;
    }

    public List<String> availableChannels() {
        return List.copyOf(senders.keySet());
    }
}
