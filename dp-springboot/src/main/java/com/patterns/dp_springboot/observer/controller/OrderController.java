package com.patterns.dp_springboot.observer.controller;

import com.patterns.dp_springboot.observer.dto.OrderRequest;
import com.patterns.dp_springboot.observer.dto.OrderResponse;
import com.patterns.dp_springboot.observer.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> place(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.place(request));
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> delete(
            @PathVariable String orderId,
            @RequestHeader("X-Deleted-By") String deletedBy) {
        orderService.delete(orderId, deletedBy);
        return ResponseEntity.noContent().build();
    }
}
