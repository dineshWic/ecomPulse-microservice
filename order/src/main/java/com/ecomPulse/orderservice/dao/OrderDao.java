package com.ecomPulse.orderservice.dao;

import com.ecomPulse.orderservice.dto.OrderResponse;
import com.ecomPulse.orderservice.model.Order;

public interface OrderDao {
    Order save(Order order);
}
