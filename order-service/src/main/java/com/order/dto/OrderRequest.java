package com.order.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OrderRequest {
	
	@NotNull (message="customerId cannot be null")
	private Long customerId;
	@NotNull (message="orderItms cannot be null")
	private List<OrderItemRequest> items;
	@NotNull (message="addressId cannot be null")
	private Long addressId;

}
