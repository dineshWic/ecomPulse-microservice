package com.ecomPulse.orderservice.converter;

import com.ecomPulse.orderservice.dto.OrderItemsDto;
import com.ecomPulse.orderservice.dto.OrderQuantity;
import com.ecomPulse.orderservice.dto.OrderRequest;
import com.ecomPulse.orderservice.dto.OrderResponse;
import com.ecomPulse.orderservice.enums.OrderStatus;
import com.ecomPulse.orderservice.model.Order;
import com.ecomPulse.orderservice.model.OrderItems;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderConverter {

    public Order orderRequestToOrderConverter(OrderRequest request){
        List<OrderItems> orderItems = this.orderItemsDtoListToOrderItemsListConverter(request.getOrderItemsDtoList());
        return Order.builder()
                .customerId(request.getCustomerId())
                .orderStatus(OrderStatus.CREATED)
                .orderItemsList(orderItems)
                .build();
    }

//    public OrderQuantity orderToOrderQuentityConverter(Order request){
//        List<OrderItems> orderItems = this.orderItemsDtoListToOrderItemsListConverter(request.getOrderItemsDtoList());
//        return OrderQuantity.builder()
//                .productId(orderItems.get)
//                .build();
//    }

    public OrderItems orderItemsDtoToOrderItemsConverter(OrderItemsDto request){
        return OrderItems.builder()
                .quantity(request.getQuantity())
                .productId(request.getProductId())
                .build();
    }

    public OrderItemsDto orderItemsToOrderItemsDtoConverter(OrderItems request){
        return OrderItemsDto.builder()
                .id(request.getId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .build();
    }

    public List<OrderItems> orderItemsDtoListToOrderItemsListConverter(List<OrderItemsDto> request){
        return request.stream().map(this::orderItemsDtoToOrderItemsConverter).toList();
    }

    public List<OrderItemsDto> orderItemsListToOrderItemsDtoListConverter(List<OrderItems> request){
        return request.stream().map(this::orderItemsToOrderItemsDtoConverter).toList();
    }

    public OrderResponse orderToOrderResponseConverter(Order request) {
        List<OrderItemsDto> orderItemsDtos = this.orderItemsListToOrderItemsDtoListConverter(request.getOrderItemsList());
        return OrderResponse.builder()
                .id(request.getId())
                .customerId(request.getCustomerId())
                .orderStatus(request.getOrderStatus())
                .orderItemsDtoList(orderItemsDtos)
                .build();
    }
}
