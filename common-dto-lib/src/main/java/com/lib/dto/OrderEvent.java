package com.lib.dto;

import java.util.List;

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
public class OrderEvent {
	
	private String eventType;
	
	private Long orderId;
	
	private Long customerId;
	
	private String email;
	
	private List<OrderItemResponse> items;
	
	private Double totalAmount;
	
	private String status;

}
