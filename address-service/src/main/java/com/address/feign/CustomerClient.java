package com.address.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.address.dto.CustomerResponse;


@FeignClient(name="CUSTOMER-SERVICE")
public interface CustomerClient {

	@GetMapping("/api/customer/{customerId}")
	CustomerResponse getCustomerById(@PathVariable Long customerId);
}
