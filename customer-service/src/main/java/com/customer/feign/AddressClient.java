package com.customer.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.customer.dto.AddressResponse;


@FeignClient(url="localhost:8082/api/address", value="address-service")
public interface AddressClient {
	
	@GetMapping("/customer/{customerId}")
	List<AddressResponse> getAddressByCustomerId(@PathVariable Long customerId);
}
