package com.order.service;

import java.time.LocalDate;
import java.util.List;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;

public interface OrderService {
	
	public OrderResponse placeOrder(OrderRequest orderRequest);
	
	public List<OrderResponse> getAllOrders();
	
	public List<OrderResponse> getOrdersByCustomerId(Long customerId);
	
	public List<OrderResponse> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate);
	
	public List<OrderResponse> getOrdersByCustomerIdAndDateRange(Long customerId,LocalDate startDate, LocalDate endDate);

}
