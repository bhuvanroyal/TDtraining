package com.order.dto;

import java.time.LocalDateTime;
import java.util.List;

//import com.order.entity.OrderItem;
import com.lib.dto.OrderItemResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {

	private Long orderId;
	
	private LocalDateTime orderDate;
	
	private double totalAmount;
	
	private String status;
	
	private Long customerId;
	
	private Long addressId;
	
	private List<OrderItemResponse> orderItems;
}
