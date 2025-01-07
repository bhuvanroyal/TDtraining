package com.order.entity;


import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="order_table")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long orderId;
	
	private LocalDateTime orderDate;
	
	private double totalAmount;
	
	private String status;
	
	private Long customerId;
	
	private Long addressId;
	
	private String paymentStatus;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "order_id")
	private List<OrderItem> orderItems;

}
