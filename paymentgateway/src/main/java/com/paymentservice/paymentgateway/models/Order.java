/*
package com.paymentservice.paymentgateway.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Order{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long orderId;

    private LocalDateTime orderDate;

    private double totalAmount;

    private String status;

    private Long customerId;

    @OneToMany(cascade=CascadeType.ALL)
    private List<OrderItem> orderItems;
}
*/
