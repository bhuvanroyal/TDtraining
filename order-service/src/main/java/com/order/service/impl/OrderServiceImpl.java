package com.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.dto.ProductDto;
import com.order.entity.Order;
import com.order.entity.OrderItem;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private ModelMapper modelMapper;
	
	private WebClient webClient;
	
	private OrderRepository orderRepository;

	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		Order order=new Order();
		order.setCustomerId(orderRequest.getCustomerId());
		order.setOrderDate(LocalDateTime.now());
		order.setStatus("Placed");
		
		List<OrderItem> orderItems=orderRequest.getItems().stream().map(item -> {
			ProductDto product=fetchProductDetails(item.getProductId());
			return createOrderItem(product,item.getQuantity());
			
		}).toList();
		
		
		
		double totalAmount=orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
		order.setTotalAmount(totalAmount);
		order.setOrderItems(orderItems);
		orderRepository.save(order);
		
		return modelMapper.map(order, OrderResponse.class);
	}

	private OrderItem createOrderItem(ProductDto product, Integer quantity) {
		OrderItem orderItem=new OrderItem();
		orderItem.setProductId(product.getProductId());
		orderItem.setPrice(product.getPrice());
		orderItem.setQuantity(quantity);
		orderItem.setTotal(product.getPrice()*quantity);
		return orderItem;
	}

	private ProductDto fetchProductDetails(Long productId) {
		return webClient.get().uri("http://localhost:8082/api/product/" +productId)
				.retrieve().bodyToMono(ProductDto.class).block();
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		return orderRepository.findAll().stream().map(ord->modelMapper.map(ord, OrderResponse.class)).toList();
	}

	@Override
	public List<OrderResponse> getOrdersByCustomerId(Long customerId) {
		return orderRepository.findByCustomerId(customerId).stream().map(ord->modelMapper.map(ord,OrderResponse.class)).toList();
	}

	@Override
	public List<OrderResponse> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
		return orderRepository.findByOrderDateBetween(startDate.atStartOfDay(), endDate.atTime(LocalTime.MAX))
				.stream().map(ord->modelMapper.map(ord,OrderResponse.class)).toList();
	}

	@Override
	public List<OrderResponse> getOrdersByCustomerIdAndDateRange(Long customerId, LocalDate startDate,
			LocalDate endDate) {
		return orderRepository.findByCustomerIdAndOrderDateBetween(customerId,startDate.atStartOfDay(),
				endDate.atTime(LocalTime.MAX)).stream().map(ord->modelMapper.map(ord,OrderResponse.class)).toList();
	}

}
