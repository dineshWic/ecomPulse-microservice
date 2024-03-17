package com.ecomPulse.orderservice.dao.impl;

import com.ecomPulse.orderservice.dao.OrderDao;
import com.ecomPulse.orderservice.model.Order;
import com.ecomPulse.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {

    private final OrderRepository orderRepository;


    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
