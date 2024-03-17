package com.ecomPulse.orderservice.controller;

import com.ecomPulse.orderservice.dto.OrderResponse;
import com.ecomPulse.orderservice.service.OrderService;
import com.ecomPulse.orderservice.dto.OrderRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "product", fallbackMethod = "placeOrderFallback")
    @TimeLimiter(name = "product")
    @Retry(name = "product")
    public CompletableFuture<OrderResponse> placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Request Came");
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));

    }
    public CompletableFuture<OrderResponse> placeOrderFallback(@RequestBody OrderRequest orderRequest, RuntimeException runtimeException) {
        log.info("Circuit Breaker fallback method called");
        log.info("Oops! something went wrong, please try again after sometime");
        return CompletableFuture.supplyAsync(() ->null);

    }

}
