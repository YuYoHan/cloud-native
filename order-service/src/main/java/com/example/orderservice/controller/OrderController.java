package com.example.orderservice.controller;

import com.example.orderservice.domain.Order;
import com.example.orderservice.service.OrderService;
import com.example.orderservice.service.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public Flux<Order> getOrders() {
        return orderService.findAll();
    }

    @PostMapping
    public Mono<Order> submitOrder(@RequestBody OrderRequest order) {
        return orderService.submitOrder(order.isbn(), order.quantity());
    }
}
