package com.order.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.dto.OrderItemDto;
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
	
	
	private WebClient webClient;
	
	private OrderRepository orderRepository;

	@Override
	public String placeOrder(OrderRequest orderRequest) {
		Order order=new Order();
		order.setCustomerId(orderRequest.getCustomerId());
		order.setOrderDate(new Date());
		order.setStatus("Placed");
		List<OrderItemDto> items=orderRequest.getItems();
		List<OrderItem> orderItems=new ArrayList<>();
		order.setOrderItems(orderItems);
		for(OrderItemDto item:items) {
			ProductDto product=webClient.get().uri("http://localhost:8082/api/product/" +item.getProductId())
			.retrieve().bodyToMono(ProductDto.class).block();
			item.getProductId();
			System.out.println(product);
			OrderItem orderItem=new OrderItem();
			orderItem.setProductId(item.getProductId());
			orderItem.setPrice(product.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setTotal(product.getPrice()*item.getQuantity());
			order.getOrderItems().add(orderItem);
		}
		
		orderRepository.save(order);

		return "Success";
	}

	@Override
	public List<OrderResponse> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

}
