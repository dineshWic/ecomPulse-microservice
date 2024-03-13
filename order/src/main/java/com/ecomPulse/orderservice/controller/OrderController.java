package com.ecomPulse.orderservice.controller;

import com.ecomPulse.orderservice.dto.OrderResponse;
import com.ecomPulse.orderservice.service.OrderService;
import com.ecomPulse.orderservice.dto.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Request Came");
        return orderService.placeOrder(orderRequest);

    }
}
