package com.patterns.dp_springboot.strategy.service;

import com.patterns.dp_springboot.strategy.dto.DispatchRequest;
import com.patterns.dp_springboot.strategy.dto.DispatchResult;
import com.patterns.dp_springboot.strategy.registry.DataSenderRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispatchService {

    private final DataSenderRegistry registry;

    public DispatchResult dispatch(DispatchRequest request) {
        registry.resolve(request.channel()).send(request);
        return new DispatchResult(request.channel(), request.topic(), "OK", "Dispatched successfully");
    }
}
