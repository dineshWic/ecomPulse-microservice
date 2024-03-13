package com.ecomPulse.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderQuentity {
    private String productId;
    private boolean isAvailable;
    private Integer quantity;
}
