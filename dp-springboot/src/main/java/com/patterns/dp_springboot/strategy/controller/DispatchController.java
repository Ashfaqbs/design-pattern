package com.patterns.dp_springboot.strategy.controller;

import com.patterns.dp_springboot.strategy.dto.DispatchRequest;
import com.patterns.dp_springboot.strategy.dto.DispatchResult;
import com.patterns.dp_springboot.strategy.registry.DataSenderRegistry;
import com.patterns.dp_springboot.strategy.service.DispatchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dispatch")
@RequiredArgsConstructor
public class DispatchController {

    private final DispatchService dispatchService;
    private final DataSenderRegistry registry;

    @PostMapping
    public ResponseEntity<DispatchResult> dispatch(@Valid @RequestBody DispatchRequest request) {
        return ResponseEntity.ok(dispatchService.dispatch(request));
    }

    @GetMapping("/channels")
    public ResponseEntity<List<String>> channels() {
        return ResponseEntity.ok(registry.availableChannels());
    }
}
