package com.order.service;

import java.util.List;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;

public interface OrderService {
	
	public String placeOrder(OrderRequest orderRequest);
	
	public List<OrderResponse> getAllOrders();

}
