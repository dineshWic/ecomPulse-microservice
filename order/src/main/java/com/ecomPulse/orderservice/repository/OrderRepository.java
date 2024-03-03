package com.ecomPulse.orderservice.repository;

import com.ecomPulse.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
