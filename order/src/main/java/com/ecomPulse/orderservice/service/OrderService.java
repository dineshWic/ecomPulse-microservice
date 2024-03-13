package com.ecomPulse.orderservice.service;

import com.ecomPulse.orderservice.dto.OrderRequest;
import com.ecomPulse.orderservice.dto.OrderResponse;

public interface OrderService {
    OrderResponse placeOrder(OrderRequest orderRequest);
}
