package com.address.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.address.dto.CustomerResponse;


@FeignClient(url="localhost:8081/api/customer", value="customer-service")
public interface CustomerClient {

	@GetMapping("/{customerId}")
	CustomerResponse getCustomerById(@PathVariable Long customerId);
}
