package com.ecomPulse.orderservice.model;


import com.ecomPulse.orderservice.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;

    @Enumerated
    private OrderStatus orderStatus;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItems> orderItemsList;
}
