package com.patterns.dp_springboot.singleton.controller;

import com.patterns.dp_springboot.singleton.dto.SnowflakeResponse;
import com.patterns.dp_springboot.singleton.service.SnowflakeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ids")
@RequiredArgsConstructor
public class SnowflakeController {

    private final SnowflakeService snowflakeService;

    @GetMapping
    public ResponseEntity<SnowflakeResponse> generate() {
        return ResponseEntity.ok(snowflakeService.generate());
    }

    @GetMapping("/batch")
    public ResponseEntity<List<SnowflakeResponse>> batch(@RequestParam(defaultValue = "5") int count) {
        return ResponseEntity.ok(snowflakeService.generateBatch(count));
    }
}
