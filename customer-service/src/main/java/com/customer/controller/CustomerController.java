package com.customer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.service.CustomerService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

	
	private CustomerService customerService;
	
	private WebClient.Builder webClientBuilder;
	
	
	@PostMapping("/")
	public CustomerResponse createCustomer(@RequestBody CustomerRequest customerRequest) {
		return customerService.createCustomer(customerRequest);
	}
	
	@GetMapping("/")
	public CustomerRequest customer() {
		return new CustomerRequest();
	}
	
	@GetMapping("/consume")
    public String consumeService() {
		
		WebClient webClient=webClientBuilder.build();
        return webClient
                .get()
                .uri("lb://ORDER-SERVICE/api/order/test") 
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
