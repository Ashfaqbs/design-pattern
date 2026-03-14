package com.patterns.dp_springboot.strategy.senders;

import com.patterns.dp_springboot.strategy.dto.DispatchRequest;

public interface DataSenderStrategy {

    /** Key that this strategy handles — must match the "channel" field in the request. */
    String channel();

    void send(DispatchRequest request);
}
