package com.ecomPulse.orderservice.dto;

import com.ecomPulse.orderservice.enums.OrderStatus;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
    private Long id;
    private Long customerId;

    @Enumerated
    private OrderStatus orderStatus;
    private List<OrderItemsDto> orderItemsDtoList;
}
