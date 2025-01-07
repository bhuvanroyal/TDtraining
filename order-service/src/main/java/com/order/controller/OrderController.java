package com.order.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.service.OrderService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
	
	private OrderService orderService;
	
	
	@PostMapping("/")
	public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest order) {
		return new ResponseEntity<>(orderService.placeOrder(order),HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<OrderResponse>> getAllOrders() {
		return new ResponseEntity<>(orderService.getAllOrders(),HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<List<OrderResponse>> getOrdersByCustomerId(@PathVariable Long customerId) {
		return new ResponseEntity<>(orderService.getOrdersByCustomerId(customerId),HttpStatus.OK);
	}
	
	@GetMapping("/{startDate}/{endDate}")
	public ResponseEntity<List<OrderResponse>> getOrdersInBetween(@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
		return new ResponseEntity<>(orderService.getOrdersBetweenDates(startDate, endDate),HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}/{startDate}/{endDate}")
	public ResponseEntity<List<OrderResponse>> getOrdersByCustomerAndBetween(@PathVariable Long customerId,
			@PathVariable LocalDate startDate,@PathVariable LocalDate endDate){
		return new ResponseEntity<>(orderService.getOrdersByCustomerIdAndDateRange(customerId, startDate, endDate),HttpStatus.OK);
	}

	@PutMapping("/{orderId}/{status}")
	public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long orderId, @PathVariable String status) {
		return new  ResponseEntity<>(orderService.updateOrderStatus(orderId, status),HttpStatus.OK);
	}
	
	@PutMapping("/payment/{orderId}/{paymentStatus}")
	public ResponseEntity<OrderResponse> updateOrderPaymentStatus(@PathVariable Long orderId, @PathVariable String paymentStatus) {
		return new  ResponseEntity<>(orderService.updateOrderPaymentStatus(orderId, paymentStatus),HttpStatus.OK);
	}
	
}
