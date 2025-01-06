package com.lib.dto;

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
public class OrderItemResponse {
	
	private Long orderItemId;
	
	private Long productId;
	
	private Long vendorId;
	
	private Integer quantity;
	
	private double price;
}
