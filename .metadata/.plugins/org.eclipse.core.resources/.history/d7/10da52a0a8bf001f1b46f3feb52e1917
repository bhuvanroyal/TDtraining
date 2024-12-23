package com.order.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.OrderItemDto;
import com.order.dto.OrderRequest;
import com.order.service.impl.OrderServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private OrderServiceImpl orderService;
	
	
	@PostMapping("/")
	public String placeOrder(@RequestBody OrderRequest order) {
		return orderService.placeOrder(order);
	}
	
	@GetMapping("/")
	public OrderRequest getOrder() {
		OrderRequest o=new OrderRequest();
		o.setItems(new ArrayList<OrderItemDto>());
		o.getItems().add(new OrderItemDto());
		o.getItems().add(new OrderItemDto());
		return o;
	}
	

}
