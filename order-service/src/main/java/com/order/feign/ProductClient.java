package com.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.dto.ProductResponse;

@FeignClient(name="PRODUCT-SERVICE")
public interface ProductClient {

	@GetMapping("/api/products/{productId}")
    ProductResponse getProductById(@PathVariable Long productId);
}
