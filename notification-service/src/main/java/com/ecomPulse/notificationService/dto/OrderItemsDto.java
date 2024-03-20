package com.ecomPulse.notificationService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemsDto {
    private Long id;
    private String productId;
    private Integer quantity;

}
