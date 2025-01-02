package com.customer.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.dto.CustomerAddressResponse;
import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

	
	private CustomerService customerService;
	
	
	@PostMapping("/")
	public ResponseEntity<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
		return new ResponseEntity<>(customerService.createCustomer(customerRequest),HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers(){
		return new ResponseEntity<>(customerService.getAllCustomers(),HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long customerId){
		return new ResponseEntity<>(customerService.getCustomerById(customerId),HttpStatus.OK);
	}
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerResponse> updateCustomerDetails(@Valid @RequestBody CustomerRequest customerRequest,@PathVariable Long customerId){
		return new ResponseEntity<>(customerService.updateCustomer(customerId,customerRequest),HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId){
		customerService.deleteCustomer(customerId);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/address/{customerId}")
	public ResponseEntity<CustomerAddressResponse> getAddressAndCustomerByCustomer(@PathVariable Long customerId){
		return new ResponseEntity<>(customerService.getAddressAndCustomerByCustomer(customerId),HttpStatus.OK);
	}
}
