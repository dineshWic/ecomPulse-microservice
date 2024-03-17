package com.ecomPulse.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderQuantity {
    private String productId;
    private boolean isAvailable;
    private Integer quantity;
}
