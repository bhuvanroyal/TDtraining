package com.customer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.dto.AddressResponse;


@FeignClient(name="ADDRESS-SERVICE")
public interface AddressClient {
	
	@GetMapping("/api/address/customer/{customerId}")
	List<AddressResponse> getAddressByCustomerId(@PathVariable Long customerId);
}
