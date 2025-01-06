package com.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.order.dto.AddressResponse;


@FeignClient(name="ADDRESS-SERVICE")
public interface AddressClient {

	@GetMapping("/api/address/{addressId}")
	AddressResponse getAddressById(@PathVariable Long addressId);
}
