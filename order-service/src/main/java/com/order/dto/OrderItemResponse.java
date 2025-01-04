package com.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
	
	private Long orderItemId;
	
	private Long productId;
	
	private Long vendorId;
	
	private Integer quantity;
	
	private double price;
}
