package com.order.dto;

import jakarta.validation.constraints.Min;
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
public class OrderItemRequest {
	@NotNull(message="productId cannot be null")
	private Long productId;
	
	@NotNull(message="productId cannot be null")
	private Long vendorId;
	
	@Min(value = 1, message = "Quantity must be at least 1")
	private Integer quantity;

}
