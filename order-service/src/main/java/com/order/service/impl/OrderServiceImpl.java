package com.order.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.order.dto.AvailabilityResponse;
import com.order.dto.OrderRequest;
import com.order.dto.OrderResponse;
import com.order.dto.ProductResponse;
import com.order.entity.Order;
import com.order.entity.OrderItem;
import com.order.exception.CustomerNotFoundException;
import com.order.exception.ProductNotFoundException;
import com.order.feign.CustomerClient;
import com.order.feign.InventoryClient;
import com.order.feign.ProductClient;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;

import feign.FeignException;
import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private ModelMapper modelMapper;
	
//	private WebClient webClient;
	
	private ProductClient productClient;
	
	private OrderRepository orderRepository;
	
	private InventoryClient inventoryClient;
	
	private CustomerClient customerClient;

	@Override
	public OrderResponse placeOrder(OrderRequest orderRequest) {
		
		 try {
		        customerClient.getCustomerById(orderRequest.getCustomerId());
		    } catch (FeignException.NotFound ex) {
		        throw new CustomerNotFoundException("Customer with ID " + orderRequest.getCustomerId() + " not found");
		    }
		Order order=new Order();
		order.setCustomerId(orderRequest.getCustomerId());
		order.setOrderDate(LocalDateTime.now());
		order.setStatus("Placed");
		
		List<OrderItem> orderItems=orderRequest.getItems().stream().map(item -> {
			ProductResponse product=fetchProductDetails(item.getProductId());
			AvailabilityResponse response=checkInventory(item.getProductId(),item.getQuantity(),item.getVendorId());
			if(!response.isAvailable()) {
				throw new RuntimeException("quantity not available");
			}
			double price=response.getPrice();
			return createOrderItem(product,item.getQuantity(),item.getVendorId(),price);
			
		}).toList();
		
		double totalAmount=orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
		order.setTotalAmount(totalAmount);
		order.setOrderItems(orderItems);
		orderRepository.save(order);
		
		return modelMapper.map(order, OrderResponse.class);
	}


	private ProductResponse fetchProductDetails(Long productId) {
		try {
			return productClient.getProductById(productId);
		}catch(FeignException.NotFound ex) {
			throw new ProductNotFoundException("Product with Id " +productId +" Not found");
		}
	}
	
	private AvailabilityResponse checkInventory(Long productId, Integer quantity, Long vendorId) {
		return inventoryClient.checkAvailability(productId, vendorId, quantity);
	}
	
	private OrderItem createOrderItem(ProductResponse product, Integer quantity, Long vendorId, Double price) {
		OrderItem orderItem=new OrderItem();
		orderItem.setProductId(product.getProductId());
		orderItem.setVendorId(vendorId);
		orderItem.setPrice(price);
		orderItem.setQuantity(quantity);
		orderItem.setTotal(price*quantity);
		return orderItem;
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
