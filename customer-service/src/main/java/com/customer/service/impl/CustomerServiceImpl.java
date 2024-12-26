package com.customer.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.customer.dto.CustomerRequest;
import com.customer.dto.CustomerResponse;
import com.customer.entity.Customer;
import com.customer.repository.CustomerRepository;
import com.customer.service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{
	
	private CustomerRepository customerRepository;
	
	private ModelMapper modelMapper;

	@Override
	public CustomerResponse createCustomer(CustomerRequest customerRequest) {
		Customer customer=customerRepository.save(modelMapper.map(customerRequest, Customer.class));
		return modelMapper.map(customer, CustomerResponse.class);
	}

	@Override
	public CustomerResponse updateCustomer(Long customerId, CustomerRequest customerRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerResponse getCustomerById(Long customerId) {
		
		return modelMapper.map(customerRepository.findById(customerId), CustomerResponse.class);
	}

	@Override
	public List<CustomerResponse> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteCustomer(Long customerId) {
		// TODO Auto-generated method stub
		
	}

}
