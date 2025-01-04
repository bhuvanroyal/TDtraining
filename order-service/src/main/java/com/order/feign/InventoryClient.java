package com.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.order.dto.AvailabilityResponse;


@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryClient {

	 @GetMapping("/api/inventory/availability")
	 AvailabilityResponse checkAvailability(@RequestParam Long productId, @RequestParam Long vendorId, @RequestParam Integer quantity);
}
