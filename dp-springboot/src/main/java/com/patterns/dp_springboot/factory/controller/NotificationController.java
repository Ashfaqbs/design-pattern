package com.patterns.dp_springboot.factory.controller;

import com.patterns.dp_springboot.factory.dto.NotificationRequest;
import com.patterns.dp_springboot.factory.dto.NotificationResult;
import com.patterns.dp_springboot.factory.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResult> send(@Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.send(request));
    }
}
